package com.liangcoder.urlshortner.exception;

public class ResolveShortUrlFailureException extends Exception {

	private static final long serialVersionUID = -682000488539322619L;

	private String shortUrl;

	public ResolveShortUrlFailureException() {
		super();
	}

	public ResolveShortUrlFailureException(String message) {
		super(message);
	}

	public ResolveShortUrlFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}
}
