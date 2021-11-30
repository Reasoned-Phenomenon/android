package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btn_read, btn_backMenu;
    EditText et_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_read = findViewById(R.id.btn_read);

        btn_backMenu = findViewById(R.id.btn_goToAdd);

        et_view = findViewById(R.id.et_view);

        //db.close();

//        View.OnClickListener readHandler = v -> {
//
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//            String sql = "select _id, name, age, mobile from emp order by _id desc ";
//            String strView = "";
//
//            Cursor cursor = db.rawQuery(sql, null);
//            while (cursor.moveToNext()) {
//
//                strView += "id:"+ cursor.getString(0)
//                        +" name:"+cursor.getString(1)
//                        +" age:"+cursor.getString(2)
//                        +" mobile:"+cursor.getString(3)
//                        +"\n";
//
//            }
//
//            et_view.setText(strView);
//        };

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());

        //전체조회
        View.OnClickListener readHandler = v -> {

            et_view.setText("");
            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String sql = "select _id, name, age, mobile from emp order by _id desc ";

            Cursor cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("_id", cursor.getString(0));
                map.put("name", cursor.getString(1));
                map.put("age", cursor.getString(2));
                map.put("mobile", cursor.getString(3));

                list.add(map);

            }

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.size());
                et_view.append("id: " + list.get(i).get("_id"));
                et_view.append(" name: " + list.get(i).get("name"));
                et_view.append(" age: " + list.get(i).get("age"));
                et_view.append(" mobile: " + list.get(i).get("mobile") + "\n");
            }

            db.close();

        };

        btn_read.setOnClickListener(readHandler);

        btn_backMenu.setOnClickListener(v-> {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
        });

    }
}