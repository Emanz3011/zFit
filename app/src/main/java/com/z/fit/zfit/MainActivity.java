package com.z.fit.zfit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public int Level = 0;
    Button btnStart;
    FileHandler fileHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Setup();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Setup();
    }

    public void Setup(){
        //Load Level
        fileHandler = new FileHandler(MainActivity.this);
        Level = fileHandler.getLevel();
        Level+=1;

        //Initialize button
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                launchZFitSession();
            }
        });

        //Show Next Session Amounts
        ((TextView) findViewById(R.id.txtLevel)).setText("Level "+Level);
        ((TextView) findViewById(R.id.txtPercent)).setText(Calculations.getPercentage(Level) +"% increase");
        ((TextView) findViewById(R.id.txtJog)).setText("Jog for "+ Calculations.getJog(Level).getTimeString());
        ((TextView) findViewById(R.id.txtPushup)).setText("Do " + Calculations.getPushup(Level) + " Push Ups");
        ((TextView) findViewById(R.id.txtCrunch)).setText("Do " + Calculations.getCrunch(Level)+" Crunches");

        //Setup Alarm Notifications
        HandleAlarms();
    }

    public void launchZFitSession(){
        Intent zFitIntent = new Intent(MainActivity.this, zFit_Session.class);
        zFitIntent.putExtra("Level", Level); //Parameters
        MainActivity.this.startActivity(zFitIntent);
    }

    public void HandleAlarms(){
        AlarmManager alarmObj = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        Long timeNow = new GregorianCalendar().getTimeInMillis();
        Long timeTomorrow = timeNow - (timeNow % (24* 60 * 60 * 1000)) + (24* 60 * 60 * 1000);
        Long timeTillnext730 = timeTomorrow + (450 * 60 * 1000);/*7.5 hours x 60 minutes = 450*/

        System.out.println("Setting up alarm...");
        Intent zSchedule = new Intent(MainActivity.this, Scheduler.class);
        alarmObj.setExact(AlarmManager.RTC_WAKEUP,timeNow + timeTillnext730, PendingIntent.getActivity(this,0,zSchedule,PendingIntent.FLAG_CANCEL_CURRENT));

    }
}