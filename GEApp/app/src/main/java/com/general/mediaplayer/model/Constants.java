package com.general.mediaplayer.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 15/03/2017.
 */

public class Constants {

    public static final String AMAZON_FOLDER = "https://s3-us-west-2.amazonaws.com/gemonogram/";
    public static final String API_DOMAIN  = "http://54.214.234.188:8080/monogramapi-1.0-SNAPSHOT/api/";

    public static final String MEDIA_URL   = "mediaurl";
    //public static final String SD_PATH = "/mnt/external_sd/gemonogram/";

    // used API
    public static final String connectedcooking = "connectedcooking";
    public static final String connectedcleaning = "connectedcleaning";
    public static final String connectedrefridgeration = "connectedrefridgeration";
    public static final String builtinrefrigeration = "builtinrefrigeration";
    public static final String cooktops = "cooktops";
    public static final String dishwasher = "dishwasher";
    public static final String dishwashercompactor = "dishwashercompactor";
    public static final String energystardishwasher = "energystardishwasher";
    public static final String experiencemonogram = "experiencemonogram";
    public static final String freestanding = "freestanding";
    public static final String fullsizerefrigeration = "fullsizerefrigeration";
    public static final String inspiredkitchen = "inspiredkitchen";
    public static final String professionalranges = "professionalranges";
    public static final String sweetreward = "sweetreward";
    public static final String wallovens = "wallovens";
    public static final String pizzaoven = "pizzaoven";
    public static final String advantium = "advantium";
    public static final String bottomfreezer = "bottomfreezer";
    public static final String frenchdoor = "frenchdoor";
    public static final String hoods = "hoods";
    public static final String integratedrefrigeration = "integratedrefrigeration";
    public static final String selectionguide = "selection_guide";
    public static final String undercounter = "undercounter";
    public static final String sidebyside = "sidebyside";

    public static final Map<String, String> urlfolderMap = new HashMap<String, String>() {{
        put(connectedcooking, "connected_cooking");
        put(connectedcleaning, "connected_cleaning");
        put(connectedrefridgeration, "connected_refridgeration");
        put(builtinrefrigeration, "builtinrefrigeration");
        put(cooktops, "cooktops");
        put(dishwasher, "dishwasher");
        put(dishwashercompactor, "dishwashercompactor");
        put(energystardishwasher, "energystardishwasher");
        put(experiencemonogram, "experiencemonogram");
        put(freestanding, "freestanding");
        put(fullsizerefrigeration, "fullsizerefrigeration");
        put(inspiredkitchen, "inspiredkitchen");
        put(professionalranges, "professionalranges");
        put(sweetreward, "sweetreward");
        put(wallovens, "wallovens");
        put(pizzaoven, "pizzaoven");
        put(advantium, "advantium");
        put(bottomfreezer, "bottomfreezer");
        put(frenchdoor, "frenchdoor");
        put(hoods, "hoods");
        put(integratedrefrigeration, "integrated_refrigeration");
        put(selectionguide, "selection_guide");
        put(undercounter, "undercounter");
        put(sidebyside, "sidebyside");

    }};

}
