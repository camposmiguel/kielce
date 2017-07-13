package com.miguelcr.a01_duckgame;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
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
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.textViewNick) TextView nick;
    @BindView(R.id.textViewCounter) TextView counter;
    @BindView(R.id.textViewTimer) TextView timer;
    @BindView(R.id.imageViewDuck) ImageView duck;
    Random random;
    Realm realm;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initRealm();
        realm = Realm.getDefaultInstance();

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

        startCountDown();

    }

    private void startCountDown() {
        // 3. Start countdown
        // CountDownTimer (total milliseconds for the countdown, period between 2 seconds)
        // 1min = 60s = 60000ms
        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 +"s");
            }

            public void onFinish() {
                timer.setText("0s");
                saveUser();
                showDialogGameOver();
            }
        }.start();
    }

    private void saveUser() {
        int points = Integer.parseInt(counter.getText().toString());
        User user = new User(nick.getText().toString(),points);

        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
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



    }

    private void showDialogGameOver() {

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Select one option")
                .setTitle("Game over");

        builder.setCancelable(false);

        View view = getLayoutInflater().inflate(R.layout.dialog_game_over, null);
        builder.setView(view);

        Button btnRestart = (Button) view.findViewById(R.id.buttonRestart);
        Button btnExit = (Button) view.findViewById(R.id.buttonExit);
        Button btnRank = (Button) view.findViewById(R.id.buttonRanking);

        btnRestart.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnRank.setOnClickListener(this);

        // 3. Get the AlertDialog from create()
        dialog = builder.create();

        dialog.show();
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

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.buttonRestart:
                counter.setText("0");
                duckRandom();

                // Close dialog
                dialog.dismiss();

                // start countdowntimer
                startCountDown();

                break;
            case R.id.buttonExit:
                finish();
                break;
            case R.id.buttonRanking:
                Intent i = new Intent(this,RankingActivity.class);
                startActivity(i);
                break;
        }
    }
}
