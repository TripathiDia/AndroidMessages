package com.example.androidmessages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button sms, defaultSms;
    EditText phoneNo, message;
    String mobile, msg;
    private static final int REQUEST_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
        sms = findViewById(R.id.btnSendMsg);
        phoneNo = findViewById(R.id.edtNumber);
        defaultSms = findViewById(R.id.btnSendDefault);
        message = findViewById(R.id.edtMsg);
        sms.setOnClickListener(view -> {
            if(phoneNo.getText().length() == 0) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "Please enter Phone Number", Snackbar.LENGTH_SHORT).show();
            } else if(message.getText().length() == 0) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "Please enter Message to send", Snackbar.LENGTH_SHORT).show();
            } else {
                sendMessage();
            }
        });

        defaultSms.setOnClickListener(view -> {
            sendDefaultMessage();
        });
    }

    private void sendMessage() {
        mobile = phoneNo.getText().toString();
        msg = message.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(mobile,null,msg,null,null);
        Snackbar.make(getWindow().getDecorView().getRootView(), "SMS Sent Successfully", Snackbar.LENGTH_SHORT).show();
    }

    private void sendDefaultMessage() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String("7021741580"));
        smsIntent.putExtra("sms_body", "This is auto generated Message");

        try {
            startActivity(smsIntent);
            finish();
        } catch (ActivityNotFoundException e) {
            Snackbar.make(getWindow().getDecorView().getRootView(), "SMS Sending Failed", Snackbar.LENGTH_SHORT).show();
            System.out.println("SMS Sending Failed");
        };


    }


}