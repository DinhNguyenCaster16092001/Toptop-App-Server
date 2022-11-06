package com.cp2196g03g2.server.toptop.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.CommentDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.entity.Notification;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.cp2196g03g2.server.toptop.enums.NotificationType;
import com.cp2196g03g2.server.toptop.repository.ICommentRepository;
import com.cp2196g03g2.server.toptop.repository.IUserRepository;
import com.cp2196g03g2.server.toptop.repository.IVideoRepository;
import com.cp2196g03g2.server.toptop.service.ICommentService;
import com.cp2196g03g2.server.toptop.service.INotificationService;

@Service
public class CommentServiceImpl implements ICommentService{

	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IVideoRepository videoRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private INotificationService notificationService;
	
	@Override
	@Transactional
	public Comment save(CommentDto commentDto) {
		Video video = videoRepository.findById(commentDto.getVideoId()).get();
		ApplicationUser user = userRepository.findById(commentDto.getUserId()).get();
		Comment comment = new Comment();
		comment.setCreatedDate(new Date());
		comment.setParent(null);
		comment.setContent(commentDto.getContent());
		comment.setUser(user);
		comment.setVideo(video);
		Notification notification = new 
				Notification(video.getUser(), user, false, false, NotificationType.LIKE);
		notificationService.createNotification(notification);
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAllParentCommentByVideoId(Long videoId) {
		return commentRepository.findAllParentCommentByVideoId(videoId);
	}

	@Override
	@Transactional
	public PagableObject<Comment> findChildrenCommentByParentId(Long parentId, PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		Page<Comment> comments = commentRepository.findAllChilrendCommentByParentId(parentId, pageable);
		List<Comment> listOfComments = comments.getContent();
		
		PagableObject<Comment> commentPage = new PagableObject<>();
		commentPage.setData(listOfComments);
		commentPage.setPageNo(request.getPageNo());
		commentPage.setPageSize(request.getPageSize());
		commentPage.setTotalElements(comments.getTotalElements());
		commentPage.setTotalPages(comments.getTotalPages());
		commentPage.setLast(comments.isLast());

		return commentPage;
	}

}
