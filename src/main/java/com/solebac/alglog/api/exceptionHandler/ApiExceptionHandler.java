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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
