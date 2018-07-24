package com.example.funny.testwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/* Класс для создания базы данных в которой должны быть записаны данных об объектах если телефон не был бы подключен к интернету  */

public class DataBase extends SQLiteOpenHelper {

    public static final String _NAMEDB = "DataUsers", _USER_TABLE = "Users" , _ID = "_id" ,_ColumnLogin = "Login",
            _ColumnAvatar = "url_avatar", _ColumnName = "Name", _ColumnCompany = "Company", _ColumnEmail = "Email";

    public DataBase(Context context) {
        super(context, _NAMEDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + _USER_TABLE + "(" + _ID +" integer PRIMARY KEY AUTOINCREMENT," + _ColumnLogin + " varchar NOT NULL UNIQUE,"
                + _ColumnAvatar +" text,"+ _ColumnName +" text,"+ _ColumnCompany +" text,"+ _ColumnEmail +" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
