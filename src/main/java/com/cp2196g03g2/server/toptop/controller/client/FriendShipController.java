package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.FriendShipDto;
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
	
	/*
	 * @PutMapping public FriendShip updateStatusFriendShip(@RequestBody
	 * FriendShipDto dto) {
	 * 
	 * }
	 */
}
