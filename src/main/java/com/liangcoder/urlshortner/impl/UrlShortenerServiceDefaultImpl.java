package com.liangcoder.urlshortner.impl;

import com.liangcoder.urlshortner.domain.AbstractUrlShortenerService;
import com.liangcoder.urlshortner.domain.ShortUrlRepository;
import com.liangcoder.urlshortner.domain.UrlPair;
import com.liangcoder.urlshortner.exception.PersistUrlPairException;

public class UrlShortenerServiceDefaultImpl extends AbstractUrlShortenerService {

	private ShortUrlRepository shortUrlRepository;

	public void setShortUrlRepository(ShortUrlRepository shortUrlRepository) {
		this.shortUrlRepository = shortUrlRepository;
	}

	public ShortUrlRepository getShortUrlRepository() {
		return shortUrlRepository;
	}

	@Override
	public UrlPair findUrlPairByShortUrl(String shortUrl) {
		return shortUrlRepository.findUrlPairByShortUrl(shortUrl);
	}

	@Override
	public UrlPair findUrlPairByLongUrl(String longUrl) {
		return shortUrlRepository.findUrlPairByLongUrl(longUrl);
	}

	@Override
	public void persistUrlPair(UrlPair urlPair) throws PersistUrlPairException {
		shortUrlRepository.persistUrlPair(urlPair);
	}
}
