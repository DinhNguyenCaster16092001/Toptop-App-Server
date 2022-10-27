package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.model.HashTagModel;

public interface IHashTagRepository extends JpaRepository<HashTag, Long>{
	HashTag findByName(String name);
	
	@Query("SELECT new com.cp2196g03g2.server.toptop.model.HashTagModel(h.id,h.name,SUM(v.view)) " + 
	"FROM HashTag h LEFT JOIN h.videos v WHERE (h.name LIKE :keyword%) " +
	"GROUP BY h.name")
	List<HashTagModel> selectTotalViewHashTagByName(@Param("keyword") String keyword);
}
