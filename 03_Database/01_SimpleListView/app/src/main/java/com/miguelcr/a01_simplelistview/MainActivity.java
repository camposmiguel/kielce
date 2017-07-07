package com.miguelcr.a01_simplelistview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_new_student:
                newStudent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newStudent() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // show the dialog
        dialog.show();
    }

}
