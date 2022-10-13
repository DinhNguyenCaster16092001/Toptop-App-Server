package com.cp2196g03g2.server.toptop.service;

import java.util.Set;

import com.cp2196g03g2.server.toptop.entity.Role;

public interface IRoleService {
	Set<Role> findAll();
	Role findById(Long id);
	Role save(Role role);
	Role update(Role role, Long id);
	void deleteById(Long id);
}
