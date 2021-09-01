package com.liangcoder.urlshortner.impl;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.liangcoder.urlshortner.algorithm.MathRandomUrlShortenAlgorithm;
import com.liangcoder.urlshortner.domain.ShortUrlRepository;
import com.liangcoder.urlshortner.domain.UrlPair;
import com.liangcoder.urlshortner.domain.UrlShortenAlgorithm;
import com.liangcoder.urlshortner.domain.UrlShortenerService;
import com.liangcoder.urlshortner.exception.GenerateShortUrlFailureException;
import com.liangcoder.urlshortner.exception.ResolveShortUrlFailureException;

public class UrlShortenerServiceTest {

  private static final String testLongUrl = "https://github.com/liangcoder";
  private UrlShortenerService urlShortenerService;

  @Before
  public void before() {
    UrlShortenerServiceDefaultImpl urlShortenerServiceDefaultImpl =
        new UrlShortenerServiceDefaultImpl();

    Set<UrlShortenAlgorithm> algorithms = new HashSet<>();
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
      Assert.assertEquals(urlPair2, urlPair1);
    } catch (GenerateShortUrlFailureException e) {
      Assert.fail();
    }
  }

  @Test
  public void testResolveShortUrl() {
    String longUrl = testLongUrl;

    UrlPair urlPair = null;
    try {
      urlPair = urlShortenerService.generateShortUrl(longUrl);
    } catch (GenerateShortUrlFailureException e) {
      Assert.fail();
      System.exit(0);
    }

    try {
      UrlPair resolvedUrlPair = urlShortenerService.resolveShortUrl(urlPair.getShortUrl());
      Assert.assertEquals(longUrl, resolvedUrlPair.getLongUrl());
    } catch (ResolveShortUrlFailureException e) {
      Assert.fail();
      System.exit(0);
    }
  }
}
