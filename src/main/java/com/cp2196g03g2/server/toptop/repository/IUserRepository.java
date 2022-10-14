package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;

public interface IUserRepository extends JpaRepository<ApplicationUser, String>{
	ApplicationUser findByAlias(String alias);
	ApplicationUser findByEmail(String email);
	List<ApplicationUser> findAllByOrderByCreatedDateDesc();
	
	@Query("SELECT u FROM ApplicationUser u WHERE (u.email LIKE %:keyword% OR " +
												   "u.fullName LIKE %:keyword% OR " +
												   "u.history LIKE %:keyword% OR " +
												   "u.role.description LIKE %:keyword%) "+
												   "AND u.role.id IN (1,2,3,4) " +
												   "AND u.isActive = :status " +
												   "AND u.role.alias != 'Super Admin'")
	Page<ApplicationUser> findAllByPage(@Param("keyword")String keyword, @Param("status")boolean isActive, Pageable pageable);
	
}
