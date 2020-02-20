package com.example.firebase_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Login extends AppCompatActivity {

    EditText email,pswd;
    Button login_btn;
    TextView reg_txt;
    FirebaseAuth fAuth;
    ProgressBar prg_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pswd = findViewById(R.id.pswd);
        login_btn = findViewById(R.id.login_btn);
        reg_txt = findViewById(R.id.register_btn);
        prg_bar = findViewById(R.id.prg_bar);
        fAuth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String pass = pswd.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    pswd.setError("Password is Required!!");
                    return;
                }
                if (pass.length() < 6) {
                    pswd.setError("Password must be less than 6 characters!!");
                    return;
                }
                prg_bar.setVisibility(View.VISIBLE);
                //authenticate the user
                fAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                reg_txt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(),Register.class));
                    }
                });

            }

        });





    }
}
