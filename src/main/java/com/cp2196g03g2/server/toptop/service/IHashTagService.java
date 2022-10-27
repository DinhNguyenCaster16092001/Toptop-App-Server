package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.model.HashTagModel;

public interface IHashTagService {
	HashTag save(String name);
	List<HashTagModel> findByName(String name);
}
