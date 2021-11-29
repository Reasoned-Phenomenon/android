package com.example.mypet;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    CheckBox ChkAgree;
    RadioGroup Rgroup1;
    RadioButton RdoDog,RdoCat,RdoRabbit;
    Button btnOK;
    ImageView imgPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChkAgree = findViewById(R.id.ChkAgree);

        Rgroup1 = findViewById(R.id.Rgroup1);

            RdoDog = findViewById(R.id.RdoDog);
            RdoCat = findViewById(R.id.RdoCat);
            RdoRabbit = findViewById(R.id.RdoRabbit);

        btnOK = findViewById(R.id.btnOK);

        imgPet = findViewById(R.id.imgPet);

        ChkAgree.setOnClickListener(v->{
            if(ChkAgree.isChecked()) {

                Rgroup1.setVisibility(View.VISIBLE);
                btnOK.setVisibility(View.VISIBLE);
                imgPet.setVisibility(View.VISIBLE);

            } else {

                Rgroup1.setVisibility(View.INVISIBLE);
                btnOK.setVisibility(View.INVISIBLE);
                imgPet.setVisibility(View.INVISIBLE);

            }
        });

        View.OnClickListener handler = v-> {
            System.out.println(v);
//            if(RdoDog.isChecked()) {
//                imgPet.setImageResource(R.drawable.dog);
//            } else if (RdoCat.isChecked()) {
//                imgPet.setImageResource(R.drawable.french);
//            } else {
//                imgPet.setImageResource(R.drawable.golden);
//            }
            switch (v.getId()) {
                case R.id.RdoDog: imgPet.setImageResource(R.drawable.dog); break;
                case R.id.RdoCat: imgPet.setImageResource(R.drawable.french); break;
                case R.id.RdoRabbit: imgPet.setImageResource(R.drawable.golden); break;
            }

        };

        RdoDog.setOnClickListener(handler);
        RdoCat.setOnClickListener(handler);
        RdoRabbit.setOnClickListener(handler);

//        btnOK.setOnClickListener(handler);




    }
}