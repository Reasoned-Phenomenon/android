package com.example.myhttp;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        System.out.println("hello");
        //Log.d("test","hello");

        Gson gson = new Gson();
        EmpVO vo = new EmpVO();

        vo.setAge(10);
        vo.setFname("choi");

        String result = gson.toJson(vo);

        System.out.println(result);

        String response = "{\"fname\":\"hong\",\"age\":20}";

        EmpVO emp = gson.fromJson(response,EmpVO.class);
        System.out.println(emp.getFname());
        System.out.println(emp.getAge());

        String res = "{\"fname\":\"hong\",\"age\":20}";
        Map<String, Object> map = gson.fromJson(res, Map.class);

        System.out.println(map);
        System.out.println(map.get("fname"));
        System.out.println(map.get("age"));

        String resres = "{\"data\":[{\"fname\":\"김유진\",\"age\":10}]}";
        Map<String, Object> map2 = gson.fromJson(resres,Map.class);
//        System.out.println(map2);
//        System.out.println(map2.get("data"));
//        System.out.println( ((ArrayList)map2.get("data")).get(0) );
//        System.out.println( ((Map<String, Object>)map2.get("data")).get("fname") );

        List list = (List) map2.get("data");
        Map std = (Map) list.get(0);
        std.get("fname");
        System.out.println(std.get("fname"));
        // 한줄로 하면,
        ((Map) ((List)map2.get("data")).get(0)).get("fname");

        String resresres = "{\"data\":[{\"fname\":\"김유진\",\"age\":10}]}";
        ListEmp listEmp = gson.fromJson(resresres, ListEmp.class);
        System.out.println(listEmp.data.get(0).getFname());



    }

}
