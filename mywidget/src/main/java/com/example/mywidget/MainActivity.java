package com.example.mywidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText txtNum1, txtNum2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        btn = findViewById(R.id.button);
        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        tv = findViewById(R.id.textView4);

        btn.setOnClickListener( v-> {
            String strNum1 = txtNum1.getText().toString();
            String strNum2 = txtNum2.getText().toString();

            int num1 = Integer.parseInt(strNum1);
            int num2 = Integer.parseInt(strNum2);

            int calcNum = num1 + num2;

            tv.setText(Integer.toString(calcNum));
        });

        //1번 방법 - 상속
//        btn.setOnClickListener(this); // implements View.OnClickListener 필요.

        //2번 방법 - 이너 클래스
//        btn.setOnClickListener(new ClickHandler()); //innerClass 사용

        //3번 방법 - 익명 클래스
        //클래스 선언과 생성을 동시에 한 것.
//        View.OnClickListener handler = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "클릭!!!", Toast.LENGTH_LONG).show();
//                System.out.println("클릭됨");
//            }
//        };
//
//        btn.setOnClickListener(handler);

        //4번 람다식 ( 하나의 추상 메서드만 있는 인터페이스인 경우 )
//        View.OnClickListener handler = (view) -> {
//                Toast.makeText(getApplicationContext(), "클릭!!!", Toast.LENGTH_LONG).show();
//                System.out.println("클릭됨");
//        };
//
//        btn.setOnClickListener(handler);

        //5번 람다식 - 표현 변경
//        View.OnClickListener handler = v -> {
//            Toast.makeText(getApplicationContext(), "클릭!!!", Toast.LENGTH_LONG).show();
//            System.out.println("클릭됨");
//        };
//        btn.setOnClickListener(handler);

        //6번 하나로.
//        btn.setOnClickListener( v-> {
//            Toast.makeText(getApplicationContext(), "클릭!!!", Toast.LENGTH_LONG).show();
//            System.out.println("클릭됨");
//        });





    }

//    public class ClickHandler implements View.OnClickListener{
//
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(getApplicationContext(),"클릭!!!",Toast.LENGTH_LONG).show();
//            System.out.println("클릭됨");
//        }
//
//    }

}