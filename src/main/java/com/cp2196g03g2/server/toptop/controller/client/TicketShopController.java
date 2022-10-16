package com.cp2196g03g2.server.toptop.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.TicketShopDto;
import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.service.ITicketShopService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ticketshop")
public class TicketShopController {

	@Autowired
	private ITicketShopService ticketShopService;
	
	@PostMapping
	public TicketShop save(@RequestBody TicketShopDto dto) {
		return ticketShopService.save(dto);
	}
}
