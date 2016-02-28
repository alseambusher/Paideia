package com.alse.paideia;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHolder {
    protected TextView title;
    protected TextView body;
    protected ImageView icon;
    protected int position;
    protected Model model;

    private String positiveFeedback = "Thats great! you will see more of these.";
    private String negativeFeedback = "We will tune the results based on this";

    public ViewHolder() {
        position = 0;
    }

    public void setUserFeedback(boolean running, Context context) {
       if(model.getRunning() != running)
            Toast.makeText(context, running ? positiveFeedback : negativeFeedback, Toast.LENGTH_SHORT).show();
        model.setRunning(running);
    }
}
