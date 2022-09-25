package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class RealSplash extends AppCompatActivity {

    LottieAnimationView splash;
    TextView pShivam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_splash);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splash = findViewById(R.id.splash);
        pShivam = findViewById(R.id.pShivam);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                pShivam.setVisibility(View.VISIBLE);

            }
        }, 2000);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                splash.playAnimation();

            }
        }, 500);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(RealSplash.this,Splash_screen.class);
                startActivity(mainIntent);
                finish();

            }
        }, 5000);

    }
}