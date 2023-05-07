package com.example.selfimprovementnavigator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class remove_task_from_daily extends AppCompatActivity {
    ImageButton ib1;
    TextView tv1;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_task_from_daily);
        ib1=findViewById(R.id.ib1);
        tv1=findViewById(R.id.tv1);
        Intent intent=getIntent();
        int i=intent.getIntExtra("val",0);
        if(i==1){
            tv1.setText("DO YOU WANT TO DELETE THE TASK PRESS YES TO CONTINUE");
        }
        else{
            tv1.setText("HAVE YOU DONE THE TASK PRESS YES TO CONTINUE");
        }

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==1){
                    Intent intent=getIntent();
                    int ii=intent.getIntExtra("id",0);
                    database database=new database(getApplicationContext());
                    database.remove_data(ii);
                    finish();
                    startActivity(new Intent(getApplicationContext(),remove.class));
                }
                else{
                    Intent iget=getIntent();
                    int id=iget.getIntExtra("id",0);
                    database database=new database(getApplicationContext());
                    database.insert_into_todays_task(id);
                    finish();
                    startActivity(new Intent(getApplicationContext(),show.class));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
        startActivity(new Intent(getApplicationContext(),show.class));
    }
}