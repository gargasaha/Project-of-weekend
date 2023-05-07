package com.example.selfimprovementnavigator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class add extends AppCompatActivity {
    Toolbar tb1;
    Button select_all_days_but;
    ImageButton submit;
    CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7;
    EditText select_title;
    int sun=0,mon=0,tue=0,wed=0,thu=0,fri=0,sat=0;
    String title;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        tb1=findViewById(R.id.crtb1);
        setSupportActionBar(tb1);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        select_all_days_but=findViewById(R.id.select_all_days_but);
        cb1=findViewById(R.id.radioButton);
        cb2=findViewById(R.id.radioButton2);
        cb3=findViewById(R.id.radioButton3);
        cb4=findViewById(R.id.radioButton4);
        cb5=findViewById(R.id.radioButton5);
        cb6=findViewById(R.id.radioButton6);
        cb7=findViewById(R.id.radioButton7);
        submit=findViewById(R.id.sb1);
        select_title=findViewById(R.id.select_title);
        select_all_days_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb1.setChecked(true);
                cb2.setChecked(true);
                cb3.setChecked(true);
                cb4.setChecked(true);
                cb5.setChecked(true);
                cb6.setChecked(true);
                cb7.setChecked(true);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked()){
                    sun=1;
                }
                if(cb2.isChecked()){
                    mon=1;
                }
                if(cb3.isChecked()){
                    tue=1;
                }
                if(cb4.isChecked()){
                    wed=1;
                }
                if(cb5.isChecked()){
                    thu=1;
                }
                if(cb6.isChecked()){
                    fri=1;
                }
                if(cb7.isChecked()){
                    sat=1;
                }
                if(sun==0 && mon==0 && tue==0 && wed==0 && thu==0 && fri==0 && sat==0){
                    Toast.makeText(getApplicationContext(), "AT LEAST ONE DAY MUST BE SELECTED", Toast.LENGTH_SHORT).show();
                    return;
                }

                title=select_title.getText().toString();
                if(title.equals("")){
                    Toast.makeText(getApplicationContext(),"TITLE FIELD CAN'T BE BLANK",Toast.LENGTH_SHORT).show();
                    return;
                }
                database db=new database(getApplicationContext());
                int id=db.getid();
                db.insert_data_in_task_table(id,title,sun,mon,tue,wed,thu,fri,sat);
                Toast.makeText(add.this, "TASK ADDED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.create_task_page_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}