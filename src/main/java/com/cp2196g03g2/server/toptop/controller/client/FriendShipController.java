package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.FriendShipDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.FriendShip;
import com.cp2196g03g2.server.toptop.service.IFriendShipService;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/friendship")
public class FriendShipController {

	@Autowired
	private IFriendShipService friendShipService;
	
	@PostMapping
	public FriendShip createFriendShip(@RequestBody FriendShipDto friendShipDto) {
		return friendShipService.save(friendShipDto);
	}
	
	@GetMapping("/{userId}")
	public List<ApplicationUser> findAllFriend(@PathVariable String userId){
		return friendShipService.getListFriendByUserId(userId);
	}
	
	
	@GetMapping("/tag/{userId}")
	public List<ApplicationUser> getListFrienByTag(@PathVariable String userId, 
			@RequestParam(name = "tagName", required = false) String tagName){
		return friendShipService.findAllFriendByName(userId, tagName);
	}
}
