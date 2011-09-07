/**
 * 
 */
package com.latestnews.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.latestnews.model.FeedItem;

/**
 * Parser to parse news rss feed
 * 
 * @author rohit
 * 
 */
public class FeedParser implements IFeedParser {

	/**
	 * Parse feed from string data
	 */
	@Override
	public List<FeedItem> parseNewsFeed(String data) {
		List<FeedItem> feedItems = null;

		// sax stuff
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			

			XMLReader xr = sp.getXMLReader();
			//xr.setFeature(arg0, arg1)
			FeedItemHandler feedDataHandler = new FeedItemHandler();
			xr.setContentHandler(feedDataHandler);

			xr.parse(data);

			feedItems = feedDataHandler.getFeedItem();

		} catch (ParserConfigurationException pce) {
			System.out.println("sax parse error"+ pce);
		} catch (SAXException se) {
			System.out.println("sax error"+ se);
		} catch (IOException ioe) {
			System.out.println("sax parse io error"+ ioe);
		}

		return feedItems;
	}

	/**
	 * Parse feed from input stream
	 */
	@Override
	public List<FeedItem> parseNewsFeed(InputStream in) {
		List<FeedItem> feedItems = null;

		// sax stuff
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			XMLReader xr = sp.getXMLReader();

			FeedItemHandler feedDataHandler = new FeedItemHandler();
			xr.setContentHandler(feedDataHandler);

			xr.parse(new InputSource(in));

			feedItems = feedDataHandler.getFeedItem();

		} catch (ParserConfigurationException pce) {
			// Log.e("SAX XML", "sax parse error", pce);
		} catch (SAXException se) {
			// Log.e("SAX XML", "sax error", se);
		} catch (IOException ioe) {
			// Log.e("SAX XML", "sax parse io error", ioe);
		}

		return feedItems;
	}

}
