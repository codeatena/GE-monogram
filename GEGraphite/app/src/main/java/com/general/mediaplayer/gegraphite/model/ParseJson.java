package com.general.mediaplayer.gegraphite.model;

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
        String name = apiObject.get("rootdirectory").getAsString();
        String photodirectory = apiObject.get("photodirectory").getAsString();

        return String.format("%s/%s/"  ,name ,photodirectory);

    }

    public static String getVideoPath(JsonObject jsonObject)
    {
        JsonObject apiObject = jsonObject.get("api").getAsJsonObject();

        String link = apiObject.get("link").getAsString();
        String bucket = apiObject.get("bucket").getAsString();
        String name = apiObject.get("rootdirectory").getAsString();
        String videodirectory = apiObject.get("videodirectory").getAsString();

        return String.format("%s/%s/"  ,name ,videodirectory);
    }

    public static String getPhotoPathServer(JsonObject jsonObject)
    {
        JsonObject apiObject = jsonObject.get("api").getAsJsonObject();

        String link = apiObject.get("link").getAsString();
        String bucket = apiObject.get("bucket").getAsString();
        String name = apiObject.get("rootdirectory").getAsString();
        String photodirectory = apiObject.get("photodirectory").getAsString();

        return String.format("%s/%s/%s/%s/" ,link ,bucket ,name ,photodirectory);

    }

    public static String getVideoPathServer(JsonObject jsonObject)
    {
        JsonObject apiObject = jsonObject.get("api").getAsJsonObject();

        String link = apiObject.get("link").getAsString();
        String bucket = apiObject.get("bucket").getAsString();
        String name = apiObject.get("rootdirectory").getAsString();
        String videodirectory = apiObject.get("videodirectory").getAsString();

        return String.format("%s/%s/%s/%s/" ,link ,bucket ,name ,videodirectory);
    }
}
