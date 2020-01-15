package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText notes_et,title_et;
    Button save_btn;
    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        notes_et=findViewById(R.id.notes);
        title_et=findViewById(R.id.title);
        save_btn=findViewById(R.id.savebtn);
        mydb=new DatabaseHelper(this);
        if ((getIntent().getStringExtra("notes")) != null) {
            notes_et.setText(getIntent().getStringExtra("notes") + notes_et.getText().toString());
            title_et.setText(getIntent().getStringExtra("title") + title_et.getText().toString());
        }

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((getIntent().getStringExtra("notes")) != null) {
                    Intent i=new Intent(Main2Activity.this,MainActivity.class);
                    mydb.update(notes_et.getText().toString(),title_et.getText().toString(),getIntent().getIntExtra("id",0));
                    startActivity(i);
                }
                else {
                    String status = mydb.insertData(title_et.getText().toString(), notes_et.getText().toString());
                    Bundle b = new Bundle();
                    b.putString("title", title_et.getText().toString());
                    b.putString("notes", notes_et.getText().toString());
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    if (status.equals("Saved")) {
                        Toast.makeText(Main2Activity.this, "Saved", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    } else
                        Toast.makeText(Main2Activity.this, "Not Saved", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
