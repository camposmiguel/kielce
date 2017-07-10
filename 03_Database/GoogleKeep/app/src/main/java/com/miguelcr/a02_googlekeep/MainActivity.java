package com.miguelcr.a02_googlekeep;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listViewNotes);


        noteList = new ArrayList<>();
        noteList.add(new Note("Supermarket","Milk, bread, eggs,...",R.color.colorBlue,true));
        noteList.add(new Note("Supermarket low cost","PS4, TV, pizza hut,...",R.color.colorGreen,false));

        NoteAdapter adapter = new NoteAdapter(
                this,
                R.layout.note_item,
                noteList
        );

        lista.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_new_note:
                createNewNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createNewNote() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.dialog_new_note, null);
        builder.setView(view);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message_new_note)
               .setTitle(R.string.dialog_title_new_note);

        builder.setPositiveButton(R.string.save_new_note, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });



        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // 4. show the dialog
        dialog.show();

    }
}
