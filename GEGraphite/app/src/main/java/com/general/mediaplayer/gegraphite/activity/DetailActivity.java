package com.general.mediaplayer.gegraphite.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.danikula.videocache.HttpProxyCacheServer;
import com.devbrackets.android.exomedia.listener.OnCompletionListener;
import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.general.mediaplayer.gegraphite.GEApplication;
import com.general.mediaplayer.gegraphite.R;
import com.general.mediaplayer.gegraphite.model.Constants;
import com.general.mediaplayer.gegraphite.model.MediaModel;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.detail_imageview)
    PhotoView detailImageView;

    @BindView(R.id.player_view)
    EMVideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        MediaModel model = (MediaModel)getIntent().getSerializableExtra(Constants.MEDIA_URL);
        if (model.bIsPhoto)
        {

            if (model.isExistPhoto)
            {
                String path =  Constants.SD_PATH  + model.photoPathFromSD;
                Picasso.with(this)
                        .load(new File(path))
                        .resize(1000, 1000)
                        .onlyScaleDown()
                        .centerInside()
                        .into(detailImageView);
            }
            else
            {
                Picasso.with(this)
                        .load(model.photoPathFromServer)
                        .resize(1000, 1000)
                        .onlyScaleDown()
                        .centerInside()
                        .into(detailImageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                            }
                        });
            }
        }
        else
        {
            videoView.setVisibility(View.VISIBLE);
            Log.d("video path" ,model.videooPathFromSD);
            videoView.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared() {

                    videoView.start();
                }
            });

            videoView.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion() {

                    videoView.restart();
                }
            });

            videoView.setVisibility( View.VISIBLE);
            if (model.isExistVideo)
            {
                // from sd card
                String path =  "file:///" + Constants.SD_PATH + model.videooPathFromSD;
                videoView.setVideoPath(path);
            }
            else
            {
                // from server
                HttpProxyCacheServer proxy = GEApplication.getProxy(this);
                String proxyUrl = proxy.getProxyUrl(model.videooPathFromServer);
                videoView.setVideoURI(Uri.parse(proxyUrl));
            }
        }
    }

    public void onBack(View view){

        finish();
    }
}
