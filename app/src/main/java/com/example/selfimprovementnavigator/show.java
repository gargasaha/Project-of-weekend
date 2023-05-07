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
import java.util.Calendar;

public class show extends AppCompatActivity {
    ListView listView;
    int i;
    Toolbar tb1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        listView=findViewById(R.id.show_page_list_view);
        database db=new database(getApplicationContext());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        tb1=findViewById(R.id.show_tb1);
        setSupportActionBar(tb1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String d=null;

        switch (day){
            case 1:
                d="sun";
                break;
            case 2:
                d="mon";
                break;
            case 3:
                d="tue";
                break;
            case 4:
                d="wed";
                break;
            case 5:
                d="thu";
                break;
            case 6:
                d="fri";
                break;
            case 7:
                d="sat";
                break;
        }
        Cursor cursor=db.get_all_data(d);
        ArrayList<temporary> temp=new ArrayList<>();
        while(cursor.moveToNext()){
            temp.add(new temporary(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8),cursor.getInt(9)));
        }
        custom_adapter custom_adapter=new custom_adapter(getApplicationContext(),temp);
        listView.setAdapter(custom_adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                temporary temp;
                temp= (com.example.selfimprovementnavigator.temporary) listView.getItemAtPosition(i);
                int id=temp.get_task_id();
                Intent iremove=new Intent(getApplicationContext(),remove_task_from_daily.class);
                iremove.putExtra("id",id);
                startActivity(iremove);
                finish();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.show_page_toolbar, menu);
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
class custom_adapter extends ArrayAdapter<temporary> {
    ArrayList<temporary> arrayList;
    Context context;
    public custom_adapter(@NonNull Context context,ArrayList<temporary> arrayList) {
        super(context, R.layout.show_page_custom_layout,arrayList);
        this.arrayList=arrayList;
        this.context=context;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view= LayoutInflater.from(context).inflate(R.layout.show_page_custom_layout,parent,false);
        TextView tv1=view.findViewById(R.id.show_page_custom_layout_tv1);
        TextView tv2=view.findViewById(R.id.show_page_custom_layout_tv2);
        tv1.setText("ID :"+String.valueOf(arrayList.get(position).get_task_id()));
        tv2.setText("TITLE :"+arrayList.get(position).get_task_name());
        return view;
    }
}