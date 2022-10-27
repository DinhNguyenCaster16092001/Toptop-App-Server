package com.cp2196g03g2.server.toptop.dto;

public class CommentDto {
	private Long id;
	private String content;
	private Long parentId;
	private Long videoId;
	private String userId;
	
	public CommentDto() {
	}
	
	public CommentDto(Long id, String content, Long parentId, Long videoId, String userId) {
		this.id = id;
		this.content = content;
		this.parentId = parentId;
		this.videoId = videoId;
		this.userId = userId;
	}

	
	public CommentDto(String content, Long parentId, Long videoId, String userId) {
		this.content = content;
		this.parentId = parentId;
		this.videoId = videoId;
		this.userId = userId;
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

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", content=" + content + ", parentId=" + parentId + ", videoId=" + videoId
				+ ", userId=" + userId + "]";
	}
	
	
	
	
	
}
