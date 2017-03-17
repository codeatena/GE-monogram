package com.virtusventures.geapp.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.virtusventures.geapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CookingActivity extends BaseActivity {

    @BindView(R.id.cookingfirst_btn)
    Button firstBtn;

    @BindView(R.id.cookingsecond_btn)
    Button secondBtn;

    @BindView(R.id.cookingthird_btn)
    Button thirdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        ButterKnife.bind(this);

    }

    public void onBack(View view){

        finish();
    }

    public void onFirstClick(View view)
    {
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCooking));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

    }

    public void onSecondClick(View view)
    {
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCooking));
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
    }

    public void onThirdClick(View view)
    {
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorCooking));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
    }
}
