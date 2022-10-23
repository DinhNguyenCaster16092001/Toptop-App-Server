package com.cp2196g03g2.server.toptop.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cp2196g03g2.server.toptop.entity.Video;

public interface IVideoRepository extends JpaRepository<Video, Long> {

	@Query("select distinct v from Video v")
	List<Video> findAllVideos(Pageable pageable);
}
