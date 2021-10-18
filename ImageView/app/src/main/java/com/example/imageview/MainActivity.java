package com.example.imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btn, Capture, crop;
    ImageView image;
    int requestCD = 1;
    int requestcropCD = 1;
    final static int REQUEST_CODE_TAKE_PICTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        Capture=(Button)findViewById(R.id.button2);
        crop = (Button) findViewById(R.id.button3);
        image = (ImageView) findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, requestCD);
            }
        });

        Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(pictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(pictureIntent, 1);
                }
            }
        });

        crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status = Environment.getExternalStorageState();
                if (status.equals(Environment.MEDIA_MOUNTED))
                {
                    File tempfile = new File(Environment.getExternalStorageState() +"temp.jpg");
                    Uri tempuri = Uri.fromFile(tempfile);

                    Intent icrop = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    icrop.setType("image/*");
                    icrop.putExtra("crop","true");
                    icrop.putExtra("OutputFormat",Bitmap.CompressFormat.JPEG.toString());
                    startActivityForResult(icrop, requestcropCD);
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent i)      //Image View
    {
        super.onActivityResult(requestCode, resultCode, i);
        if(requestCode == requestCD && resultCode == RESULT_OK && i!=null && i.getData() != null)
        {
            Uri uri = i.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                image.setImageBitmap(bitmap);
            }catch (IOException e)
            {}
        }
        else if (requestCode == requestcropCD && resultCode == RESULT_OK && i!=null && i.getData() != null)
        {
            Bitmap croppedimage = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/temp.jpg");
            image.setImageBitmap(croppedimage);
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
}
