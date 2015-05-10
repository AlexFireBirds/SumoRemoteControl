package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by dave_sch_6 on 04.05.15.
 */
public class DriveActivity extends Activity{

    private int speed = 0;
    private TextView TV_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TV_progress = (TextView) findViewById(R.id.SeekBarTextView);

        final SeekBar speedSB = (SeekBar) findViewById(R.id.SpeedSeekBar);
        TV_progress.setText("Covered: " + speedSB.getProgress() + "/" + speedSB.getMax());

        speedSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed = progress;
                TV_progress.setText("Covered: " + progress + "/" + seekBar.getMax());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        Button btnTop = (Button) findViewById(R.id.Button_top);
        final Button btnBottom = (Button) findViewById(R.id.Button_bottom);
        Button btnLeft = (Button) findViewById(R.id.Button_left);
        Button btnRight = (Button) findViewById(R.id.Button_right);
        Button btnStop= (Button) findViewById(R.id.Button_stop);

        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drive(4,speedSB.getProgress());
                Toast.makeText(getApplicationContext(), "Taped backward", Toast.LENGTH_SHORT).show();
            }
        });

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drive(1,speedSB.getProgress());
                Toast.makeText(getApplicationContext(), "Taped ahead", Toast.LENGTH_SHORT).show();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drive(3,speedSB.getProgress());
                Toast.makeText(getApplicationContext(), "Taped right", Toast.LENGTH_SHORT).show();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drive(2,speedSB.getProgress());
                Toast.makeText(getApplicationContext(), "Taped left", Toast.LENGTH_SHORT).show();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drive(0,0);
                Toast.makeText(getApplicationContext(), "Taped stop", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
