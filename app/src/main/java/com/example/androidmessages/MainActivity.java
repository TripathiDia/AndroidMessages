package com.example.androidmessages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sms;
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
        message = findViewById(R.id.edtMsg);
        sms.setOnClickListener(view -> {
           sendMessage();
        });
    }

    private void sendMessage() {
        mobile = phoneNo.getText().toString();
        msg = message.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(mobile,null,msg,null,null);
        Toast.makeText(getApplicationContext(),"SMS Sent",Toast.LENGTH_SHORT);
    }


}