package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.constant.AppConstants;
import com.cp2196g03g2.server.toptop.dto.CommentDto;
import com.cp2196g03g2.server.toptop.dto.LikeDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.model.CommentModel;
import com.cp2196g03g2.server.toptop.service.ICommentService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/comment")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@GetMapping("/video/{videoId}")
	public PagableObject<Comment> findAllCommentByVideoId(@PathVariable Long videoId,
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir);
		return commentService.findAllParentCommentByVideoId(videoId, request);
	}
	
	@GetMapping("/search/{id}")
	public Comment findById(@PathVariable Long id) {
		return commentService.findParentById(id);
	}

	@PostMapping
	public Comment saveComment(@RequestBody CommentDto dto) {
		if (dto.getParentId() == null)
			return commentService.save(dto);
		return commentService.replyComment(dto);
	}
	
	
	@GetMapping("/children/{parentId}")
	public PagableObject<Comment> findAllChildrendComment(@PathVariable("parentId") Long parentId,
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		PagingRequest request = new PagingRequest(pageNo, pageSize, sortBy, sortDir);
		return commentService.findChildrenCommentByParentId(parentId, request);
	}

}
