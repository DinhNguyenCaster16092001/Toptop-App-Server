package com.cp2196g03g2.server.toptop.dto;


public class MessageDto {
	private Long id;
	private String content;
	private String senderId;
	private String recciveId;
	private boolean status;
	
	
	
	public MessageDto() {
	}


	public MessageDto(String content, String senderId, String recciveId, boolean status) {
		this.content = content;
		this.senderId = senderId;
		this.recciveId = recciveId;
		this.status = status;
	}


	public MessageDto(Long id, String content, String senderId, String recciveId, boolean status) {
		this.id = id;
		this.content = content;
		this.senderId = senderId;
		this.recciveId = recciveId;
		this.status = status;
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


	public String getSenderId() {
		return senderId;
	}


	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecciveId() {
		return recciveId;
	}


	public void setRecciveId(String recciveId) {
		this.recciveId = recciveId;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
