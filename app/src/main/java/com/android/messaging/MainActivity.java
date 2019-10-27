package com.android.messaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.messaging.Authentication.Userin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, Userin.class);
                startActivity(intent);
                finishAffinity();

            }
        },2000);



    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        System.exit(0);
    }
}
