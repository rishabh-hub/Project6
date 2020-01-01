package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

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

        notes_et=findViewById(R.id.notes);
        title_et=findViewById(R.id.title);
        mydb=new DatabaseHelper(this);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status=mydb.insertData(notes_et.getText().toString());

                if(status.equals("Saved"))
                    Toast.makeText(Main2Activity.this,"Saved",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main2Activity.this,"Not Saved",Toast.LENGTH_LONG).show();
            }
        });
    }
}
