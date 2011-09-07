/**
 * 
 */
package com.latestnews.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import com.latestnews.model.FeedItem;

/**
 * @author rohit
 *
 */
public class FeedParserTest extends TestCase {
	private final String TEST_FILE_PATH = "test//data.xml";
	
	/**
	 * Test method for {@link com.latestnews.parser.FeedParser#parseNewsFeed()}.
	 * @throws IOException 
	 */
	public void testParseNewsFeed() throws IOException {
		
		IFeedParser feedParser = new FeedParser();
		InputStream in = new FileInputStream(new File (TEST_FILE_PATH));
		URL url = new URL("http://www.fifa.com/newscentre/photo/rss.xml");
		
		List<FeedItem> feedItems =  feedParser.parseNewsFeed(url.openStream());
		
		assertNotNull(feedItems);
		assertEquals(feedItems.size(), 200);
	}

}
