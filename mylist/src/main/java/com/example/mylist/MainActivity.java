package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);

//        String[] data = new String[] {"","",""};  //직접 만들어 넣어도 됨
        String[] data = getResources().getStringArray(R.array.city); //배열 가져옴
        //어댑터가 data랑 뷰를 연결
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                data);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this,data[i],Toast.LENGTH_SHORT).show();
        });


        

    }
}