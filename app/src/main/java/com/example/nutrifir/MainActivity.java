package com.example.nutrifir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    private TextView Steps;
    private  TextView Distance;
    private TextView Calories;
    private double MagnitudePrevious = 0;
    private Integer stepCount = 0;
    private ProgressBar progress;
    private Integer DistanceTraveled;
    private Integer DistanceTraveled1;
    private Integer CaloriesBurnt = 1;
    private Integer x=0;


    public Integer Weight,Height;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btn for test
        Steps = findViewById(R.id.textView);
        Distance = findViewById(R.id.DistanceH);
        Calories = findViewById(R.id.CaloriesH);
        progress = findViewById(R.id.progressBar);
        //TextView textView4 = (TextView)findViewById(R.id.textView4);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ImageButton Button_User = (ImageButton)findViewById(R.id.Button_User);

        Button_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent User = new Intent(MainActivity.this,User_page.class);
                MainActivity.this.startActivity(User);
            }
        });
        Button Button_workout = (Button)findViewById(R.id.Button_workout);
        Button_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Workout = new Intent(MainActivity.this, Workout_List.class);
                MainActivity.this.startActivity(Workout);
            }
        });

        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException e) {
                String weight = value.getString("Weight");
                String height = value.getString("Height");
                Integer Height = Integer.parseInt(height);
            }
        });

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent != null){
                    float x_accleration = sensorEvent.values[0];
                    float y_accleration = sensorEvent.values[1];
                    float z_accleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_accleration*x_accleration + y_accleration*y_accleration + z_accleration*z_accleration);

                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;
                    if(MagnitudeDelta > 6 ){
                        stepCount++;   //TODO send it in
                    }
                    Steps.setText(stepCount.toString());
                    progress.setProgress(stepCount);
                    //DistanceTraveled = (71*stepCount)/100;
                    DistanceTraveled = (56*stepCount)/100;
                    //DistanceTraveled = DistanceTraveled1 * Height;
                    //DistanceTraveled = (DistanceTraveled1 * 163)/100;
                    Distance.setText(DistanceTraveled.toString());
                    x=stepCount;
                    //distance * 0.000621371 * weight * 2.20462 * 0.653
                    //CaloriesBurnt= DistanceTraveled* 0.000621371*2.20462 * 0.653*65;
                    CaloriesBurnt = (DistanceTraveled*58)/100;
                    Calories.setText(CaloriesBurnt.toString());

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy){
            }
        };


        sensorManager.registerListener(stepDetector,sensor,SensorManager.SENSOR_DELAY_NORMAL);


        Button_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Workout = new Intent(MainActivity.this, Workout_List.class);
                MainActivity.this.startActivity(Workout);
            }
        });

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
    protected void onPause() {

            super.onPause();
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putInt("stepCount", stepCount);
            editor.apply();

    }
    protected void onStop() {

            super.onStop();
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putInt("stepCount", stepCount);
            editor.apply();

    }
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount",0);
    }

}