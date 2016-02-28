package com.alse.paideia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.speech.tts.TextToSpeech;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.alse.paideia.Classifier.Recognition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.System.*;

public class RecognitionScoreView extends View {
  private static final float TEXT_SIZE_DIP = 30;
  private static final float TEXT_SIZE_DIP_FACTOR = (float)0.5;
  private List<Recognition> results;
  private List<Recognition> prevResults;
  private List<HashMap<List<Recognition>, Long>> cached_results = new ArrayList<>();
  private final Paint fgPaint;

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    float textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setColor(MainActivity.baseTextColor);
    fgPaint.setTextSize(textSizePx);
  }

  public void setResults(final List<Recognition> results) {
    this.results = results;
    postInvalidate();
  }

  public void filterCached(){
    for (Iterator<HashMap<List<Recognition>, Long>> iterator = cached_results.iterator(); iterator.hasNext();) {
        HashMap<List<Recognition>, Long> h = iterator.next();
        if (currentTimeMillis() / 1000 - h.get(h.keySet().iterator().next()) > 10){
          iterator.remove();
        }
    }
  }

  public List<String> getTopResults(){
    HashMap<String, Float> classes = new HashMap<>();
    for(HashMap<List<Recognition>, Long> h: cached_results){
      for(Recognition r : h.keySet().iterator().next()){
        if (classes.containsKey(r.getTitle()))
          classes.put(r.getTitle(), classes.get(r.getTitle()) > r.getConfidence() ? classes.get(r.getTitle()): r.getConfidence());
        else
          classes.put(r.getTitle(), r.getConfidence());
      }
    }
    Set<Map.Entry<String, Float>> set = classes.entrySet();
    List<Map.Entry<String, Float>> list = new ArrayList<>(
            set);
    Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
      public int compare(Map.Entry<String, Float> o1,
                         Map.Entry<String, Float> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    });
    List<String> result = new ArrayList<>();
    for(int i=0; i<(list.size() > 5? 5:list.size()); i++){
      result.add(list.get(i).getKey());
    }
    return result;
  }
  @Override
  public void onDraw(final Canvas canvas) {

    float max_confidence = -1;
    float min_confidence = 1;

    if (results != null && results.size() > 0) {
      for (final Recognition recog : results) {
        max_confidence = max_confidence > recog.getConfidence() ? max_confidence : recog.getConfidence();
        min_confidence = min_confidence < recog.getConfidence() ? min_confidence : recog.getConfidence();
      }

      final int x = 100;
      int y = 0;

      fgPaint.setTextSize(TEXT_SIZE_DIP);
      for (final Recognition recog : results) {
//        fgPaint.setTextSize(recog.getConfidence()*TEXT_SIZE_DIP + TEXT_SIZE_DIP_FACTOR/recog.getConfidence());
        if (y == 0)
          y = (int) (fgPaint.getTextSize() * 1.5f);
        String text = recog.getTitle() + ": " + recog.getConfidence();
        canvas.drawText(text, x, y, fgPaint);
        y += fgPaint.getTextSize();
      }
      if (cached_results != null && cached_results.size() > 0) {
        if (!prevResults.get(0).getTitle().equals(results.get(0).getTitle())){
          MainActivity.speak(results.get(0).getTitle());
        }
      }

      if (results != null && results.size() > 0) {
        HashMap<List<Recognition>, Long> cache = new HashMap<List<Recognition>, Long>();
        cache.put(results, currentTimeMillis() / 1000);
        cached_results.add(cache);
      }
      prevResults = results;
    }
  }
}
