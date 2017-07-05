package com.miguelcr.a04_tictactoe;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewGameActivity extends AppCompatActivity {
    TextView textViewTitle;
    EditText editTextP1, editTextP2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        editTextP1 = (EditText) findViewById(R.id.editTextPlayer1);
        editTextP2 = (EditText) findViewById(R.id.editTextPlayer2);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "children.ttf");
        textViewTitle.setTypeface(face);
    }


    public void startGame(View view) {

        String player1 = editTextP1.getText().toString();
        String player2 = editTextP2.getText().toString();

        if(player1.equals("") || player2.equals("")) {
            Toast.makeText(this, "Please, complete the required fields", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Intent i = new Intent(NewGameActivity.this, GameActivity.class);
            i.putExtra("p1Name",player1);
            i.putExtra("p2Name",player2);
            startActivity(i);
        }
    }
}
