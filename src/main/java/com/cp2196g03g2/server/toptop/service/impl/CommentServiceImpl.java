package com.cp2196g03g2.server.toptop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.CommentDto;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.repository.ICommentRepository;
import com.cp2196g03g2.server.toptop.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private ICommentRepository commentRepository;
	
	@Override
	public Comment save(CommentDto commentDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllParentCommentByVideoId(Long videoId) {
		return commentRepository.findAllParentCommentByVideoId(videoId);
	}

}
