package com.example.nutrifir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Workout_List extends AppCompatActivity {
    public String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout__list);

        ImageButton Button_B1 =(ImageButton)findViewById(R.id.Button_B1);
        ImageButton Button_B2 =(ImageButton)findViewById(R.id.Button_B2);
        ImageButton Button_B3 =(ImageButton)findViewById(R.id.Button_B3);
        ImageButton Button_B4 = (ImageButton)findViewById(R.id.Button_B4);
        ImageButton Button_B5 = (ImageButton)findViewById(R.id.Button_B5);
        ImageButton Button_B6 = (ImageButton)findViewById(R.id.Button_6B);
        ImageButton Button_B7 = (ImageButton)findViewById(R.id.Button_B7);
        ImageButton Button_B8 = (ImageButton)findViewById(R.id.Button_B8);
        //TODO activity 8


        Button_B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x ="1";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x="2";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x="3";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x ="4";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x ="5";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x ="6";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x ="7";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
        Button_B8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x ="8";
                Intent intent = new Intent(getBaseContext(),Workout_Main.class);
                intent.putExtra("number",x);
                startActivity(intent);
            }
        });
    }
}