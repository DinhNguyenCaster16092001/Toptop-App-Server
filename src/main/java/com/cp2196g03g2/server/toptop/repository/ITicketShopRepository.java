package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.TicketShop;

public interface ITicketShopRepository extends JpaRepository<TicketShop, Integer>{
	@Query("SELECT t FROM TicketShop t WHERE t.user.id = ?1")
	List<TicketShop> findByUserId(String id);
	
	; 
}
