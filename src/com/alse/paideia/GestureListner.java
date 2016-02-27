package com.alse.paideia;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alse on 2/27/16.
 */
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
            if (padding == 0) {
                v.setBackgroundColor(0xFF000000);
                if (viewHolder.model.isRunning())
                    v.setBackgroundColor(0xFF058805);
            }
            if (padding > 75) {
                viewHolder.setRunning(true);
                v.setBackgroundColor(0xFF00FF00);
                viewHolder.icon.setImageResource(R.drawable.ic_action_info);
            }
            if (padding < -75) {
                viewHolder.setRunning(false);
                v.setBackgroundColor(0xFFFF0000);
            }
            v.setPadding(padding, 0, 0, 0);
        }
        return true;
    }
}
