package com.cp2196g03g2.server.toptop.dto;

public class CommentDto {
	private Long id;
	private String content;
	private Long parentId;
	
	public CommentDto() {
	}
	
	public CommentDto(Long id, String content, Long parentId) {
		this.id = id;
		this.content = content;
		this.parentId = parentId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", content=" + content + ", parentId=" + parentId + "]";
	}
	
	
	
}
