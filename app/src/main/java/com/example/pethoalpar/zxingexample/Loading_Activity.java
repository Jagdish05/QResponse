package com.example.pethoalpar.zxingexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rey.material.widget.ProgressView;

public class Loading_Activity extends AppCompatActivity {
    ProgressView circularProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_);

        Thread t = new Thread(){
            public void run(){
                try
                {
                    circularProgressBar = (ProgressView) findViewById(R.id.circular_progress2);
                   sleep(4000);
                    Intent i = new Intent(Loading_Activity.this,Login.class);
                    startActivity(i);
                    finish();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }

}
