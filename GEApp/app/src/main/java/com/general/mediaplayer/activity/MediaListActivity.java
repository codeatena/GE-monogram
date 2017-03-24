package com.general.mediaplayer.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.general.mediaplayer.adapter.MediaAdapter;
import com.general.mediaplayer.control.APICallback;
import com.general.mediaplayer.control.APIService;
import com.general.mediaplayer.R;
import com.general.mediaplayer.model.Constants;
import com.general.mediaplayer.model.MediaModel;
import com.general.mediaplayer.model.ParseJson;
import com.general.mediaplayer.view.RecyclerItemClickListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class MediaListActivity extends BaseActivity {

    private static final String TAG = "MediaListActivity";
    Subscription subscription;

    @BindView(R.id.media_recyclerView)
    RecyclerView mediaRecyclerView;

    MediaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_media_list);
        ButterKnife.bind(this);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mediaRecyclerView.setLayoutManager(gridLayoutManager);
        mediaRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mediaRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        Intent intent = new Intent(MediaListActivity.this , DetailActivity.class);
                        intent.putExtra(Constants.MEDIA_URL, adapter.getMedia(position));
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        adapter = new MediaAdapter(this);
        mediaRecyclerView.setAdapter(adapter);

        String url = getIntent().getStringExtra(Constants.MEDIA_URL);
        Log.d(TAG ,url);
        loadAPI(url);

    }

    @Override
    protected void onDestroy() {

        if (subscription != null) subscription.unsubscribe();
        super.onDestroy();
    }

    private void loadAPI(String url)
    {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading ...");
        pd.show();

        subscription = APIService.getInstance().getAPI(url);
        APIService.getInstance().setOnCallback(new APICallback() {
            @Override
            public void doNext(JsonObject jsonObject) {

                pd.dismiss();

                String photoPath = ParseJson.getPhotoPath(jsonObject);
                JsonArray jsonArray = jsonObject.get("api").getAsJsonObject().get("photos").getAsJsonObject().get("photo").getAsJsonArray();
                for (int i = 0 ; i < jsonArray.size() ; i ++)
                {
                    //Log.d(TAG ,jsonArray.get(i).getAsString());
                    String encodeStr = Uri.encode(jsonArray.get(i).getAsString());
                    MediaModel model = new MediaModel(photoPath + jsonArray.get(i).getAsString() , true);
                    model.photoPathFromServer =  ParseJson.getPhotoPathServer(jsonObject) + encodeStr;
                    // check if exist photo file in SD card
                    File file = new File(Constants.SD_PATH  + model.photoPathFromSD);
                    if(!file.exists())
                        model.isExistPhoto = false;
                    else
                        model.isExistPhoto = true;

                    adapter.addMedia(model);
                }

                String videoPath = ParseJson.getVideoPath(jsonObject);
                if (jsonObject.get("api").getAsJsonObject().get("videos") instanceof JsonObject)
                {
                    jsonArray = jsonObject.get("api").getAsJsonObject().get("videos").getAsJsonObject().get("video").getAsJsonArray();
                    for (int i = 0 ; i < jsonArray.size() ; i ++)
                    {
                        String encodeStr = Uri.encode(jsonArray.get(i).getAsString());
                        String subStr = jsonArray.get(i).getAsString().replaceAll(".mp4" ,"");
                        int index = adapter.getMedia(subStr);
                        if (index != -1)
                        {
                            MediaModel model = adapter.getMedia(index);
                            model.bIsPhoto = false;
                            model.videooPathFromSD =  videoPath + jsonArray.get(i).getAsString();
                            model.videooPathFromServer =  ParseJson.getVideoPathServer(jsonObject) + encodeStr;
                            model.photoPathFromServer = ParseJson.getPhotoPathServer(jsonObject) + encodeStr.replaceAll(".mp4" ,".jpg");
                            File file = new File(Constants.SD_PATH  + model.videooPathFromSD);
                            if(!file.exists())
                                model.isExistVideo = false;
                            else
                                model.isExistVideo = true;
                        }
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void doNext(JsonArray jsonObject)
            {

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

    public void onBack(View view){

        finish();
    }

}
