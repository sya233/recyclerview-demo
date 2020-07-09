package com.upuai.recyclerviewdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upuai.recyclerviewdemo.R;
import com.upuai.recyclerviewdemo.gson.History;
import com.upuai.recyclerviewdemo.gson.HistoryResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder> {

    private List<HistoryResult> historyResultList = new ArrayList<>();

    static class Holder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDate;
        TextView tvDes;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDes = itemView.findViewById(R.id.tv_des);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_history, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.Holder holder, int position) {
        HistoryResult result = historyResultList.get(position);
        holder.tvTitle.setText(result.getTitle());

        String date = result.getYear() + "." + result.getMonth() + "." + result.getDay();
        holder.tvDate.setText(date);
        holder.tvDes.setText(result.getDes());
    }

    @Override
    public int getItemCount() {
        return historyResultList.size();
    }

    public void updateList(List<HistoryResult> list) {
        historyResultList = list;
    }
}
