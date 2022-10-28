package com.cp2196g03g2.server.toptop.dto;

public class HeartDto {
	private Long videoId;
	private String userId;
	private boolean status;
	
	
	public HeartDto(Long videoId, String userId, boolean status) {
		this.videoId = videoId;
		this.userId = userId;
		this.status = status;
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


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
