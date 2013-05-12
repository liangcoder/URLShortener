package com.liangcoder.urlshortner.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liangcoder.urlshortner.domain.ShortUrlRepository;
import com.liangcoder.urlshortner.domain.UrlPair;
import com.liangcoder.urlshortner.exception.PersistUrlPairException;

public class ShortUrlRepositoryMapImpl implements ShortUrlRepository {

	private Map<String, UrlPair> shortUrlPairMap = new ConcurrentHashMap<String, UrlPair>();

	private Map<String, UrlPair> longUrlPairMap = new ConcurrentHashMap<String, UrlPair>();

	@Override
	public UrlPair findUrlPairByShortUrl(String shortUrl) {
		UrlPair urlPair = shortUrlPairMap.get(shortUrl);
		return urlPair;
	}

	@Override
	public UrlPair findUrlPairByLongUrl(String longUrl) {
		UrlPair urlPair = longUrlPairMap.get(longUrl);
		return urlPair;
	}

	@Override
	public void persistUrlPair(UrlPair urlPair) throws PersistUrlPairException {
		shortUrlPairMap.put(urlPair.getShortUrl(), urlPair);
		longUrlPairMap.put(urlPair.getLongUrl(), urlPair);
	}
}
