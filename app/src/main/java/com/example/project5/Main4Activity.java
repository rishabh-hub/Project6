package com.example.project5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main4Activity extends AppCompatActivity {
    EditText name,email,username,password;
    SharedPreferences sharedPreferences;
    Button signin;
    TextView login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        /*sharedPreferences=getApplicationContext().getSharedPreferences("MyPrefs",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();

         */
        name=findViewById(R.id.ed_name);
        username=findViewById(R.id.ed_username);
        email=findViewById(R.id.ed_email);
        password=findViewById(R.id.ed_password);
        signin=findViewById(R.id.btnl);
        login=findViewById(R.id.tv);
        progressBar=findViewById(R.id.pgbar);
        fAuth=FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(fAuth.getCurrentUser() !=null)
        {
            Intent intent=new Intent(Main4Activity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        //if(sharedPreferences.getBoolean("is_first_time",true))
        //{
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String mail=email.getText().toString().trim();
                String un=username.getText().toString();
                String pass=password.getText().toString();
                if(n.equals("")||mail.equals("")||un.equals("")||pass.equals(""))
                {
                    Toast.makeText(Main4Activity.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
                }
                else if (!emailValidator(mail))
                {
                    Toast.makeText(Main4Activity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<6)
                {
                    Toast.makeText(Main4Activity.this, "Password should be of minimum 6 characters", Toast.LENGTH_SHORT).show();
                }

                else
                {
                       /* editor.putString("name",n);
                        editor.putString("email",mail);
                        editor.putString("username",un);
                        editor.putString("password",pass);
                        */
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Main4Activity.this, "User created", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Main4Activity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                Toast.makeText(Main4Activity.this, "Error Occurred"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                    //editor.putBoolean("is_first_time",false);
                    //editor.commit();
                    //Toast.makeText(MainActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main4Activity.this,LoginPage.class);
                startActivity(intent);
            }
        });

    }

    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
