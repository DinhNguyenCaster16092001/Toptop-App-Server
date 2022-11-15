package com.cp2196g03g2.server.toptop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.FriendShipDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.FriendShip;
import com.cp2196g03g2.server.toptop.entity.Notification;
import com.cp2196g03g2.server.toptop.enums.NotificationType;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IFriendShipRepository;
import com.cp2196g03g2.server.toptop.repository.INotifacationRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IFriendShipService;
import com.cp2196g03g2.server.toptop.service.INotificationService;

@Service
public class FriendShipServiceImpl implements IFriendShipService {

	@Autowired
	private IFriendShipRepository friendShipRepository;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private INotificationService notificationService;

	@Override
	@Transactional
	public FriendShip save(FriendShipDto dto) {
		// find user who follower
		ApplicationUser requestUser = userRepository.findById(dto.getRequestId()).get();

		// find user who followed
		ApplicationUser acceptUser = userRepository.findById(dto.getAccetpId()).get();

		// create friend ship
		FriendShip friendShip = new FriendShip(requestUser, acceptUser, false);

		Notification notification = 
		new Notification(acceptUser, requestUser, null, null, false, false, 4);

		
		
		FriendShip savedFriendShip = friendShipRepository.save(friendShip);
		if (savedFriendShip == null) {
			throw new InternalServerException("Cannot saved the friendship");
		}
		// create notifcation and push to user
		notificationService.createNotification(notification);
		return savedFriendShip;
	}

	@Override
	@Transactional
	public List<ApplicationUser> getListFriendByUserId(String userId) {
		return getIdsFriendByUserId(userId);
	}

	private List<ApplicationUser> getIdsFriendByUserId(String userId) {
		List<String> idsFriend = friendShipRepository.findAllListFriend(userId);
		return idsFriend.size() > 0 ? userRepository.findAllById(idsFriend) : new ArrayList<>();
	}

	@Override
	public List<ApplicationUser> findAllFriendByName(String userId, String name) {
		List<ApplicationUser> friends = getIdsFriendByUserId(userId);
		return friends.stream().filter(f -> f.getFullName().contains(name)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public boolean isYouFollowUser(String requestId, String acceptId) {
		if (requestId.isEmpty() || requestId == null)
			return false;

		FriendShip friendShip = friendShipRepository.findByRequestIdAndAcceptId(requestId, acceptId);
		if (friendShip == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void deleteByRequestId(String requestId, String acceptId) {
		try {
			friendShipRepository.deleteByRequestId(requestId, acceptId);
		}catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
		
	}

}
