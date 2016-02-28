package com.alse.paideia;
import android.util.Log;

import org.json.JSONObject;

import java.net.*;
import java.io.*;

public class JGet {
    public static JSONObject get(String input) {
        try {
            URL url = new URL(input);
            final int bufferSize = 1024;
            final char[] buffer = new char[bufferSize];
            final StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(url.openStream(), "UTF-8");
            for (; ; ) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0)
                    break;
                out.append(buffer, 0, rsz);
            }
            return new JSONObject(out.toString());
        }
        catch (Exception e) {
            return null;
        }
    }
}
