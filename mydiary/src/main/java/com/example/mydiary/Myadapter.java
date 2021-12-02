package com.example.mydiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Myadapter extends BaseAdapter {

    ArrayList<DiaryVO> data;

    public Myadapter () {}

    public Myadapter (ArrayList<DiaryVO> data) {
        this.data = data;
    }

    public ArrayList<DiaryVO> getData() {
        return data;
    }

    public void setData(ArrayList<DiaryVO> data) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        convertView = inflater.inflate(R.layout.listview_item,parent,false);

        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtContent = convertView.findViewById(R.id.txtContent);
        TextView txtTime = convertView.findViewById(R.id.txtTime);

        txtTitle.setText(data.get(position).getTitle());
        txtContent.setText(data.get(position).getContent());
        txtTime.setText(data.get(position).getTime());

        return convertView;
    }
}
