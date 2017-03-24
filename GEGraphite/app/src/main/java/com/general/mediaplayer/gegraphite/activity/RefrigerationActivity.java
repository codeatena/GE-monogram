package com.general.mediaplayer.gegraphite.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.general.mediaplayer.gegraphite.R;
import com.general.mediaplayer.gegraphite.control.APIService;
import com.general.mediaplayer.gegraphite.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefrigerationActivity extends BaseActivity {

    @BindView(R.id.refrigerationfirst_btn)
    Button firstBtn;

    @BindView(R.id.refrigerationsecond_btn)
    Button secondBtn;

    @BindView(R.id.refrigerationthird_btn)
    Button thirdBtn;

    @BindView(R.id.refrigerationfourth_btn)
    Button fourthBtn;

    @BindView(R.id.refrigerationfifth_btn)
    Button fifthBtn;

    @BindView(R.id.refrigerationsixth_btn)
    Button sixthBtn;

    @BindView(R.id.refrigerationseventh_btn)
    Button seventhBtn;

    @BindView(R.id.refrigerationeightth_btn)
    Button eightthBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_refrigeration);
        ButterKnife.bind(this);

        Typeface copperplateGothicLight = Typeface.createFromAsset(getAssets(), "fonts/Gotham Light.otf");
        firstBtn.setTypeface(copperplateGothicLight);
        secondBtn.setTypeface(copperplateGothicLight);
        thirdBtn.setTypeface(copperplateGothicLight);
        fourthBtn.setTypeface(copperplateGothicLight);
        fifthBtn.setTypeface(copperplateGothicLight);
        sixthBtn.setTypeface(copperplateGothicLight);
        seventhBtn.setTypeface(copperplateGothicLight);
        eightthBtn.setTypeface(copperplateGothicLight);
    }

    public void onBack(View view){

        finish();
    }

    public void onFirstClick(View view)
    {
        hideAllButtons();
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.fullsizerefrigeration);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.fullsizerefrigeration);
        startActivity(intent);
    }

    public void onSecondClick(View view)
    {
        hideAllButtons();
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.builtinrefrigeration);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.builtinrefrigeration);
        startActivity(intent);
    }

    public void onThirdClick(View view)
    {
        hideAllButtons();
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.freestanding);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.freestanding);
        startActivity(intent);
    }

    public void onFourthClick(View view)
    {
        hideAllButtons();
        fourthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.bottomfreezer);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.bottomfreezer);
        startActivity(intent);
    }

    public void onFifthClick(View view)
    {
        hideAllButtons();
        fifthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.frenchdoor);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.frenchdoor);
        startActivity(intent);
    }

    public void onSixthClick(View view)
    {
        hideAllButtons();
        sixthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.integratedrefrigeration);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.integratedrefrigeration);
        startActivity(intent);
    }

    public void onSeventhClick(View view)
    {
        hideAllButtons();
        seventhBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

        APIService.trakCategory(this ,Constants.sidebyside);

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.sidebyside);
        startActivity(intent);
    }

    public void onEighthClick(View view)
    {
        hideAllButtons();
        eightthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));

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
        sixthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        seventhBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        eightthBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

    }
}
