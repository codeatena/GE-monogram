package com.virtusventures.geapp.model;

/**
 * Created by mac on 20/03/2017.
 */

public class MediaModel {

    public String urlPath;
    public Boolean bIsPhoto;

    public MediaModel(String path , Boolean IsPhoto)
    {
        urlPath = path;
        bIsPhoto = IsPhoto;
    }
}
