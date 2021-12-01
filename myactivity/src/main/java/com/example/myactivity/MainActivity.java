package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btn_map, btn_call, btn_pic, btn_sms, btn_life, btn_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_map = findViewById(R.id.btn_map);
        btn_call = findViewById(R.id.btn_call);
        btn_pic = findViewById(R.id.btn_pic);
        btn_sms = findViewById(R.id.btn_sms);
        btn_life = findViewById(R.id.btn_life);
        btn_web = findViewById(R.id.btn_web);


        btn_life.setOnClickListener(v -> {
            Intent intent = new Intent(this, LifeActivity.class);
            startActivity(intent);
        });

        btn_call.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:010-1234-5678");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
        });

        btn_web.setOnClickListener(v -> {
            Uri uri = Uri.parse("http://www.google.co.kr");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        btn_sms.setOnClickListener( v-> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.putExtra("sms_body","안녕하세요");
            intent.setData(Uri.parse("smsto:" + Uri.encode("010-1234-5678")));
            startActivity(intent);
        });

        btn_pic.setOnClickListener( v-> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });

    }
}