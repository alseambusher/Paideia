package com.alse.paideia;

public class Model {
    private String name;
    private boolean selected;
    private Boolean running;
    public Model(String name) {
        this.name = name;
        selected = false;
        running = false;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRuning(boolean running) {
        this.running = running;
    }
}
