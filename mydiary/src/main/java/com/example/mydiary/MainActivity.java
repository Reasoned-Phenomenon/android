package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnWriteForm;
    ListView listDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWriteForm = findViewById(R.id.btnWriteForm);
        listDiary = findViewById(R.id.listDiary);

        DBHelper dbHelper = new DBHelper(this);

        ArrayList<DiaryVO> list = MemoDAO.selectAll(dbHelper);

        Myadapter adapter = new Myadapter(list);
        listDiary.setAdapter(adapter);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        //listView 초기화 -> Adapter 지정하고 클릭이벤트
        //쓰기버튼 이벤트 지정 : writeActivity로 이동

        //아이템 클릭 이벤트 : 수정 / 삭제

        btnWriteForm.setOnClickListener(v-> {
            Intent intent = new Intent(this, WriteActivity.class);
            startActivity(intent);
        });

        listDiary.setOnItemClickListener((parent, view, position, id) -> {

            //다이얼로그 띄워서.
            //수정 - 삭제 선택
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("선택하세요")
                    .setMessage("수정 또는 삭제 선택하세요")
                    .setPositiveButton("수정",(dialogInterface, i) -> {
                        Log.d("alert","수정버튼");

                        DiaryVO vo = new DiaryVO();
                        vo.set_id(list.get(position).get_id());
                        vo = MemoDAO.select(dbHelper,vo);

                        //값 넘겨줘야함.
                        Intent intent = new Intent(this, WriteActivity.class);

                        intent.putExtra("id",vo.get_id());
                        intent.putExtra("title",vo.getTitle());
                        intent.putExtra("content",vo.getContent());

                        if(vo.getImg() != null) {
                            intent.putExtra("uri",vo.getImg());
                        }

//                        intent.putExtra("selected",vo); //객체 넘기는 방법
                        startActivity(intent);

                        //교수님
//                        ((Myadapter)listDiary.getAdapter()).setData(MemoDAO.selectAll(dbHelper));
//                         list.set()로 해도 괜찮을 것 같음.
//                        ((Myadapter)listDiary.getAdapter()).notifyDataSetChanged();
                    })
                    .setNegativeButton("삭제",(di, i) -> {
                        Log.d("alert","삭제버튼");

                        DiaryVO vo = new DiaryVO();
                        vo.set_id(list.get(position).get_id());
                        
                        //파일삭제
//                        File file = new File(list.get(position).getImg());
//                        file.delete();
                        
                        MemoDAO.delete(dbHelper,vo);


                        list.remove(position);
                        ((Myadapter)listDiary.getAdapter()).notifyDataSetChanged();

//                        listDiary.adapter.notifyDataSetChanged(); //바로 반영
                        //listDiary.getAdapter().notify();

                    })
                    .create()
                    .show();

        });

        Intent backIntent = getIntent();
        if (backIntent.hasExtra("msg")) {
            Toast.makeText(this,backIntent.getStringExtra("msg"),Toast.LENGTH_LONG).show();
        }

    }

}