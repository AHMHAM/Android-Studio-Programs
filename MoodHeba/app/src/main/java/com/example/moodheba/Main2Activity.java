package com.example.moodheba;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button btn2;
    TextView txt;
    ProgressBar pb;
    CheckBox cb;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt=(TextView)findViewById(R.id.textView3);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        cb=(CheckBox)findViewById(R.id.checkBox);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        btn2=(Button)findViewById(R.id.button2);

        Intent i=getIntent();
        n=i.getStringExtra("name");

        txt.setText("Hi "+n+"");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feeling="";
                if(rb1.isChecked())
                {
                    feeling="Happy";
                }
                else if(rb2.isChecked())
                {
                    feeling="Sad";
                    feeling=rb2.getText().toString();
                }
                Intent ii=new Intent(Main2Activity.this, Main3Activity.class);
                ii.putExtra("name",n);
                ii.putExtra("feeling",feeling);
                startActivity(ii);

            }
        });


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    pb.setVisibility(View.VISIBLE);
                }
                else
                    pb.setVisibility(View.INVISIBLE);
            }
        });
    }
}
