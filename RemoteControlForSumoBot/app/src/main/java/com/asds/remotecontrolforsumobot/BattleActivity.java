package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by dave_sch_6 on 11.05.15.
 */
public class BattleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battle);

        Button button = (Button) findViewById(R.id.Btn_calibrate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCalibrateCommand();
            }
        });

        Button start = (Button) findViewById(R.id.Btn_enterBattle);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBattle();
            }
        });

        Button stop = (Button) findViewById(R.id.Btn_exitBattle);
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                endBattle();
            }
        });

    }

    private void sendCalibrateCommand(){
        MainActivity.BattleCmd("calibrate");
    }

    private void startBattle(){
        MainActivity.BattleCmd("start");
    }
    private void endBattle(){
        MainActivity.BattleCmd("end");
    }
}
