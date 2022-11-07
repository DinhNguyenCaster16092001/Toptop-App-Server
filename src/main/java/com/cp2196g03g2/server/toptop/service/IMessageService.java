package com.cp2196g03g2.server.toptop.service;


import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.cp2196g03g2.server.toptop.dto.MessageDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.Message;

public interface IMessageService {
	Message save(MessageDto dto);
	List<Message> findAllMessageByUserId(String userId);
	PagableObject<Message> findAllMessagePrivateChat(String userId, String friendId, PagingRequest request);
}
