package com.decode.qme;

/**
 * Created by anubhavj on 29/12/16.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ServiceInfo extends AppCompatActivity {


    private static final String TAG = "ServiceInfo";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);

        String queueId = getIntent().getStringExtra("queueId");
        final long myPos = getIntent().getLongExtra("myPos",0);

        DatabaseReference queueRef = FirebaseDatabase.getInstance().getReference().child("queue").child(queueId);

        final TextView currentCustomerNum = (TextView) findViewById(R.id.currentCustText);
        final TextView myCustomerNum = (TextView) findViewById(R.id.myNumText);

        Log.d(TAG, "onCreate: activity reached ");

        queueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long current = (long)dataSnapshot.child("firstuser").getValue();
                if(current==myPos)
                {
                    Toast.makeText(ServiceInfo.this, "You are being served", Toast.LENGTH_SHORT).show();
                }
                if(current>myPos)
                {
                    Toast.makeText(ServiceInfo.this, "You have been served!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ServiceInfo.this,HomeActivity.class);
                    startActivity(intent);
                }
                currentCustomerNum.setText(String.valueOf(current));
                myCustomerNum.setText(String.valueOf(myPos));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
