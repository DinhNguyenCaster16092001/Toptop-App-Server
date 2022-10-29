package com.cp2196g03g2.server.toptop.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.MessageDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Message;
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

}
