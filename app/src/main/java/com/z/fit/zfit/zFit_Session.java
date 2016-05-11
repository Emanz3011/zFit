package com.z.fit.zfit;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Scanner;

public class zFit_Session extends AppCompatActivity {

    public int Level;
    FileHandler fileHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfit_session);

        //Collect Data
        Intent intent = getIntent();
        Level = intent.getIntExtra("Level", 1);

        //SetupButtons
        ((LinearLayout) findViewById(R.id.sectionJog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckBox) findViewById(R.id.checkJog)).toggle();
                checkChecklist();
            }
        });
        ((LinearLayout) findViewById(R.id.sectionPushup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckBox) findViewById(R.id.checkPushup)).toggle();
                checkChecklist();
            }
        });
        ((LinearLayout) findViewById(R.id.sectionCrunch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckBox) findViewById(R.id.checkCrunch)).toggle();
                checkChecklist();
            }
        });
        ((CheckBox) findViewById(R.id.checkJog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkChecklist();
            }
        });
        ((CheckBox) findViewById(R.id.checkPushup)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkChecklist();
            }
        });
        ((CheckBox) findViewById(R.id.checkCrunch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkChecklist();
            }
        });
        ((Button) findViewById(R.id.btnFinish)).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   //Show SessionFinished screen
                   Intent zFinished = new Intent(zFit_Session.this, SessionFinished.class);
                   zFinished.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                   startActivity(zFinished);
                   finish();
               }
           }
        );
        ((Button)findViewById(R.id.btnTimer)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_TIMER).putExtra(AlarmClock.EXTRA_LENGTH, Calculations.getJog(Level).getTimeInt());
                openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openClockIntent);
            }
        });

        //Show Session Amounts
        ((TextView) findViewById(R.id.Title)).setText("Level " + Level);
        ((TextView) findViewById(R.id.txtJog)).setText("Jog for " + Calculations.getJog(Level).getTimeString());
        ((TextView) findViewById(R.id.txtPushup)).setText("Do " + Calculations.getPushup(Level) + " Push Ups");
        ((TextView) findViewById(R.id.txtCrunch)).setText("Do " + Calculations.getCrunch(Level) + " Crunches");
    }

    public void checkChecklist() {
        if (((CheckBox) findViewById(R.id.checkJog)).isChecked()
                && ((CheckBox) findViewById(R.id.checkPushup)).isChecked()
                && ((CheckBox) findViewById(R.id.checkCrunch)).isChecked()) {

            ((Button) findViewById(R.id.btnFinish)).setEnabled(true);
        } else {
            ((Button) findViewById(R.id.btnFinish)).setEnabled(false);
        }
    }
}
