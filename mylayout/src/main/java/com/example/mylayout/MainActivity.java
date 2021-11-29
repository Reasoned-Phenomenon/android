package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    GridLayout linear;
    int startNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = findViewById(R.id.linear);

        linearLayout = new LinearLayout(this);
        setContentView(linearLayout);

        View.OnClickListener handler = v-> {
//            Toast.makeText(this,"토스트",Toast.LENGTH_SHORT).show();

            //클릭한 버튼의 text(숫자값)을 읽어서 startNum과 같다면
            if ( Integer.parseInt( ((Button) v).getText().toString() ) == startNum) {

                //버튼의 text reset
//                v.setVisibility(View.INVISIBLE);
                ((Button) v).setText("");

                //startNum 1증가
                startNum++;
            }

            //16번까지 도착했으면 게임 완료라는 토스트
            if(startNum == 16) {
                Toast.makeText(this,"게임끝",Toast.LENGTH_SHORT).show();
            }
        };


        //linear.setOrientation(LinearLayout.VERTICAL);

        View.OnClickListener startHandler = v-> {

            //시작숫자 초기화
            startNum=1;

            //1차원 배열로 16개의 값 - 임의의 순서 -> 배열값 넣어주면 됨.
            List<Integer> list = new ArrayList<>();

            for ( int i = 0; i < 16; i ++ ) {
                list.add(i+1);
            }

            Collections.shuffle(list);

//        //배열을 리스트로 만들기
//        List numList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);

            for(int i = 0 ; i < 16; i ++ ) {

                Button btn = new Button(this);
                btn.setText(String.valueOf(list.get(i)));

                linear.addView(btn);

                btn.setOnClickListener(handler);
            }
        };

        //게임 시작 버튼
        Button startBtn = new Button(this);
        startBtn.setText("시작");
        startBtn.setOnClickListener(startHandler);

        linearLayout.addView(startBtn);




    }
}