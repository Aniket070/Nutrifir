package com.example.nutrifir;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class UserSetting extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        final EditText SUser_Weight =(EditText) findViewById(R.id.SUser_Weight);
        final EditText SUser_Age =(EditText) findViewById(R.id.SUser_Age);
        final EditText SUser_Height =(EditText) findViewById(R.id.SUser_Height);
        final TextView SUsername = (TextView) findViewById(R.id.SUser_username);
        final Button SUser_cal = (Button)findViewById(R.id.SUser_cal);
        final ImageButton InfoButton = (ImageButton)findViewById(R.id.InfoButton);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();

        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserSetting.this,"We require this information to calculate BMI.\nBody mass index (BMI) is a measure of body fat based on height and weight.\nIt can be viewed on your profile.",Toast.LENGTH_LONG).show();
            }
        });

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException e) {
                SUsername.setText(value.getString("Username"));
            }
        });
        SUser_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Weight =SUser_Weight.getText().toString().trim();
                String Age = SUser_Age.getText().toString().trim();
                String Height = SUser_Height.getText().toString().trim();
                String Username = SUsername.getText().toString().trim();

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
        });


    }
}