package com.general.mediaplayer.geapp.model;

import java.io.Serializable;

/**
 * Created by mac on 20/03/2017.
 */

public class MediaModel implements Serializable{

    public String photoPathFromSD;
    public String videooPathFromSD;

    public String photoPathFromServer;
    public String videooPathFromServer;

    public Boolean bIsPhoto;

    public MediaModel(String path , Boolean IsPhoto)
    {
        photoPathFromSD = path;
        bIsPhoto = IsPhoto;
    }
}
