package com.example.selfimprovementnavigator;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class create_acc extends AppCompatActivity {
    EditText et1,et2;
    ImageButton eb1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        eb1=findViewById(R.id.imageButton);
        database db=new database(getApplicationContext());
        eb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!et1.getText().toString().equals("") && !et2.getText().toString().equals("")) {
                    int temp = db.date_insert_for_create_acc(et1.getText().toString(), et2.getText().toString());
                    Toast.makeText(create_acc.this, "YOUR ID IS " + temp, Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(create_acc.this, "NAME AND PASSWORD FIELD CAN'T BE BLANK", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}