package com.cp2196g03g2.server.toptop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Coupon;

public interface ICouponRepository extends JpaRepository<Coupon, Integer>{
	Coupon findByCode(String Code);
	
	@Query("SELECT c FROM Coupon c WHERE c.code LIKE %:keyword%")
	Page<Coupon> findAllByPage(@Param("keyword") String keyword, Pageable pageable);

	@Query("UPDATE Coupon c SET c.enable = :status WHERE c.id = :id")
	@Modifying
	void updateStatusCoupon(@Param("id")Integer id, @Param("status")boolean status);
}
