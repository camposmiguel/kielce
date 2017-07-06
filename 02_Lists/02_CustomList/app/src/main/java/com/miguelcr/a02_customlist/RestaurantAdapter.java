package com.miguelcr.a02_customlist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by miguelcampos on 6/7/17.
 */

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    Context ctx;
    int templateLayout;
    List<Restaurant> restaurants;


    public RestaurantAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);

        ctx = context;
        templateLayout = resource;
        restaurants = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(templateLayout, parent, false);

        // 1. Get the information for the current item
        Restaurant current = restaurants.get(position);

        // 2. Get all the view components references
        ImageView imageViewPhoto = (ImageView) v.findViewById(R.id.imageViewPhoto);
        TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
        TextView textViewPhone = (TextView) v.findViewById(R.id.textViewPhone);
        RatingBar ratingBarRest = (RatingBar) v.findViewById(R.id.ratingBarRestaurant);

        // 3. Set the current item INFO into the view components
        textViewName.setText(current.getName());
        textViewPhone.setText(current.getTelephone());
        ratingBarRest.setRating(current.getRating());

        Picasso.with(ctx)
                .load(current.getPhotoPath())
                .resize(100,100)
                .centerCrop()
                .into(imageViewPhoto);

        return v;
    }
}
