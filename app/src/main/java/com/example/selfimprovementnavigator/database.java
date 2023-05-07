package com.example.selfimprovementnavigator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.Calendar;

public class database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="db1";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME1="log_in_table";
    private static final String TABLE_NAME2="current_log_in";
    private static final String TABLE_NAME3="task_table";
    private static final String TABLE_NAME4="todays_task";
    private static final String TABLE_NAME5="day";
    private static final String CID="id";
    private static final String CNAME="cName";
    private static final String CPASSWORD="password";
    private static final String CURRENT_CID="current_cid";
    private static final String CURRENT_VAL="current_val";
    private static final String USER_ID="user_id";
    private static final String TASK_ID="Task_id";
    private static final String TASK_NAME="task_name";
    private static final String SUN="sun";
    private static final String MON="mon";
    private static final String TUE="tue";
    private static final String WED="wed";
    private static final String THU="thu";
    private static final String FRI="fri";
    private static final String SAT="sat";
    private static final String CURR_ID="curr_id";
    private static final String CURR_DAY="curr_day";
    public database(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME1+"("+CID+" INTEGER primary key autoincrement,"+CNAME+" varchar(100) not null,"+CPASSWORD+" varchar(100) not null)");
        String query="create table "+TABLE_NAME2+"("+CURRENT_VAL+" int,"+CURRENT_CID+" int)";
        String query2="create table "+TABLE_NAME3+"("+USER_ID+" int,"+TASK_ID+" integer primary key autoincrement,"+TASK_NAME+" text,"+SUN+" int,"+MON+" int,"+TUE+" int,"+WED+" int,"+THU+" int,"+FRI+" int,"+SAT+" int)";
        String query3="create table "+TABLE_NAME4+"("+CURR_ID+" int)";
        String query4="create table "+TABLE_NAME5+"("+CURR_DAY+" int)";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
        sqLiteDatabase.execSQL(query4);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }
    public int date_insert_for_create_acc(String name,String pass){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CNAME,name);
        contentValues.put(CPASSWORD,pass);
        sqLiteDatabase.insert(TABLE_NAME1,null,contentValues);
        sqLiteDatabase.close();
        SQLiteDatabase sqLiteDatabase1=getReadableDatabase();
        String query="select max("+CID+") from "+TABLE_NAME1;
        Cursor cs=sqLiteDatabase1.rawQuery(query,null);
//        cs.close();
        int temp=-1;
        while(cs.moveToNext()){
            temp=cs.getInt(0);
        }
        sqLiteDatabase1.close();
        return temp;
    }
    public int verify(String temp1,String temp2){
        int flag=0;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String query="select "+CID+","+CPASSWORD+","+CNAME+" from "+TABLE_NAME1+" where "+CID+"="+temp1;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(temp2)){
                flag=1;
            }
        }
//        cursor.close();
        sqLiteDatabase.close();
        if(flag==1){
            return 1;
        }
        return 0;
    }
    public int getid(String temp1,String temp2){
        int flag=0;
        int id=-1;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String query="select "+CID+","+CPASSWORD+","+CNAME+" from "+TABLE_NAME1+" where "+CID+"="+temp1;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        while(cursor.moveToNext()){
            if(cursor.getString(1).equals(temp2)){
                flag=1;
                id=cursor.getInt(0);
            }
        }
//        cursor.close();
        sqLiteDatabase.close();
        if(flag==1){
            return id;
        }
        return -1;
    }
    public int getstatus(){
        String query="select "+CURRENT_VAL+" from "+TABLE_NAME2;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//        cursor.close();
        int i=0;
        while(cursor.moveToNext()){
            i=cursor.getInt(0);
        }
        return i;
    }
    public int getid(){
        String query="select "+CURRENT_CID+" from "+TABLE_NAME2;
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//        cursor.close();
        int id=-1;
        while(cursor.moveToNext()){
            id=cursor.getInt(0);
        }
        return id;
    }
    public void log_out(){
//        String query1="delete from "+TABLE_NAME2;
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME2,null,null);
    }
    public void update_status(int id){
        String query1="delete from "+TABLE_NAME2;
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.rawQuery(query1,null);
        ContentValues contentValues=new ContentValues();
        contentValues.put(CURRENT_VAL,1);
        contentValues.put(CURRENT_CID,id);
        sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);
    }
    public void insert_data_in_task_table(int user_id,String task_name,int sun,int mon,int tue,int wed,int thu,int fri,int sat){
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_ID,user_id);
        contentValues.put(TASK_NAME,task_name);
        contentValues.put(SUN,sun);
        contentValues.put(MON,mon);
        contentValues.put(TUE,tue);
        contentValues.put(WED,wed);
        contentValues.put(THU,thu);
        contentValues.put(FRI,fri);
        contentValues.put(SAT,sat);
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME3,null,contentValues);
    }
    public Cursor get_all_data(String day){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        int curr_id=0;
        String query="select "+CURRENT_CID+" from "+TABLE_NAME2+";";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//        cursor.close();
        while(cursor.moveToNext()){
            curr_id=cursor.getInt(0);
        }
        query="select * from "+TABLE_NAME3+" where "+USER_ID+"="+curr_id+" and "+day+"=1 and "+TASK_ID+" not in (select "+CURR_ID+" from "+TABLE_NAME4+")";
        cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
    public void insert_into_todays_task(int id){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CURR_ID,id);
        sqLiteDatabase.insert(TABLE_NAME4,null,contentValues);
    }
    public void update_current_day(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String query="select * from "+TABLE_NAME5;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//        cursor.close();
        int d=0;
        while(cursor.moveToNext()){
            d=cursor.getInt(0);
        }
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(day+""+d);
        if(d!=day){
            ContentValues contentValues=new ContentValues();
            contentValues.put("curr_day",day);
//            System.out.println("acted");
            sqLiteDatabase.insert(TABLE_NAME5,null,contentValues);
            query="delete from "+TABLE_NAME4;
            sqLiteDatabase.execSQL(query);
        }
    }
    public void remove_data(int id){
        String query="delete from "+TABLE_NAME3+" where "+TASK_ID+"="+id;
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }
    public Cursor delete_from_task_table(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        int curr_id=0;
        String query="select "+CURRENT_CID+" from "+TABLE_NAME2+";";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
//        cursor.close();
        while(cursor.moveToNext()){
            curr_id=cursor.getInt(0);
        }
        query="select * from "+TABLE_NAME3+" where "+USER_ID+"="+curr_id;
        cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

}
