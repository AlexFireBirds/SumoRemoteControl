package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class modeChooser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_chooser);

        Button button = (Button) findViewById(R.id.RemoteControl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDriveActivity();
            }
        });

        Button BtnPID = (Button) findViewById(R.id.Btn_PID);
        BtnPID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPIDActivity();
            }
        });

        Button BtnTest = (Button) findViewById(R.id.BtnCrawlerControl);
        BtnTest.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                startCrawlerActivity();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mode_chooser, menu);
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

    private void startDriveActivity(){
        Intent driveRemote = new Intent(this,DriveActivity.class);
        startActivity(driveRemote);
    }

    private void startPIDActivity(){
        Intent driveRemote = new Intent(this,PIDSettings.class);
        startActivity(driveRemote);
    }

    private void startCrawlerActivity(){
        Intent driveRemote = new Intent(this,CrawlerActivity.class);
        startActivity(driveRemote);
    }
}
