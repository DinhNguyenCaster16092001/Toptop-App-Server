package com.cp2196g03g2.server.toptop.dto;

public class FavouriteVideoDto {

	private Long id;
	
	private String userId;
	
	private Long videoId;

	public FavouriteVideoDto(Long id, String userId, Long videoId) {
		this.id = id;
		this.userId = userId;
		this.videoId = videoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "FavouriteVideoDto [id=" + id + ", userId=" + userId + ", videoId=" + videoId + "]";
	}
	
}
