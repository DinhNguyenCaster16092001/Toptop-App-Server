package com.cp2196g03g2.server.toptop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.dto.TicketShopDto;
import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.repository.ITicketShopRepository;
import com.cp2196g03g2.server.toptop.service.ITicketShopService;

@RestController
@RequestMapping("/api/v1/ticketshop")
public class TicketShopController {
	
	@Autowired
	private ITicketShopService ticketShopService;
	
	@GetMapping
	public List<TicketShop> findAll(){
		return ticketShopService.findAll();
	}
	
	@GetMapping("/{id}")
	public TicketShop findById(@PathVariable Integer id){
		return ticketShopService.findById(id);
	}
	
	@GetMapping("/user/{userid}")
	public List<TicketShop> findByUserId(@PathVariable String userid){
		return ticketShopService.findByUserId(userid);
	}
	
	
	@PostMapping
	public TicketShop save(@RequestBody TicketShopDto dto) {
		return ticketShopService.save(dto);
	}
	
	@PutMapping("/{id}")
	public TicketShop update(@RequestBody Integer status, @PathVariable Integer id) {
		return ticketShopService.updateStatusTicket(status, id);
	}

}
