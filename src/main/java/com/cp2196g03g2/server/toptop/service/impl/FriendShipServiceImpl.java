package com.cp2196g03g2.server.toptop.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.FriendShipDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.FriendShip;
import com.cp2196g03g2.server.toptop.entity.Notification;
import com.cp2196g03g2.server.toptop.enums.NotificationType;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IFriendShipRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IFriendShipService;
import com.cp2196g03g2.server.toptop.service.INotificationService;

@Service
public class FriendShipServiceImpl implements IFriendShipService{

	@Autowired
	private IFriendShipRepository friendShipRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	
	@Autowired
	private INotificationService notificationService;
	
	@Override
	@Transactional
	public FriendShip save(FriendShipDto dto) {
		//find user who follower
		ApplicationUser requestUser = userRepository.findById(dto.getRequestId()).get();
		
		//find user who followed
		ApplicationUser acceptUser =  userRepository.findById(dto.getAccetpId()).get();
		
		//create friend ship
		FriendShip friendShip = new FriendShip(requestUser, acceptUser, false);
		
		Notification notification = new 
				Notification(acceptUser, requestUser, false, false, NotificationType.FOLLOW);
		
		
		FriendShip savedFriendShip = friendShipRepository.save(friendShip);
		if(savedFriendShip == null) {
			throw new InternalServerException("Cannot saved the friendship");
		}
		//create notifcation and push to user
		notificationService.createNotification(notification);
		return savedFriendShip;
	}


	
}
