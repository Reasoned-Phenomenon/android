package com.example.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_dlg,btn_list,btn_multi,btn_select, btn_login;

        btn_dlg = findViewById(R.id.btn_dlg);
        btn_list = findViewById(R.id.btn_list);
        btn_multi = findViewById(R.id.btn_multi);
        btn_select = findViewById(R.id.btn_select);
        btn_login = findViewById(R.id.btn_login);

        String[] city = new String[] {"대구","서울","부산"};

        btn_dlg.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("alert")
                    .setMessage("알림")
                    .setPositiveButton("수정",(dialogInterface, i) -> {
                        Log.d("alert","수정버튼");
                    })
                    .setNegativeButton("삭제",(di, i) -> {
                        Log.d("alert","삭제버튼");
                    })
                    .create()
                    .show();

        });
        btn_list.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(city,(d, i) -> {
                Log.d("alert",city[i]);
            })
                    .setTitle("여기가 제목")
                    .setIcon(R.mipmap.ic_launcher)
                    .create()
                    .show();
        });
        btn_multi.setOnClickListener(v->{
//            ArrayList<Integer> selectedItems = new ArrayList();
            ArrayList selectedItems = new ArrayList<Integer>();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMultiChoiceItems(city,null,(dialog, which, isChecked) -> {
                if (isChecked) {
                    selectedItems.add(which);
                } else if (selectedItems.contains(which)) {
                    selectedItems.remove(which);
                }
            }).setPositiveButton("OK",(dialog, which) -> {
                //값 보여주는 부분(체크한 것)
                //내가 한 것.
//                for(int i=0 ; i < selectedItems.size() ; i ++) {
//                    System.out.println(city[selectedItems.get(i)]);
//                }
                //교수님
//                city.stream().filter().map(System.out::pirnt); //교수님 //다시 한번 보기

                for(Object i : selectedItems) {
//                    int pos = (Integer) i;
                    int pos = ((Integer) i).intValue();
                    Log.d("alert",city[pos]);
                }
            })
                    .setTitle("멀티 제목")
                    .setIcon(R.mipmap.ic_launcher)
                    .create()
                    .show();
        });

        //select

        //커스텀 - 모달 - 로그인
        btn_login.setOnClickListener(v->{
            customModal();
        });

    }

    private void customModal () {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = this.getLayoutInflater(); //view 찾아 넣기 위해 필요한 부분. id 값 쓴다면 필요 없음.

        builder.setView(R.layout.activity_login)
                .create()
                .show();

    }

}