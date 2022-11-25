package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Notification;

public interface INotifacationRepository extends JpaRepository<Notification, Integer>{

	@Query("SELECT n FROM Notification n WHERE n.userTo.id=:userId AND n.delivered = false")
	List<Notification> findByUserToAndDeliveredFalse(String userId);


	@Query(value = "SELECT * FROM tbl_notification " + 
			"where from_id =:fromId " + 
			"and to_id =:toId" + 
			"and from_video_id is null " + 
			"and from_comment_id is null " + 
			"and type = 4 ", nativeQuery = true)
	Notification getFollowNotificationExist(@Param("fromId") String fromId, @Param("toId") String toId);
	
	
	@Query(value = "SELECT * FROM tbl_notification " + 
			"where from_id =:fromId " + 
			"and to_id =:toId " + 
			"and from_video_id =:videoId " + 
			"and from_comment_id is null " + 
			"and type = 1 ", nativeQuery = true)
	Notification getHeartVideoNotificationExist(@Param("fromId") String fromId, @Param("toId") String toId, @Param("videoId") Long videoId);
	

	@Query(value="SELECT * FROM tbl_notification WHERE to_id =:userId " + 
			"ORDER BY created_date desc", nativeQuery = true)
	Page<Notification> findAllNotificationByToUserId(@Param("userId") String userId, Pageable pageable);


	@Query("SELECT n FROM Notification n WHERE n.userTo.id =:userId AND n.readed = 0 ORDER BY n.createdDate DESC")
	Page<Notification> findAllNotificationByToUserIdAndNotReaded(@Param("userId") String userId, Pageable pageable);
}
