package com.example.automatedattendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountChooser extends AppCompatActivity {

    Button teacher_chooser;
    Button student_chooser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_chooser);

        teacher_chooser = findViewById(R.id.account_chooser_teacher_btn);
        student_chooser = findViewById(R.id.account_chooser_student_btn);



    }
    public void teacher_chooser(View view) {
        Intent teacher_btn = new Intent(this, LoginTeacherActivity.class);
        startActivity(teacher_btn);
    }

    public void student_chooser(View view) {
        Intent student_btn = new Intent(this, LoginStudentActivity.class);
        startActivity(student_btn);
    }
}
