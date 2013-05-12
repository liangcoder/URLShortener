package com.liangcoder.urlshortner.algorithm;

import com.liangcoder.urlshortner.domain.AbstractUrlShortenAlgorithm;

public class MathRandomUrlShortenAlgorithm extends AbstractUrlShortenAlgorithm {

	private static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public MathRandomUrlShortenAlgorithm(int shortUrlLength){
		super(shortUrlLength);
	}
	
	@Override
	public String generateShortUrl(String longUrl) {
		StringBuilder shortUrl = new StringBuilder();
		for (int i = 0; i < getShortUrlLength(); i++) {
			int index = (int) (Math.random() * 1000 % characters.length());
			shortUrl.append(characters.charAt(index));
		}
		return shortUrl.toString();
	}

	@Override
	public String getName() {
		return "MathRandomUrlShortenAlgorithm";
	}
}
