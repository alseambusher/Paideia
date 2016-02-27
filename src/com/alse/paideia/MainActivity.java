/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alse.paideia;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity {
    static TextToSpeech tts;
    static ImageButton playPause;
    static boolean isPaused = false; // TODO  get it from prefs
    static int baseColor = Color.parseColor("#266e91");
    static int baseTextColor = Color.parseColor("#ffffff");

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    setContentView(R.layout.activity_camera);

    tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
      @Override
      public void onInit(int status) {
          if(status != TextToSpeech.ERROR) {
              tts.setLanguage(Locale.US);
          }
      }
    });
    if (null == savedInstanceState) {
      getFragmentManager()
          .beginTransaction()
          .replace(R.id.container, CameraConnectionFragment.newInstance())
          .commit();
    }
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(baseColor);
  }
    static void speak(String string){
        if (!isPaused){
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null, Integer.toString((new Random()).nextInt()));
        }
    }
    static void togglePlayback(){
        if(playPause != null) {
            if (isPaused)
                playPause.setImageResource(R.drawable.ic_pause_circle_filled_white_48dp);
            else
                playPause.setImageResource(R.drawable.ic_play_circle_filled_white_48dp);
            isPaused = !isPaused;
        }
    }
}
