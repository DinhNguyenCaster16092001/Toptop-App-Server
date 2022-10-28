package com.cp2196g03g2.server.toptop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Video;

public interface IVideoRepository extends JpaRepository<Video, Long> {
	
	@Query("SELECT v FROM Video v LEFT JOIN v.hashTags h WHERE (v.title LIKE %:keyword% OR " + 
															   "v.user.fullName LIKE %:keyword% OR "  +
															   "v.user.id LIKE %:keyword% OR "  +
															   "h.name LIKE %:keyword%) " +
															   "GROUP BY v.title, v.id")
	Page<Video> findAllVideoByPage(Pageable pageable, @Param("keyword")String keyword);
	
}
