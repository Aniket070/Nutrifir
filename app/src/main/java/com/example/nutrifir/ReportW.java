package com.example.nutrifir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ReportW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_w);
        TextView DEC = (TextView)findViewById(R.id.DEC);
        TextView Main = (TextView)findViewById(R.id.GoMain);
        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            String num = extra.getString("num");
            int c1 =Integer.parseInt(num);

            switch(c1){
                case 1:
                    DEC.setText("     Beginner \n\nMorning Warm Up\n\nTotal Time : 5mins\n\nCalories Burnt : 50");
                    break;
                case 2:
                    DEC.setText("     Beginner \n\nTABATA\n\nTotal Time : 4mins\n\nCalories Burnt : 63");
                    break;
                case 3:
                    DEC.setText("     Beginner \n\nFull Body Stretch\n\nTotal Time : 7mins\n\nCalories Burnt : 50");
                    break;
                case 4:
                    DEC.setText("     Intermediate \n\nFat Burning HIIT\n\nTotal Time : 10mins\n\nCalories Burnt : 200");
                    break;
                case 5:
                    DEC.setText("     Intermediate \n\nPlank Challange \n\nTotal Time : 12mins\n\nCalories Burnt : 150");
                    break;
                case 6:
                    DEC.setText("     Advanced \n\nBrutal Ladder HIIT\n\nTotal Time : 20mins\n\nCalories Burnt : 350");
                    break;
                case 7:
                    DEC.setText("     Advanced \n\nCalories Burner \n\nTotal Time : 20mins\n\nCalories Burnt : 350");
                    break;
                case 8:
                    DEC.setText("      \n\nSleep Time Stretch\n\nTotal Time : 8mins\n\nCalories Burnt : 50");
                    break;
            }
        }//TODO abdcvfljkv
        Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}