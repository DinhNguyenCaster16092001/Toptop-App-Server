package com.cp2196g03g2.server.toptop.repository.ticketshop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.enums.TicketStatus;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.ITicketShopRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TicketShopRespositoryTest {

	@Autowired
	private ITicketShopRepository ticketShopRepository;
	
	@Test
	public void inital() {
		
	}
	
	@Test
	public void createTicketShopTest() {
		try {
			TicketShop ticket = new TicketShop("Anh ơi duyệt mở cho em tính năng shop", null,TicketStatus.INACTIVE);
			TicketShop ticketSaved = ticketShopRepository.save(ticket);
			assertThat(ticketSaved.getId()).isGreaterThan(0);
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	

	@Test
	public void updateStatusTicket() {
	try {
		Integer id = 1;
		TicketShop ticketShop = ticketShopRepository.findById(id).get();
		ticketShop.setStatus(TicketStatus.PENDING);
		ticketShopRepository.save(ticketShop);
	}catch (Exception e) {
		throw new RuntimeException(e.getMessage());
		}
	}
	
	@Test 
	public void findAllTicketTest() {
		List<TicketShop> ticketsShop = ticketShopRepository.findAll();
		ticketsShop.stream().forEach(System.out::println);
	}
	
	
	
	
	
}
