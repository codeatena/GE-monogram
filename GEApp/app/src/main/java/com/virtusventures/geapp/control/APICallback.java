package com.virtusventures.geapp.control;

import com.google.gson.JsonObject;

/**
 * Created by mac on 08/01/2017.
 */

public interface APICallback {

    public void doNext(JsonObject jsonObject);
    public void doCompleted();
    public void doError(Throwable e);

}
