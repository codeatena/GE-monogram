package com.general.mediaplayer.geapp.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.general.mediaplayer.geapp.R;
import com.general.mediaplayer.geapp.control.APIService;
import com.general.mediaplayer.geapp.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CleaningActivity extends BaseActivity {

    @BindView(R.id.cleaningfirst_btn)
    Button firstBtn;

    @BindView(R.id.cleaningsecond_btn)
    Button secondBtn;

    @BindView(R.id.cleaningthird_btn)
    Button thirdBtn;

    @BindView(R.id.cleaningfourth_btn)
    Button fourthBtn;

    @BindView(R.id.cleaningfifth_btn)
    Button fifthBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cleaning);
        ButterKnife.bind(this);

        Typeface copperplateGothicLight = Typeface.createFromAsset(getAssets(), "fonts/Gotham Medium.otf");
        firstBtn.setTypeface(copperplateGothicLight);
        secondBtn.setTypeface(copperplateGothicLight);
        thirdBtn.setTypeface(copperplateGothicLight);
        fourthBtn.setTypeface(copperplateGothicLight);
        fifthBtn.setTypeface(copperplateGothicLight);
    }

    public void onBack(View view){

        finish();
    }

    public void onFirstClick(View view)
    {
        hideAllButtons();
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));

        APIService.trakCategory(this ,Constants.dishwasher);
        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.dishwasher);
        startActivity(intent);

    }

    public void onSecondClick(View view)
    {
        hideAllButtons();
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));

        APIService.trakCategory(this ,Constants.dishwashercompactor);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.dishwashercompactor);
        startActivity(intent);
    }

    public void onThirdClick(View view)
    {
        hideAllButtons();
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));

        APIService.trakCategory(this ,Constants.energystardishwasher);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.energystardishwasher);
        startActivity(intent);
    }

    public void onFourthClick(View view)
    {
        hideAllButtons();
        fourthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));

        APIService.trakCategory(this ,Constants.undercounter);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.undercounter);
        startActivity(intent);
    }

    public void onFifthClick(View view)
    {
        hideAllButtons();
        fifthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));

        APIService.trakCategory(this ,Constants.selectionguide);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.selectionguide);
        startActivity(intent);
    }

    private void hideAllButtons()
    {
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        fourthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        fifthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
    }
}
