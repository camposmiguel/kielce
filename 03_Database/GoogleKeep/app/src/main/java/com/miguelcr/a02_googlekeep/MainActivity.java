package com.miguelcr.a02_googlekeep;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    List<Note> noteList;
    EditText editTextTitle, editTextDescription;
    RadioGroup radioGroupColors;
    Switch switchPriority;
    Realm realm;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listViewNotes);

        // Database initialization
        initRealm();
        realm = Realm.getDefaultInstance();

        noteList = realm.where(Note.class).findAll();

        adapter = new NoteAdapter(
                this,
                R.layout.note_item,
                noteList
        );

        lista.setAdapter(adapter);


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

        // Get the view components
        editTextTitle = (EditText) view.findViewById(R.id.editTextTItle);
        editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        radioGroupColors = (RadioGroup) view.findViewById(R.id.radioGroupColors);
        switchPriority = (Switch) view.findViewById(R.id.switchHighPriority);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message_new_note)
               .setTitle(R.string.dialog_title_new_note);

        builder.setPositiveButton(R.string.save_new_note, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button

                Note newUserNote = new Note();
                newUserNote.setTitle(editTextTitle.getText().toString());
                newUserNote.setDescription(editTextDescription.getText().toString());
                int radioButtonSelected = radioGroupColors.getCheckedRadioButtonId();
                switch(radioButtonSelected) {
                    case R.id.radioButtonBlue:
                        newUserNote.setColor(R.color.colorBlue);
                        break;
                    case R.id.radioButtonGreen:
                        newUserNote.setColor(R.color.colorGreen);
                        break;
                    case R.id.radioButtonYellow:
                        newUserNote.setColor(R.color.colorYellow);
                        break;
                }
                newUserNote.setHighPriority(switchPriority.isChecked());


                realm.beginTransaction();
                realm.copyToRealm(newUserNote);
                realm.commitTransaction();

                noteList = realm.where(Note.class).findAll();
                adapter.notifyDataSetChanged();


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
