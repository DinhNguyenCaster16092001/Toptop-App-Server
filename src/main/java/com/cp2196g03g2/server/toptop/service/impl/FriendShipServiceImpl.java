package com.cp2196g03g2.server.toptop.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.entity.FriendShip;
import com.cp2196g03g2.server.toptop.repository.IFriendShipRepository;
import com.cp2196g03g2.server.toptop.service.IFriendShipService;

@Service
public class FriendShipServiceImpl implements IFriendShipService{

	@Autowired
	private IFriendShipRepository friendShipRepository;
	
	@Override
	@Transactional
	public FriendShip save(FriendShip friendShip) {
		return friendShipRepository.save(friendShip);
	}

}
