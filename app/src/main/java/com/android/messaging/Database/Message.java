package com.android.messaging.Database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.messaging.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Message extends AppCompatActivity {
private FirebaseAuth mAuth;
Button send,id_sub;
public String myusername,otherusername,username,common_id,glob_uidd,uidd;
TextView m,head,line;
    private ProgressDialog progressDialog;
public ProgressDialog pr;
EditText t1,msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        head=findViewById(R.id.textView5);
        id_sub=findViewById(R.id.id_sub);
        m=findViewById(R.id.textView4);
        msg=findViewById(R.id.editText2);
        t1=findViewById(R.id.editText);
        send=findViewById(R.id.send);
        mAuth=FirebaseAuth.getInstance();
        m.setMovementMethod(new ScrollingMovementMethod());


        id_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog=ProgressDialog.show(Message.this,"Loading","Your Previous Messages" ,true);
                uidd = t1.getText().toString();
                DatabaseReference get_uid=FirebaseDatabase.getInstance().getReference().child("UID");
                get_uid.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        glob_uidd=dataSnapshot.child(t1.getText().toString()).getValue(String.class);
                        Toast.makeText(Message.this,glob_uidd,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                uidd=glob_uidd;
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseReference usernames=FirebaseDatabase.getInstance().getReference().child("Users");
                        usernames.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                myusername=dataSnapshot.child(mAuth.getUid()).getValue().toString();
//                        Toast.makeText(Message.this,uidd,Toast.LENGTH_SHORT).show();
//                        otherusername=dataSnapshot.child(uidd).getValue().toString();
                                head.setVisibility(View.VISIBLE);
                                head.setText(t1.getText().toString());
                                common_id=generate_com_id(mAuth.getUid(),glob_uidd);
//                                Toast.makeText(Message.this,common_id,Toast.LENGTH_SHORT).show();
//                         generate_com_id(mAuth.getUid(),uidd);
//                        Toast.makeText(Message.this,"Sent by : "+myusername +"\n Sent To : "+otherusername,Toast.LENGTH_SHORT).show();
                                t1.setEnabled(false);
                                t1.setVisibility(View.INVISIBLE);
                                id_sub.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                },4000);


                //////////////////////Reading Previous Messages ///////////////////////
Handler handler = new Handler();
handler.postDelayed(new Runnable() {
    @Override
    public void run() {
        //////////////////////Reading Previous Messages ///////////////////////
        DatabaseReference read_msg=FirebaseDatabase.getInstance().getReference().child("Messages").child(common_id);
        read_msg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
m.setText("");
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {String mesg=postSnapshot.child("MSG").getValue(String.class);
                    String name = postSnapshot.child("Name").getValue(String.class);
                    String last_msg=m.getText().toString();
                    m.setText(last_msg+"\n     "+name+":\n     "+mesg+"\n");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
},8000);




           Handler handler1 =new Handler();
           handler1.postDelayed(new Runnable() {
               @Override
               public void run() {
                   progressDialog.dismiss();
               }
           },8500);

            }
        });


        //////For Reading Previous Messages /////////////////////////////////////////////////////////////////////////////////////////////////////////

//        DatabaseReference us_ref_mes=FirebaseDatabase.getInstance().getReference().child("Messages").child("qnbRdl07sigrqdzfEwnpaL8ZoMq1_refSREdwe23sfdEWD");
//        us_ref_mes.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String my_name =dataSnapshot.child("Username").getValue(String.class);
//                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
//                    String Mssg=postSnapshot.child("MSG").getValue().toString();
//                    String name=postSnapshot.child("Name").getValue().toString();
//                    String h=m.getText().toString();
//                    m.setText(h+"\n\n"+name+"\n"+Mssg);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String com_id=mAuth.getCurrentUser().getUid()+"_"+t1.getText().toString();
        DatabaseReference us_ref=FirebaseDatabase.getInstance().getReference().child("Messages").child(common_id).push();
        DatabaseReference ss = FirebaseDatabase.getInstance().getReference().child("Users");
        ss.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username=dataSnapshot.child(mAuth.getUid()).getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        us_ref.child("Name").setValue(myusername);
        us_ref.child("MSG").setValue(msg.getText().toString());

        m.append("\n\n"+myusername+":\n"+msg.getText().toString());
        msg.setText("");



    }
});







    }

    public String  generate_com_id(String min,String othe)
    {
        char mine_id=min.charAt(0),  otherid=othe.charAt(0);
        int mine=(int)mine_id;
        int other=(int)otherid;
        if(mine >other)
        {
            return  othe+"_"+min;
        }
        else {
            return  min+"_"+othe;
        }

    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
