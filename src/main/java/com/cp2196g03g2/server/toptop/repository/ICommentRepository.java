package com.cp2196g03g2.server.toptop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cp2196g03g2.server.toptop.entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long>{

	
	@Query("SELECT c FROM Comment c WHERE c.video.id =:videoid AND c.parent.id = NULL")
	List<Comment> findAllParentCommentByVideoId(@Param("videoid")Long videoId);
	
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.video.id =:videoid")
	long countByVideoId(@Param("videoid")Long videoId);
	
	@Query("SELECT c FROM Comment c WHERE c.parent.id =:parentId")
	Page<Comment> findAllChilrendCommentByParentId(@Param("parentId")Long parentId, Pageable pageable);
}
