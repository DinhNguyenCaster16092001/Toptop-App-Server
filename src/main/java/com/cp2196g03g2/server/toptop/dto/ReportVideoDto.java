package com.cp2196g03g2.server.toptop.dto;

public class ReportVideoDto {

	private Long id;
	
	private Integer typeId;
	
	private String userId;
	
	private String replyUserId;
	
	private Long videoId;


	public ReportVideoDto() {

	}

	

	public ReportVideoDto(Long id, Integer typeId, String userId, String replyUserId, Long videoId) {
		this.id = id;
		this.typeId = typeId;
		this.userId = userId;
		this.replyUserId = replyUserId;
		this.videoId = videoId;
	}
	
	
	


	public ReportVideoDto(Long id, String replyUserId) {
		this.id = id;
		this.replyUserId = replyUserId;
	}



	public ReportVideoDto(Integer typeId, String userId, String replyUserId, Long videoId) {
		this.typeId = typeId;
		this.userId = userId;
		this.replyUserId = replyUserId;
		this.videoId = videoId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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
