package com.liangcoder.urlshortner.exception;

public class PersistUrlPairException extends GenerateShortUrlFailureException {

	private static final long serialVersionUID = -7214410930575457877L;

	public PersistUrlPairException(String message){
		super(message);
	}
	
	public PersistUrlPairException(String message, Throwable cause){
		super(message, cause);
	}
}
