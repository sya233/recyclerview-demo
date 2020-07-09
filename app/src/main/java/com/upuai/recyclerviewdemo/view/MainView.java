package com.upuai.recyclerviewdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upuai.recyclerviewdemo.R;
import com.upuai.recyclerviewdemo.adapter.HistoryAdapter;
import com.upuai.recyclerviewdemo.gson.History;
import com.upuai.recyclerviewdemo.model.MainModel;
import com.upuai.recyclerviewdemo.presenter.MainPresenter;

import java.util.Calendar;

public class MainView extends AppCompatActivity implements View.OnClickListener {

    private MainPresenter mainPresenter;

    private Button btnHistory;
    private ProgressBar pbHistory;
    // RecyclerView
    private RecyclerView rvHistory;
    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化
        init();
        // btnHistory按钮监听
        btnHistory.setOnClickListener(this);
    }

    private void init() {
        // 初始化控件id
        btnHistory = findViewById(R.id.btn_history);
        pbHistory = findViewById(R.id.pb_history);
        rvHistory=findViewById(R.id.rv_history);
        // 初始化Model和Presenter
        mainPresenter = new MainPresenter(new MainModel(), this);
        // 初始化RecyclerView
        LinearLayoutManager layoutManager=new LinearLayoutManager(rvHistory.getContext());
        rvHistory.setLayoutManager(layoutManager);
        historyAdapter=new HistoryAdapter();
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (v.getId() == R.id.btn_history) {
            mainPresenter.findDataFromServer(month, day);
        }
    }

    private void findAddress(String key, String v, int month, int day) {

    }

    private void updateRecyclerView() {

    }


    public void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pbHistory.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pbHistory.setVisibility(View.GONE);
            }
        });
    }

    public void showErrorData(String error) {
        showToast(this, error);
    }

    public void showData(final History history) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                historyAdapter.updateList(history.getResult());
                historyAdapter.notifyDataSetChanged();
                rvHistory.setAdapter(historyAdapter);
            }
        });
    }

    public void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}