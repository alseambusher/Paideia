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
        String[] tokens = type.split("\\s");
        type= "";

        for(int i = 0; i < tokens.length; i++){
            char capLetter = Character.toUpperCase(tokens[i].charAt(0));
            type+=  " " + capLetter + tokens[i].substring(1);
        }
        type = type.trim().replace(" ", "_");
        JSONObject object = null;
        try {
            String url = (infoURL + type);
            object = JGet.get(url).getJSONObject("query").getJSONObject("pages");
            return object.getJSONObject(object.names().getString(0)).getString("extract");
        } catch (JSONException e) {
            Log.e("com.alse.paideia", "exception", e);
        }
        return "";
    }

    public static Bitmap getIcon(String type){
        JSONObject object = null;
        try {
            String url = (imgURL + type.replace(" ", "_"));
            Log.d("alseambusher", JGet.get(url).getString("query"));
            object = JGet.get(url).getJSONObject("query").getJSONObject("pages");
            Log.d("alseambusher", object.getString(object.names().getString(0)));
            URL _url = new URL(object.getJSONObject(object.names().getString(0)).getJSONObject("thumbnail").getString("original"));

            return BitmapFactory.decodeStream(_url.openConnection().getInputStream());
        } catch (Exception e) {
            Log.e("com.alse.paideia", "exception", e);
        }
        return null;
    }
}
