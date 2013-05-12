package com.liangcoder.urlshortner.exception;

public class GenerateShortUrlFailureException extends Exception {

	private static final long serialVersionUID = -8867798083247931177L;

	private String longUrl;

	public GenerateShortUrlFailureException() {
		super();
	}

	public GenerateShortUrlFailureException(String message) {
		super(message);
	}

	public GenerateShortUrlFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
}
