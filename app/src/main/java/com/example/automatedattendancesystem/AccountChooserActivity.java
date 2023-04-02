package com.example.automatedattendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

public class AccountChooserActivity extends AppCompatActivity {

    Button teacher_chooser;
    Button student_chooser;
    Button change_ssid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_chooser);

        teacher_chooser = findViewById(R.id.account_chooser_teacher_btn);
        student_chooser = findViewById(R.id.account_chooser_student_btn);
        change_ssid = findViewById(R.id.change_ssid);

    }
    public void teacher_chooser(View view) {
        Intent teacher_btn = new Intent(this, TeacherPrivateKey.class);
        startActivity(teacher_btn);
    }

    public void student_chooser(View view) {
        Intent student_btn = new Intent(this, LoginStudentActivity.class);
        startActivity(student_btn);
    }

    public void changeSSID(View view) {
            try {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
                WifiConfiguration wifiConfig = (WifiConfiguration) getConfigMethod.invoke(wifiManager);

                wifiConfig.SSID = "BEIT0001";


                Method setConfigMethod = wifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
                setConfigMethod.invoke(wifiManager, wifiConfig);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }
}
