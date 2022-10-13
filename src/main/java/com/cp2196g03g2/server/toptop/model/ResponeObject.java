package com.cp2196g03g2.server.toptop.model;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ResponeObject {
	
	private HttpStatus status;
	
	private String message;
	
	private String timestamp;
	
	private Object data;
	

	public ResponeObject() {
	}



	public ResponeObject(HttpStatus status, String message,  Object data) {
		this.status = status;
		this.message = message;
		this.timestamp = Instant.now().toString();
		this.data = data;
	}



	public HttpStatus getStatus() {
		return status;
	}



	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getTimestamp() {
		return Instant.now().toString();
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = Instant.now().toString();
	}



	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}
	
	
}
