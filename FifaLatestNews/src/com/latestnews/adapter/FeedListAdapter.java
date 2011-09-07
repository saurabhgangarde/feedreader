/**
 * 
 */
package com.latestnews.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.latestnews.R;
import com.latestnews.model.FeedItem;

/**
 * FeedListAdapter adapts List<FeedItem> to ListView
 * @author rohit
 *
 */
public class FeedListAdapter extends BaseAdapter {

	private List<FeedItem> feeds;
	private Context context;
	private LayoutInflater inflater;
	
	public FeedListAdapter(Context context, List<FeedItem> feeds){
		this.context= context;
		this.feeds=feeds;
		this.inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
		
		return feeds.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	public Object getItem(int index) {
		
		return feeds.get(index);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	public long getItemId(int index) {
		
		return index;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		if(null==convertView){
			convertView = inflater.inflate(R.layout.fifafeedentry, null);
		}
		FeedItem item = (FeedItem)getItem(position);
		ImageView imageview = (ImageView)convertView.findViewById(R.id.image);
		TextView textView = (TextView)convertView.findViewById(R.id.title); 

		Log.d(FeedListAdapter.class.getName(),"Title "+item.getTitle());
		textView.setText(item.getTitle());
		
		//TODO Use Threading/AsyncTask/CacheManager to set the Image
		
		return convertView;
	}

}
