/**
 * 
 */
package com.latestnews.parser;

import java.io.InputStream;
import java.util.List;

import com.latestnews.model.FeedItem;

/**
 * Parser to parse news rss feed
 * 
 * @author rohit
 *
 */
public class FeedParser implements IFeedParser{
	
	/**
	 * Parse feed from string data
	 */
	@Override
	public List<FeedItem> parseNewsFeed(String data) {
		
		return null;
	}

	/**
	 * Parse feed from input stream
	 */
	@Override
	public List<FeedItem> parseNewsFeed(InputStream in) {
		
		return null;
	}
}
