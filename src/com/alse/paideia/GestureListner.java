package com.alse.paideia;

import android.view.MotionEvent;
import android.view.View;


public class GestureListner implements View.OnTouchListener {
    private int padding = 0;
    private int initialx = 0;
    private int currentx = 0;

    private ViewHolder viewHolder;

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            padding = 0;
            initialx = (int) event.getX();
            currentx = (int) event.getX();
            viewHolder = ((ViewHolder) v.getTag());
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            currentx = (int) event.getX();
            padding = currentx - initialx;
        }

        if (event.getAction() == MotionEvent.ACTION_UP ||
                event.getAction() == MotionEvent.ACTION_CANCEL) {
            padding = 0;
            initialx = 0;
            currentx = 0;
        }

        if (viewHolder != null) {
            if (padding > 75) {
                viewHolder.setUserFeedback(true, v.getContext());
            }
            if (padding < -75) {
                viewHolder.setUserFeedback(false, v.getContext());
            }
            v.setPadding(padding, 0, 0, 0);
        }
        return true;
    }
}
