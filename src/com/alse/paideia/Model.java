package com.alse.paideia;

import android.graphics.Bitmap;

public class Model {
    private String title;
    private String body;
    private Bitmap icon;
    private Boolean running;
    public Model(String title, String body, Bitmap icon) {
        running = false;
        this.title = title;
        this.body = body;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public Boolean getRunning() {
        return running;
    }
    public void setRunning(Boolean running){
        this.running = running;
    }
}
