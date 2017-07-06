package com.miguelcr.a01_simplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    List<String> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Get the ListView reference to manage the list
        lista = (ListView) findViewById(R.id.listViewStudents);

        // 2. Create the list of data
        students = new ArrayList<>();
        students.add("Bartek");
        students.add("Krzysztof");
        students.add("Damian");
        students.add("Krystian");
        students.add("Pawel");
        students.add("Milosz");
        students.add("Adrian I");
        students.add("Wiktor");
        students.add("Wojtek I");
        students.add("Adrian II");
        students.add("Patryk I");
        students.add("Norbert");
        students.add("Karolina");
        students.add("Gregor");
        students.add("Michal");
        students.add("Wojtek II");
        students.add("Kamil");
        students.add("Mateusz I");
        students.add("Agnieszka");
        students.add("Damian");
        students.add("Patryk II");
        students.add("Mateusz II");

        // 3. Adapter (convert List<String> into ListView elements)
        ArrayAdapter adapterStudents = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                students
        );

        // 4. Connect ListView and Adapter
        lista.setAdapter(adapterStudents);

        // Item click event
        lista.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = students.get(position);
        Toast.makeText(this, "Student: "+name, Toast.LENGTH_SHORT).show();

        //view.setVisibility(View.GONE);
        view.animate().rotationX(360).setDuration(2000).start();
    }
}
