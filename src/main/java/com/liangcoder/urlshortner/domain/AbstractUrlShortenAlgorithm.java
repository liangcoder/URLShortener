package com.liangcoder.urlshortner.domain;

public abstract class AbstractUrlShortenAlgorithm implements UrlShortenAlgorithm {

  private int shortUrlLength;

  public AbstractUrlShortenAlgorithm() {}

  public AbstractUrlShortenAlgorithm(int shortUrlLength) {
    setShortUrlLength(shortUrlLength);
  }

  public int getShortUrlLength() {
    return shortUrlLength;
  }

  public void setShortUrlLength(int shortUrlLength) {
    this.shortUrlLength = shortUrlLength;
  }
}
