package com.cp2196g03g2.server.toptop.dto;

public class ReportVideoDto {

	private Long id;
	
	private String content;
	
	private String userId;
	
	private String replyUserId;
	
	private Long videoId;


	public ReportVideoDto() {

	}

	public ReportVideoDto(String content, String userId, String replyUserId, Long videoId) {
		this.content = content;
		this.userId = userId;
		this.replyUserId = replyUserId;
		this.videoId = videoId;
	}

	public ReportVideoDto(String content, String userId, Long videoId) {
		this.content = content;
		this.userId = userId;
		this.videoId = videoId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	
}
