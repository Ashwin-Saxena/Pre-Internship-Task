package com.android.messaging.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.messaging.Controller.Auth_support;
import com.android.messaging.Controller.switch_btw;
import com.android.messaging.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Userin extends AppCompatActivity {
Button signi;
TextView t1;
private FirebaseAuth mAuth;
EditText email_id,password;
//ProgressDialog progressDialog;
Auth_support B=new Auth_support();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userin);

        t1=findViewById(R.id.textView3);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch_btw Aa = new switch_btw();
                Aa.switch_signup(Userin.this);
//                Intent i = new Intent(Userin.this, Signup.class);
//                startActivity(i);
//                finishAffinity();
            }
        });
        signi=findViewById(R.id.buttonsignin);
        email_id=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        mAuth=FirebaseAuth.getInstance();
        signi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressDialog=ProgressDialog.show(Userin.this,"Please Wait","Signing you In",true);
                B.start(Userin.this);


                mAuth=FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email_id.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(Userin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    switch_btw A =new switch_btw();
                                    B.stop(Userin.this);
//                                    progressDialog.dismiss();
                                    A.trans(Userin.this);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Userin.this, "Authentication failed. \n "+task.getException(),Toast.LENGTH_SHORT).show();
//                               progressDialog.dismiss();
                                    B.stop(Userin.this);



                                }

                                // ...
                            }
                        });
            }
        });


    }

//    public void updateUI(FirebaseUser user)
//    {
//        Intent intent=new Intent(Userin.this, Message.class);
//        startActivity(intent);
//        finishAffinity();
//    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser=mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            switch_btw A =new switch_btw();
            finishAffinity();
            A.trans(Userin.this);
        }

    }
}
