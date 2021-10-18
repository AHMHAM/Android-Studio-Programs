package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int CONVERT_ID = 1;
    TextView text;
    Button speechtotext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    text = (TextView) findViewById(R.id.textView);
    speechtotext = (Button) findViewById(R.id.button);

    speechtotext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "uk");
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start Talking..........");
            startActivityForResult(i, CONVERT_ID);
        }
    });


    }           //On Create end


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && data != null && requestCode ==CONVERT_ID)
        {
            ArrayList<String> out = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            text.setText((out.get(0)));
        }
    }
}
