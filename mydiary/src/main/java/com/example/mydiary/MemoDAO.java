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

    //전체 조회
    public static ArrayList<DiaryVO> selectAll (DBHelper dbHelper) {

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

        db.close();

        return list;

    }

    //한건 조회-수정버튼 누르면 다음화면으로 넘어가게
    public static DiaryVO select (DBHelper dbHelper, DiaryVO vo) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String id = vo.get_id();
        String sql = "select _id, title, content, time From diary WHERE _id="+id;

        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToNext()) {

            vo.set_id(cursor.getString(0));
            vo.setTitle(cursor.getString(1));
            vo.setContent(cursor.getString(2));
            vo.setTime(cursor.getString(3));

        }

        db.close();

        return vo;

    }

    //등록
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void insert(DBHelper dbHelper, DiaryVO vo) {
        SQLiteDatabase db =dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", vo.getTitle());
        contentValues.put("content", vo.getContent());

        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time",sdt);

        db.insert("diary",null,contentValues);

        db.close();
    }

    //수정
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void update(DBHelper dbHelper, DiaryVO vo) {
        System.out.println(vo);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", vo.getTitle());
        contentValues.put("content", vo.getContent());

        LocalDate dt = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time",sdt);

        String id = vo.get_id();

        db.update("diary",contentValues,"_id=?",new String[]{id});

        db.close();
    }

    //삭제
    public static void delete(DBHelper dbHelper, DiaryVO vo) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String id = vo.get_id();
        db.delete("diary", "_id=?", new String[]{id});

        db.close();
    }
}
