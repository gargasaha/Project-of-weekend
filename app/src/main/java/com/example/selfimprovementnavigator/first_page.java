package com.example.selfimprovementnavigator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class first_page extends AppCompatActivity {
    Toolbar tb1;
    TextView dt,nametv1;
    ListView lv1;
    LottieAnimationView lav;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        tb1=findViewById(R.id.tb1);
        lv1=findViewById(R.id.lv1);
        setSupportActionBar(tb1);
        lav=findViewById(R.id.lav1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dt=findViewById(R.id.dt);
        Calendar calendar=Calendar.getInstance();
        String da= DateFormat.getDateInstance().format(calendar.getTime());
        dt.setText(da);
        List<String> ti=new ArrayList<>();
        List<Integer> im=new ArrayList<>();
        ti.add("ADD TASK");
        ti.add("SHOW TASK");
        ti.add("REMOVE TASK");
        im.add(R.drawable.add);
        im.add(R.drawable.show);
        im.add(R.drawable.remove);
        database database=new database(getApplicationContext());

        myAdapter myAdapter=new myAdapter(getApplicationContext(),ti,im);
        lv1.setAdapter(myAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    startActivity(new Intent(getApplicationContext(),add.class));
                }
                else if(i==1){
                    database.update_current_day();
                    startActivity(new Intent(getApplicationContext(),show.class));
                }
                else if(i==2){
                    startActivity(new Intent(getApplicationContext(),remove.class));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.first_page_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            finish();
        }
        else if(id==R.id.ab){
            startActivity(new Intent(getApplicationContext(),animation_page2.class));
        }
        else if(id==R.id.log_out){
            database db=new database(getApplicationContext());
            db.log_out();
            startActivity(new Intent(getApplicationContext(),animation_page1.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
class myAdapter extends ArrayAdapter{
    List<String> title;
    List<Integer> img;
    Context context;
    public myAdapter(@NonNull Context context, List<String> title,List<Integer> img) {
        super(context, R.layout.custom_lv1,title);
        this.title=title;
        this.img=img;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        @SuppressLint("ViewHolder") View view= LayoutInflater.from(context).inflate(R.layout.custom_lv1,parent,false);
        ImageView imageView=view.findViewById(R.id.cviv1);
        TextView textView=view.findViewById(R.id.cvtv1);
        imageView.setImageResource(img.get(position));
        textView.setText(title.get(position));
        return view;
    }
}