package com.cp2196g03g2.server.toptop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.service.ICommentService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/comment")
public class CommentController {

	@Autowired
	private ICommentService commentService;
	
	@GetMapping("/video/{videoId}")
	public List<Comment> findAllCommentByVideoId(@PathVariable Long videoId){
		return commentService.findAllParentCommentByVideoId(videoId);
	}

}
