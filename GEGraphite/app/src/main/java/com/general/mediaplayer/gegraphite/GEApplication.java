package com.general.mediaplayer.gegraphite;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by mac on 19/03/2017.
 */

public class GEApplication extends Application {

    private HttpProxyCacheServer proxy;
    private Tracker mTracker;

    private static final String PROPERTY_ID = "UA-96083093-1";

    @Override
    public void onCreate() {
        super.onCreate();

        // Increase Picasso Cache Size To 250 MB
        Picasso picasso =  new Picasso.Builder(this).downloader(new OkHttpDownloader(getCacheDir(), 250000000)).build();
        Picasso.setSingletonInstance(picasso);

    }

    public static HttpProxyCacheServer getProxy(Context context) {
        GEApplication app = (GEApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        //return new HttpProxyCacheServer(this);
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
                .build();
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(PROPERTY_ID);
        }
        return mTracker;
    }

}
