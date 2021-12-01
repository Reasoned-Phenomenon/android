package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn_list,btn_insert, btn_select, btn_delete;

    TextView tv_view;

    EditText et_id, et_name, et_pw, et_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_list = findViewById(R.id.btn_list);
        btn_select = findViewById(R.id.btn_select);
        btn_delete = findViewById(R.id.btn_delete);
        btn_insert = findViewById(R.id.btn_insert);

        tv_view = findViewById(R.id.tv_view);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_pw = findViewById(R.id.et_pw);
        et_role = findViewById(R.id.et_role);

        RequestQueue queue = Volley.newRequestQueue(this);
        Gson gson = new Gson();

        btn_select.setOnClickListener( v -> {

//            String url ="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101"; //매개변수가 무엇인지는 알아야함.
//            String url ="http://10.0.2.2/userList";

            // 단건 조회
            //id값 가져와서
            //String targetId = et_id.getText().toString();

//            String url ="http://10.0.2.2/users?id=";//있는 id값 넣어서 조회하기.
            //String url ="http://10.0.2.2/users?id="+targetId;//있는 id값 넣어서 조회하기.
            String url ="http://10.0.2.2/userList";

            StringRequest request = new StringRequest(url, s-> {
                //단건조회 -> 이름과 패스워드 표시. Map이나 UserVO 사용.
                System.out.println(s);
                //List list = gson.fromJson(s, List.class); //형태확인해보기

//                et_name.setText();
//                et_pw.setText();
//                et_role.setText();

                //
                //tv_view.setText(s);
            }, e->{
                Log.d("request",e.toString());
            });

            queue.add(request);

//            StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                    url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });
        });


        btn_insert.setOnClickListener( v-> {
            //view에서 값 받아와서 넣어주면 됨.
            String url = "http://10.0.2.2/userInsert";
            StringRequest request = new StringRequest(Request.Method.POST,url, s-> {
                tv_view.setText(s);
            }, e->{}) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id","chichi");
                    map.put("name","홍길동");
                    map.put("password","222");
                    map.put("role","Admin");
                    return map;
                }
            }; //post 방식 표시
            //중괄호를 붙여준다는 것은 StringRequest를 상속받는다는 의미

            queue.add(request);
        });

        //삭제
        btn_delete.setOnClickListener(v-> {
            String url ="http://10.0.2.2/userDelete";
            StringRequest request = new StringRequest(url, s-> {
                //단건조회 -> 이름과 패스워드 표시. Map이나 UserVO 사용.
                
                gson.toJson(s);


                tv_view.setText(s);

                //
                //tv_view.setText(s);
            }, e->{
                Log.d("request",e.toString());
            });

            queue.add(request);
        });

    }
}