package com.example.registrationformjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name =(EditText)findViewById(R.id.editText);
        final   EditText pass =(EditText)findViewById(R.id.editText2);
        RatingBar r=(RatingBar)findViewById(R.id.ratingBar);
        r.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this,""+rating,Toast.LENGTH_LONG).show();
            }
        });

        ImageButton login =(ImageButton)findViewById(R.id.imageButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("admin")&&pass.getText().toString().equals("admin"))
                {
                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_LONG).show();
                }
                else

                    Toast.makeText(MainActivity.this,"Falied",Toast.LENGTH_LONG).show();
            }
        });

    }

    
}