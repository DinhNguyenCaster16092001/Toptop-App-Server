package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.cp2196g03g2.server.toptop.dto.MessageDto;
import com.cp2196g03g2.server.toptop.entity.Message;
import com.cp2196g03g2.server.toptop.service.IFriendShipService;
import com.cp2196g03g2.server.toptop.service.IMessageService;

@Controller
public class PublicController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private IMessageService messageService;

	@Autowired
	private IFriendShipService friendShipService;

	@MessageMapping("/message")
	@SendTo("/chatroom/public") 
	public com.cp2196g03g2.server.toptop.dto.Message receviePublicMessage(@Payload com.cp2196g03g2.server.toptop.dto.Message message) {
		return message; 
	}

	/*
	 * @MessageMapping("/private-message") public Message
	 * receviePrivateMessage(@Payload MessageDto dto) { Message message =
	 * messageService.save(dto);
	 * simpMessagingTemplate.convertAndSendToUser(dto.getReccive_id(), "/private",
	 * message); return message; }
	 */
	
	
	@MessageMapping("/private-message")
	public com.cp2196g03g2.server.toptop.dto.Message receviePrivateMessage(@Payload com.cp2196g03g2.server.toptop.dto.Message dto) {
		simpMessagingTemplate.convertAndSendToUser(dto.getReceiverName(), "/private", dto);
		return dto;
	}

	/*
	 * @MessageMapping("/friendship") public Message receviePrivateMessage(@Payload
	 * MessageDto dto) { Message message = messageService.save(dto);
	 * simpMessagingTemplate.convertAndSendToUser(dto.getReccive_id(), "/private",
	 * dto); return message; }
	 */
}
