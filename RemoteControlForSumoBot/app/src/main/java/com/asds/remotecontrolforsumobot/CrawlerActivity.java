package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Created by Alexander on 11.05.2015.
 */
public class CrawlerActivity extends Activity{

    private final int SPEED_OFFSET = 6000;
    private final int SEND_SCALEDOWN = 30;

    private SeekBar seekBarLeft;
    private SeekBar seekBarRight;

    private int leftCntr;
    private int rightCntr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive2);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        leftCntr = 100;
        rightCntr = 100;

        seekBarLeft = (SeekBar) findViewById(R.id.SB_left);
        seekBarRight = (SeekBar) findViewById(R.id.SB_right);

        seekBarLeft.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(leftCntr % SEND_SCALEDOWN == 0) {
                   if (progress > SPEED_OFFSET) {
                        MainActivity.drive(6, progress - SPEED_OFFSET);
                    } else {
                       MainActivity.drive(6, -(SPEED_OFFSET - progress));
                    }
                    leftCntr = 0;
                }
                leftCntr++;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MainActivity.drive(6,0);
                seekBarLeft.setProgress(SPEED_OFFSET);
            }
        });


        seekBarRight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(rightCntr % SEND_SCALEDOWN == 0) {
                    if (progress > SPEED_OFFSET) {
                        MainActivity.drive(5, progress - SPEED_OFFSET);
                    } else {
                        MainActivity.drive(5, -(SPEED_OFFSET+1-progress));
                    }
                    rightCntr = 0;
                }
                rightCntr++;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MainActivity.drive(5,0);
                seekBarRight.setProgress(SPEED_OFFSET);
            }
        });

    }


}
