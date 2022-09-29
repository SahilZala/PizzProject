package com.pack.exception;

public class NoTopingFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public NoTopingFoundException(String msg){
		super(msg);
	}
}
