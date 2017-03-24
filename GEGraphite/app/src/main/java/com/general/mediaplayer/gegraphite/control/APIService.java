package com.general.mediaplayer.gegraphite.control;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.general.mediaplayer.gegraphite.GEApplication;
import com.general.mediaplayer.gegraphite.model.Constants;
import com.general.mediaplayer.gegraphite.model.Global;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 08/01/2017.
 */

public class APIService {


    private static APIService ourInstance = new APIService();
    private ApiEndpointInterface apiService;
    private APICallback callback;

    public static APIService getInstance() {
        return ourInstance;
    }

    private APIService()
    {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        apiService = retrofit.create(ApiEndpointInterface.class);

    }

    public void setOnCallback(APICallback callback1)
    {
        callback = callback1;
    }

    public Subscription getAPI(String name)
    {
        Map<String, String> data = new HashMap<>();
        data.put("secretkey", "29245eb0655a62f37045e528da2f63fb");
        data.put("username", "matt");
        data.put("apiname", name);

        final Observable<JsonObject> call = apiService.getAPI(data);
        Subscription subscription = call
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {

                        callback.doCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        callback.doError(e);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        Log.d("response" ,jsonObject.toString());
                        callback.doNext(jsonObject);
                    }
                });

        return subscription;
    }

    public Subscription getBatchAPI(String name1 ,String name2)
    {
        Map<String, String> data1 = new HashMap<>();
        data1.put("secretkey", "29245eb0655a62f37045e528da2f63fb");
        data1.put("username", "matt");
        data1.put("apiname", name1);

        Map<String, String> data2 = new HashMap<>();
        data2.put("secretkey", "29245eb0655a62f37045e528da2f63fb");
        data2.put("username", "matt");
        data2.put("apiname", name2);

        final Observable<JsonObject> call1 = apiService.getAPI(data1);
        final Observable<JsonObject> call2 = apiService.getAPI(data2);

        Observable<JsonArray> combined = Observable.zip(call1, call2, new Func2<JsonObject, JsonObject, JsonArray>() {
            @Override
            public JsonArray call(JsonObject jsonObject1, JsonObject jsonObject2) {
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(jsonObject1);
                jsonArray.add(jsonObject2);
                return jsonArray;
            }
        });

        Subscription subscription = combined
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<JsonArray>() {
                    @Override
                    public void onCompleted() {

                        callback.doCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // cast to retrofit.HttpException to get the response code
                        callback.doError(e);
                    }

                    @Override
                    public void onNext(JsonArray jsonArray) {

                        Log.d("response" ,jsonArray.toString());
                        callback.doNext(jsonArray);
                    }
                });

        return subscription;
    }

    public static void trakCategory(AppCompatActivity activity ,String category)
    {
        Tracker t = ((GEApplication)activity.getApplication()).getDefaultTracker();
        t.send(new HitBuilders.EventBuilder()
                .setCategory(Global.location)
                .setAction("category")
                .setLabel(category)
                .build());
    }
}
