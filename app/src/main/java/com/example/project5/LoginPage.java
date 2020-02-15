package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText email, password;
    Button login;
    TextView signin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        email = findViewById(R.id.ed2_email);
        password = findViewById(R.id.ed2_password);
        login = findViewById(R.id.btnl_login);
        signin=findViewById(R.id.tv2);
        progressBar=findViewById(R.id.pgbar2);
        fAuth=FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        /*SharedPreferences sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        final String un = sp.getString("username", null);
        final String pass = sp.getString("password", null);

         */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = email.getText().toString();
                final String pwd = password.getText().toString();


                if (user.equals("")||pwd.equals("")) {
                    Toast.makeText(LoginPage.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(pwd.length()<6)
                {
                    Toast.makeText(LoginPage.this, "Password should be of minimum 6 characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Toast.makeText(LoginPage.this, "Valid Username and Password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(user,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent=new Intent(LoginPage.this,MainActivity.class);
                                //intent me user ka existing data firebase se retrieve krke bhejna hai
                                startActivity(intent);
                                Toast.makeText(LoginPage.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginPage.this, "Error occured", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editor.putBoolean("is_first_time",true);
                //editor.commit();
                Intent intent=new Intent(LoginPage.this,Main4Activity.class);
                startActivity(intent);
            }
        });

    }
}
