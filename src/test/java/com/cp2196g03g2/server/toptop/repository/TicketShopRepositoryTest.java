package com.cp2196g03g2.server.toptop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.model.ChartCloumModel;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TicketShopRepositoryTest {

	@Autowired
	private ITicketShopRepository ticketShopRepository;
	
	@Test
	public void getNumberUserSendingTicketByStatus() {
		Long total = ticketShopRepository.countUserSendingTicketInCurrentMonthByStatus(1);
		System.out.println(total);
	}
	
	

	@Test
	public void countUserSendingTicketInCurrentYear() {
		Long total = ticketShopRepository.countUserSendingTicketInCurrentYearByStatus(1);
		System.out.println(total);
	}
	
	
	@Test
	public void statics() {
		int year = 2021;
		int  status = 2;
		List<ChartCloumModel> model = ticketShopRepository.ticketStatisticsBytTwelveMonthPassStatus(status, year);
		for (ChartCloumModel chartCloumModel : model) {
			System.out.println(chartCloumModel.getMonth() + " " + chartCloumModel.getTotal());
		}
	}
}
