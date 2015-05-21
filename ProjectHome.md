Application gets latest news from RSS feed of fifa news feed. This application showcases a framework which solves the problem of fetching Images requested by a ListAdapter.

The Framework is composed of various parts which includes
1. A Blocking Queue where List Adapter pushes for requests
2. A Engine which processes the request from the Queue, fetches images and provides the Image back to Image Adapter
3. A Generic Image Cache (which can be implemented in which ever manner you want)


![http://feedreader.googlecode.com/files/Screenshot.png](http://feedreader.googlecode.com/files/Screenshot.png)