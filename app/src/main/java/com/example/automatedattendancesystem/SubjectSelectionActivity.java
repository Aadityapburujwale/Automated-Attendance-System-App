package com.example.automatedattendancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SubjectSelectionActivity extends AppCompatActivity {

    AutoCompleteTextView branchDropDown;
    AutoCompleteTextView classDropDown;
    AutoCompleteTextView subjectDropDown;
    AppCompatButton takeAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_selection);

        branchDropDown = findViewById(R.id.dropdown_branch);
        classDropDown = findViewById(R.id.dropdown_class);
        subjectDropDown = findViewById(R.id.dropdown_subject);
        takeAttendance = findViewById(R.id.take_attendance_btn);

        takeAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String branch = branchDropDown.getText().toString();
                String _class = classDropDown.getText().toString();
                String subject = subjectDropDown.getText().toString();

                if(branch.equals("Select Branch")){
                    branchDropDown.setError("Select Branch");
                    return;
                }
               else if(_class.equals("Select Class")){
                    classDropDown.setError("Select Class");
                    return;
                }
               else if(subject.equals("Select Subject")){
                    subjectDropDown.setError("Select Subject");
                    return;
                }else{

                    if(branch.equals("Information Technology")){
                        branch = "IT";
                    }else if(branch.equals("Computer Science")){
                        branch = "CS";
                    }

                    Intent i = new Intent(SubjectSelectionActivity.this,TakeAttendanceActivity.class);
                    i.putExtra("Branch",branch);
                    i.putExtra("Class",_class);
                    i.putExtra("Subject",subject);
                    startActivity(i);
                    finish();
                }

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        String designations[] = getResources().getStringArray(R.array.branches);
        String stations[] = getResources().getStringArray(R.array.classes);
        String subjects[] = getResources().getStringArray(R.array.subjects);

        ArrayAdapter<CharSequence> designationsAdapter = new ArrayAdapter(this, R.layout.dropdown_items, designations);
        branchDropDown.setAdapter(designationsAdapter);

        ArrayAdapter<CharSequence> stationsAdapter = new ArrayAdapter(this, R.layout.dropdown_items, stations);
        classDropDown.setAdapter(stationsAdapter);

        ArrayAdapter<CharSequence> subjectsAdapter = new ArrayAdapter(this, R.layout.dropdown_items, subjects);
        subjectDropDown.setAdapter(subjectsAdapter);
    }
}