package com.example.mydiary;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Myadapter extends BaseAdapter {

    ArrayList<DiaryVO> data;

    public Myadapter () {}

    public Myadapter (ArrayList<DiaryVO> data) {
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return convertView;
    }
}
