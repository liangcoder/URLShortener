package com.liangcoder.urlshortner.impl;

import org.junit.Assert;
import org.junit.Test;

import com.liangcoder.urlshortner.algorithm.MathRandomUrlShortenAlgorithm;
import com.liangcoder.urlshortner.domain.UrlShortenAlgorithm;

public class MathRandomUrlShortenAlgorithmTest {

  @Test
  public void testGenerateShortUrl() {
    int shortUrlLength = 8;

    UrlShortenAlgorithm algo = new MathRandomUrlShortenAlgorithm(8);

    String longUrl =
        "https://github.com/martonsereg/urlchopper/blob/master/src/main/java/com/epam/urlchopper/service/simple/SimpleUrlService.java";
    String shortUrl = algo.generateShortUrl(longUrl);
    Assert.assertEquals(shortUrlLength, shortUrl.length());
  }
}
