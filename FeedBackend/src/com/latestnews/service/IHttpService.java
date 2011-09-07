/**
 * 
 */
package com.latestnews.service;

/**
 * HttpService (platform independent) to fetch XML feed from a URL
 * @author rohit
 *
 */
public interface IHttpService {

	/**
	 * Fetches XML from a URL - typically RSS Feed
	 * @param url The url to read xml from
	 * @return String if able to read data, else returns null
	 */
	public String fetchXMLResponse(String url);
	
	
	
}
