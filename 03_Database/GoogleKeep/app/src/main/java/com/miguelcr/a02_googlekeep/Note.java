package com.miguelcr.a02_googlekeep;

import io.realm.RealmObject;

/**
 * Created by miguelcampos on 7/7/17.
 */

public class Note extends RealmObject {
    String title;
    String description;
    int color;
    boolean highPriority;

    public Note() {
    }

    public Note(String title, String description, int color, boolean highPriority) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.highPriority = highPriority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isHighPriority() {
        return highPriority;
    }

    public void setHighPriority(boolean highPriority) {
        this.highPriority = highPriority;
    }
}
