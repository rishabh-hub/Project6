package com.example.project5;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static String DATABASE_NAME="Notes.db";
    public  String TABLE_NAME="abc";
    public  String col_1="Data";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" ("+col_1+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public String insertData(String DATA)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(col_1,DATA);
        long res=db.insert(TABLE_NAME,null,contentValues);

        if (res==-1)
            return "Not Saved";
        else
            return "Saved";
    }
}
