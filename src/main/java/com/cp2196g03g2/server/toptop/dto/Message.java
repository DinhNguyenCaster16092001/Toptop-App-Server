package com.cp2196g03g2.server.toptop.dto;

import com.cp2196g03g2.server.toptop.enums.Status;

public class Message {
	private String senderName;
	private String reviceName;
	private String message;
	private String date;
	private Status status;
	
	public Message() {
		
	}
	
	
	
	public Message(String senderName, String reviceName, String message, String date, Status status) {
		this.senderName = senderName;
		this.reviceName = reviceName;
		this.message = message;
		this.date = date;
		this.status = status;
	}



	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReviceName() {
		return reviceName;
	}

	public void setReviceName(String reviceName) {
		this.reviceName = reviceName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [senderName=" + senderName + ", reviceName=" + reviceName + ", message=" + message + ", date="
				+ date + ", status=" + status + "]";
	}
}
