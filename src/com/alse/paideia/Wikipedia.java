package com.alse.paideia;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class Wikipedia {
    static String infoURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
    static String imgURL = "http://en.wikipedia.org/w/api.php?action=query&prop=pageimages&format=json&piprop=original&titles=";

    void getInfo(String type){
       new getAsync().execute(type);
    }
    class getAsync extends AsyncTask<String, Void, String> {

        protected void onPostExecute(String out) {
            if (out != null){
                Log.d("alsealsealsepaideia", out);
            }
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject object = null;
            try {
                object = JGet.get(infoURL + params[0]).getJSONObject("query").getJSONObject("pages");
                return object.getString(object.names().getString(0));
            } catch (JSONException e) {
                Log.e("alsealsepaideia", "exception", e);
            }
            return null;
        }
    }
}
