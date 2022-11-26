package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;

public interface IUserRepository extends JpaRepository<ApplicationUser, String> {
	ApplicationUser findByAlias(String alias);

	ApplicationUser findByEmail(String email);

	List<ApplicationUser> findAllByOrderByCreatedDateDesc();

	@Query("SELECT u FROM ApplicationUser u WHERE (u.email LIKE %:keyword% OR " + "u.fullName LIKE %:keyword% OR "
			+ "u.history LIKE %:keyword% OR " + "u.role.alias LIKE %:keyword%) " + "AND u.role.id IN (1,2,3,4) "
			+ "AND u.isActive = :status " + "AND u.role.alias != 'Super Admin'")
	Page<ApplicationUser> findAllByPage(@Param("keyword") String keyword, @Param("status") boolean isActive,
			Pageable pageable);

	@Query("UPDATE ApplicationUser u SET u.isActive = :status WHERE u.id = :id")
	@Modifying
	void updateStatusUser(@Param("id") String id, @Param("status") boolean status);

	@Query("SELECT COUNT(u.id) FROM ApplicationUser u WHERE u.role.id=5 AND u.isActive = true")
	Long coutUserRoleCustomer();

	@Query("SELECT u FROM ApplicationUser u WHERE (u.fullName LIKE %:keyword% OR "
			+ "u.alias LIKE %:keyword%) " + "AND u.role.id IN (5,6)")
	Page<ApplicationUser> findAllCustomerByPage(Pageable pageable, @Param("keyword") String keyword);

	@Query(value = "SELECT COUNT(id) FROM tbl_user WHERE MONTH(created_date) = MONTH(CURRENT_DATE) " + 
			"AND role_id IN (5,6)", nativeQuery = true)
	long getTotalNewUserOfCurrentMonth();
	
	
}
