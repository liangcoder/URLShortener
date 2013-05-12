package com.liangcoder.urlshortner.domain;

public interface UrlShortenAlgorithm {

	public abstract String generateShortUrl(String longUrl);

	public abstract String getName();

}
