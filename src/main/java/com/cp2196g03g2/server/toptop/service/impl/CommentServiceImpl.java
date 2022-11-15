package com.cp2196g03g2.server.toptop.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cp2196g03g2.server.toptop.dto.CommentDto;
import com.cp2196g03g2.server.toptop.dto.LikeDto;
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
public class CommentServiceImpl implements ICommentService {

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
		Comment savedComment = commentRepository.save(comment);
		Notification notification = new Notification(video.getUser(), user, video, savedComment, false, false, 2);
		notificationService.createNotification(notification);
		return savedComment;
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

	@Override
	public Comment replyComment(CommentDto commentDto) {
		// get video
		Video video = videoRepository.findById(commentDto.getVideoId()).get();

		// get comment parent
		Comment parentComment = commentRepository.findById(commentDto.getParentId()).get();

		// Get User Comment
		ApplicationUser user = userRepository.findById(commentDto.getUserId()).get();

		Comment childComment = new Comment(commentDto.getContent(), new Date(), user, video, parentComment);

		// commet to database
		Comment savedCommet = commentRepository.save(childComment);
		if (savedCommet != null) {
			Set<String> userIds = getListUserId(parentComment, childComment);
			if (userIds.size() > 0) {
				for (String userTo : userIds) {
					Notification notification = new Notification(userRepository.findById(userTo).get(), user, video,
							parentComment, false, false, 3);
					notification.setContent(childComment.getContent());
					notificationService.createNotification(notification);
				}
			}
		}

		return savedCommet;
	}

	private Set<String> getListUserId(Comment parentComment, Comment childComment) {
		List<Comment> chidrenComments = parentComment.getChildren();
		Set<String> set = new HashSet<>();
		if (chidrenComments.size() > 0) {
			set = chidrenComments.stream().flatMap(p -> Stream.of(p.getUser().getId())).collect(Collectors.toSet());
		}
		if (parentComment.getUser().getId() != childComment.getUser().getId()) {
			set.add(parentComment.getUser().getId());
		}
		set.removeIf(x -> x.equals(childComment.getUser().getId()));
		return set;
	}

	@Override
	public PagableObject<Comment> findAllParentCommentByVideoId(Long videoId, PagingRequest request) {
		Sort sort = request.getSortDir().equalsIgnoreCase(Sort.Direction.ASC.name())
				? Sort.by(request.getSortBy()).ascending()
				: Sort.by(request.getSortBy()).descending();

		Pageable pageable = PageRequest.of(request.getPageNo(), request.getPageSize(), sort);

		Page<Comment> comments = commentRepository.findAllParentCommentByVideoId(videoId, pageable);
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

	@Override
	@Transactional
	public Comment likeComment(LikeDto dto) {
		// get user information
		ApplicationUser user = userRepository.findById(dto.getUserId()).get();

		// get comment information
		Comment comment = commentRepository.findById(dto.getCommenId()).get();

		Long currentNumberLike = comment.getHeart() != null ? comment.getHeart() : 0;
		if (dto.isStatus())
			currentNumberLike += 1;
		else
			currentNumberLike -= 1;
		
		if (currentNumberLike < 0)
			currentNumberLike = 0L;
		
		comment.setHeart(currentNumberLike);

		// ignore notification when user is owner that comment
		if (!ignoreOwnerComment(user, comment)) {
			Notification notification = new Notification(comment.getUser(), user, comment.getVideo(), comment, false,
					false, 6);
			notificationService.createNotification(notification);
		}

		return commentRepository.save(comment);
	}

	private boolean ignoreOwnerComment(ApplicationUser user, Comment comment) {
		return user.getId().equals(comment.getUser().getId());
	}
}
