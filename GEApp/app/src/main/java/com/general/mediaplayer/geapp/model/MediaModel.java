package com.general.mediaplayer.geapp.model;

import java.io.Serializable;

/**
 * Created by mac on 20/03/2017.
 */

public class MediaModel implements Serializable{

    public String photoPath;
    public String videooPath;

    public Boolean bIsPhoto;

    public MediaModel(String path , Boolean IsPhoto)
    {
        photoPath = path;
        bIsPhoto = IsPhoto;
    }
}
