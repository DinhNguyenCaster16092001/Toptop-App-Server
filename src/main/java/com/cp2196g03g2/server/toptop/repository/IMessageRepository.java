package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Message;


public interface IMessageRepository extends JpaRepository<Message, Long>{
	
	// or you can use @Query to achieve the same
    @Query("select m from Message m where m.senderUser.id = :id or m.recciveUser.id = :id")
    List<Message> findMessagesForUser(@Param("id") String id);
    
    
    /*@Query(value = "select * from tbl_message where (reccive_id =:userId " + 
    		"and sender_id=:friendId) " + 
    		"or (reccive_id =:friendId " + 
    		"and sender_id=:userId) ", nativeQuery = true)
    Page<Message> findAllMessageByUserIdAndFriendId(@Param("userId") String userId, @Param("friendId") String friendId, Pageable pageable);*/



    @Query("SELECT m FROM Message m WHERE (m.recciveUser.id =:userId " + 
    		"AND m.senderUser.id=:friendId) " +
    		"OR (m.recciveUser.id =:userId " +
    		"AND m.senderUser.id=:friendId) ")
    Page<Message> findAllMessageByUserIdAndFriendId(@Param("userId") String userId, @Param("friendId") String friendId, Pageable pageable);
}
