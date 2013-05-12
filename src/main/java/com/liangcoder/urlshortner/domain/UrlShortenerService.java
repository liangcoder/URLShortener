package com.liangcoder.urlshortner.domain;

import com.liangcoder.urlshortner.exception.GenerateShortUrlFailureException;
import com.liangcoder.urlshortner.exception.ResolveShortUrlFailureException;

public interface UrlShortenerService {

	public abstract UrlPair generateShortUrl(String longUrl) throws GenerateShortUrlFailureException;

	public abstract UrlPair resolveShortUrl(String shortUrl) throws ResolveShortUrlFailureException;

}
