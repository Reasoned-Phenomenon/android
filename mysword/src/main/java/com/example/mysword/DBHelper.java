package com.example.mysword;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME = "record.db";
    public static int VERSION = 1;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB생성");
        String sql = "create table if not exists record("
                + " id     integer PRIMARY KEY autoincrement, "
                + " name   text, "
                + " score   integer, "
                + " time    text)";
        db.execSQL(sql);

        //초기값
        sql = "insert into record (name,score,time) values('start','10','2021-12-02')";
        db.execSQL(sql);
        sql = "insert into record (name,score,time) values('start','20','2021-12-04')";
        db.execSQL(sql);
        sql = "insert into record (name,score,time) values('start','30','2021-12-05')";
        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS noteData");
        }
    }
}
