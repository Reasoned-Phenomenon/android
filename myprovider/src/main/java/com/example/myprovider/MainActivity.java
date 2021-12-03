package com.example.myprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_contact = findViewById(R.id.btn_contact);
        Button btn_call = findViewById(R.id.btn_call);

        EditText et_view = findViewById(R.id.et_view);

        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_CONTACTS},MODE_PRIVATE);

        AutoPermissions.Companion.loadAllPermissions(this,101);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_view.setText(getCallHistory());
            }
        });

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_view.setText(getContacts());
            }
        });


    }

    public String getCallHistory() {
        String[] callSet = new String[]{CallLog.Calls.DATE,
                CallLog.Calls.TYPE, CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION};

        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                callSet, null, null, null);

        if (c == null)
            return "통화기록 없음";

        StringBuffer callBuff = new StringBuffer(); // 최대 100 통화 저장
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");
        c.moveToFirst();
        do {
            long callDate = c.getLong(0);
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");
            if (c.getInt(1) == CallLog.Calls.INCOMING_TYPE)
                callBuff.append("착신 :");
            else
                callBuff.append("발신 :");
            callBuff.append(c.getString(2) + ":");
            callBuff.append(c.getString(3) + "초\n");
        } while (c.moveToNext());

        c.close();

        return callBuff.toString();
    }

    public String getContacts () {

        String[] PROJECTION = //Callset과 유사. 컬럼정보.
                {
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.LOOKUP_KEY,
                        Build.VERSION.SDK_INT
                                >= Build.VERSION_CODES.HONEYCOMB ?          //버전에 따라서 컬럼명이 달라서, 버전에 따라서 가져오는 컬럼명 바꿔주는 부분.
                                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                                ContactsContract.Contacts.DISPLAY_NAME

                };
        Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                PROJECTION, null, null, null);

        if (c == null) //항상 null 체크
            return "주소록 비어있음";

        StringBuffer contactBuff = new StringBuffer();
        contactBuff.append("\nRow ID : 검색키 : 이름\n\n");
        c.moveToFirst();

        do {
            contactBuff.append("Row ID:"+c.getString(0)+"\n");
            contactBuff.append(" 키:"+c.getString(1)+"\n");
            contactBuff.append(" 이름:"+c.getString(2)+"\n\n\n");
        } while (c.moveToNext());

        c.close();

        return contactBuff.toString();
    }
}

