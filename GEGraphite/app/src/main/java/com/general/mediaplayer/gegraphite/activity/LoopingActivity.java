package com.general.mediaplayer.gegraphite.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.general.mediaplayer.gegraphite.R;

public class LoopingActivity extends AppCompatActivity {

    TextView tvBeginButton;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_looping);
        tvBeginButton = (TextView) findViewById(R.id.begin_button_textView);
        videoView = (VideoView) findViewById(R.id.videoView);

        tvBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoopingActivity.this, LandingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                finish();
            }
        });

        videoView.setVisibility(View.VISIBLE);
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
        }
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.samplevideo7));
        videoView.requestFocus();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        videoView.start();
    }
}
