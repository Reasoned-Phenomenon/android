package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    LinearLayout linear;
    GridLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = findViewById(R.id.linear);

        int startNum = 1;

        View.OnClickListener handler = v-> {
//            Toast.makeText(this,"토스트",Toast.LENGTH_SHORT).show();
            //클릭한 버튼의 text(숫자값)을 읽어서 startNum과 같다면
            if (v.getId() == startNum) {

            }
            //startNum 1증가
            startNum++;
            //버튼의 text reset
            //16번까지 도착했으면 게임 완료라는 토스트
        };


        //linear.setOrientation(LinearLayout.VERTICAL);
        //1차원 배열로 16개의 값 - 임의의 순서 -> 배열값 넣어주면 됨.


        for(int i = 1 ; i <= 16; i ++ ) {

            Button btn = new Button(this);
//            btn.setText(""+i);
            btn.setText(String.valueOf(i));

            linear.addView(btn);

            btn.setOnClickListener(handler);
        }

    }
}