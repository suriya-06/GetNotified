package com.example.getnotified;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.getnotified.Util.Util;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private Button saveButton;
    private Button stopButton;
    private Ringtone ringtone;
    private String value="";
    private Integer battery=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        saveButton = findViewById(R.id.save);
        stopButton = findViewById(R.id.stop);


        saveButton.setOnClickListener(view -> {
            value=editText.getText().toString();
            battery=Integer.parseInt(value);
            Util.hideKeyBoard(MainActivity.this,view);
            Toast.makeText(MainActivity.this,"Battery Constraint "+value.toString()+"%",Toast.LENGTH_LONG).show();

        });
        ringtone= RingtoneManager.getRingtone(MainActivity.this,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int value=intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                if(battery==value){
                    ringtone.play();
                }
            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        stopButton.setOnClickListener(view -> {
            ringtone.stop();
        });
    }
}