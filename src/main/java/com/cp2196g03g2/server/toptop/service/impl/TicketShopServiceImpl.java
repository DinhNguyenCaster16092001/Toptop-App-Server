package com.cp2196g03g2.server.toptop.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.cp2196g03g2.server.toptop.dto.MailRequest;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.TicketShopDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.TicketShop;
import com.cp2196g03g2.server.toptop.enums.TicketStatus;
import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.repository.IRoleRepository;
import com.cp2196g03g2.server.toptop.repository.ITicketShopRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IRoleService;
import com.cp2196g03g2.server.toptop.service.ITicketShopService;


@Service
public class TicketShopServiceImpl implements ITicketShopService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private ITicketShopRepository ticketShopRepository;

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;

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
	@Transactional
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
	@Transactional
	public TicketShop updateStatusTicket(TicketShopDto dto) {
		try {
			
			TicketShop ticketShop = ticketShopRepository.findById(dto.getId()).
					orElseThrow(() -> new NotFoundException("Cannot found Ticket have id" + dto.getId()));	
			ticketShop.setStatus(convertIntToTicketStatus(dto.getStatus()));
			ticketShop.setReply(dto.getReply());
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

	@Override
	public PagableObject<TicketShop> findAllByPage(PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(request.getSortBy()).ascending()
                : Sort.by(request.getSortBy()).descending();
	 
	 	// create Pageable instance
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);
        
        boolean status = request.getIsActive() == 1 ? true : false;
        
        Page<TicketShop> tickkets = ticketShopRepository.findAllByPage(request.getKeyword(), pageable); 

        List<TicketShop> listOfTicketShops = tickkets.getContent();
        
        PagableObject<TicketShop> tickShopPage = new PagableObject<>();
        tickShopPage.setData(listOfTicketShops);
        tickShopPage.setPageNo(request.getPageNo());
        tickShopPage.setPageSize(request.getPageSize());
        tickShopPage.setTotalElements(tickkets.getTotalElements());
        tickShopPage.setTotalPages(tickkets.getTotalPages());
        tickShopPage.setLast(tickkets.isLast());
        
        return tickShopPage;
	}
	

}
	


