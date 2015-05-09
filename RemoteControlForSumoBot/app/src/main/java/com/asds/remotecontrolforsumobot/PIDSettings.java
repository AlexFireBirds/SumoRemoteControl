package com.asds.remotecontrolforsumobot;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by dave_sch_6 on 07.05.15.
 */
public class PIDSettings extends Activity {

    private short long_PValue = 0;
    private short long_IValue = 0;
    private short long_DValue = 0;

    private final String KEY_PVAL = "PVal";
    private final String KEY_IVAL = "IVal";
    private final String KEY_DVAL = "DVal";

    private EditText ET_Pval;
    private EditText ET_Ival;
    private EditText ET_Dval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pid);

        this.InitComponents();
    }

    @Override
    protected void onResume(){
        super.onResume();

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final Map Map_PIDVal = preferences.getAll();

        if(Map_PIDVal.size()<3){
            Map_PIDVal.put(KEY_PVAL,0);
            Map_PIDVal.put(KEY_IVAL,0);
            Map_PIDVal.put(KEY_DVAL,0);
        }

        ET_Pval.setText(Map_PIDVal.get(KEY_PVAL).toString());
        ET_Ival.setText(Map_PIDVal.get(KEY_IVAL).toString());
        ET_Dval.setText(Map_PIDVal.get(KEY_DVAL).toString());

    }

    public void sendPIDValues(){

        int p = Integer.valueOf(ET_Pval.getText().toString());
        int i = Integer.valueOf(ET_Ival.getText().toString());
        int d = Integer.valueOf(ET_Dval.getText().toString());

        final SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_PVAL,p);
        editor.apply();
        editor.putInt(KEY_IVAL,i);
        editor.apply();
        editor.putInt(KEY_DVAL,d);
        editor.apply();

        /* Send values to the SumoBot
          param FALSE means right motor
          param TRUE means left motor */
        MainActivity.setPIDValues(p,i,d,Boolean.FALSE);
        MainActivity.setPIDValues(p,i,d,Boolean.TRUE);
    }

    private void InitComponents(){

        ET_Pval = (EditText) findViewById(R.id.PID_PVal_TV);
        ET_Ival = (EditText) findViewById(R.id.PID_IVal_TV);
        ET_Dval = (EditText) findViewById(R.id.PID_DVal_TV);

        Button sendValues = (Button) findViewById(R.id.BtnSendPID);
        sendValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPIDValues();
            }
        });

    }
}
