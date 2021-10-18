package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btn, Capture;
    VideoView image;
    int requestCD = 1;
    final static int REQUEST_CODE_TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        Capture = (Button) findViewById(R.id.button2);
        image = (VideoView) findViewById(R.id.videoView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("video/*");
                startActivityForResult(i, requestCD);
            }
        });

        Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(pictureIntent, 1);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent i)      //View
    {
        super.onActivityResult(requestCode, resultCode, i);
        if (requestCode == requestCD && resultCode == RESULT_OK && i != null && i.getData() != null) {
            Uri uri = i.getData();
                // Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                Uri bitmap = MediaStore.Video.Media.getContentUri(String.valueOf(uri));
                image.setVideoURI(bitmap);
                //  image.setImageBitmap(bitmap);

        }
    }
}

/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {     //Image Caputre
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PICTURE  && resultCode == RESULT_OK)
        {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(imageBitmap);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings)
     //       return true;

        return super.onOptionsItemSelected(item);
    }

 */
