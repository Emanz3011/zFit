package com.z.fit.zfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SessionFinished extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_finished);

        //Save Completed Level
        FileHandler fh = new FileHandler(SessionFinished.this);
        fh.incrementLevel();
        ((RelativeLayout) findViewById(R.id.MainLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
