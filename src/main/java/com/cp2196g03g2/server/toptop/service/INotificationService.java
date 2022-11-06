package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import org.springframework.http.codec.ServerSentEvent;

import com.cp2196g03g2.server.toptop.entity.Notification;

import reactor.core.publisher.Flux;

public interface INotificationService {

	Notification createNotification(Notification notification);
	List<Notification> getNotificationByUserIsNotRead(String userId);
	Notification changeNotifStatusToRead(Integer id);
	Flux<ServerSentEvent<List<Notification>>> getNotificationsByUserToID(String userId);
}
