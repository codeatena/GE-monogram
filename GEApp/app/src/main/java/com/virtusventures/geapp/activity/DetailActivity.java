package com.virtusventures.geapp.activity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.virtusventures.geapp.R;
import com.virtusventures.geapp.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.detail_imageview)
    PhotoView detailImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra(Constants.MEDIA_URL);
        Picasso.with(this)
                .load(url)
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
