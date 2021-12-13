package com.example.mysword;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecordDAO {

    public int maxRec (DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT MAX(score) FROM record";

        Cursor cursor = db.rawQuery(sql,null);
        int score = 0;
        if (cursor.moveToNext()) {

            score = cursor.getInt(0);
        }

        db.close();

        return score;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertRec (DBHelper dbHelper, int num) {
        SQLiteDatabase db =  dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name","ysw");
        contentValues.put("score",num);

        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time",sdt);

        db.insert("record",null,contentValues);

        db.close();
    }
}
