package com.example.mywidget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_plus,btn_minus,btn_multiple,btn_divide;
    EditText txtNum1, txtNum2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiple = findViewById(R.id.btn_multiple);
        btn_divide = findViewById(R.id.btn_divide);

        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        
        tv = findViewById(R.id.txtResult); //결과창

        //이벤트 핸들러 - 동일한 핸들러로 처리.
        View.OnClickListener handler = v -> {

            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());
            int result =0;
            switch (v.getId()) {
                case R.id.btn_plus: result = n1+n2; break;
                case R.id.btn_minus: result = n1 - n2; break;
                case R.id.btn_multiple: result = n1 * n2; break;
                //case R.id.btn_divide: result = n1 / n2; break; //double 어떻게 하면 좋을지.
            }

            tv.setText(String.valueOf(result));

        };

        btn_plus.setOnClickListener(handler);
        btn_minus.setOnClickListener(handler);
        btn_multiple.setOnClickListener(handler);
        //나눗셈
        btn_divide.setOnClickListener( v-> {
            String strNum1 = txtNum1.getText().toString();
            String strNum2 = txtNum2.getText().toString();

            double num1 = Integer.parseInt(strNum1);
            double num2 = Integer.parseInt(strNum2);
            double calcNum=0;
            if( num2 != 0) {

                calcNum = (double) num1 / num2;
            }

            tv.setText(String.valueOf(calcNum));

        });



//        //덧셈
//        btn_plus.setOnClickListener( v-> {
//            String strNum1 = txtNum1.getText().toString();
//            String strNum2 = txtNum2.getText().toString();
//
//            int num1 = Integer.parseInt(strNum1);
//            int num2 = Integer.parseInt(strNum2);
//
//            int calcNum = num1 + num2;
//
////            tv.setText(Integer.toString(calcNum));
//            tv.setText(String.valueOf(calcNum));
//
//        });
//
//        //뺄셈
//        btn_minus.setOnClickListener( v-> {
//            String strNum1 = txtNum1.getText().toString();
//            String strNum2 = txtNum2.getText().toString();
//
//            int num1 = Integer.parseInt(strNum1);
//            int num2 = Integer.parseInt(strNum2);
//
//            int calcNum = num1 - num2;
//
//            tv.setText(String.valueOf(calcNum));
//
//        });
//
//        //곱셈
//        btn_multiple.setOnClickListener( v-> {
//            String strNum1 = txtNum1.getText().toString();
//            String strNum2 = txtNum2.getText().toString();
//
//            int num1 = Integer.parseInt(strNum1);
//            int num2 = Integer.parseInt(strNum2);
//
//            int calcNum = num1 * num2;
//
//            tv.setText(String.valueOf(calcNum));
//
//        });
//
//        //나눗셈
//        btn_divide.setOnClickListener( v-> {
//            String strNum1 = txtNum1.getText().toString();
//            String strNum2 = txtNum2.getText().toString();
//
//            double num1 = Integer.parseInt(strNum1);
//            double num2 = Integer.parseInt(strNum2);
//
//            double calcNum = (double) num1 / num2;
//
//            tv.setText(String.valueOf(calcNum));
//
//        });

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