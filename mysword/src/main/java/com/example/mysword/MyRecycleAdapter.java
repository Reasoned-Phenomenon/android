package com.example.mysword;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    ArrayList<String> data;
    Context context;

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public MyRecycleAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    public MyRecycleAdapter () {};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position%2 == 1) {
            holder.tv_txt.setBackground(ContextCompat.getDrawable(context,R.drawable.bubble_send));
            holder.tv_txt.setGravity(Gravity.RIGHT);
        } else {
            holder.tv_txt.setBackground(ContextCompat.getDrawable(context,R.drawable.bubble_receive));
            holder.tv_txt.setGravity(Gravity.LEFT);
        }

        holder.tv_txt.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_txt = itemView.findViewById(R.id.tv_txt);
        }

    }
}