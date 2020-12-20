package com.example.nutrifir;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.Math;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    String Username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();

        final EditText RUser_Weight =(EditText) findViewById(R.id.SUser_Weight);
        final TextView Verify = (TextView) findViewById(R.id.Verification);
        final EditText RUser_Age =(EditText) findViewById(R.id.SUser_Age);
        final EditText RUser_Height =(EditText) findViewById(R.id.SUser_Height);
        final TextView RUsername = (TextView) findViewById(R.id.SUser_username);
        final Button RUser_cal = (Button)findViewById(R.id.SUser_cal);
        //RUser_cal.setVisibility(View.INVISIBLE);
        final ImageButton InfoButton = (ImageButton)findViewById(R.id.InfoButton);

        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterUser.this,"We require this information to calculate BMI.\nBody mass index (BMI) is a measure of body fat based on height and weight.\nIt can be viewed on your profile.",Toast.LENGTH_LONG).show();
            }
        });



        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException e) {
                RUsername.setText(value.getString("Username"));
            }
        });

        RUser_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user.isEmailVerified()){

                }else{
                    Verify.setText("User Not Verified.. Click here to Verify!");
                }

                    Verify.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Verify.setText("Verified User!");
                                        String Weight =RUser_Weight.getText().toString().trim();
                                        String Age = RUser_Age.getText().toString().trim();
                                        String Height = RUser_Height.getText().toString().trim();
                                        String Username = RUsername.getText().toString().trim();

                                        Double H = Double.parseDouble(Height);
                                        Double W = Double.parseDouble(Weight);
                                        Double H1 = H/100;
                                        Double H2 = Math.pow(H1,2);
                                        Double BMI1 = W/H2;
                                        String BMI = String.valueOf(BMI1);
                                        Map<String,Object> user1 = new HashMap<>();
                                        user1.put("Username",Username);
                                        user1.put("Height",Height);
                                        user1.put("Age",Age);
                                        user1.put("Weight",Weight);
                                        user1.put("BMI",BMI);
                                        documentReference.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //Log.d(TAG,"onSuccess : user profile created for "+userID);
                                            }
                                        });

                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                }
                            });
                        }
                    });


                }





        });

    }

}