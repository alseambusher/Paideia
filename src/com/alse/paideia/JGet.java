package com.alse.paideia;
import android.util.Log;

import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.nio.charset.Charset;

public class JGet {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject get(String input) {
        StringBuilder result = new StringBuilder();

        HttpURLConnection urlConnection;
        try {
            URL url = new URL(input);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Log.d("ambusher", result.toString()+input);
            return new JSONObject(result.toString());

        }catch( Exception e) {
            e.printStackTrace();
        }
        return null;

//        try {
//            InputStream is = new URL(input).openStream();
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            return new JSONObject(jsonText);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
    }
}
//        try {
//            URL url = new URL(input);
//            final int bufferSize = 1024;
//            final char[] buffer = new char[bufferSize];
//            final StringBuilder out = new StringBuilder();
//            Reader in = new InputStreamReader(url.openStream(), "UTF-8");
//            for (; ; ) {
//                int rsz = in.read(buffer, 0, buffer.length);
//                if (rsz < 0)
//                    break;
//                out.append(buffer, 0, rsz);
//            }
//            Log.e("json.alse", out.toString()+input );
//            return new JSONObject(out.toString());
//        }
//        catch (Exception e) {
//            Log.e("com.alse.paideia", "exception", e);
//            return null;
//        }
