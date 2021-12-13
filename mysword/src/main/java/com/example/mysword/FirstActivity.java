package com.example.mysword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    Button btn_start;
    EditText et_name;
    RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        DBHelper dbHelper = new DBHelper(this);

        //선언
        btn_start = findViewById(R.id.btn_start);
        et_name = findViewById(R.id.et_name);
        rv_list = findViewById(R.id.rv_list);

        //리스트
        RecordDAO dao = new RecordDAO();
        ArrayList<RecordVO> list = dao.rankList(dbHelper);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(layoutManager);
        ListAdapter adapter = new ListAdapter(this,list);
        rv_list.setAdapter(adapter);

//        tv_list.setText("등수   이름   점수   등록일자 \n");
//        for(int i =0 ; i < list.size(); i ++) {
//            String str_rank = "" + (i+1) + "   " + list.get(i).name + "   " + list.get(i).score + "   " + list.get(i).time +"\n";
//            tv_list.append(str_rank);
//        }

        //게임 시작 버튼
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //이름 넘기기
                intent.putExtra("name",et_name.getText().toString());
                startActivity(intent);
            }
        });
    }
}