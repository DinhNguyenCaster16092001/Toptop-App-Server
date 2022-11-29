package com.cp2196g03g2.server.toptop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cp2196g03g2.server.toptop.entity.ApplicationUser;
import com.cp2196g03g2.server.toptop.entity.Comment;
import com.cp2196g03g2.server.toptop.entity.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CommentModel {
	private Long id;
	private String content;
	@JsonSerialize(as = LocalDateTime.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss dd-MM-yyyy")
	private LocalDateTime createdDate;
	private ApplicationUser user;
	private Comment parent; 
	private List<Comment> children = new ArrayList<>();
	
	
	
	public CommentModel() {
	}
	public CommentModel(Long id, String content, LocalDateTime createdDate, ApplicationUser user, 
			Comment parent, List<Comment> children) {
		this.id = id;
		this.content = content;
		this.createdDate = createdDate;
		this.user = user;
		this.parent = parent;
		this.children = children;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the user
	 */
	public ApplicationUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	
	/**
	 * @return the parent
	 */
	public Comment getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Comment parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public List<Comment> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Comment> children) {
		this.children = children;
	}
	
	
}
