package com.virtusventures.geapp.model;

import com.google.gson.JsonObject;

/**
 * Created by mac on 17/03/2017.
 */

public class ParseJson {

    public static String getPhotoPath(JsonObject jsonObject)
    {
        JsonObject apiObject = jsonObject.get("api").getAsJsonObject();

        String link = apiObject.get("link").getAsString();
        String bucket = apiObject.get("bucket").getAsString();
        String name = apiObject.get("name").getAsString();
        String photodirectory = apiObject.get("photodirectory").getAsString();

        return String.format("%s/%s/%s/%s/" ,link ,bucket ,name ,photodirectory);
    }

}
