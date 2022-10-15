package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.dto.CouponDTO;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Coupon;

public interface ICouponService {
	Coupon save(Coupon coupon);
	Coupon update(CouponDTO couponDTO);
	Coupon findByCode(String code);
	Coupon findById(Integer id);
	List<Coupon> findAll();
	PagableObject<Coupon> findAllByPage(PagingRequest request);
	void updateStatusCoupon(Integer id, boolean status);
}
