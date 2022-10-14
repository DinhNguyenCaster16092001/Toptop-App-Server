package com.cp2196g03g2.server.toptop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p WHERE (p.name LIKE %:keyword% OR "
			                             +"p.description LIKE %:keyword%) AND "
			                             + "p.user.alias = :alias")
	Page<Product> findAllByPage(@Param("keyword")String keyword, @Param("alias") String alias, Pageable pageable);
}
