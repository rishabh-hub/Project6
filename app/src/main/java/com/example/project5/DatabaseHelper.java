package com.example.project5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static String DATABASE_NAME="note.db";
    public  String TABLE_NAME="abc";
    public  String col_1="Id";
    public  String col_2="Title";
    public String col_3="Data";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" ("+col_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+col_2+" TEXT,"+col_3+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public String insertData(String TITLE,String DATA)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(col_2,TITLE);
        contentValues.put(col_3,DATA);
        long res=db.insert(TABLE_NAME,null,contentValues);

        if (res==-1)
            return "Not Saved";
        else
            return "Saved";
    }
    public void update(String notes,String title,int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,title);
        contentValues.put(col_3,notes);
        db.update(TABLE_NAME,contentValues,"Id=?",new String[]{Integer.toString(id)});
    }
    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"Id = ?",new String[]{Integer.toString(id)});

    }

    public Cursor getData() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
    public Cursor getSearch(String searchitem)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM abc WHERE Data LIKE '%"+searchitem+"%' OR Title LIKE '%"+searchitem+"%'",null);
        return cursor;
    }
    public void deleteRow()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("DELETE * FROM "+TABLE_NAME,null);
    }
}
