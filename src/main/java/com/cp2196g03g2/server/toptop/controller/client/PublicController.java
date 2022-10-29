package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.cp2196g03g2.server.toptop.dto.Message;

@Controller
public class PublicController {
	 
	 	@Autowired
		private SimpMessagingTemplate simpMessagingTemplate;
		
		@MessageMapping("/message")
		@SendTo("/chatroom/public")
		public Message receviePublicMessage(@Payload Message message) {
			return message;
		}
		
		@MessageMapping("/private-message")
		public Message receviePrivateMessage(@Payload Message message) {
			simpMessagingTemplate.convertAndSendToUser(message.getReviceName(), "/private", message);
			return message;
		}
}
