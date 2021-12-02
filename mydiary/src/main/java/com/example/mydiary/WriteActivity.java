package com.example.mydiary;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {

    Button btnSave;
    EditText editTitle,editContent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        btnSave = findViewById(R.id.btnSave);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);

        DBHelper dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        if(intent.hasExtra("id")) {
            editTitle.setText(intent.getStringExtra("title"));
            editContent.setText(intent.getStringExtra("content"));
        }

        btnSave.setOnClickListener(v-> {

            Intent intent1 = new Intent(this,MainActivity.class);

            DiaryVO vo = new DiaryVO();

            vo.setTitle(editTitle.getText().toString());
            vo.setContent(editContent.getText().toString());

            if(intent.hasExtra("id")) {
                String id= intent.getStringExtra("id");
                vo.set_id(id);
                MemoDAO.update(dbHelper,vo);
                intent1.putExtra("msg","수정됐습니다.");

            } else {

                MemoDAO.insert(dbHelper,vo);
                intent1.putExtra("msg","등록됐습니다.");

            }

            startActivity(intent1);

        });

    }

}
