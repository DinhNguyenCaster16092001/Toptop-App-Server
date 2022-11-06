package com.cp2196g03g2.server.toptop.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

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
	@Transactional
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
		return notifis;
	}

}
