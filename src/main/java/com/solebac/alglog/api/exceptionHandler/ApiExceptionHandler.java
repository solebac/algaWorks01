package com.solebac.alglog.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.solebac.alglog.api.controller.domain.exception.EntidadeNotFoundException;
import com.solebac.alglog.api.controller.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> lista = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			System.out.println(nome + " - " + msg);
			//lista.add(new Problema.Campo(nome, msg));
		}
		
		Problema obj = new Problema();
		obj.setStatus(status.value());
		obj.setDataHora(LocalDateTime.now());
		obj.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		obj.setCampos(lista);
		return handleExceptionInternal(ex, obj, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema obj = new Problema();
		obj.setStatus(status.value());
		obj.setDataHora(LocalDateTime.now());
		obj.setTitulo(ex.getMessage());
		return handleExceptionInternal(ex, obj, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeNotFoundException.class)
	public ResponseEntity<Object> handleEntidadeNotFound(EntidadeNotFoundException ex, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problema obj = new Problema();
		obj.setStatus(status.value());
		obj.setDataHora(LocalDateTime.now());
		obj.setTitulo(ex.getMessage());
		return handleExceptionInternal(ex, obj, new HttpHeaders(), status, request);
	}
}
