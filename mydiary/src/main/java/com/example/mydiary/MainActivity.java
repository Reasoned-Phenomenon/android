package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button2;
    ListView listDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);

        button2 = findViewById(R.id.button2);
        listDiary = findViewById(R.id.listDiary);

        button2.setOnClickListener(v-> {
            ArrayList<DiaryVO> list = new ArrayList<>();
            MemoDAO dao = new MemoDAO();

            list = dao.selectAll(dbHelper);

            Myadapter adapter = new Myadapter(list);
            listDiary.setAdapter(adapter);

        });

    }


}