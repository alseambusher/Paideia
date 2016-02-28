package com.alse.paideia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Wikipedia {
    static String infoURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
    static String imgURL = "http://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&piprop=original&titles=";

    public static String getInfo(String type){
        JSONObject object = null;
        try {
            object = JGet.get(infoURL + type).getJSONObject("query").getJSONObject("pages");
            return object.getString(object.names().getString(0));
        } catch (JSONException e) {
            Log.e("com.alse.paideia", "exception", e);
        }
        return "";
    }

    public static Bitmap getIcon(String type){
        JSONObject object = null;
        try {
            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/6a/CelegansGoldsteinLabUNC.jpg");
//            URL url = new URL(imgURL + type);
//            object = JGet.get(imgURL + type).getJSONObject("query").getJSONObject("pages");

//            return BitmapFactory.decodeStream(new URL(type).openConnection().getInputStream());
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            Log.e("com.alse.paideia", "exception", e);
        }
        return null;
    }
}
