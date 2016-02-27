package com.alse.paideia;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
        protected TextView text;
        protected ImageView icon;
        protected CheckBox checkbox;
        protected int position;
        protected Model model;
        private int color;
        private int imageid;

        public ViewHolder()
        {
            position = 0;
            imageid = R.drawable.ic_action_info;
            color = 0xFFFFFFFF;
        }
        public int getColor() {
            return color;
        }
        public int getImageId() {
            return imageid;
        }
        public void setRunning(boolean running) {
            model.setRuning(running);
            if(running)
            {
                color = 0xFFffffb6;
                imageid = R.drawable.ic_launcher;
            }
            else
            {
                imageid = R.drawable.ic_action_info;
                color = 0xFFFFFFFF;
            }
        }
}
