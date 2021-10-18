package com.example.messagecall;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText num;
    Button Call;
    Button send;
    EditText message;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = (EditText) findViewById(R.id.editText);
        message = (EditText) findViewById(R.id.editText2);
        Call = (Button) findViewById(R.id.Button);
        send = (Button) findViewById(R.id.Button2);


        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num.getText().toString()));
                startActivity(i);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + num.getText().toString()));
                is.putExtra("sms_body", message.getText().toString());

                startActivity(is);
            }
        });

    }
}