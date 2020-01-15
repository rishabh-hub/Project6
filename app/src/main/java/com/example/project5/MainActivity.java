
package com.example.project5;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<NotesModel> data1;
    DatabaseHelper mydb;
    ImageButton save_btn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    ArrayList<String> key=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        save_btn=findViewById(R.id.searchbutn);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        data1 = new ArrayList<NotesModel>(3);

        FloatingActionButton floatingActionButton=findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,LoginPage.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(i);
                finish();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        getFromDatabase();
        RvAdapter adapter = new RvAdapter(data1);
        recyclerView.setAdapter(adapter);

        data1 = new ArrayList<>();

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(i);
            }
        });

    }


    void getFromDatabase() {
        mydb = new DatabaseHelper(this);
     /*   new FirebaseDatabaseHelper().readNotes(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(ArrayList<NotesModel> data, ArrayList<String> keys) {
                data1 = data;
                key = keys;
            }
        });
        mydb.deleteRow();
        for (int i=0;i<data1.size();i++)
        mydb.insertData(data1.get(i).getTitle(),data1.get(i).getNotes());

      */
        Cursor cursor = mydb.getData();
        if (cursor.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No notes", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                data1.add(new NotesModel(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
            }
        }
    }

    }


