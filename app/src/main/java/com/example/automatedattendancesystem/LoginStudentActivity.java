package com.example.automatedattendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);
    }

    public void student_registration_redirect(View view) {
        Intent registrationStudent = new Intent(this, StudentRegistrationActivity.class);
        startActivity(registrationStudent);
    }
}