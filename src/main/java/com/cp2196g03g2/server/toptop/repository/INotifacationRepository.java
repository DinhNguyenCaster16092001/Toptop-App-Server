package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cp2196g03g2.server.toptop.entity.Notification;

public interface INotifacationRepository extends JpaRepository<Notification, Integer>{

	@Query("SELECT n FROM Notification n WHERE n.userTo.id=:userId AND n.delivered = false")
	List<Notification> findByUserToAndDeliveredFalse(String userId);
}
