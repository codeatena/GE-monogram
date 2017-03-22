package com.general.mediaplayer.geapp.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.general.mediaplayer.geapp.R;
import com.general.mediaplayer.geapp.model.Constants;
import com.general.mediaplayer.geapp.model.MediaModel;
import com.github.chrisbanes.photoview.PhotoView;

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
            String path =  Constants.SD_PATH  + model.photoPath;
            Bitmap bmp = BitmapFactory.decodeFile(path);
            if (bmp != null)
                detailImageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 1000, 1000, true));

        }
        else
        {
            videoView.setVisibility(View.VISIBLE);
            Log.d("video path" ,model.videooPath);
            videoView.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared() {

                    videoView.start();
                }
            });

            String path =  "file:///" + Constants.SD_PATH + model.videooPath;
            videoView.setVideoPath(path);
        }
    }

    public void onBack(View view){

        finish();
    }
}
