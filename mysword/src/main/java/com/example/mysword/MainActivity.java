package com.example.mysword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //화면 로드시, volly 사용 -> 사진, 성함 가져오기
        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();
        String inputWord = "사과";
        String url = "https://stdict.korean.go.kr/api/search.do?key=228B1E6329BDCFE43D09A1BE5129B58B&req_type=json&q="+inputWord;
        StringRequest request = new StringRequest(url, s->{

            if (s.isEmpty()) {
                System.out.println("없는 단어입니다");
            } else {
                Map<String,Object> map = gson.fromJson(s,Map.class);
                System.out.println(((Map)((Map)((List)((Map)map.get("channel")).get("item")).get(0)).get("sense")).get("definition"));

            }
//            Map<String,Object> map = gson.fromJson(s,Map.class);
//            System.out.println(map);
//            System.out.println(map.get("channel"));
//            System.out.println(((Map)map.get("channel")).get("item"));
//            System.out.println(((List)((Map)map.get("channel")).get("item")).get(0));
//            System.out.println(((List)((Map)map.get("channel")).get("item")).get(1));

            //완성
//            System.out.println(((Map)((List)((Map)map.get("channel")).get("item")).get(0)).get("sense"));
//            System.out.println(((Map)((Map)((List)((Map)map.get("channel")).get("item")).get(0)).get("sense")).get("definition"));
        },e->{
            System.out.println("에러발생");
            System.out.println(e);
        });

        queue.add(request);

    }
}