package com.miguelcr.a04_tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    boolean isPlayingPlayer1 = true;
    int[] selectedCells = {0,0,0,0,0,0,0,0,0};
    TextView textViewPlayer;
    String player1Name, player2Name;
    boolean gameOver = false;
    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9;

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

        // Get the variables that we receive in the Intent object
        Bundle extras = getIntent().getExtras();
        player1Name = extras.getString("p1Name");
        player2Name = extras.getString("p2Name");

        textViewPlayer = (TextView) findViewById(R.id.textViewPlayer);
        iv1 = (ImageView) findViewById(R.id.imageView1);
        iv2 = (ImageView) findViewById(R.id.imageView2);
        iv3 = (ImageView) findViewById(R.id.imageView3);
        iv4 = (ImageView) findViewById(R.id.imageView4);
        iv5 = (ImageView) findViewById(R.id.imageView5);
        iv6 = (ImageView) findViewById(R.id.imageView6);
        iv7 = (ImageView) findViewById(R.id.imageView7);
        iv8 = (ImageView) findViewById(R.id.imageView8);
        iv9 = (ImageView) findViewById(R.id.imageView9);

        textViewPlayer.setText(player1Name);

    }

    public void cellClicked(View view) {
        if(gameOver) {
            Toast.makeText(this, "Restart the game", Toast.LENGTH_SHORT).show();
        } else {
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

            if (selectedCells[position] == 0) { // It's possible to play with this cell
                if (isPlayingPlayer1) {
                    cell.setImageResource(R.drawable.ic_player_1);
                    selectedCells[position] = 1;

                    if (existSolution()) {
                        gameOver = true;
                        Toast.makeText(this, player1Name + " wins!", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        isPlayingPlayer1 = false;
                        textViewPlayer.setText(player2Name);
                    }

                } else {
                    cell.setImageResource(R.drawable.ic_player_2);
                    selectedCells[position] = 2;

                    if (existSolution()) {
                        gameOver = true;
                        Toast.makeText(this, player2Name + " wins!", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        isPlayingPlayer1 = true;
                        textViewPlayer.setText(player1Name);
                    }
                }


            } else {
                Toast.makeText(this, "The cell isn't empty!", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private boolean existSolution() {
        boolean winner = false;

        /*


        0 1 2
        3 4 5
        6 7 8

        Cells: 0, 4, 8 **********
        x - -
        - x -
        - - x

        Cells 2, 4, 6
        - - x
        - x -
        x - -

        Cells 0, 3, 6
        x - -
        x - -
        x - -

        Cells 1,4,7
        - x -
        - x -
        - x -

        Cells 2,5,8
        - - x
        - - x
        - - x

        Cells 0,1,2
        x x x
        - - -
        - - -

        Cells 3,4,5
        - - -
        x x x
        - - -

        Cells 6,7,8
        - - -
        - - -
        x x x

         */


        if(selectedCells[0]!=0
                && selectedCells[0] == selectedCells[4]
                && selectedCells[4] == selectedCells[8]) {
            winner = true;
        } else if(selectedCells[2]!=0
                && selectedCells[2] == selectedCells[4]
                && selectedCells[4] == selectedCells[6]) {
            winner = true;
        } else if(selectedCells[0]!=0
                && selectedCells[0] == selectedCells[3]
                && selectedCells[3] == selectedCells[6]) {
            winner = true;
        } else if(selectedCells[1]!=0
                && selectedCells[1] == selectedCells[4]
                && selectedCells[4] == selectedCells[7]) {
            winner = true;
        } else if(selectedCells[2]!=0
                && selectedCells[2] == selectedCells[5]
                && selectedCells[5] == selectedCells[8]) {
            winner = true;
        } else if(selectedCells[0]!=0
                && selectedCells[0] == selectedCells[1]
                && selectedCells[1] == selectedCells[2]) {
            winner = true;
        } else if(selectedCells[3]!=0
                && selectedCells[3] == selectedCells[4]
                && selectedCells[4] == selectedCells[5]) {
            winner = true;
        } else if(selectedCells[6]!=0
                && selectedCells[6] == selectedCells[7]
                && selectedCells[7] == selectedCells[8]) {
            winner = true;
        }

        return winner;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_restart_game:
                restartGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void restartGame() {
        isPlayingPlayer1 = true;
        textViewPlayer.setText(player1Name);
        gameOver = false;
        iv1.setImageResource(R.drawable.ic_empty);
        iv2.setImageResource(R.drawable.ic_empty);
        iv3.setImageResource(R.drawable.ic_empty);
        iv4.setImageResource(R.drawable.ic_empty);
        iv5.setImageResource(R.drawable.ic_empty);
        iv6.setImageResource(R.drawable.ic_empty);
        iv7.setImageResource(R.drawable.ic_empty);
        iv8.setImageResource(R.drawable.ic_empty);
        iv9.setImageResource(R.drawable.ic_empty);

        for(int i=0; i<8; i++) {
            selectedCells[i]=0;
        }
    }
}
