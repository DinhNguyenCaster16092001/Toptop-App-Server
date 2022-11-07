package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.constant.AppConstants;
import com.cp2196g03g2.server.toptop.dto.MessageDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.Message;
import com.cp2196g03g2.server.toptop.entity.Product;
import com.cp2196g03g2.server.toptop.service.IMessageService;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

	@Autowired
	private IMessageService messageService;
	
	@GetMapping("/{userId}")
	public List<Message> createMessage(@PathVariable String userId) {
		return messageService.findAllMessageByUserId(userId);
	}
	
	
	@PostMapping
	public Message createMessage(@RequestBody MessageDto dto) {
		return messageService.save(dto);
	}
	
	@GetMapping
	public PagableObject<Message> findAllByPage(
			@RequestParam(required = true) String userId,
			@RequestParam(required = true) String friendId,				
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
		PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir);
		return messageService.findAllMessagePrivateChat(userId, friendId, request);
	}
}
