package com.latestnews;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.latestnews.adapter.FeedListAdapter;
import com.latestnews.model.FeedItem;
import com.latestnews.service.IFeedService;
import com.latestnews.service.mock.DummyFeedServiceImpl;

public class FeedReader extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final IFeedService feedService = new DummyFeedServiceImpl();
        
        AsyncTask<String, Void, List<FeedItem>> asyncTask = new AsyncTask<String, Void, List<FeedItem>>(){

        	/* (non-Javadoc)
			 * @see android.os.AsyncTask#onPreExecute()
			 */
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}
			
			@Override
			protected List<FeedItem> doInBackground(String... params) {
				
				return feedService.fetchLatestFeeds();
			}
			
			/* (non-Javadoc)
			 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
			 */
			@Override
			protected void onPostExecute(List<FeedItem> feeds) {
				super.onPostExecute(feeds);
				setListAdapter(new FeedListAdapter(getApplicationContext(), feeds));
			}

			

			
        	
        };
        
        asyncTask.execute("http://www.fifa.com/newscentre/photo/rss.xml");
        
    }
}