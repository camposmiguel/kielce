package com.miguelcr.a02_googlekeep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}
