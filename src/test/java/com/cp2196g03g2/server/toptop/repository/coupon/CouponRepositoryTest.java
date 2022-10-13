package com.cp2196g03g2.server.toptop.repository.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.Coupon;
import com.cp2196g03g2.server.toptop.repository.ICouponRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CouponRepositoryTest {
	
	@Autowired
	private ICouponRepository couponRepository;
	
	@Test
	public void createCouponTest() {
		Date currentDate = new Date(); // ngày hiện tại giả sử hôm nay 10/10
		Date dayAfter =  
		new Date(currentDate.getTime() + TimeUnit.DAYS.toMillis(1)); // ngày hết hạn sẽ  + 1 lên  
		Coupon coupon = new Coupon("XYZKIMALUSGHWM", dayAfter, 100, 0.2d);
		Coupon savedCoupon = couponRepository.save(coupon);
		assertThat(savedCoupon.getId()).isGreaterThan(0);
	}
}
