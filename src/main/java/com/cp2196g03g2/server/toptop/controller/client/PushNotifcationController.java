package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.entity.Notification;
import com.cp2196g03g2.server.toptop.service.INotificationService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/notification")
@CrossOrigin
public class PushNotifcationController {

	@Autowired
	private INotificationService notificationService;
	
	@GetMapping(path = "/{userID}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<List<Notification>>> streamLastMessage(@PathVariable String userID) {
		return notificationService.getNotificationsByUserToID(userID);
	}
}
