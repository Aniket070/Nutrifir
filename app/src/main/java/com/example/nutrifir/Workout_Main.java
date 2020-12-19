package com.example.nutrifir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class Workout_Main extends AppCompatActivity {

    private Chronometer chronometer;
    private boolean running;
    private long pauseOffset;
    public String c;
    public String timework;
    public String xxxx;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout__main);
        TextView Workout_dec = (TextView)findViewById(R.id.Workout_dec);
        TextView Workout_cal = (TextView)findViewById(R.id.Workout_cal);
        TextView GetReport = (TextView)findViewById(R.id.GetReport);
        chronometer = findViewById(R.id.Chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

            }
        });

        Bundle extra = getIntent().getExtras();


        if(extra != null){
            String number = extra.getString("number");
            int x = Integer.parseInt(number); //TODO ajdcvk



            switch (x){
                case 1:
                        Workout_dec.setText("Bridge\nReverse Crunches\nAbdominal Crunches\nButt Bridge\nBird Dog\nPlank\nCobra Stretch\nShoulder Stretch\nButt Kicks\nJumping Jacks");
                        Workout_cal.setText("20sec\n20sec\n20sec\n30sec\n30sec\n30sec\n30sec\n20sec\n30sec\n30sec");
                        c="1";
                    break;
                case 2:
                    Workout_dec.setText("Mountain Climber\nJumping Squats\nBurpees\nJumping Jacks\nButt Kicks\nBurpees \nJumping Squats\nMountain Climber");
                    Workout_cal.setText("20sec\n20sec\n20sec\n20sec\n20sec\n20sec\n20sec\n20sec");
                    c="2";
                    break;
                case 3:
                    Workout_dec.setText("Shoulder Stretch\nStanding Side Bend\nChest Stretch\nTriceps Stretch\nSpine Lumbar Twist Stretch\nKneeling Lunge Stretch\nCalf Stretch \nSeated Butterfly\nCobra Stretch\nChild's Pose");
                    Workout_cal.setText("20sec\n20sec\n20sec\n40sec\n40sec\n\n60sec\n40sec\n30sec\n20sec\n30sec");
                    c="3";
                    break;
                case 4:
                    Workout_dec.setText("a)Single Leg Hip Rotation\nb)Squat Reach Ups \nc)Skater Jump\nd)Side Hop\ne)Alternating Hooks\nf)Butt Kicks\ng)Jumping Jacks\nRepeat d, e, f, g\nRepeat d, e, f, g\nh)Right Quad Stretch with Wall\n i) Left Quad Stretch with Wall");
                    Workout_cal.setText("30secs\n30secs\n30secs\n30secs\n30secs \n30secs \n30secs \n20secs\n10secs \n30secs\n30secs");
                    c="4";
                    break;
                case 5:
                    Workout_dec.setText("Plank\n Plank jacks\n Diagonal plank\n Side plank\n Plank and reach\n Plank hip dips\n Side bridges\n Cross knee plank\n Mountain climber\n Side leg raise\n Side plank front kick\n In & outs\n Crab kick up\n Straight Arm plank");
                    Workout_cal.setText("30secs\n30sec\n30secs\n40secs\n30secs\n30secs\n40secs\n30secs\n30secs\n40secs\n40secs\n30secs\n30secs\n30secs");
                    c="5";
                    break;
                case 6:
                    Workout_dec.setText("a)Single leg hip rotation\nb)Knee to elbow crunches\nc)Squat Reach Ups\nd)Skater Jump\ne)Mountain climber\nf)Scissors\ng)High stepping\nh)Modified burpees\ni)Mountain climber\nRepeat f, g, h\nj)Squats\n Repeat f, g, h\nk)Jumping Jacks\nRepeat f, g, h, j\nRepeat f, g, h, k\nl)Quad Stretch with Wall\nm)Calf stretch\nn)Cobra stretch");
                    Workout_cal.setText("30secs\n30secs\n30secs\n30secs\n30secs\n50secs\n50secs\n50secs\n50secs\n40secs\n40secs\n30secs\n30secs\n20secs\n10secs\n40secs\n40secs\n30secs");
                    c="6";
                    break;
                case 7:
                    Workout_dec.setText("a)Squat reach ups\nb)Triceps dips\nc)Diagonal plank\nd)Inchworms\ne)Long arm crunches\nf)Reverse crunches\ng)Bent leg twist\nh)Bicycle crunches\ni)Dead bug\nj)Cross knee plank\nk)Froggy glute lifts\nl)Frog press\nm)Donkey kicks\nn)In & outs\nRepeat b and c\no)Plank jacks\np)Mountain climber\nq)Child’s pose");
                    Workout_cal.setText("30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n30secs\n40secs\n30secs\n\n30secs\n30secs\n30secs");
                    c="7";
                    break;
                case 8:
                    Workout_dec.setText("a)Quad Stretch With Wall\nb)Calf Stretch\nc)Triceps Stretch\nd)Cobra Stretch\ne)Child's Pose\nf)Cat Cow Pose\ng)Bridge\nh)Lying Butterfly Stretch\ni)Lying Twist Stretch");
                    Workout_cal.setText("40secs\n40secs\n60secs\n30secs\n30secs\n40secs\n30secs\n50secs\n40secs");
                    c="8";
                    break;
            }
        }


        GetReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ReportW.class);
                intent.putExtra("num",c);
                startActivity(intent);


            }
        });
    }





    public void startChronometer(View v){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v){
        if(running){
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            running = false;
        }
    }
    public void resetChronometer(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;

    }
}