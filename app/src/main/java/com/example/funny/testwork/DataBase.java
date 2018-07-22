package com.example.funny.testwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/* Класс для создания базы данных в которой должны быть записаны данных об объектах если телефон не был бы подключен к интернету  */

public class DataBase extends SQLiteOpenHelper {

    public static final String DB = "DataUsers";

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
