package com.cp2196g03g2.server.toptop.dto;


public class FriendShipDto {
	private String requestId;
	private String accetpId;

	public FriendShipDto(String requestId, String accetpId, boolean status) {
		this.requestId = requestId;
		this.accetpId = accetpId;
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
	
}
