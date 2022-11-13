package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.model.ChartCloumModel;

public interface ITicketShopRepository extends JpaRepository<TicketShop, Integer>{
	@Query("SELECT t FROM TicketShop t WHERE t.user.id = ?1")
	List<TicketShop> findByUserId(String id);
	
	@Query("SELECT t FROM TicketShop t WHERE (t.user.email LIKE %:keyword% OR "
											 +"t.user.fullName LIKE %:keyword%)")
	Page<TicketShop> findAllByPage(@Param("keyword") String keyword, Pageable pageable);


	@Query( value = "SELECT COALESCE(COUNT(total),0) FROM (SELECT userid as total FROM tbl_ticketshop WHERE " + 
			"STATUS =:status AND YEAR(created_date) = YEAR(CURRENT_DATE()) AND MONTH(created_date) = MONTH(CURRENT_DATE()) GROUP BY userid) as total", nativeQuery = true)
	Long countUserSendingTicketInCurrentMonthByStatus(@Param("status") Integer status);


	@Query( value = "SELECT COALESCE(COUNT(total),0) FROM (SELECT userid as total FROM tbl_ticketshop WHERE " + 
			"STATUS =:status AND YEAR(created_date) = YEAR(CURRENT_DATE()) GROUP BY userid) as total", nativeQuery = true)
	Long countUserSendingTicketInCurrentYearByStatus(@Param("status") Integer status);

	@Query(value = "SELECT m.id AS month, COALESCE(COUNT(i.id),0) AS total FROM tbl_month m LEFT JOIN tbl_ticketshop i " +
					"ON m.id = MONTH(i.created_date) AND i.status =:status AND YEAR(i.created_date) =:year GROUP BY m.id " + 
					"ORDER BY m.id ASC", nativeQuery = true)
	List<ChartCloumModel> ticketStatisticsBytTwelveMonthPassStatus(@Param("status") Integer status, @Param("year") Integer year);
}


