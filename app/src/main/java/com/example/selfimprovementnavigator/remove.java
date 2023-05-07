package com.example.selfimprovementnavigator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class remove extends AppCompatActivity {
    Toolbar tb1;
    ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        tb1=findViewById(R.id.tb1);
        listView=findViewById(R.id.lv1);
        setSupportActionBar(tb1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        database db=new database(getApplicationContext());
        Cursor cursor=db.delete_from_task_table();
        ArrayList<temporary> temp=new ArrayList<>();
        while(cursor.moveToNext()){
            temp.add(new temporary(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8),cursor.getInt(9)));
        }
        custom_adapter custom_adapter=new custom_adapter(getApplicationContext(),temp);
        listView.setAdapter(custom_adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),remove_task_from_daily.class);
                intent.putExtra("val",1);
                temporary temp= (temporary) listView.getItemAtPosition(position);
                int i=temp.get_task_id();
                intent.putExtra("id",i);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.remove_page_menu,menu);
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
    static class custom_adapter extends ArrayAdapter<temporary> {
        ArrayList<temporary> temp;
        Context context;
        @SuppressLint("ResourceType")
        public custom_adapter(@NonNull Context context, ArrayList<temporary> temp) {
            super(context, R.layout.show_page_custom_layout,temp);
            this.temp=temp;
            this.context=context;
        }
        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            @SuppressLint("ViewHolder") View view= LayoutInflater.from(context).inflate(R.layout.show_page_custom_layout,parent,false);
            TextView tv1=view.findViewById(R.id.show_page_custom_layout_tv1);
            TextView tv2=view.findViewById(R.id.show_page_custom_layout_tv2);
            tv1.setText("ID :"+String.valueOf(temp.get(position).get_task_id()));
            tv2.setText("TITLE :"+temp.get(position).get_task_name());
            return view;
        }
    }
}