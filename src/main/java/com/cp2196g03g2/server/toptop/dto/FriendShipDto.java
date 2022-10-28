package com.cp2196g03g2.server.toptop.dto;


public class FriendShipDto {
	private String requestId;
	
	private String accetpId;
	
	private boolean status;

	public FriendShipDto(String requestId, String accetpId, boolean status) {
		this.requestId = requestId;
		this.accetpId = accetpId;
		this.status = status;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getAccetpId() {
		return accetpId;
	}

	public void setAccetpId(String accetpId) {
		this.accetpId = accetpId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
