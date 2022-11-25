package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.constant.AppConstants;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
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
	
	@PutMapping("/{id}")
	public Notification updateNotificationToRead(@PathVariable Integer id) {
		return notificationService.updateNotification(id);
	}
	
	
	@GetMapping("/user/{userId}")
	public PagableObject<Notification> findAllNotification(@PathVariable String userId, 
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
			@RequestParam(value = "read", required = false) boolean read) {
			PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir);
			return notificationService.findAllNotificationByToUserId(userId, read, request);
	}
}
