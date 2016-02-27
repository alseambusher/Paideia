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

import java.util.List;
import java.util.Random;

public class RecognitionScoreView extends View {
  private static final float TEXT_SIZE_DIP = 70;
  private static final float TEXT_SIZE_DIP_FACTOR = (float)0.5;
  private List<Recognition> results;
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

  public Recognition getTopResult(){
    if (results.size() > 0)
      return results.get(0);
    return null;
  }

  @Override
  public void onDraw(final Canvas canvas) {

    float max_confidence = -1;
    float min_confidence = 1;

    if (results != null) {
      for (final Recognition recog: results){
        max_confidence = max_confidence > recog.getConfidence() ? max_confidence : recog.getConfidence();
        min_confidence = min_confidence < recog.getConfidence() ? min_confidence : recog.getConfidence();
      }

      final int x = 100;
      int y = 0;

      for (final Recognition recog : results) {
        fgPaint.setTextSize(recog.getConfidence()*TEXT_SIZE_DIP + TEXT_SIZE_DIP_FACTOR/recog.getConfidence());
        if (y == 0)
          y = (int) (fgPaint.getTextSize() * 1.5f);
        String text = recog.getTitle() + ": " + recog.getConfidence();
        canvas.drawText(text, x ,  y, fgPaint);
        y += fgPaint.getTextSize();
      }
    }
  }
}
