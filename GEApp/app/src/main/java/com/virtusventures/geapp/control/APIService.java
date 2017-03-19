package com.virtusventures.geapp.control;

import android.util.Log;

import com.google.gson.JsonObject;
import com.virtusventures.geapp.model.Constants;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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
}
