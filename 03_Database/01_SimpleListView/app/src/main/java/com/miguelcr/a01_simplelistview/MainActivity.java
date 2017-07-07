package com.miguelcr.a01_simplelistview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    List<Student> students;
    EditText editTextName;
    Realm realm;
    StudentAdapter adapterStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // We initialize the database
        initRealm();
        realm = Realm.getDefaultInstance();

        // 1. Get the ListView reference to manage the list
        lista = (ListView) findViewById(R.id.listViewStudents);

        // 2. Create the list of data
        // SELECT * FROM Student
        // Give me the complete list of student from the database
        students = realm.where(Student.class).findAll();

        // 3. Adapter (convert List<String> into ListView elements)
        adapterStudents = new StudentAdapter(
                this,
                R.layout.student_item,
                students
        );

        // 4. Connect ListView and Adapter
        lista.setAdapter(adapterStudents);

        // Item click event
        lista.setOnItemClickListener(this);


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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = students.get(position).getName();
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

        View view = getLayoutInflater().inflate(R.layout.dialog_new_student, null);
        builder.setView(view);
        editTextName = (EditText) view.findViewById(R.id.editTextNewStudent);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        // Add the buttons
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "Name: "+editTextName.getText().toString(), Toast.LENGTH_SHORT).show();
                Student newStudent = new Student();
                newStudent.setName(editTextName.getText().toString());

                realm.beginTransaction();
                realm.copyToRealm(newStudent);
                realm.commitTransaction();

                // Get the new list of students from de Database
                students = realm.where(Student.class).findAll();
                adapterStudents.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // If user click on cancel, we close the dialog
                dialog.dismiss();
            }
        });


        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // show the dialog
        dialog.show();
    }

}
