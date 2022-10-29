package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Message;


public interface IMessageRepository extends JpaRepository<Message, Long>{
	
	// or you can use @Query to achieve the same
    @Query("select m from Message m where m.senderUser.id = :id or m.recciveUser.id = :id")
    List<Message> findMessagesForUser(@Param("id") String id);
}
