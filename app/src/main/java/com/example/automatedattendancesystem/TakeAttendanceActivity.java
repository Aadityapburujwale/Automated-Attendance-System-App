package com.example.automatedattendancesystem;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TakeAttendanceActivity extends AppCompatActivity {

    WifiManager mainWifiObj;
    WifiScanReceiver wifiReciever;
    String wifis[];

    TextView countDownTimer;
    int counter = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        countDownTimer = findViewById(R.id.timer);

        mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiReciever = new WifiScanReceiver();
        mainWifiObj.startScan();
        registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                countDownTimer.setText(String.valueOf(counter));
                counter--;
            }
            public  void onFinish(){
                unregisterReceiver(wifiReciever);
                Intent i = new Intent(TakeAttendanceActivity.this,ShowWifiListActivity.class);
                i.putExtra("wifi",wifis);
                startActivity(i);
            }
        }.start();

    }

    class WifiScanReceiver extends BroadcastReceiver {
        @SuppressLint("UseValueOf")
        public void onReceive(Context c, Intent intent) {
            List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
            wifis = new String[wifiScanList.size()];
            for(int i = 0; i < wifiScanList.size(); i++){
                wifis[i] = wifiScanList.get(i).BSSID;
                Toast.makeText(TakeAttendanceActivity.this,"MAC ID : " + wifiScanList.get(i).BSSID,Toast.LENGTH_SHORT).show();
            }
            String filtered[] = new String[wifiScanList.size()];
            int counter = 0;
            for (String eachWifi : wifis) {
                String[] temp = eachWifi.split(",");

//                filtered[counter] = temp[0].substring(5).trim();//+"\n" + temp[2].substring(12).trim()+"\n" +temp[3].substring(6).trim();//0->SSID, 2->Key Management 3-> Strength
                filtered[counter] = eachWifi;
                counter++;
            }
        }
    }

}