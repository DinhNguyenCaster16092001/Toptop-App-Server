package com.cp2196g03g2.server.toptop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.repository.IHashTagRepository;
import com.cp2196g03g2.server.toptop.service.IHashTagService;

@Service
public class HashTagServiceImpl implements IHashTagService{

	@Autowired
	private IHashTagRepository hashTagRepository;
 	
	@Override
	public HashTag save(String name) {
		return hashTagRepository.save(new HashTag(name));
	}
	
	
}
