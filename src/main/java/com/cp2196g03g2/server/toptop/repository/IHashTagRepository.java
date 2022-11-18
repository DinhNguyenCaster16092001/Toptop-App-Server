package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.HashTag;
import com.cp2196g03g2.server.toptop.model.HashTagModel;

public interface IHashTagRepository extends JpaRepository<HashTag, Long>{
	HashTag findByName(String name);
	

	@Query(value = "SELECT h.id, h.name, COALESCE(SUM(DISTINCT v.view),0) as views  FROM tbl_hashtag h " + 
			"LEFT JOIN tbl_videos_hashtag vh " + 
			"ON h.id = vh.video_id " + 
			"LEFT JOIN tbl_video v " + 
			"ON vh.video_id = v.id " + 
			"WHERE h.name LIKE :name% " + 
			"GROUP BY h.id, h.name " + 
			"ORDER BY views DESC LIMIT 10", nativeQuery = true)
	List<HashTagModel> findTopTenHashTagOrderByView(@Param("name") String name);
}
