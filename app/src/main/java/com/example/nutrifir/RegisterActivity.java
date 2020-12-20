package com.example.nutrifir;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //req stuff
        final TextView Verify = (TextView) findViewById(R.id.Verification);
        final EditText RegisterEmailID =(EditText) findViewById(R.id.Register_Email);
        final EditText RegisterPassword =(EditText) findViewById(R.id.Register_Password);
        final EditText RegisterRePassword =(EditText) findViewById(R.id.Register_rePassword);
        final EditText RegisterUsername =(EditText) findViewById(R.id.Register_Username);
        TextView RegisterLogin =(TextView) findViewById(R.id.Register_Login);
        Button RegisterBtn =(Button) findViewById(R.id.Register_btn);

        fAuth = FirebaseAuth.getInstance();         //we get the current instance of the database
        fStore = FirebaseFirestore.getInstance();

        RegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = RegisterEmailID.getText().toString().trim();   //if the user uses space after entering the mailID, we get an error(to avoid this error we use trim)
                String password = RegisterPassword.getText().toString().trim();
                String passwordre = RegisterRePassword.getText().toString().trim();
                String username = RegisterUsername.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    RegisterEmailID.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    RegisterPassword.setError("Password is empty");
                    return;
                }
                if(password.length()<8){
                    RegisterPassword.setError("weak password");
                    return;
                }
                if(password.equals(passwordre) == false){
                    RegisterRePassword.setError("Both password dont match");
                    return;
                }
                if(TextUtils.isEmpty(username)){
                    RegisterUsername.setError("Username is empty");
                    return;
                }
                if(isValidPassword(password)){
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //we check if the process of registration is complete or not
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"User is created",Toast.LENGTH_SHORT).show();
                                userID = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("Username",username);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //Log.d(TAG,"onSuccess : user profile created for "+userID);
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(),RegisterUser.class));
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    RegisterPassword.setError("Add special characters to password");
                    return;
                }
                //firebase part


            }
        });
    }
}