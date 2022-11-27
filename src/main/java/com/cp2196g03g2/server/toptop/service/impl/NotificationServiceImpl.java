package com.cp2196g03g2.server.toptop.service.impl;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Notification;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.INotifacationRepository;
import com.cp2196g03g2.server.toptop.service.INotificationService;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private INotifacationRepository notifacationRepository;

	@Override
	@Transactional
	public Notification createNotification(Notification notification) {
		return notifacationRepository.save(notification);
	}

	@Override
	@Transactional
	public List<Notification> getNotificationByUserIsNotRead(String userId) {
		return notifacationRepository.findByUserToAndDeliveredFalse(userId);
	}

	@Override
	@Transactional
	public Notification changeNotifStatusToRead(Integer id) {
		Notification notification = notifacationRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot Found notification: " + id));
		notification.setReaded(true);
		return notifacationRepository.save(notification);
	}
	
	

	@Override
	public Flux<ServerSentEvent<List<Notification>>> getNotificationsByUserToID(String userId) {
		if (userId != null) {
            return Flux.interval(Duration.ofSeconds(1))
                    .publishOn(Schedulers.boundedElastic())
                    .map(sequence -> ServerSentEvent.<List<Notification>>builder().id(String.valueOf(sequence))
                            .event("user-list-event").data(getNotifs(userId))
                            .build());
        }
		return Flux.interval(Duration.ofSeconds(1)).map(sequence -> ServerSentEvent.<List<Notification>>builder()
                .id(String.valueOf(sequence)).event("user-list-event").data(new ArrayList<>()).build());
	}


	private List<Notification> getNotifs(String userID) {
		List<Notification> notifis = notifacationRepository.findByUserToAndDeliveredFalse(userID);
		notifis.forEach(x -> x.setDelivered(true));
		notifacationRepository.saveAll(notifis);
		setTimeAgoForNotification(notifis);
		return notifis;
	}

	@Override
	@Transactional
	public Notification getFollowNotifcation(Notification notification) {
		return notifacationRepository.getFollowNotificationExist(notification.getUserFrom().getId(), notification.getUserTo().getId());
	}

	@Override
	@Transactional
	public Notification getHeartNotifcation(Notification notification) {
		return notifacationRepository.getHeartVideoNotificationExist(
				notification.getUserFrom().getId(), 
				notification.getUserTo().getId(),
				notification.getFromVideo().getId());
	}

	@Override
	public void deleteNotification(Notification notification) {
		notifacationRepository.delete(notification);
	}

	@Override
	public PagableObject<Notification> findAllNotificationByToUserId(String userId, boolean readed, PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		// create Pageable instance
		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);
		
		Page<Notification> notifications = null;
		
		if(readed == false) 
			notifications = notifacationRepository.findAllNotificationByToUserIdAndNotReaded(userId, pageable);
		else
			notifications = notifacationRepository.findAllNotificationByToUserId(userId ,pageable);

		List<Notification> listOfNotifications = notifications.getContent();
		
		setTimeAgoForNotification(listOfNotifications);

		
		PagableObject<Notification> notificationPage = new PagableObject<>();
		notificationPage.setData(listOfNotifications);
		notificationPage.setPageNo(request.getPageNo());
		notificationPage.setPageSize(request.getPageSize());
		notificationPage.setTotalElements(notifications.getTotalElements());
		notificationPage.setTotalPages(notifications.getTotalPages());
		notificationPage.setLast(notifications.isLast());

		return notificationPage;
	}

	private void setTimeAgoForNotification(List<Notification> notifications) {
		
		 PrettyTime prettyTime = new PrettyTime(); notifications.forEach(n -> {
		  System.out.println(n.getCreatedDate());
		  Date convertedDatetime = Date.from(n.getCreatedDate().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
		  n.setPastTime(prettyTime.format(convertedDatetime)); });
		 
	}
	
	private void setTimeAgoForNotification(Notification notifications) {
		  PrettyTime prettyTime = new PrettyTime();
		  Date convertedDatetime = Date.from(notifications.getCreatedDate().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
		  notifications.setPastTime(prettyTime.format(convertedDatetime));
		 
	}

	@Override
	public Notification updateNotification(Integer id) {
		Notification notification = notifacationRepository.findById(id).get();
		notification.setReaded(true);
		setTimeAgoForNotification(notification);
		return notifacationRepository.save(notification);
	}
}
