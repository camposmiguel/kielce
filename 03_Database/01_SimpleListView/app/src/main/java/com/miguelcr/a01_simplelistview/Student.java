package com.miguelcr.a01_simplelistview;

import android.widget.ArrayAdapter;

import io.realm.RealmObject;

/**
 * Created by miguelcampos on 6/7/17.
 */

public class Student extends RealmObject {
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
