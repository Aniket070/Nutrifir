package com.example.nutrifir;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        // if the user has already logged in, they will be directed to the main page directly


        final EditText LoginUsername =(EditText) findViewById(R.id.Login_Username);
        final EditText LoginPassword =(EditText) findViewById(R.id.Login_Password);
        final TextView LoginRegister =(TextView) findViewById(R.id.Login_Register);
        final Button Login_btn =(Button) findViewById(R.id.Login_Button);

        fAuth = FirebaseAuth.getInstance();  //we get current instance from the database
        LoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginUsername.getText().toString().trim();
                String password = LoginPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    LoginUsername.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    LoginPassword.setError("Password is empty");
                    return;
                }
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Welcome Back !",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}