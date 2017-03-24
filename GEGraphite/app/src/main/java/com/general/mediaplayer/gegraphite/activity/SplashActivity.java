package com.general.mediaplayer.gegraphite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.general.mediaplayer.gegraphite.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @BindView(R.id.splash_leftbtn)
    Button leftBtn;

    @BindView(R.id.splash_rightbtn)
    Button rightBtn;

    boolean isLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (!isLocation)
                {
                    Intent mainIntent = new Intent(SplashActivity.this, LandingActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private long mLastClickTIme = 0;

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTIme < 500)
        {
            isLocation = true;
            Intent mainIntent = new Intent(SplashActivity.this, LocationActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }

        mLastClickTIme = SystemClock.elapsedRealtime();

    }
}
