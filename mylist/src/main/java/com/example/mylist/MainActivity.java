package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv);

//        String[] data = new String[] {"","",""};  //직접 만들어 넣어도 됨
//        String[] data = getResources().getStringArray(R.array.city); //배열 가져옴

        ArrayList<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<String,String>();

        map.put("name","홍길동");
        map.put("addr","대구");
        list.add(map);

        map = new HashMap<String,String>();
        map.put("name","김길동");
        map.put("addr","서울");
        list.add(map);

        map = new HashMap<String,String>();
        map.put("name","강감찬");
        map.put("addr","부산");
        list.add(map);

        //어댑터가 data랑 뷰를 연결
//        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
//                android.R.layout.simple_list_item_1,
//                data);
//        lv.setOnItemClickListener((adapterView, view, i, l) -> {
//            Toast.makeText(this,data[i],Toast.LENGTH_SHORT).show();
//        });
            //특정값 1개 + 아이디 부여
//        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
//                list,
//                android.R.layout.simple_list_item_1,
//                new String[]{"name"},
//                new int[]{android.R.id.text1});
            //특정값 2개 + 아이디 부여
//        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
//                list,
//                android.R.layout.simple_list_item_2,
//                new String[]{"name","addr"},
//                new int[]{android.R.id.text1,android.R.id.text2});
//      //
//        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(),
//                list,
//                android.R.layout.simple_list_item_checked,
//                new String[]{"name"},
//                new int[]{android.R.id.text1});

        MyAdapter adapter = new MyAdapter(list);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this,list.get(i).get("name"),Toast.LENGTH_SHORT).show();
        });


    }
}