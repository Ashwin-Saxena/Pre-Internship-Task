package com.android.messaging.Controller;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class Auth_support extends AppCompatActivity {
    ProgressDialog progressDialog;

    public void start(Context mContext)
    {
        progressDialog=ProgressDialog.show(mContext,"Loading","Signing you in",true);
    }
    public  void stop(Context mContext)
    {
        progressDialog.dismiss();
    }
}
