package com.cp2196g03g2.server.toptop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.entity.Coupon;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.service.ICouponService;

@RestController
public class HomeController {
	
	@Autowired
	private ICouponService couponService;
	
	@GetMapping("/test")
	public String sayHello() {
		String code = "BJHXXOHZQV";
		return couponService.findByCode(code).getCode();
	}
}
