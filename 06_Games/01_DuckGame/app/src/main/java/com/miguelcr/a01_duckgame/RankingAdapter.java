package com.miguelcr.a01_duckgame;

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
 * Created by miguelcampos on 13/7/17.
 */

class RankingAdapter extends ArrayAdapter<User> {
    Context ctx;
    int templateLayout;
    List<User> userList;

    public RankingAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.templateLayout = resource;
        this.userList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(templateLayout, parent, false);

        User current = userList.get(position);

        TextView textViewNumber = (TextView) v.findViewById(R.id.textViewNumber);
        TextView textViewNick = (TextView) v.findViewById(R.id.textViewNick);
        TextView textViewPoints = (TextView) v.findViewById(R.id.textViewPoints);

        textViewNumber.setText(String.valueOf(position+1));
        textViewNick.setText(current.getNick());
        textViewPoints.setText(String.valueOf(current.getPoints()));

        return v;
    }
}
