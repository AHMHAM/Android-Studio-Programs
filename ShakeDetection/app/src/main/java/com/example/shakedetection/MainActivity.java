package com.example.shakedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {        //implements creates onSensorChanged method

    SensorManager mgr;
    final int shakethreshold=800;
    float lastx;
    float lasty;
    float lastz;
    long lastupdate;

    @Override
    protected void onPause() {            //Code --> Override Methods --> OnPause
        super.onPause();
        mgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mgr.registerListener(this, mgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgr=(SensorManager) getSystemService(SENSOR_SERVICE);
        lastupdate=System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        float[] values = event.values;
        float x=values[0];
        float y=values[1];
        float z=values[2];
        long currenttime = System.currentTimeMillis();
        long difftime = currenttime - lastupdate;
        lastupdate = currenttime;
        float speed = (x+y+z+lastx+lasty+lastz)/difftime*1000;
        if(speed > shakethreshold) {
            Toast.makeText(getApplicationContext(),"Shake Detected", Toast.LENGTH_SHORT).show();
        }
        }
}
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
