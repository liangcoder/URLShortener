package com.liangcoder.urlshortner.domain;

import com.liangcoder.urlshortner.exception.PersistUrlPairException;

public interface ShortUrlRepository {

	/**
	 * Find the UrlPair based on the given shortUrl.
	 * 
	 * @return the UrlPair of the specific shortUrl, return null if it is not found.
	 */
	public abstract UrlPair findUrlPairByShortUrl(String shortUrl);

	/**
	 * Find the UrlPair based on the given longUrl.
	 * 
	 * @return the UrlPair of the specific longUrl, return null if it is not found.
	 */
	public abstract UrlPair findUrlPairByLongUrl(String longUrl);

	/**
	 * Persist a UrlPair.
	 * 
	 * @param urlPair
	 * @throws PersistUrlPairException
	 *             if the UrlPair is persisted failure.
	 */
	public abstract void persistUrlPair(UrlPair urlPair) throws PersistUrlPairException;
}
