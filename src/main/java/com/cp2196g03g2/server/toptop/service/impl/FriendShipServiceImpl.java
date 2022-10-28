package com.cp2196g03g2.server.toptop.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.FriendShipDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.FriendShip;
import com.cp2196g03g2.server.toptop.repository.IFriendShipRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IFriendShipService;

@Service
public class FriendShipServiceImpl implements IFriendShipService{

	@Autowired
	private IFriendShipRepository friendShipRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	@Transactional
	public FriendShip save(FriendShipDto dto) {
		ApplicationUser requestUser = userRepository.findById(dto.getRequestId()).get();
		ApplicationUser acceptUser =  userRepository.findById(dto.getAccetpId()).get();
		FriendShip friendShip = new FriendShip(requestUser, acceptUser, false);
		return friendShipRepository.save(friendShip);
	}

}
