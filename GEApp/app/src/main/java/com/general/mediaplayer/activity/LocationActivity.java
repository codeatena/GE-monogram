package com.general.mediaplayer.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.general.mediaplayer.R;
import com.general.mediaplayer.model.Global;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.location_edit)
    EditText locationEdit;

    @BindView(R.id.location_savebtn)
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ButterKnife.bind(this);

        Typeface copperplateGothicLight = Typeface.createFromAsset(getAssets(), "fonts/Gotham Medium.otf");
        saveBtn.setTypeface(copperplateGothicLight);
        locationEdit.setTypeface(copperplateGothicLight);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (locationEdit.getText().length() == 0)
                {
                    Toast.makeText(LocationActivity.this ,"Please type location !!" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                Global.location = locationEdit.getText().toString();

                Intent mainIntent = new Intent(LocationActivity.this, LandingActivity.class);
                LocationActivity.this.startActivity(mainIntent);
                LocationActivity.this.finish();
            }
        });
    }
}
