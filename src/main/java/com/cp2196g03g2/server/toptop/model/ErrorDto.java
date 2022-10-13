package com.cp2196g03g2.server.toptop.model;

public class ErrorDto {

	private String exception;
	private String timestamp;
	private String status;
	private String errorMessage;
	
	public ErrorDto() {
	}

	

	public ErrorDto(String exception, String timestamp, String status, String errorMessage) {
		this.exception = exception;
		this.timestamp = timestamp;
		this.status = status;
		this.errorMessage = errorMessage;
	}


	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}



	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
