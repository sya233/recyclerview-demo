package com.upuai.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder> {

    private List<History.Result> mResultList = new ArrayList<>();

    static class Holder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDate;
        TextView tvDes;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvDes = itemView.findViewById(R.id.tv_des);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.rv_item_history, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.Holder holder, int position) {
        History.Result result = mResultList.get(position);
        holder.tvTitle.setText(result.title);
        String date = "(" + result.year + "." + result.month + "." + result.day + ")";
        holder.tvDate.setText(date);
        holder.tvDes.setText(result.dec);
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    public void updateList(List<History.Result> list) {
        mResultList = list;
    }
}
