package com.example.mysword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    ArrayList<RecordVO> data;
    Context context;

    public ArrayList<RecordVO> getData() {
        return data;
    }

    public void setData(ArrayList<RecordVO> data) {
        this.data = data;
    }

    public ListAdapter(Context context, ArrayList<RecordVO> data) {
        this.context = context;
        this.data = data;
    }

    public ListAdapter () {};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list, parent, false);
        ListAdapter.MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_list_rank.setText(Integer.toString(position+1));
        holder.tv_list_name.setText(data.get(position).getName());
        holder.tv_list_score.setText(Integer.toString(data.get(position).getScore()));
        holder.tv_list_time.setText(data.get(position).getTime().substring(5,10));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_list_rank,tv_list_name,tv_list_score,tv_list_time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_list_rank = itemView.findViewById(R.id.tv_list_rank);
            tv_list_name = itemView.findViewById(R.id.tv_list_name);
            tv_list_score = itemView.findViewById(R.id.tv_list_score);
            tv_list_time = itemView.findViewById(R.id.tv_list_time);
        }

    }
}
