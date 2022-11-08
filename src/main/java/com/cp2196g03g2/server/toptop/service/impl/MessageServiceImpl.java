package com.cp2196g03g2.server.toptop.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.MessageDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Message;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.repository.IMessageRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService{

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IMessageRepository messageRepository;
	
	@Override
	@Transactional
	public Message save(MessageDto dto) {
		Message message = new Message();
		
		ApplicationUser senderUser = userRepository.findById(dto.getSenderId()).get();
		ApplicationUser recciveUser = userRepository.findById(dto.getReccive_id()).get();
		
		message.setCreatedDate(new Date());
		message.setContent(dto.getContent());
		message.setSenderUser(senderUser);
		message.setRecciveUser(recciveUser);
		message.setStatus(true);
		
		return messageRepository.save(message);
	}

	@Override
	public List<Message> findAllMessageByUserId(String userId) {
		return messageRepository.findMessagesForUser(userId);
	}

	@Override
	@Transactional
	public PagableObject<Message> findAllMessagePrivateChat(String userId, String friendId, PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);


		Page<Message> messages = messageRepository.findAllMessageByUserIdAndFriendId(userId, friendId, pageable);

		// reverse order list message
		List<Message> upsideDownListMessage = 
					messages.getContent().stream().
					sorted(Comparator.comparing(Message::getId)).
					collect(Collectors.toList());

		for (Message message : upsideDownListMessage) {
			System.out.println(message.toString());
		}

		PagableObject<Message> messagePagable = new PagableObject<>();

		messagePagable.setData(upsideDownListMessage);
		messagePagable.setPageNo(request.getPageNo());
		messagePagable.setPageSize(request.getPageSize());
		messagePagable.setTotalElements(messages.getTotalElements());
		messagePagable.setTotalPages(messages.getTotalPages());
		messagePagable.setLast(messages.isLast());

		return messagePagable;
	}

}
