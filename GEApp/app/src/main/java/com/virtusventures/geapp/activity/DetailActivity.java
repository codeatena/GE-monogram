package com.virtusventures.geapp.activity;

import android.os.Bundle;
import android.view.View;

import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.virtusventures.geapp.R;
import com.virtusventures.geapp.model.Constants;
import com.virtusventures.geapp.model.MediaModel;

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
        Picasso.with(this)
                .load(model.photoPath)
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

        /*
        if (model.bIsPhoto)
        {
            Picasso.with(this)
                    .load(model.photoPath)
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
        else
        {
            videoView.setVisibility(View.VISIBLE);
            //HttpProxyCacheServer proxy = GEApplication.getProxy(this);
            //String proxyUrl = proxy.getProxyUrl(model.videooPath);

            Log.d("video path" ,model.videooPath);
            videoView.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared() {

                    videoView.start();
                }
            });
            videoView.setVideoURI(Uri.parse(model.videooPath));
        }*/

    }

    public void onBack(View view){

        finish();
    }
}
