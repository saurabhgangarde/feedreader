/**
 * 
 */
package com.latestnews.cache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

/**
 * Loads the Images Queued in this class 
 * @author rohit
 * 
 */
public class QueuedImageLoader implements ImageLoader {

	/**
	 * Delegating caching to Image Cache
	 */
	private ImageCache imageCache = null;
	/**
	 * A Blocking queue to queue up PhoneToLoad Task
	 */
	private BlockingQueue<PhotoToLoad> photoQueue = new LinkedBlockingQueue<PhotoToLoad>();

	/**
	 * Default Constructor. Also starts the thread which polls the queue to
	 * fetch the image
	 */
	public QueuedImageLoader() {
		Runnable runnable = new Runnable() {

			public void run() {

				try {

					while (true) {
						// Blocking call
						final PhotoToLoad photoToLoadTask = photoQueue.take();
						Log.d("FifaLatestNews", "Loaded "+photoToLoadTask );
						final Bitmap bitmap = getBitmap(photoToLoadTask
								.getUrl());
						photoToLoadTask.getImageView().post(new Runnable() {

							public void run() {
								photoToLoadTask.getImageView().setImageBitmap(
										bitmap);

							}
						});

					}
				} catch (InterruptedException iex) {
					Log.d("FifaLastestNews","Caught Exception while loading queue bitmap "+iex);
				} finally {

				}

			}
		};
		Thread imageLoadThread = new Thread(runnable);
		imageLoadThread.start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.latestnews.cache.ImageLoader#queueImage(java.lang.String,
	 * android.widget.ImageView)
	 */
	public void queueImage(final String url, final ImageView imageView) {
		// FIXME we are not checking for null for imagecache here
		final Bitmap cachedBitMap = imageCache.get(url);
		if (null != cachedBitMap) {
			// Use UI thread to set the Image on Image View
			imageView.post(new Runnable() {

				public void run() {
					imageView.setImageBitmap(cachedBitMap);

				}
			});
		} else {
			photoQueue.add(new PhotoToLoad(url, imageView));
		}

	}

	public void setImageCache(ImageCache imageCache) {
		this.imageCache = imageCache;

	}

	/**
	 * 
	 * @param url
	 * @return Bitmap read from url
	 */
	private Bitmap getBitmap(String url) {
		Bitmap bitmap = null;
		try {

			URL imageUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			InputStream is = conn.getInputStream();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 3;
			return BitmapFactory.decodeStream(is, null,
					options); 

		} catch (Exception ex) {
			Log.d("FifaLatestNews",
					"Caught Exception while reading bitmap from url " + url);

		}
		return bitmap;
	}

}
