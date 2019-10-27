package com.android.messaging.Controller;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.android.messaging.Authentication.Signup;
import com.android.messaging.Database.Message;

public class switch_btw extends AppCompatActivity {
    public void switch_signup(Context mContext) {
        Intent i = new Intent(mContext, Signup.class);
        finishAffinity();
        mContext.startActivity(i);
        finishAffinity();
    }
    public void trans(Context  mContext)
    {
        Intent intent = new Intent(mContext, Message.class);
        mContext.startActivity(intent);
        finishAffinity();
    }
}