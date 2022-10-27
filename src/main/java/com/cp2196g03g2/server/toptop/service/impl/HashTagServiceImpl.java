package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.model.HashTagModel;
import com.cp2196g03g2.server.toptop.repository.IHashTagRepository;
import com.cp2196g03g2.server.toptop.service.IHashTagService;

@Service
public class HashTagServiceImpl implements IHashTagService {

	@Autowired
	private IHashTagRepository hashTagRepository;

	@Override
	@Transactional
	public HashTag save(String name) {
		return hashTagRepository.save(new HashTag(name));
	}

	@Override
	@Transactional
	public List<HashTagModel> findByName(String name) {
		List<HashTagModel> listHashTag = hashTagRepository.selectTotalViewHashTagByName(name);
		listHashTag.stream().forEach(hashtag -> {
			if(hashtag.getView() == null)
				hashtag.setView(0L);
		});
		listHashTag.sort((h1, h2) -> h2.getView().compareTo(h1.getView()));
		return listHashTag;
	}

}
