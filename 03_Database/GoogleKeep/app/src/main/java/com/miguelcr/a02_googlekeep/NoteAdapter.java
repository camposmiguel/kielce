package com.miguelcr.a02_googlekeep;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by miguelcampos on 7/7/17.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    Context ctx;
    int templateLayout;
    List<Note> noteList;


    public NoteAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Note> objects) {
        super(context, resource, objects);
        ctx = context;
        templateLayout = resource;
        noteList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(templateLayout, parent, false);


        Note current = noteList.get(position);

        TextView title = (TextView) v.findViewById(R.id.textViewTitle);
        TextView description = (TextView) v.findViewById(R.id.textViewDescription);
        ImageView priority = (ImageView) v.findViewById(R.id.imageViewPriority);
        ConstraintLayout layout = (ConstraintLayout) v.findViewById(R.id.layout);

        title.setText(current.getTitle());
        description.setText(current.getDescription());

        if(current.isHighPriority()) {
            priority.setImageResource(R.drawable.ic_priority_high_red_24px);
        } else {
            priority.setImageResource(R.drawable.ic_low_priority_white_24px);
        }

        layout.setBackgroundColor(ContextCompat.getColor(ctx,current.getColor()));

        return v;
    }
}
