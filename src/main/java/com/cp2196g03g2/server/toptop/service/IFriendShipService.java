package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.dto.FriendShipDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.FriendShip;

public interface IFriendShipService {
	FriendShip save(FriendShipDto dto);
	List<ApplicationUser> getListFriendByUserId(String userId);
	List<ApplicationUser> findAllFriendByName(String userId,String name);
	boolean isYouFollowUser(String requestId, String acceptId);
}
