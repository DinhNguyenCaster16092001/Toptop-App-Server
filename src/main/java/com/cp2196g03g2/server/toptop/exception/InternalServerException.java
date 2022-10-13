package com.cp2196g03g2.server.toptop.exception;

public class InternalServerException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InternalServerException(String message) {
		super(message);
	}
	
	public InternalServerException(String message, Throwable cause) {
		super(message, cause);
	}

}
