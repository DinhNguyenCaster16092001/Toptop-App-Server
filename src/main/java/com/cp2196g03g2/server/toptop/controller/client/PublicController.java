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
	 SimpMessagingTemplate simpMessagingTemplate;
	 
	 	@MessageMapping("/chat")
	    @SendTo("/topic/messages")
	    public String send(String message) throws Exception {
	        return "test";
	    }
		
		/*
		 * @MessageMapping("/private-message") public Message
		 * receviePrivateMessage(@Payload Message message) {
		 * simpMessagingTemplate.convertAndSendToUser(message.getReviceName(),
		 * "/private", message); return message; }
		 */
}
