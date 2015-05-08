package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;
    public final String TAG = "Main";

    public static Bluetooth bt;
    private TextView status;

    /**
     * Newly discovered devices
     */
    private ArrayAdapter<String> mArrayAdapter;
    private BroadcastReceiver mReceiver;
    private BluetoothAdapter mBluetoothAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.textStatus);

        bt = new Bluetooth(this, mHandler);
    }

    public static void drive(int direction, int speed)
    {
        switch(direction)
        {
            case 0:
                bt.sendMessage("drive speed L " + "0");
                bt.sendMessage("drive speed R " + "0");
            break;

            case 1:
                bt.sendMessage("drive speed L " + String.valueOf(speed));
                bt.sendMessage("drive speed R " + String.valueOf(speed));
            break;

            case 2:

                bt.sendMessage("drive speed R " + String.valueOf(speed));
                bt.sendMessage("drive speed L " + "-" + String.valueOf(speed));
            break;

            case 3:
                bt.sendMessage("drive speed L " + String.valueOf(speed));
                bt.sendMessage("drive speed R " + "-" + String.valueOf(speed));
            break;

            case 4:
                bt.sendMessage("drive speed R " + "-" + String.valueOf(speed));
                bt.sendMessage("drive speed L " + "-" + String.valueOf(speed));
            break;
            default:
                bt.sendMessage("drive speed L " + "0");     //invalid command emergency break
                bt.sendMessage("drive speed R " + "0");
                break;

        }
    }

    public void enableBluetooth(View v) {
        connectService();

    }

    public void discoverDevice(View v) {
        bt.sendMessage("drive speed L 1000");
       /* BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Make sure we're not doing discovery anymore
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }

        // Unregister broadcast listeners
        //this.unregisterReceiver(mReceiver);
    }


    public void ModeChooser (View v) {
        Intent modeChooser = new Intent(this,modeChooser.class);
        startActivity(modeChooser);
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Bluetooth.MESSAGE_STATE_CHANGE:
                    Log.d(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    break;
                case Bluetooth.MESSAGE_WRITE:
                    Log.d(TAG, "MESSAGE_WRITE ");
                    break;
                case Bluetooth.MESSAGE_READ:
                    Log.d(TAG, "MESSAGE_READ ");
                    break;
                case Bluetooth.MESSAGE_DEVICE_NAME:
                    Log.d(TAG, "MESSAGE_DEVICE_NAME "+msg);
                    break;
                case Bluetooth.MESSAGE_TOAST:
                    Log.d(TAG, "MESSAGE_TOAST "+msg);
                    break;
            }
        }
    };

    public void connectService(){
        try {
            status.setText("Connecting...");
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {

                String model = Build.MODEL;     //identify target Device
                bt.start();
                if(model.endsWith("GT-I9100")){
                    bt.connectDevice("BBerta");
                }else{
                    bt.connectDevice("Nemo");
                }
                Log.d(TAG, "Btservice started - listening");
                status.setText("Connected");
            } else {
                Log.w(TAG, "Btservice started - bluetooth is not enabled");
                status.setText("Bluetooth Not enabled");
            }
        } catch(Exception e){
            Log.e(TAG, "Unable to start bt ",e);
            status.setText("Unable to connect "); //+e would add the error text
        }
    }


}
