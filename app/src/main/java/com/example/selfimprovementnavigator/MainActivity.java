package com.example.selfimprovementnavigator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView lav1;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lav1=findViewById(R.id.ani1);
        lav1.setAnimation(R.raw.ani);
        lav1.playAnimation();
        database db=new database(getApplicationContext());
        int i=db.getstatus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(i==1){
                        Intent ihome=new Intent(getApplicationContext(),first_page.class);
                        startActivity(ihome);
                    }
                    else{
                        Intent ihome=new Intent(getApplicationContext(),log_in_page.class);
                        startActivity(ihome);
                    }
                    finish();
                }
            },4000);
        }
    }
}