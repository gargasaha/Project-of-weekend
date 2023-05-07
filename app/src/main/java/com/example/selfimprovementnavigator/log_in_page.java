package com.example.selfimprovementnavigator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class log_in_page extends AppCompatActivity {
    Toolbar tb1;
    ImageButton ib1;
    EditText et1,et2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        tb1=findViewById(R.id.tb11);
        setSupportActionBar(tb1);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ib1=findViewById(R.id.bb11);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp1=et1.getText().toString();
                String temp2=et2.getText().toString();
                if(temp1.equals("") && temp2.equals("")){
                    Toast.makeText(log_in_page.this, "ID AND PASSWORD SECTION CANT'T BE BLANK", Toast.LENGTH_SHORT).show();
                    return;
                }
                database db=new database(getApplicationContext());
                int i=db.verify(temp1,temp2);
                int id=db.getid(temp1,temp2);
                if(i==1){
                    Intent intent=new Intent(getApplicationContext(),first_page.class);
                    db.update_status(id);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(log_in_page.this, "CREDENTIAL NOT MATCHED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.log_in_page_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int  id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        if(id==R.id.create_acc){
            Intent icreate_acc;
            icreate_acc=new Intent(getApplicationContext(),create_acc.class);
            startActivity(icreate_acc);
        }
        return super.onOptionsItemSelected(item);
    }
}