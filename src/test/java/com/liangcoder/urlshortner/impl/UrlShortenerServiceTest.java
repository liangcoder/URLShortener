package com.liangcoder.urlshortner.impl;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.liangcoder.urlshortner.algorithm.MathRandomUrlShortenAlgorithm;
import com.liangcoder.urlshortner.domain.ShortUrlRepository;
import com.liangcoder.urlshortner.domain.UrlPair;
import com.liangcoder.urlshortner.domain.UrlShortenAlgorithm;
import com.liangcoder.urlshortner.domain.UrlShortenerService;
import com.liangcoder.urlshortner.exception.GenerateShortUrlFailureException;
import com.liangcoder.urlshortner.exception.ResolveShortUrlFailureException;
import com.liangcoder.urlshortner.impl.ShortUrlRepositoryMapImpl;
import com.liangcoder.urlshortner.impl.UrlShortenerServiceDefaultImpl;

public class UrlShortenerServiceTest {

	private UrlShortenerService urlShortenerService;

	private static final String testLongUrl = "https://github.com/liangcoder";

	@Before
	public void before() {
		UrlShortenerServiceDefaultImpl urlShortenerServiceDefaultImpl = new UrlShortenerServiceDefaultImpl();
		Set<UrlShortenAlgorithm> algorithms = new HashSet<UrlShortenAlgorithm>();
		algorithms.add(new MathRandomUrlShortenAlgorithm(8));
		urlShortenerServiceDefaultImpl.setUrlShortenAlgorithms(algorithms);

		ShortUrlRepository shortUrlRepository = new ShortUrlRepositoryMapImpl();
		urlShortenerServiceDefaultImpl.setShortUrlRepository(shortUrlRepository);
		urlShortenerService = urlShortenerServiceDefaultImpl;
	}

	@Test
	public void testGenerateShortUrl() {
		String longUrl = testLongUrl;

		try {
			UrlPair urlPair1 = urlShortenerService.generateShortUrl(longUrl);
			UrlPair urlPair2 = urlShortenerService.generateShortUrl(longUrl);
			Assert.assertTrue(urlPair2.equals(urlPair1));
		} catch (GenerateShortUrlFailureException e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testResolveShortUrl() {
		String longUrl = testLongUrl;

		UrlPair urlPair = null;
		try {
			urlPair = urlShortenerService.generateShortUrl(longUrl);
		} catch (GenerateShortUrlFailureException e) {
			Assert.assertTrue(false);
			System.exit(0);
		}

		try {
			UrlPair resolvedUrlPair = urlShortenerService.resolveShortUrl(urlPair.getShortUrl());
			Assert.assertEquals(longUrl, resolvedUrlPair.getLongUrl());
		} catch (ResolveShortUrlFailureException e) {
			Assert.assertTrue(false);
			System.exit(0);
		}
	}
}
