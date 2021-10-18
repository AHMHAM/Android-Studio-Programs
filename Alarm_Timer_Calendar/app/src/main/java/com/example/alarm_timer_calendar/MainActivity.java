package com.example.alarm_timer_calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3;
    EditText t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (EditText) findViewById(R.id.editText);
        t2 = (EditText) findViewById(R.id.editText2);
        t3 = (EditText) findViewById(R.id.editText3);
        t4 = (EditText) findViewById(R.id.editText4);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);


        //In manifest file add in "Intent Filter" :   <category android:name="android.intent.category.DEFAULT" />
        b1.setOnClickListener(new View.OnClickListener() {      // Create Alarm
            @Override                                           //<action android:name="android.intent.action.SET_ALARM" />
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Alarm Created", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, t1.getText().toString());        // message
                i.putExtra(AlarmClock.EXTRA_HOUR, t2.getText().toString());           // hour
                i.putExtra(AlarmClock.EXTRA_MINUTES, t3.getText().toString());       // minutes
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {      // Create Timer
            @Override                                           //  <action android:name="android.intent.action.SET_TIMER" />
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Timer Created", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AlarmClock.ACTION_SET_TIMER);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, t1.getText().toString());   //message
                i.putExtra(AlarmClock.EXTRA_LENGTH, t2.getText().toString());   //seconds
                i.putExtra(AlarmClock.EXTRA_SKIP_UI, false);
                startActivity(i);

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {      // Create Calender Event
            @Override                                           // // <action android:name="android.intent.action.INSERT" />
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Creating Calendar Event", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, t1.getText().toString())
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, t2.getText().toString())
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, t3.getText().toString())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, t4.getText().toString());
                startActivity(intent);
            }
        });


    }
}



  /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(getApplicationContext(),"Button Clicked", Toast.LENGTH_SHORT).show();

            }
        });
   */


/*
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r1.isChecked())
                    count++;
                else
                    count--;
                t.setText(count+"");

            }
        });

 */