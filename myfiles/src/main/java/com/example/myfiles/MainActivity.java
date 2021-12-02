package com.example.myfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    String FileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_write = findViewById(R.id.btn_write);
        Button btn_read = findViewById(R.id.btn_read);

        DatePicker datePicker1 = findViewById(R.id.datePicker1);

        EditText et_dlg = findViewById(R.id.et_dlg);

        //시작값 변경.
        datePicker1.init(2021,11,2,(view, year, monthOfYear, dayOfMonth) -> {
            et_dlg.setText("");
            FileName = ""+year+(monthOfYear+1)+dayOfMonth+".txt";
            fileRead(FileName);

        });

        btn_write.setOnClickListener(v-> {
            //날짜값은 달력에서 읽어와서 파일 제목으로
            // et 에서 내용 가져와서 일기 쓰게.

//            fileWrite(FileName);

            //전역변수 안 쓸때.
            String reName = ""+datePicker1.getYear()+(datePicker1.getMonth()+1)+datePicker1.getDayOfMonth()+".txt";

            try{
                FileOutputStream outFs = openFileOutput(reName, Context.MODE_PRIVATE);
                String str = et_dlg.getText().toString();
                outFs.write(str.getBytes());
                outFs.close();
                Toast.makeText(getApplicationContext(),FileName+"가 생성됨", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),FileName+"생성 실패", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        });

        btn_read.setOnClickListener(v-> {

           fileRead(FileName);

        });

    }

    public void fileRead (String FileName) {
        try {

            EditText et_dlg = findViewById(R.id.et_dlg);

            FileInputStream inFs = openFileInput(FileName);
            byte[] txt = new byte[30];
            inFs.read(txt);
            String str = new String(txt);
            et_dlg.setText(str);
            inFs.close();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "파일없음", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void fileWrite (String FileName) {

        EditText et_dlg = findViewById(R.id.et_dlg);

        try{
            FileOutputStream outFs = openFileOutput(FileName, Context.MODE_PRIVATE);
            String str = et_dlg.getText().toString();
            outFs.write(str.getBytes());
            outFs.close();
            Toast.makeText(getApplicationContext(),FileName+"가 생성됨", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),FileName+"생성 실패", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

}