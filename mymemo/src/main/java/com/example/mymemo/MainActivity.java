package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_insert, btn_read;
    EditText et_name, et_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_insert = findViewById(R.id.btn_insert);
        btn_read = findViewById(R.id.btn_read);

        et_name = findViewById(R.id.et_name);
        et_view = findViewById(R.id.et_view);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //DB, table 생성

        View.OnClickListener handler = v -> {

            String InsertName = et_name.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sqlInsert = "INSERT INTO emp " +
                    "(NAME, age, mobile) VALUES ('" + InsertName + "',20,'010-211')";

            db.execSQL(sqlInsert);

            et_view.setText(InsertName);
            et_name.setText("");

        };

        //db.close();

        btn_insert.setOnClickListener(handler);

    }
}