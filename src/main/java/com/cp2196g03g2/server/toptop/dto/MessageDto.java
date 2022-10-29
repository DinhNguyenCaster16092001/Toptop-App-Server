package com.cp2196g03g2.server.toptop.dto;


public class MessageDto {
	private Long id;
	private String content;
	private String senderId;
	private String reccive_id;
	private boolean status;
	
	
	
	public MessageDto() {
	}


	public MessageDto(String content, String senderId, String reccive_id, boolean status) {
		this.content = content;
		this.senderId = senderId;
		this.reccive_id = reccive_id;
		this.status = status;
	}


	public MessageDto(Long id, String content, String senderId, String reccive_id, boolean status) {
		this.id = id;
		this.content = content;
		this.senderId = senderId;
		this.reccive_id = reccive_id;
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


	public String getReccive_id() {
		return reccive_id;
	}


	public void setReccive_id(String reccive_id) {
		this.reccive_id = reccive_id;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
