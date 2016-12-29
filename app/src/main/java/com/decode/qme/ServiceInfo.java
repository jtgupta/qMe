package com.decode.qme;

/**
 * Created by anubhavj on 29/12/16.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ServiceInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView currentCustomerNum = (TextView) findViewById(R.id.currentCustText);
        TextView myCustomerNum = (TextView) findViewById(R.id.myNumText);



    }
}
