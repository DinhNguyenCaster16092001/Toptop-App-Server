package com.cp2196g03g2.server.toptop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.entity.Coupon;
import com.cp2196g03g2.server.toptop.service.ICouponService;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {
	
	@Autowired
	private ICouponService couponService;
	
	@GetMapping
	public List<Coupon> findAll(){
		return couponService.findAll();
	}
	
	
	@GetMapping("/{id}")
	public Coupon findById(Integer id) {
		return couponService.findById(id);
	}
	

	@GetMapping("/{code}")
	public Coupon findByCode(String code) {
		return couponService.findByCode(code);
	}
	
}
