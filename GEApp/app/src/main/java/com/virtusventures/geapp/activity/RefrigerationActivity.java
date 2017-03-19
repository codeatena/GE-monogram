package com.virtusventures.geapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.virtusventures.geapp.R;
import com.virtusventures.geapp.model.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RefrigerationActivity extends BaseActivity {

    @BindView(R.id.refrigerationfirst_btn)
    Button firstBtn;

    @BindView(R.id.refrigerationsecond_btn)
    Button secondBtn;

    @BindView(R.id.refrigerationthird_btn)
    Button thirdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_refrigeration);
        ButterKnife.bind(this);
    }

    public void onBack(View view){

        finish();
    }

    public void onFirstClick(View view)
    {
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.fullsizerefrigeration);
        startActivity(intent);
    }

    public void onSecondClick(View view)
    {
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.builtinrefrigeration);
        startActivity(intent);
    }

    public void onThirdClick(View view)
    {
        thirdBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRefrigeration));
        secondBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
        firstBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));

        Intent intent = new Intent(this , MediaListActivity.class);
        intent.putExtra(Constants.MEDIA_URL, Constants.freestanding);
        startActivity(intent);
    }
}
