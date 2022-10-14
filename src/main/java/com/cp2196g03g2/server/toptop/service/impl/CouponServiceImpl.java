package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
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

	@Override
	public PagableObject<Coupon> findAllByPage(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(request.getSortBy()).ascending()
                : Sort.by(request.getSortBy()).descending();
		
		// create Pageable instance
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);
        
        Page<Coupon> coupons = couponRepository.findAllByPage(request.getKeyword(), pageable);
        
        List<Coupon> listOfCoupons = coupons.getContent();
        
        PagableObject<Coupon> couponsPage = new PagableObject<>();
        couponsPage.setData(listOfCoupons);
        couponsPage.setPageNo(request.getPageNo());
        couponsPage.setPageSize(request.getPageSize());
        couponsPage.setTotalElements(coupons.getTotalElements());
        couponsPage.setTotalPages(coupons.getTotalPages());
        couponsPage.setLast(coupons.isLast());
        
        return couponsPage;
	}

}
