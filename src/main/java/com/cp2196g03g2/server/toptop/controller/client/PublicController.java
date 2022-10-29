package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.cp2196g03g2.server.toptop.dto.MessageContent;
import com.cp2196g03g2.server.toptop.dto.ResponeMessage;

@Controller
public class PublicController {

	 @Autowired
	 SimpMessagingTemplate simpMessagingTemplate;
	 
	 	@MessageMapping("/chat")
	    @SendTo("/topic/messages")
		public ResponeMessage receviePublicMessage(@Payload MessageContent content) {
			return new ResponeMessage(content.getMessageContent());
		}
		
		/*
		 * @MessageMapping("/private-message") public ResponeMessage
		 * receviePrivateMessage(@Payload MessageContent content) {
		 * simpMessagingTemplate.convertAndSendToUser(, "/private", message); return
		 * message; }
		 */
}
