package com.example.mysword;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView view_rv;

    EditText et_input;
    Button btn_send;
    TextView tv_time, tv_topic, tv_rank, tv_score;

    String str_start, str_def,inputWord, outputWord;
    int int_score;

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
        tv_topic = findViewById(R.id.tv_topic);
        tv_rank = findViewById(R.id.tv_rank);
        tv_score = findViewById(R.id.tv_score);

        //queue, gson 선언.
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        //DB 호출
        DBHelper dbHelper = new DBHelper(this);

        //어댑터
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        view_rv.setLayoutManager(layoutManager);

        //최고점
        RecordDAO dao = new RecordDAO();
        tv_rank.append(Integer.toString( dao.maxRec(dbHelper)) );

        //제시어
        String[] arr = new String[] {"홍길동","대한민국","예담","대구","나비잠","아슬라","여우비","별하"};

        Random random2 = new Random();
        str_start = arr[(random2.nextInt(arr.length))];
        tv_topic.append(str_start);
        list.add(str_start);

        view_rv.setAdapter(new MyRecycleAdapter(this,list));

        //다이얼 로그
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("alert")
                .setMessage("시간이 초과 됐습니다")
                .setPositiveButton("다시하기",(dialogInterface, i) -> {
                    finish();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                })
                .setNegativeButton("종료",(dialogInterface, i) -> {

                })
                .create();

        //타이머
        CountDownTimer timer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_time.setText(millisUntilFinished / 1000 + " 초");
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onFinish() {
                tv_time.setText("시간 초과!!!");
                //다이얼로그 띄워주기
                builder.show();
                //기록저장
                //TODO - 다이얼로그로 이름 받기
                RecordDAO dao = new RecordDAO();
                dao.insertRec(dbHelper,int_score);
            }
        }.start();

        //입력버튼
        btn_send.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                            view_rv.scrollToPosition(view_rv.getAdapter().getItemCount()-1);
                            
                            //입력창 비우고, 시간 재시작
                            et_input.setText("");
                            timer.start();

                            //글자 길이에 따라서 점수획득
                            int_score += inputWord.length();

                            //현재점수에 등록
                            tv_score.setText("현재: "+Integer.toString(int_score) + " 점");

                            //컴퓨터 대응
                            outputWord = inputWord.substring(inputWord.length()-1)+"??";
                            String comUrl = "https://stdict.korean.go.kr/api/search.do?key=228B1E6329BDCFE43D09A1BE5129B58B&req_type=json&advanced=y&method=wildcard&q="+outputWord;
                            StringRequest comRequest = new StringRequest(comUrl, comS->{

                                if (comS.equals("{}")) {

                                    //승리하였습니다
                                    Toast.makeText(getApplicationContext(), "승리하셨습니다", Toast.LENGTH_SHORT).show();

                                    //기록 저장 - 승리시 50점 추가
                                    RecordDAO dao = new RecordDAO();
                                    dao.insertRec(dbHelper,int_score+50);

                                } else {

                                    Map<String, Object> comMap = gson.fromJson(comS, Map.class);

                                    int forSize = ((List) ((List) ((Map) comMap.get("channel")).get("item"))).size();
                                    Random random = new Random();
                                    int num = random.nextInt(forSize);
                                    outputWord = (((Map) ((List) ((Map) comMap.get("channel")).get("item")).get(num)).get("word")).toString();

                                    //설명 내용
                                    str_def = ((Map) ((Map) ((List) ((Map) comMap.get("channel")).get("item")).get(num)).get("sense")).get("definition").toString();
                                    System.out.println(str_def);

                                    //특수문자 제거
                                    outputWord = outputWord.replace("-", "");

                                    //설명부분을 토스트로 띄우기
                                    Toast.makeText(getApplicationContext(), str_def, Toast.LENGTH_SHORT).show();

                                    //입력단어 뷰에 넣기
                                    list.add(outputWord);
                                    view_rv.setAdapter(new MyRecycleAdapter(getApplicationContext(), list));
                                    ((MyRecycleAdapter) view_rv.getAdapter()).notifyDataSetChanged();
                                    view_rv.scrollToPosition(view_rv.getAdapter().getItemCount() - 1);
                                }
                                //volley 에러 발생시
                            },comE->{
                                System.out.println("에러발생");
                                System.out.println(comE);
                            });

                            queue.add(comRequest);
                        }
                        
                    //volley 에러 발생시
                    },e->{
                        System.out.println("에러발생");
                        System.out.println(e);
                    });

                    queue.add(request);

                } else {
                    System.out.println("다시 입력해주세요");
                }

            }
        });

    }
    //TODO - 엔터키로 입력
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        System.out.println("키다운");
        if(keyCode == KeyEvent.KEYCODE_ENTER) {
            System.out.println("엔터");
            btn_send.callOnClick();

        }

        return super.onKeyDown(keyCode, event);
    }
}