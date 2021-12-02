package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btn_read, btn_goToAdd;
    //EditText et_view;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_read = findViewById(R.id.btn_read);

        btn_goToAdd = findViewById(R.id.btn_goToAdd);

        //et_view = findViewById(R.id.et_view);

        lv = findViewById(R.id.lv);

//        Intent intent = getIntent();
//
//        if(intent.getExtras() !=null) {
//            ArrayList<String> getList =(ArrayList<String>) intent.getExtras().getSerializable("one");
//            System.out.println(getList.get(0));
//            String str = getList.get(0);
//            et_view.setText(str);
        //}

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

        ArrayList<String> list = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                list);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this,list.get(i),Toast.LENGTH_SHORT).show();
        });

        //전체조회
        View.OnClickListener readHandler = v -> {

            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String sql = "select _id, name, age, mobile from emp order by _id desc ";

            Cursor cursor = db.rawQuery(sql, null);

            while (cursor.moveToNext()) {
                list.add(cursor.getString(1));
            }

            db.close();
//            System.out.println(lv.getAdapter());
//            lv.getAdapter().notifyAll();


            //et_view.setText("");
//            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//            String sql = "select _id, name, age, mobile from emp order by _id desc ";
//
//            Cursor cursor = db.rawQuery(sql, null);
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

//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.size());
//                et_view.append("id: " + list.get(i).get("_id"));
//                et_view.append(" name: " + list.get(i).get("name"));
//                et_view.append(" age: " + list.get(i).get("age"));
//                et_view.append(" mobile: " + list.get(i).get("mobile") + "\n");
//            }

//            db.close();
//
//            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
//                    android.R.layout.simple_list_item_1,
//                    list);
//
//            lv.setAdapter(adapter);
//
//            lv.setOnItemClickListener((adapterView, view, i, l) -> {
//                Toast.makeText(this,list.get(i).get("name"),Toast.LENGTH_SHORT).show();
//            });



        };

        btn_read.setOnClickListener(readHandler);

        btn_goToAdd.setOnClickListener(v-> {
            Intent intentGoToAdd = new Intent(getApplicationContext(), AddActivity.class);
//            startActivity(intentBack);
            startActivityForResult(intentGoToAdd,10);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Main");
        System.out.println(requestCode);
        System.out.println(resultCode);
        System.out.println("Main");

        //등록
        if(resultCode == 1) {
            System.out.println(data.getExtras().get("msg"));
            Toast.makeText(this,"등록됐습니다.",Toast.LENGTH_SHORT).show();
        //수정
        } else if(resultCode == 2) {
//            System.out.println(data.getExtras());
//            System.out.println(data.getExtras().get("one"));
//            System.out.println(data.getStringArrayListExtra("one"));

//            ArrayList<Parcelable> getList = data.getParcelableArrayListExtra("one");
//                et_view.append("id: " + ((Map)getList.get(0)).get("_id"));
//                et_view.append(" name: " + ((Map)getList.get(0)).get("name"));
//                et_view.append(" age: " + ((Map)getList.get(0)).get("age"));
//                et_view.append(" mobile: " + ((Map)getList.get(0)).get("mobile") + "\n");
            String str = data.getStringExtra("update");
            //et_view.setText(str);
            
            Toast.makeText(this,"1건 수정 됐습니다.",Toast.LENGTH_SHORT).show();

        }

    }
}