package com.cp2196g03g2.server.toptop.service;

import java.util.List;

import com.cp2196g03g2.server.toptop.dto.CommentDto;
import com.cp2196g03g2.server.toptop.dto.PagableObject;
import com.cp2196g03g2.server.toptop.dto.PagingRequest;
import com.cp2196g03g2.server.toptop.entity.Comment;

public interface ICommentService {
	Comment save(CommentDto commentDto);
	List<Comment> findAllParentCommentByVideoId(Long videoId);
	PagableObject<Comment> findChildrenCommentByParentId(Long parentId, PagingRequest request);
}
