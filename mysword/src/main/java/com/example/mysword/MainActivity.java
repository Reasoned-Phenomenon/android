package com.example.mysword;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView view_rv;

    EditText et_input;
    Button btn_send;
    TextView tv_time;

    String str_def,inputWord;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view 선언.
        view_rv = findViewById(R.id.view_rv);
        et_input = findViewById(R.id.et_input);
        btn_send = findViewById(R.id.btn_send);
        tv_time = findViewById(R.id.tv_time);

        //queue, gson 선언.
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        //어댑터
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        view_rv.setLayoutManager(layoutManager);
        //TODO - 제시어 선정
        list.add("제시어");

        view_rv.setAdapter(new MyRecycleAdapter(this,list));

        //다이얼 로그
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("alert")
                .setMessage("시간이 초과 됐습니다")
                .setPositiveButton("다시하기",(dialogInterface, i) -> {
                    //TODO - 재시작 버튼 추가해야함
                    System.out.println("다시 시작 다이얼로그");
                })
                .create();

        //TODO - 타이머
        CountDownTimer timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_time.setText("남은 시간: " + millisUntilFinished / 1000 + " 초");
            }

            public void onFinish() {
                //TODO-dialog 다시 하겠냐는 물음
                tv_time.setText("시간 초과!!!");
                //다이얼로그 띄워주기
                builder.show();
            }
        }.start();

        //입력버튼
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //입력 단어의 첫글자
                inputWord = et_input.getText().toString();
                if(inputWord.length() < 2) {
                    Toast.makeText(getApplicationContext(),"두글자 이상 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                String firstLetter = inputWord.substring(0,1);

                //이전 단어의 마지막 글자
                String lastWord = list.get(list.size()-1);
                String lastLetter = lastWord.substring(lastWord.length()-1);

                //이전 단어의 마지막 글자 = 입력 단어의 첫글자
                if(lastLetter.equals(firstLetter)) {
                    String url = "https://stdict.korean.go.kr/api/search.do?key=228B1E6329BDCFE43D09A1BE5129B58B&req_type=json&q="+inputWord;
                    StringRequest request = new StringRequest(url, s->{
                        //사전에 해당 단어가 없을 때 -> s가 널일때.
                        if (s.isEmpty()) {
                            //없는 단어라는 토스트 띄우기.
                            Toast.makeText(getApplicationContext(),"없는 단어입니다",Toast.LENGTH_SHORT).show();

                        //사전에 해당 단어가 있을때
                        } else {

                            Map<String,Object> map = gson.fromJson(s,Map.class);
                            str_def = ((Map)((Map)((List)((Map)map.get("channel")).get("item")).get(0)).get("sense")).get("definition").toString();

                            //설명부분을 토스트로 띄우기
                            Toast.makeText(getApplicationContext(),str_def,Toast.LENGTH_SHORT).show();
                            
                            //입력단어 뷰에 넣기
                            list.add(inputWord);
                            view_rv.setAdapter(new MyRecycleAdapter(getApplicationContext(),list));
                            ((MyRecycleAdapter)view_rv.getAdapter()).notifyDataSetChanged();
                            
                            //입력창 비우고, 시간 재시작
                            et_input.setText("");
                            timer.start();
                        }
                    },e->{
                        System.out.println("에러발생");
                        System.out.println(e);
                    });

                    queue.add(request);
                    
                    //TODO-컴퓨터 대응

                    
                    

                } else {
                    System.out.println("다시 입력해주세요");
                }

            }
        });

    }
}