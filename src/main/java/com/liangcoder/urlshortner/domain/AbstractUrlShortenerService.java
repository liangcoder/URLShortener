package com.liangcoder.urlshortner.domain;

import java.util.Set;

import com.liangcoder.urlshortner.exception.GenerateShortUrlFailureException;
import com.liangcoder.urlshortner.exception.PersistUrlPairException;
import com.liangcoder.urlshortner.exception.ResolveShortUrlFailureException;

public abstract class AbstractUrlShortenerService implements UrlShortenerService {

	private Set<UrlShortenAlgorithm> urlShortenAlgorithms;

	@Override
	public UrlPair generateShortUrl(String longUrl) throws GenerateShortUrlFailureException {
		if (longUrl == null || longUrl.length() == 0) {
			throw new IllegalArgumentException("[generateShortUrl] longUrl is empty.");
		}

		/* If the UrlPair of the given longUrl has already existed, return it directly. */
		UrlPair existedUrlPair = findUrlPairByLongUrl(longUrl);
		if (existedUrlPair != null) {
			return existedUrlPair;
		}

		/* Check the algorithms availability. */
		Set<UrlShortenAlgorithm> algorithms = getUrlShortenAlgorithms();
		if (algorithms == null || algorithms.isEmpty()) {
			throw new GenerateShortUrlFailureException("[[generateShortUrl]] UrlShortenAlgorithms is empty.");
		}

		for (UrlShortenAlgorithm algorithm : algorithms) {
			String generatedShortUrl = algorithm.generateShortUrl(longUrl);

			/* Check generatedShortUrl exist or not */
			UrlPair checkUrlPair = findUrlPairByShortUrl(generatedShortUrl);
			if (checkUrlPair != null) {
				if (longUrl.equals(checkUrlPair.getLongUrl())) {
					return checkUrlPair;
				}
			} else {
				UrlPair newUrlPair = new UrlPair(generatedShortUrl, longUrl);
				try {
					persistUrlPair(newUrlPair);
					return newUrlPair;
				} catch (PersistUrlPairException e) {
					throw new GenerateShortUrlFailureException(e.getMessage(), e.getCause());
				}
			}
		}

		/* All possible generated urlPair have already existed, throw a failure exception */
		GenerateShortUrlFailureException ex = new GenerateShortUrlFailureException();
		ex.setLongUrl(longUrl);
		throw ex;
	}

	public UrlPair resolveShortUrl(String shortUrl) throws ResolveShortUrlFailureException {
		if (shortUrl == null || shortUrl.length() == 0) {
			throw new IllegalArgumentException("[resolveShortUrl] shortUrl is empty.");
		}

		UrlPair urlPair = findUrlPairByShortUrl(shortUrl);
		if (urlPair != null) {
			return urlPair;
		} else {
			ResolveShortUrlFailureException ex = new ResolveShortUrlFailureException();
			ex.setShortUrl(shortUrl);
			throw ex;
		}
	}

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

	public void setUrlShortenAlgorithms(Set<UrlShortenAlgorithm> urlShortenAlgorithms) {
		this.urlShortenAlgorithms = urlShortenAlgorithms;
	}

	public Set<UrlShortenAlgorithm> getUrlShortenAlgorithms() {
		return urlShortenAlgorithms;
	}
}
