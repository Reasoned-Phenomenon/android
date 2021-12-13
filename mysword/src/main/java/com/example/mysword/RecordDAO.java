package com.example.mysword;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RecordDAO {
    
    public ArrayList<RecordVO> rankList (DBHelper dbHelper) {
        ArrayList<RecordVO> list = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT name, score, time FROM record ORDER BY 2 desc";

        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()) {

            RecordVO vo = new RecordVO();
            vo.setName(cursor.getString(0));
            vo.setScore(cursor.getInt(1));
            vo.setTime(cursor.getString(2));

            list.add(vo);
        }

        db.close();

        return list;

    }
    
    //최고점수
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
    
    //기록 등록
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertRec (DBHelper dbHelper, String name, int num) {
        SQLiteDatabase db =  dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("score",num);

        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time",sdt);

        db.insert("record",null,contentValues);

        db.close();
    }
}
