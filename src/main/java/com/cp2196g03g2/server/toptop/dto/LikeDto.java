package com.cp2196g03g2.server.toptop.dto;

public class LikeDto {
	private Long commenId;
	private String userId;
	private boolean status;
	
	
	public LikeDto(Long commenId, String userId, boolean status) {
		this.commenId = commenId;
		this.userId = userId;
		this.status = status;
	}

	
	public Long getCommenId() {
		return commenId;
	}

	public void setCommenId(Long commenId) {
		this.commenId = commenId;
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
