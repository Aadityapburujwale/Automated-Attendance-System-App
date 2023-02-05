package com.example.automatedattendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT= 500;

    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        splashImage = findViewById(R.id.spash_image_view);

        // Hide action bar
       // Objects.requireNonNull(getSupportActionBar()).hide();

//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_animation);
//        splashImage.startAnimation(animation);
//
//        splashImage.startAnimation(animation); // start animation

        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(() -> {
            Intent i=new Intent(SplashScreen.this,
                    AccountChooserActivity.class);
            //Intent is used to switch from one activity to another.

            startActivity(i);
            //invoke the SecondActivity.

            finish();
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT);


    }
}