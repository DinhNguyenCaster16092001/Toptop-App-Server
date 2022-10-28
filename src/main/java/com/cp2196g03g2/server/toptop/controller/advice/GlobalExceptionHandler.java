package com.cp2196g03g2.server.toptop.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cp2196g03g2.server.toptop.exception.InternalServerException;
import com.cp2196g03g2.server.toptop.exception.NotFoundException;
import com.cp2196g03g2.server.toptop.model.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorDto> generateException(NotFoundException re) {
		ErrorDto dto = new ErrorDto();
		dto.setTimestamp(new Date().toString());
		dto.setException(re.getClass().getSimpleName());
		dto.setStatus("404");
		dto.setErrorMessage(re.getMessage());

		return new ResponseEntity<ErrorDto>(dto, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorDto> internalException(RuntimeException re) {
		ErrorDto dto = new ErrorDto();
		dto.setTimestamp(new Date().toString());
		dto.setException(re.getClass().getSimpleName());
		dto.setStatus("500");
		dto.setErrorMessage(re.getMessage());
		return new ResponseEntity<ErrorDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex) {
		ErrorDto dto = new ErrorDto();
		dto.setTimestamp(new Date().toString());
		dto.setException(ex.getClass().getSimpleName());
		dto.setStatus("403");
		dto.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorDto>(dto, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> exception(Exception ex) {
		ErrorDto dto = new ErrorDto();
		dto.setTimestamp(new Date().toString());
		dto.setException(ex.getClass().getSimpleName());
		dto.setStatus("502");
		dto.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorDto>(dto, HttpStatus.BAD_GATEWAY);
	}
}
