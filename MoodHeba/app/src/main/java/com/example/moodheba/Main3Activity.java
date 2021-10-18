package com.example.moodheba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.accounts.AccountManager;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView txt2;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt2=(TextView)findViewById(R.id.textView4);
        btn3=(Button)findViewById(R.id.button3);
        Intent i= getIntent();
        txt2.setText(i.getStringExtra("name")+" is "+i.getStringExtra("feeling"));



        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(iii);
            }
        });
    }
}
