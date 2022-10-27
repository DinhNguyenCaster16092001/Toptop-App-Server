package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.ReportVideo;

public interface IReportVideoRepository extends JpaRepository<ReportVideo, Long>{

	
	@Query("SELECT p FROM ReportVideo p WHERE p.user.id =:keyword OR p.user.fullName=:keyword")
	Page<ReportVideo> findAllByPage(@Param("keyword")String keyword, Pageable pageable);
}
