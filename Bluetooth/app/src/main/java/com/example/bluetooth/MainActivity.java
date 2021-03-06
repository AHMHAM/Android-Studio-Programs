package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_ENABLE_BT = 1;
    Button onBtn;
    Button offBtn;
    Button listBtn;
    Button findBtn;
    TextView text;
    BluetoothAdapter myBluetoothAdapter;
    Set<BluetoothDevice> pairedDevices;
    ListView myListView;
    ArrayAdapter<String> BTArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView1);
        onBtn = (Button) findViewById(R.id.button1);
        myListView = (ListView)findViewById(R.id.listView1);
        offBtn = (Button) findViewById(R.id.button2);
        listBtn = (Button)findViewById(R.id.button3);
        findBtn = (Button)findViewById(R.id.button4);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (myBluetoothAdapter == null) {
            onBtn.setEnabled(false);
            offBtn.setEnabled(false);
            listBtn.setEnabled(false);
            findBtn.setEnabled(false);
            text.setText("Status: not supported");

            Toast.makeText(getApplicationContext(), "Your device does not support Bluetooth", Toast.LENGTH_LONG).show();
        }
        else {
            onBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!myBluetoothAdapter.isEnabled()) {
                        Intent turnOnIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(turnOnIntent, REQUEST_ENABLE_BT);

                        Toast.makeText(getApplicationContext(),"Bluetooth turned on" ,
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Bluetooth is already on",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBluetoothAdapter.disable();
                text.setText("Status: Disconnected");

                Toast.makeText(getApplicationContext(),"Bluetooth turned off",Toast.LENGTH_LONG).show();

            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get paired devices
                pairedDevices = myBluetoothAdapter.getBondedDevices();
                // put it's one to the adapter
                for(BluetoothDevice device : pairedDevices)
                    BTArrayAdapter.add(device.getName()+ "\n" + device.getAddress());
                Toast.makeText(getApplicationContext(),"Show Paired Devices",
                        Toast.LENGTH_SHORT).show();
            }
        });
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBluetoothAdapter.isDiscovering()) {
                    // the button is pressed when it discovers, so cancel the discovery
                    myBluetoothAdapter.cancelDiscovery();
                }
                else {
                    BTArrayAdapter.clear();
                    myBluetoothAdapter.startDiscovery();

                    registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
                }
            }
        });
        // create the arrayAdapter that contains the BTDevices, and set it to the ListView
        BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        myListView.setAdapter(BTArrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_ENABLE_BT){
            if(myBluetoothAdapter.isEnabled()) {
                text.setText("Status: Enabled");
            } else {
                text.setText("Status: Disabled");
            }
        }
    }
    final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // add the name and the MAC address of the object to the arrayAdapter
                BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                BTArrayAdapter.notifyDataSetChanged();
            }
        }
    };
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(bReceiver);
    }

}
