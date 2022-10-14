package com.cp2196g03g2.server.toptop.repository.coupon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Coupon;
import com.cp2196g03g2.server.toptop.repository.ICouponRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CouponRepositoryTest {

	@Autowired
	private ICouponRepository couponRepository;

	@Autowired
	private IUserRepository userRepository;

	@Test
	public void createCouponTest() {
		try {
			Date dateActive = new SimpleDateFormat("dd/MM/yyyy").parse("30/08/2011");
			Date dateExpire = new SimpleDateFormat("dd/MM/yyyy").parse("10/9/2012");
			String code = "LIKUSDNNCWKL";
			Coupon coupon = new Coupon();
			coupon.setCode(code);
			coupon.setCreatedDate(dateActive);
			coupon.setExpiredAt(dateExpire);
			Date now = new Date();
			if (now.equals(dateActive)) {
				coupon.setEnable(true);
			} else {
				coupon.setEnable(false);
			}
			coupon.setQty(100);
			Coupon savedCoupon = couponRepository.save(coupon);
			assertThat(savedCoupon.getId()).isGreaterThan(0);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void userUsingCodeCoupon() {
		String uid = "088574c8-5cf6-4d33-aa7f-254d39208280";
		String code = "YNRWIACVNB";

		ApplicationUser user = userRepository.findById(uid).get();
		
		Coupon coupon = new Coupon();
		coupon = couponRepository.findByCode(code);
		if(user.getCoupons().contains(coupon)) {
			System.out.println("The Coupon has been used");
		}else {
			coupon.addUser(user);
		}
		

		Coupon savedCoupon = couponRepository.save(coupon);
		assertThat(savedCoupon.getUsers()).hasSizeGreaterThan(0);
	}
	
	@Test
	public void userHasCodeCoupon() {
		String uid = "088574c8-5cf6-4d33-aa7f-254d39208280";
		ApplicationUser user = userRepository.findById(uid).get();
		for (Coupon coupon : user.getCoupons()) {
			System.out.println(coupon.getCode());
		}
	}

}
