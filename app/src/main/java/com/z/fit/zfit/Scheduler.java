package com.z.fit.zfit;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Scheduler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        ((Button) findViewById(R.id.btnSetAlarm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Remove Previous Alarms
                        //Set Alarm
                        Intent setAlarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        setAlarmIntent.putExtra(AlarmClock.EXTRA_HOUR,hourOfDay);
                        setAlarmIntent.putExtra(AlarmClock.EXTRA_MINUTES,minute);
                        setAlarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE,"Next zFit Session");
                        setAlarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
                        startActivity(setAlarmIntent);
                        /*if (hourOfDay > 12)
                            ShowToast("Alarm set for " + (hourOfDay - 12) + ":" + minute + "pm");
                        else
                            ShowToast("Alarm set for " + (hourOfDay) + ":" + minute + "am");*/

                    }
                };
                newFragment.show(getFragmentManager(),"timePicker");
            }
        });
    }

    public void ShowToast(String sMessage){
        Toast.makeText(this,sMessage,Toast.LENGTH_LONG).show();
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, 17, 30,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        }
    }
}
