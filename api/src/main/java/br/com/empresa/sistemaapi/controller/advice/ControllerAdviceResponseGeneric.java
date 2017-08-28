package br.com.empresa.sistemaapi.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.empresa.sistemaapi.exception.BadRequestResponseException;
import br.com.empresa.sistemaapi.exception.ConflictResponseException;
import br.com.empresa.sistemaapi.exception.InternalServerErrorResponseException;
import br.com.empresa.sistemaapi.exception.ResponseException;

@ControllerAdvice
public class ControllerAdviceResponseGeneric extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ConflictResponseException.class})
	protected ResponseEntity<ResponseException> conflict(ConflictResponseException ex) {

		ResponseException responseException = new ResponseException();
		responseException.setCodigo(HttpStatus.CONFLICT.value());
		responseException.setMensagem(ex.getMessage());

		return new ResponseEntity<ResponseException>(responseException, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = {InternalServerErrorResponseException.class})
	protected ResponseEntity<ResponseException> conflict(InternalServerErrorResponseException ex) {

		ResponseException responseException = new ResponseException();
		responseException.setCodigo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		responseException.setMensagem(ex.getMessage());

		return new ResponseEntity<ResponseException>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = {BadRequestResponseException.class})
	protected ResponseEntity<ResponseException> conflict(BadRequestResponseException ex) {

		ResponseException responseException = new ResponseException();
		responseException.setCodigo(HttpStatus.BAD_REQUEST.value());
		responseException.setMensagem(ex.getMessage());

		return new ResponseEntity<ResponseException>(responseException, HttpStatus.BAD_REQUEST);
	}
}