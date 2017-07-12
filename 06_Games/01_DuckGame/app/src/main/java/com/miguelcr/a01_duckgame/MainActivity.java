package com.miguelcr.a01_duckgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;
    EditText editTextNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.buttonStartGame);
        editTextNick = (EditText) findViewById(R.id.editTextNick);

        Typeface face = Typeface.createFromAsset(getAssets(), "pixel.ttf");
        buttonStart.setTypeface(face);
        editTextNick.setTypeface(face);
    }

    public void startGame(View view) {
        String nick = editTextNick.getText().toString();

        if(nick.equals("")) {
            editTextNick.setError("Nickname is required");
        } else {
            // Start the game
            Intent i = new Intent(this,GameActivity.class);
            i.putExtra("nickName",nick);
            startActivity(i);
        }
    }
}
