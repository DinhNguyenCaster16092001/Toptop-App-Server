package com.cp2196g03g2.server.toptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;

public interface IUserRepository extends JpaRepository<ApplicationUser, String>{
	ApplicationUser findByAlias(String alias);
	ApplicationUser findByEmail(String email);
	
}
