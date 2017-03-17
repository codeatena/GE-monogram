package com.virtusventures.geapp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;

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

public class LandingActivity extends AppCompatActivity {

    Subscription subscription;

    @BindView(R.id.centerlogo_btn)
    ImageButton centerlogoBtn;

    @BindView(R.id.cooking_btn)
    ImageButton cookingBtn;

    @BindView(R.id.cleaning_btn)
    ImageButton cleaningBtn;

    @BindView(R.id.refrigeration_btn)
    ImageButton refrigerationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        ButterKnife.bind(this);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading ...");
        pd.show();

        subscription = APIService.getInstance().getAPI(Constants.landing);
        APIService.getInstance().setOnCallback(new APICallback() {
            @Override
            public void doNext(JsonObject jsonObject) {

                pd.dismiss();

                String photoPath = ParseJson.getPhotoPath(jsonObject);
                Picasso.with(LandingActivity.this)
                        .load(photoPath + "cooking.jpg")
                        .into(cookingBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "cleaning.jpg")
                        .into(cleaningBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "refridgeration.jpg")
                        .into(refrigerationBtn);

                Picasso.with(LandingActivity.this)
                        .load(photoPath + "logo_for_center.png")
                        .into(centerlogoBtn);
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
}
