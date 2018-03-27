package com.general.mediaplayer.gegraphite.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;

import com.general.mediaplayer.gegraphite.R;
import com.general.mediaplayer.gegraphite.control.APIService;
import com.general.mediaplayer.gegraphite.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.cooking_btn)
    ImageButton cookingBtn;

    @BindView(R.id.cleaning_btn)
    ImageButton cleaningBtn;

    @BindView(R.id.refrigeration_btn)
    ImageButton refrigerationBtn;

    @BindView(R.id.sweetreward_btn)
    ImageButton sweetrewardBtn;

    @BindView(R.id.experiencemonogram_btn)
    ImageButton experiencemonogramBtn;

    @BindView(R.id.inspiredkitchen_btn)
    ImageButton inspiredkitchenBtn;

    @BindView(R.id.graphite_btn)
    ImageButton graphiteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);

        sweetrewardBtn.setOnClickListener(this);
        inspiredkitchenBtn.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        checkPermission();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    123);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)     {


                } else {

                    checkPermission();
                }
            }
        }
    }

    public void onCooking(View view)
    {

        Intent intent =  new Intent(this , CookingActivity.class);
        startActivity(intent);
    }

    public void onCleaning(View view)
    {
        Intent intent =  new Intent(this , CleaningActivity.class);
        startActivity(intent);
    }

    public void onRefrigeration(View view)
    {
        Intent intent =  new Intent(this , RefrigerationActivity.class);
        startActivity(intent);
    }

    public void onSweetRewards(View view)
    {
        Intent intent =  new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.sweetreward);
        startActivity(intent);
    }

    public void onGraphite(View view)
    {
        Intent intent =  new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.graphite);
        startActivity(intent);
    }

    public void onExperienceMonogram(View view)
    {
        APIService.trakCategory(this ,Constants.experiencemonogram);

        Intent intent =  new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.experiencemonogram);
        startActivity(intent);
    }

    public void onInspiredKitchen(View view)
    {
        Intent intent =  new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.inspiredkitchen);
        startActivity(intent);
    }

    private long mLastClickTIme = 0;
    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTIme < 500)
        {
            // show CSR app
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.general.mediaplayer.csr");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }

            return;
        }

        mLastClickTIme = SystemClock.elapsedRealtime();

        if (v == sweetrewardBtn)
        {
            APIService.trakCategory(this ,Constants.sweetreward);

            Intent intent =  new Intent(this , MediaListActivity.class);
            intent.putExtra(Constants.MEDIA_URL, Constants.sweetreward);
            startActivity(intent);
        }

        if (v == inspiredkitchenBtn)
        {
            APIService.trakCategory(this ,Constants.inspiredkitchen);

            Intent intent =  new Intent(this , MediaListActivity.class);
            intent.putExtra(Constants.MEDIA_URL, Constants.inspiredkitchen);
            startActivity(intent);
        }
    }
}
