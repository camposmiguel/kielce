package com.miguelcr.a01_simplelistview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by miguelcampos on 6/7/17.
 */

public class StudentAdapter extends ArrayAdapter<Student> {
    Context ctx;
    int templateLayout;
    List<Student> studentList;


    public StudentAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);

        ctx = context;
        templateLayout = resource;
        studentList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(templateLayout, parent, false);

        // 1. Get the information for the current item
        Student current = studentList.get(position);

        // 2. Get all the view components references
        TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
        textViewName.setText(current.getName());

        return v;
    }
}
