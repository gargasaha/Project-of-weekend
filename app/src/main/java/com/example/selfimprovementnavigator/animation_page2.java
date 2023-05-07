package com.example.selfimprovementnavigator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class animation_page2 extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_page2);
        lottieAnimationView=findViewById(R.id.lav2);
        lottieAnimationView.setAnimation(R.raw.ani2);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),about_page.class));
                finish();
            }
        },4000);
    }
}