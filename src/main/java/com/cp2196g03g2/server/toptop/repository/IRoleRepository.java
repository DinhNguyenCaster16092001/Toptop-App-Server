package com.cp2196g03g2.server.toptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp2196g03g2.server.toptop.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Long>{
	Role findByAlias(String alias);
	
}
