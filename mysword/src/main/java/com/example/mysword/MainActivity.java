package com.example.mysword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        //queue, gson 선언.
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        //어댑터
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        view_rv.setLayoutManager(layoutManager);
        list.add("제시어");
        System.out.println(list);
        view_rv.setAdapter(new MyRecycleAdapter(this,list));

        //입력버튼
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastWord = list.get(list.size()-1);
                String lastLetter = lastWord.substring(lastWord.length()-1);

                inputWord = et_input.getText().toString();
                String firstLetter = inputWord.substring(0,1);
                System.out.println(firstLetter);
                if(lastLetter.equals(firstLetter)) {
                    String url = "https://stdict.korean.go.kr/api/search.do?key=228B1E6329BDCFE43D09A1BE5129B58B&req_type=json&q="+inputWord;
                    StringRequest request = new StringRequest(url, s->{

                        if (s.isEmpty()) {
                            System.out.println("없는 단어입니다");
                        } else {

                            Map<String,Object> map = gson.fromJson(s,Map.class);
                            str_def = ((Map)((Map)((List)((Map)map.get("channel")).get("item")).get(0)).get("sense")).get("definition").toString();

                            //설명부분 토스트
                            Toast.makeText(getApplicationContext(),str_def,Toast.LENGTH_SHORT).show();

                            list.add(inputWord);
                            view_rv.setAdapter(new MyRecycleAdapter(getApplicationContext(),list));
                            ((MyRecycleAdapter)view_rv.getAdapter()).notifyDataSetChanged();

                            et_input.setText("");
                        }
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
}