package com.cp2196g03g2.server.toptop.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.CommentDto;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.repository.ICommentRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;
import com.cp2196g03g2.server.toptop.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IVideoRepository videoRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	@Transactional
	public Comment save(CommentDto commentDto) {
		Video video = videoRepository.findById(commentDto.getVideoId()).get();
		ApplicationUser user = userRepository.findById(commentDto.getUserId()).get();
		Comment comment = new Comment();
		if(commentDto.getParentId() != null) {
			comment.setParent(commentRepository.findById(commentDto.getParentId()).get());
		}
		comment.setCreatedDate(new Date());
		comment.setContent(commentDto.getContent());
		comment.setUser(user);
		comment.setVideo(video);
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAllParentCommentByVideoId(Long videoId) {
		return commentRepository.findAllParentCommentByVideoId(videoId);
	}

}
