package com.virtusventures.geapp.control;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by mac on 08/01/2017.
 */

public interface ApiEndpointInterface {

    @GET("meta.get")
    Observable<JsonObject> getAPI(@QueryMap(encoded=true) Map<String, String> options);

}
