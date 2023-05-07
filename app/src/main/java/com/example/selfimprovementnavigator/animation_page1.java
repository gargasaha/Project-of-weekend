package com.example.selfimprovementnavigator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class animation_page1 extends AppCompatActivity {
    LottieAnimationView lav;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_page1);
        lav=findViewById(R.id.lav1);
        lav.setAnimation(R.raw.ani2);
        lav.playAnimation();
        lav.loop(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),log_in_page.class));
                finish();
            }
        },2000);
    }
}