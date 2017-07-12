package com.miguelcr.a01_duckgame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {
    @BindView(R.id.textViewNick) TextView nick;
    @BindView(R.id.textViewCounter) TextView counter;
    @BindView(R.id.textViewTimer) TextView timer;
    @BindView(R.id.imageViewDuck) ImageView duck;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Butter knife connection
        ButterKnife.bind(this);

        // Hide the toolbar
        getSupportActionBar().hide();

        random = new Random();

        // Get the nick name from the MainActivity Intent
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("nickName");

        nick.setText(name);

        duckRandom();

    }

    public void duckClicked(View view) {
        //Toast.makeText(this, "Duck hunt!", Toast.LENGTH_SHORT).show();

        // 1. UPDATE THE DUCK COUNTER
        
        int duckNumber = Integer.parseInt(counter.getText().toString());
        duckNumber++;

        // int > String
        counter.setText(String.valueOf(duckNumber));
        
        
        // 2. GENERATE A NEW DUCK RANDOM POSITION 
        duckRandom();

        // 3. Start countdown

        // CountDownTimer (total milliseconds for the countdown, period between 2 seconds)
        // 1min = 60s = 60000ms
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 +"s");
            }

            public void onFinish() {
                timer.setText("Game over!");
            }
        }.start();


    }

    private void duckRandom() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int maxX = width - duck.getWidth();
        int maxY = height - duck.getHeight();
        int min = 0;

        int randomX = random.nextInt(maxX - min + 1) + min;
        int randomY = random.nextInt(maxY - min + 1) + min;

        duck.setX(randomX);
        duck.setY(randomY);

    }
}
