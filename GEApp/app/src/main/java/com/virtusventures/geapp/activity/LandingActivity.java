package com.virtusventures.geapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.virtusventures.geapp.R;
import com.virtusventures.geapp.control.APICallback;
import com.virtusventures.geapp.control.APIService;
import com.virtusventures.geapp.model.Constants;
import com.virtusventures.geapp.model.ParseJson;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class LandingActivity extends BaseActivity{

    Subscription subscription;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_landing);

        ButterKnife.bind(this);
        loadAPI();
    }

    private void loadAPI()
    {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading ...");
        pd.show();

        subscription = APIService.getInstance().getBatchAPI(Constants.landing ,Constants.bottomtab);
        APIService.getInstance().setOnCallback(new APICallback() {
            @Override
            public void doNext(JsonObject jsonObject) {

            }

            @Override
            public void doNext(JsonArray jsonObject)
            {
                pd.dismiss();

                JsonObject jsonObject1 = jsonObject.get(0).getAsJsonObject();
                String photoPath = ParseJson.getPhotoPath(jsonObject1);
                Picasso.with(LandingActivity.this)
                        .load(photoPath + "cooking.jpg")
                        .into(cookingBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "cleaning.jpg")
                        .into(cleaningBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "refridgeration.jpg")
                        .into(refrigerationBtn);

                JsonObject jsonObject2 = jsonObject.get(1).getAsJsonObject();
                photoPath = ParseJson.getPhotoPath(jsonObject2);
                Picasso.with(LandingActivity.this)
                        .load(photoPath + "sweet_rewards_button.png")
                        .into(sweetrewardBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "experience_monogram_button.png")
                        .into(experiencemonogramBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "inspired_kitchens.png")
                        .into(inspiredkitchenBtn);
            }

            @Override
            public void doCompleted() {

            }

            @Override
            public void doError(Throwable e) {

                pd.dismiss();
                Log.e("error" ,e.getLocalizedMessage());

            }
        });

    }

    @Override
    protected void onDestroy() {

        if (subscription != null) subscription.unsubscribe();
        super.onDestroy();
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

    public void onExperienceMonogram(View view)
    {
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
}
