package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Created by Alexander on 11.05.2015.
 */
public class CrawlerActivity extends Activity{

    private SeekBar seekBarLeft;
    private SeekBar seekBarRight;

    private int leftCntr;
    private int rightCntr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive2);

        leftCntr = 100;
        rightCntr = 100;

        seekBarLeft = (SeekBar) findViewById(R.id.SB_left);
        seekBarRight = (SeekBar) findViewById(R.id.SB_right);

        seekBarLeft.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(leftCntr % 30 == 0) {
                   if (progress > 6000) {
                        MainActivity.drive(6, progress - 6000);
                    } else {
                       MainActivity.drive(6, -(progress - 6000));
                        //MainActivity.drive(8, 6000-progress);
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
                seekBarLeft.setProgress(6000);
            }
        });


        seekBarRight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(rightCntr % 30 == 0) {
                    if (progress > 6000) {
                        MainActivity.drive(5, progress - 6000);
                    } else {
                        MainActivity.drive(5, -(6000 - progress));
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
                seekBarRight.setProgress(6000);
            }
        });

    }


}
