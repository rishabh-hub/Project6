package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText search_data;
    DatabaseHelper mydb;
    ArrayList<NotesModel> data;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mydb=new DatabaseHelper(this);

        search_data=findViewById(R.id.search);
        button=findViewById(R.id.srh);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.RecyclerView);
        data = new ArrayList<NotesModel>(3);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        data = new ArrayList<>();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=mydb.getSearch(search_data.getText().toString());

                RvAdapter adapter = new RvAdapter(data);
                recyclerView.setAdapter(adapter);

                while (cursor.moveToNext())
                {
                    data.add(new NotesModel(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
                }

            }
        });

    }
}
