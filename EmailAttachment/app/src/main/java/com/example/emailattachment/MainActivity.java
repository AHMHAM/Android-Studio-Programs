package com.example.emailattachment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button att,snd;
    EditText to,sub,msg;
    Uri uri = null;
    int reqcode=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        att = (Button) findViewById(R.id.button);
        snd = (Button) findViewById(R.id.button2);
        to = (EditText) findViewById(R.id.editText);
        sub = (EditText) findViewById(R.id.editText2);
        msg = (EditText) findViewById(R.id.editText3);

        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent isend = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto"))
                        .putExtra(Intent.EXTRA_EMAIL, to.getText().toString())
                        .putExtra(Intent.EXTRA_SUBJECT, msg.getText().toString())
                        .putExtra(Intent.EXTRA_TEXT, sub.getText().toString());
                if(uri != null)
                isend.putExtra(Intent.EXTRA_STREAM, uri);

                startActivity(isend);
            }
        });

        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iattach=new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(iattach,reqcode);

            }
        });
    }

    protected void OnActivityResult(int requestCode, int resultCode, Intent i)
    {
        super.onActivityResult(requestCode, resultCode, i);
        if (requestCode == reqcode && resultCode == RESULT_OK && i!=null && i.getData() != null)
            uri = i.getData();
    }
}
