package com.example.amarhisab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper1 extends SQLiteOpenHelper {
    public DBHelper1(@Nullable Context context) {
        super(context, "Customerdata.DB", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Customerdetails(name TEXT primary key, bill TEXT, pay TEXT, due TEXT, date TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists Customerdetails");

    }
    public boolean insertdata(String name, String bill,String pay, String due, String date){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("bill",bill);
        contentValues.put("pay",pay);
        contentValues.put("due",due);
        contentValues.put("date",date);
        long result=DB.insert("Customerdetails", null, contentValues);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean updatedata(String name, String bill,String pay, String due, String date) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bill", bill);
        contentValues.put("pay", pay);
        contentValues.put("due", due);
        contentValues.put("date", date);
        Cursor cursor = DB.rawQuery("Select * from Customerdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Customerdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}


    public boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Customerdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Customerdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Customerdetails", null);
        return cursor;

    }
}
