package com.example.mydiary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MemoDAO {

    DBHelper dbHelper;

    public ArrayList<DiaryVO> selectAll (DBHelper dbHelper) {

        ArrayList<DiaryVO> list = new ArrayList<DiaryVO>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select _id, title, content, time From diary order by _id desc";

        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {

            DiaryVO vo = new DiaryVO();

            vo.set_id(cursor.getString(0));
            vo.setTitle(cursor.getString(1));
            vo.setContent(cursor.getString(2));
            vo.setTime(cursor.getString(3));

            list.add(vo);
        }

        dbHelper.close();
        return list;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insert(DBHelper dbHelper, DiaryVO vo) {
        SQLiteDatabase db =dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", vo.getTitle());
        contentValues.put("content", vo.getContent());

        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time",sdt);

        db.insert("diary",null,contentValues);
        dbHelper.close();
    }
}
