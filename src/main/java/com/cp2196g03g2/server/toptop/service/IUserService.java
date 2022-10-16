package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.dto.ObjectKey;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.dto.UserDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;

public interface IUserService {
	List<ApplicationUser> findAll();
	PagableObject<ApplicationUser> findAllByPage(PagingRequest request);
	ApplicationUser findById(String id);
	ApplicationUser save(UserDto userDto);
	ApplicationUser update(UserDto dto);
	void delete(String id);
	boolean findByAlias(ObjectKey objectKey);
	boolean findByEmail(ObjectKey objectKey);
	void updateStatusUser(String id, boolean status);
}
