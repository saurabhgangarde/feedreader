/**
 * 
 */
package com.latestnews.service;

import java.util.List;

import com.latestnews.model.FeedItem;
import com.latestnews.parser.IFeedParser;

/**
 * @author rohit
 *
 */
public class ServerFeedServiceImpl implements IFeedService {

	//TODO Currently Hardcoded, but can be feed through configuration
	private static final String URL = "http://www.fifa.com/newscentre/photo/rss.xml";
	
	/**
	 * http service to fetch the string/inputstream. Injected into this class
	 */
	private IHttpService httpService = null;
	
	/**
	 * http service to fetch the string/inputstream. Injected into this class
	 */
	private IFeedParser feedParser = null;
	
	/* (non-Javadoc)
	 * @see com.latestnews.service.IFeedService#fetchLatestFeeds()
	 */
	@Override
	public List<FeedItem> fetchLatestFeeds() {
		String data= httpService.fetchXMLResponse(URL);
		System.out.println("FifaLatestNews : XML Data = "+data);
		
		return  feedParser.parseNewsFeed(data);
	}

	@Override
	public void setHttpService(IHttpService httpService) {
		this.httpService=httpService;
		
	}

	@Override
	public void setFeedParser(IFeedParser feedParser) {
		this.feedParser=feedParser;
		
	}

}
