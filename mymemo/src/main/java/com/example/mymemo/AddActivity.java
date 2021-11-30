package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {


    Button btn_back,btn_insert, btn_select, btn_delete, btn_update;
    EditText et_name,et_age, et_phone, et_id,et_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        btn_insert = findViewById(R.id.btn_insert);
        btn_select = findViewById(R.id.btn_select);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_phone = findViewById(R.id.et_phone);
        et_id = findViewById(R.id.et_id);

        et_view = findViewById(R.id.et_view); //수정필요
        
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //DB, table 생성

        //등록
        View.OnClickListener insertHandler = v -> {

            String InsertName = et_name.getText().toString();
            String InsertAge = et_age.getText().toString();
            String InsertPhone = et_phone.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sqlInsert = "INSERT INTO emp (NAME, age, mobile) "
                    //+"VALUES ('" + InsertName + "','"+InsertAge+"','"+InsertPhone+"')";
                    +"VALUES (? ,?, ?)";

            db.execSQL(sqlInsert, new Object[]{InsertName,InsertAge,InsertPhone});

            et_name.setText("");
            et_age.setText("");
            et_phone.setText("");

            db.close();

        };

        //삭제
        View.OnClickListener deleteHandler = v -> {

            String deleteId = et_id.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.delete("emp", "_id=?", new String[]{deleteId});

            //sql 사용
//            String sql = "DELETE FROM emp WHERE _id="+deleteId;
//            db.execSQL(sql);

            db.close();
        };

        //단건조회
//        View.OnClickListener selectHandler = v -> {
//
//            String selectId = et_id.getText().toString();
//
//            et_view.setText("");
//            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//            String sql = "select _id, name, age, mobile from emp where _id=" + selectId;
//
//            Cursor cursor = db.rawQuery(sql, null);
//
//            while (cursor.moveToNext()) {
//
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("_id", cursor.getString(0));
//                map.put("name", cursor.getString(1));
//                map.put("age", cursor.getString(2));
//                map.put("mobile", cursor.getString(3));
//
//                list.add(map);
//
//            }
//
//            for (int i = 0; i < list.size(); i++) {
//                et_view.append("id: " + list.get(i).get("_id"));
//                et_view.append(" name: " + list.get(i).get("name"));
//                et_view.append(" age: " + list.get(i).get("age"));
//                et_view.append(" mobile: " + list.get(i).get("mobile") + "\n");
//            }
//
//            db.close();
//
//        };

        //수정
        View.OnClickListener updateHandler = v -> {

            String updateId = et_id.getText().toString();
            String updateName = et_name.getText().toString();
            String updateAge = et_age.getText().toString();
            String updatePhone = et_phone.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase(); //데이터베이스 연결

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", updateName);
            contentValues.put("age", updateAge);
            contentValues.put("mobile", updatePhone);

            db.update("emp", contentValues, "_id=?", new String[]{updateId});

            db.close();
        };

        btn_insert.setOnClickListener(insertHandler);
        //btn_select.setOnClickListener(selectHandler);
        btn_delete.setOnClickListener(deleteHandler);
        btn_update.setOnClickListener(updateHandler);
    }

}