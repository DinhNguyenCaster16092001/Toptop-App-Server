package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.TicketShopDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.enums.TicketStatus;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.ITicketShopRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.ITicketShopService;

@Service
public class TicketShopServiceImpl implements ITicketShopService {

	@Autowired
	private ITicketShopRepository ticketShopRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public List<TicketShop> findAll() {
		return ticketShopRepository.findAll();
	}

	@Override
	public TicketShop findById(Integer id) {
		return ticketShopRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot found ticket have id : " + id));
	}

	@Override
	public List<TicketShop> findByUserId(String id) {
		List<TicketShop> ticketOfUser = ticketShopRepository.findByUserId(id);
		if (ticketOfUser.isEmpty())
			throw new NotFoundException("The User " + id + " Not Have Any Ticket");
		return ticketOfUser;
	}

	@Override
	public TicketShop save(TicketShopDto ticketShopDto) {
		try {
			ApplicationUser user = userRepository.findById(ticketShopDto.getUserid())
					.orElseThrow(() -> new NotFoundException(" Cannot found user have id " + ticketShopDto.getUserid()));
			TicketShop ticket = new TicketShop();
			ticket.setStatus(TicketStatus.PENDING);
			ticket.setUser(user);
			ticket.setContent(ticketShopDto.getContent());
			return ticketShopRepository.save(ticket);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Override
	public TicketShop updateStatusTicket(Integer status, Integer id) {
		try {
			TicketShop ticketShop = ticketShopRepository.findById(id).
					orElseThrow(() -> new NotFoundException("Cannot found Ticket have id" + id));
			ticketShop.setStatus(convertIntToTicketStatus(status));
			return ticketShopRepository.save(ticketShop);
		}catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}
	
	private TicketStatus convertIntToTicketStatus(Integer number) {
		switch (number) {
		case 1:
			return TicketStatus.PENDING;
		case 2:
			return TicketStatus.ACTIVE;
		case 3:
			return TicketStatus.CANCEL;
		default:
			return TicketStatus.INACTIVE;
		}
	}
	
	

}
