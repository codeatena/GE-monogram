package com.general.mediaplayer.gegraphite.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.general.mediaplayer.gegraphite.R;
import com.general.mediaplayer.gegraphite.adapter.MediaAdapter;
import com.general.mediaplayer.gegraphite.control.APICallback;
import com.general.mediaplayer.gegraphite.control.APIService;
import com.general.mediaplayer.gegraphite.model.Constants;
import com.general.mediaplayer.gegraphite.model.MediaModel;
import com.general.mediaplayer.gegraphite.model.StorageUtil;
import com.general.mediaplayer.gegraphite.view.RecyclerItemClickListener;
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

        if (APIService.hasInternetConnection(this))
            loadAPI(url);
        else
            getLocal(url);

    }

    @Override
    protected void onDestroy() {

        if (subscription != null) subscription.unsubscribe();
        super.onDestroy();
    }

    private void loadAPI(final String url)
    {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading ...");
        pd.show();

        subscription = APIService.getInstance().getAPI(url);
        APIService.getInstance().setOnCallback(new APICallback() {
            @Override
            public void doNext(JsonObject jsonObject) {

                pd.dismiss();

                JsonArray jsonArray;
                if (jsonObject.get("api").getAsJsonObject().get("photos") instanceof JsonObject)
                {
                    JsonObject jsonObject1 = jsonObject.get("api").getAsJsonObject().get("photos").getAsJsonObject();
                    if (jsonObject1.get("photo") instanceof JsonArray)
                    {
                        jsonArray = jsonObject1.get("photo").getAsJsonArray();
                        for (int i = 0 ; i < jsonArray.size() ; i ++)
                        {
                            String photo = jsonArray.get(i).getAsString();
                            getPhoto(photo ,url);
                        }
                    }
                    else
                    {
                        String photo = jsonObject1.get("photo").getAsString();
                        getPhoto(photo ,url);
                    }

                }

                if (jsonObject.get("api").getAsJsonObject().get("videos") instanceof JsonObject)
                {
                    JsonObject jsonObject1 = jsonObject.get("api").getAsJsonObject().get("videos").getAsJsonObject();
                    if (jsonObject1.get("video") instanceof JsonArray)
                    {
                        jsonArray = jsonObject1.get("video").getAsJsonArray();
                        for (int i = 0 ; i < jsonArray.size() ; i ++)
                        {
                            String photo = jsonArray.get(i).getAsString();
                            getVideo(photo ,url);
                        }
                    }
                    else
                    {
                        String photo = jsonObject1.get("video").getAsString();
                        getVideo(photo ,url);
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

    public void getLocal(String url)
    {
        String folder = Constants.urlfolderMap.get(url);
        String dirPath = StorageUtil.getStorageDirectory(this)  + folder + "/photo";
        File f = new File(dirPath);
        if (!f.exists()) return;
        File[] listFile = f.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++)
            {
                String fileName = listFile[i].getName();
                if (fileName.substring(0 ,1).equals(".")) continue;
                getPhoto(fileName ,url);
            }
        }

        dirPath = StorageUtil.getStorageDirectory(this)  + folder + "/video";
        f = new File(dirPath);
        if (!f.exists()) return;
        listFile = f.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++)
            {
                String fileName = listFile[i].getName();
                if (fileName.substring(0 ,1).equals(".")) continue;
                getVideo(fileName ,url);
            }
        }
        adapter.notifyDataSetChanged();

    }

    public void getVideo(String photo ,String url)
    {
        String folder = Constants.urlfolderMap.get(url);
        if (photo.length() > 0)
        {
            String encodeStr = Uri.encode(photo);
            String subStr = encodeStr.replaceAll(".mp4" ,"");
            int index = adapter.getMedia(subStr);
            if (index != -1)
            {
                MediaModel model = adapter.getMedia(index);
                model.bIsPhoto = false;
                model.videooPathFromSD =  folder + "/video/" + photo;
                model.videooPathFromServer =  Constants.AMAZON_FOLDER + folder + "/video/"  + encodeStr;
                model.photoPathFromServer = Constants.AMAZON_FOLDER + folder + "/photo/" +  encodeStr.replaceAll(".mp4" ,".jpg");
                File file = new File(StorageUtil.getStorageDirectory(this)  + model.videooPathFromSD);
                if(!file.exists())
                    model.isExistVideo = false;
                else
                    model.isExistVideo = true;
            }
        }
    }

    public void getPhoto(String photo ,String url)
    {
        String folder = Constants.urlfolderMap.get(url);
        if (photo.length() > 0)
        {
            String encodeStr = Uri.encode(photo);
            MediaModel model = new MediaModel(folder + "/photo/" + photo , true);
            model.photoPathFromServer =  Constants.AMAZON_FOLDER + folder + "/photo/" + encodeStr;
            // check if exist photo file in SD card
            File file = new File(StorageUtil.getStorageDirectory(this)  + model.photoPathFromSD);
            if(!file.exists())
                model.isExistPhoto = false;
            else
                model.isExistPhoto = true;
            adapter.addMedia(model);
        }
    }

    public void onBack(View view){

        finish();
    }

}
