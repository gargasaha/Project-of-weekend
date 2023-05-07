package com.example.selfimprovementnavigator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class mydb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="db1";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="date_table";
    private static final String DATE="date";
    private static final int ID=1;
    public mydb(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME +
                "(" + ID + "INTEGER primary key," + DATE + "char" + ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
