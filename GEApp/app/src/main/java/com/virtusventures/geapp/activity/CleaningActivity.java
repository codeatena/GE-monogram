package com.virtusventures.geapp.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.virtusventures.geapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CleaningActivity extends BaseActivity {

    @BindView(R.id.cleaningfirst_btn)
    Button firstBtn;

    @BindView(R.id.cleaningsecond_btn)
    Button secondBtn;

    @BindView(R.id.cleaningthird_btn)
    Button thirdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);

        ButterKnife.bind(this);
    }

    public void onBack(View view){

        finish();
    }

    public void onFirstClick(View view)
    {
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

    }

    public void onSecondClick(View view)
    {
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
    }

    public void onThirdClick(View view)
    {
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCleaning));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
    }
}
