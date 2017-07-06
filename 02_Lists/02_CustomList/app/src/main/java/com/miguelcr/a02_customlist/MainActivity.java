package com.miguelcr.a02_customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    List<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Get the ListView reference
        lista = (ListView) findViewById(R.id.listViewRestaurants);

        // 2. Create the elements for the list
        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(
                "http://www.algonquinhotel.com/wp-content/uploads/2015/02/BlueBar_3.jpg",
                "Seville Pub",
                "954 11 22 33",
                4.0f)
        );
        restaurantList.add(new Restaurant(
                "http://www.algonquinhotel.com/wp-content/uploads/2015/02/BlueBar_3.jpg",
                "Seville Pub II",
                "954 11 22 34",
                4.2f)
        );

        // 3. Custom Restaurant Adapter
        RestaurantAdapter adapter = new RestaurantAdapter(
                this,
                R.layout.restaurant_item,
                restaurantList
        );

        // 4. Connect ListView & Adapter
        lista.setAdapter(adapter);



    }
}
