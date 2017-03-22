package com.general.mediaplayer.geapp;

import android.app.Application;

import com.danikula.videocache.HttpProxyCacheServer;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by mac on 19/03/2017.
 */

public class GEApplication extends Application {

    private HttpProxyCacheServer proxy;

    @Override
    public void onCreate() {
        super.onCreate();

        // Increase Picasso Cache Size To 250 MB
        Picasso picasso =  new Picasso.Builder(this).downloader(new OkHttpDownloader(getCacheDir(), 250000000)).build();
        Picasso.setSingletonInstance(picasso);
    }

}
