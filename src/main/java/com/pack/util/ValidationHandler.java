package com.pack.util;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pack.model.StatusModel;

import org.springframework.validation.FieldError;




@ControllerAdvice

public class ValidationHandler extends
ResponseEntityExceptionHandler {

@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
	WebRequest request) {
	
		List<String> errors = new ArrayList<String>();
		List<FieldError>  errList=ex.getBindingResult().getFieldErrors(); 
		
		for (FieldError error :  errList) {
		
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		
		}
		
		
	
		return new ResponseEntity<Object>(new StatusModel(LocalDateTime.now().toString(),HttpStatus.BAD_REQUEST,errors.get(0),"","no data"),HttpStatus.BAD_REQUEST);
	}
}
