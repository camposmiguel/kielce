package com.miguelcr.a03_relativelayouthotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.miguelcr.a03_relativelayouthotel.R.id.imageView;

public class MainActivity extends AppCompatActivity {
    TextView textViewHotelName, textViewHotelPrice;
    ImageView imageViewHotelPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect the visual components with the local variables
        textViewHotelName = (TextView) findViewById(R.id.textViewName);
        textViewHotelPrice = (TextView) findViewById(R.id.textViewPrice);
        imageViewHotelPhoto = (ImageView) findViewById(R.id.imageViewPhoto);

        // Change TextView text
        textViewHotelName.setText("Kielce Cheapest Hotel!");
        textViewHotelPrice.setText("€4");

        // Load an image from Internet
        Picasso.with(this)
                .load("https://media-cdn.tripadvisor.com/media/photo-s/07/9b/68/0c/binkowski-hotel.jpg")
                .resize(100,150)
                .centerCrop()
                .into(imageViewHotelPhoto);

    }
}
