package com.cp2196g03g2.server.toptop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.constant.AppConstants;
import com.cp2196g03g2.server.toptop.dto.CouponDTO;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.Coupon;
import com.cp2196g03g2.server.toptop.service.ICouponService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/management/coupon")
public class CouponController {
	
	@Autowired
	private ICouponService couponService;
	
	@GetMapping
	public PagableObject<Coupon> findAllByPage(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "keyword", defaultValue = AppConstants.DEFAULT_KEYWORD, required = false) String keyword
    ){
		PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir, keyword);
		return couponService.findAllByPage(request);
	}
	
	
	@GetMapping("/{id}")
	public Coupon findById(@PathVariable Integer id) {
		return couponService.findById(id);
	}
	

	@GetMapping("/code/{code}")
	public Coupon findByCode(@PathVariable String code) {
		return couponService.findByCode(code);
	}
	
	@PostMapping
	public Coupon save(@RequestBody Coupon coupon) {
		return couponService.save(coupon);
	}
	
	@PutMapping("/{id}")
	public Coupon save(@RequestBody CouponDTO couponDTO) {
		return couponService.update(couponDTO);
	}
	
	@PutMapping("/{id}/active/{status}")
	public void updateUser(@PathVariable(name = "id") Integer id, @PathVariable(name = "status") boolean status) {
		couponService.updateStatusCoupon(id, status);
	}
	
}
