package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.entity.Coupon;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.ICouponRepository;
import com.cp2196g03g2.server.toptop.service.ICouponService;

@Service
public class CouponServiceImpl implements ICouponService{

	@Autowired
	private ICouponRepository couponRepository;
	
	@Override
	public Coupon findByCode(String code) {
		try {
			return couponRepository.findByCode(code);
		}catch (Exception e) {
			throw new NotFoundException("Coupon Not Exist");
		}
	}

	@Override
	public Coupon findById(Integer id) {
		return couponRepository.findById(id).orElseThrow(() -> 
		new NotFoundException("Cannot found coupon have id" + id));
	}

	@Override
	public List<Coupon> findAll() {
		return couponRepository.findAll();
	}

}
