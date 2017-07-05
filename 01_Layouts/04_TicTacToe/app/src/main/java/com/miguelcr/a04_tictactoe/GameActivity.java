package com.miguelcr.a04_tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    boolean isPlayingPlayer1 = true;
    int[] selectedCells = {0,0,0,0,0,0,0,0,0};

    // selectedCells[0] = 1 >> The player 1 selected the cell number 0
    /*
    x - -
    - - -
    - - -

    selectedCells[8] = 3 >> Player 2 selected the cell number 8

    x - -
    - - -
    - - O

    if(selectedCells[8]==0) ... > It's empty
    else >> we can't select this cell

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void cellClicked(View view) {
        ImageView cell = (ImageView) view;
        // We get the id for the ImageView where we clicked
        int id = cell.getId();
        int position = 0;

        switch (id) {
            case R.id.imageView1:
                position = 0;
                break;
            case R.id.imageView2:
                position = 1;
                break;
            case R.id.imageView3:
                position = 2;
                break;
            case R.id.imageView4:
                position = 3;
                break;
            case R.id.imageView5:
                position = 4;
                break;
            case R.id.imageView6:
                position = 5;
                break;
            case R.id.imageView7:
                position = 6;
                break;
            case R.id.imageView8:
                position = 7;
                break;
            case R.id.imageView9:
                position = 8;
                break;
        }

        if(selectedCells[position]==0) { // It's possible to play with this cell
            if (isPlayingPlayer1) {
                cell.setImageResource(R.drawable.ic_player_1);
                isPlayingPlayer1 = false;
            } else {
                cell.setImageResource(R.drawable.ic_player_2);
                isPlayingPlayer1 = true;
            }
        } else {
            Toast.makeText(this, "The cell isn't empty!", Toast.LENGTH_SHORT).show();
        }

    }
}
