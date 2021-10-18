package com.example.videoview2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    int pickvideorequest = 1;
    Button btnselect;
    VideoView videoviewer;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnselect = (Button) findViewById(R.id.button);
        videoviewer = (VideoView) findViewById(R.id.videoView);

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("video/*");
                startActivityForResult(Intent.createChooser(i,"Select Video"), pickvideorequest);
            }
        });

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(pictureIntent, 1);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pickvideorequest && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            videoviewer.setVideoURI(uri);
            mediaController= new MediaController(this);
            videoviewer.setMediaController(mediaController);
            videoviewer.start();


        }
    }
}