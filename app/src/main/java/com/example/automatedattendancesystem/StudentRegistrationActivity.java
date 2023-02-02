package com.example.automatedattendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.automatedattendancesystem.Constants.APIs;
import com.example.automatedattendancesystem.utils.APICaller;
import com.example.automatedattendancesystem.utils.Keyboard;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentRegistrationActivity extends AppCompatActivity {

    EditText fullNameET,emailET,mobileET,rollNumberET,passwordET;
    AutoCompleteTextView branchDropDown;
    AutoCompleteTextView classDropDown;
    Button registerBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        initializeView();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Keyboard.close(StudentRegistrationActivity.this);

                String name = fullNameET.getText().toString();
                String email = emailET.getText().toString();
                String mobileNumber = mobileET.getText().toString();
                String rollNumber = rollNumberET.getText().toString();
                String password = passwordET.getText().toString();
                String branch = fullNameET.getText().toString();
                String _class = fullNameET.getText().toString();

                doRegistration(name,email,mobileNumber,rollNumber,password,branch,_class);

            }
        });

    }

    private void initializeView() {

        fullNameET = findViewById(R.id.name_edittext);
        emailET = findViewById(R.id.email_edit_text);
        mobileET = findViewById(R.id.mobile_edit_text);
        rollNumberET = findViewById(R.id.roll_edit_text);
        passwordET = findViewById(R.id.password_edit_text);

        branchDropDown = findViewById(R.id.dropdown_branch);
        classDropDown = findViewById(R.id.dropdown_class);

        registerBtn = findViewById(R.id.btn_register);

        progressBar = findViewById(R.id.progressBar);

    }

    private void doRegistration(String name, String email, String mobileNumber, String rollNumber, String password, String branch, String aClass) {

        registerBtn.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        if(branch.equals("Information Technology")){
            branch = "IT";
        }else if(branch.equals("Computer Science")){
            branch = "CS";
        }

        // RequestQueue to make API Request
        RequestQueue requestQueue = APICaller.getInstance(this).getRequestQueue();

        // Data To Send As A Body Or Parameters To The Sign Up API
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("mobileNumber", mobileNumber);
        requestBody.put("degree", "BE");
        requestBody.put("branch", "IT");
        requestBody.put("rollNo", rollNumber);
        requestBody.put("password", password);
        requestBody.put("macAddress","MAC HERE");

        // Build API Request
        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, APIs.SIGN_UP_URL, new JSONObject(requestBody),
                response -> {

                    registerBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    if(response.has("studentId")){
                         try {
                             Toast.makeText(StudentRegistrationActivity.this,"Registration Successful!",Toast.LENGTH_SHORT).show();
                             Toast.makeText(StudentRegistrationActivity.this,"Your ID is "+response.get("studentId").toString(),Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(StudentRegistrationActivity.this, LoginStudentActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(StudentRegistrationActivity.this,"Email Already Registered!",Toast.LENGTH_SHORT).show();
                    }

                },
                error -> {

                    registerBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(StudentRegistrationActivity.this,"Error Occurred, Try again!",Toast.LENGTH_SHORT).show();
                });

        // Execute API Request
        requestQueue.add(loginRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String designations[] = getResources().getStringArray(R.array.branches);
        String stations[] = getResources().getStringArray(R.array.classes);

        ArrayAdapter<CharSequence> designationsAdapter = new ArrayAdapter(this, R.layout.dropdown_items, designations);
        branchDropDown.setAdapter(designationsAdapter);

        ArrayAdapter<CharSequence> stationsAdapter = new ArrayAdapter(this, R.layout.dropdown_items, stations);
        classDropDown.setAdapter(stationsAdapter);
    }
}