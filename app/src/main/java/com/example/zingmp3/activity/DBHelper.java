package com.example.zingmp3.activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper {
    public static final String dbregister="register.db";

    public DBHelper(@Nullable Context context) {
        super(context, dbregister, null, 1);
        String dbPath = context.getDatabasePath("register.db").getAbsolutePath();
        Log.d("DB_PATH", "Đường dẫn tới cơ sở dữ liệu: " + dbPath);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }
    public boolean insertData(String username, String password){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = myDB.insert("users",null,contentValues);
        if (result == -1) return false;
        else return true;
    }
    public boolean checkUserName(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor =myDB.rawQuery("select * from users where username =?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public boolean checkUser(String username,String pw){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where username =? and password =?",new String[]{username,pw});
        if (cursor.getCount()>0)
            return  true;
        else return false;
    }
}

