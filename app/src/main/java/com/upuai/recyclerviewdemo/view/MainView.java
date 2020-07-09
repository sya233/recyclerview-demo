package com.upuai.recyclerviewdemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.upuai.recyclerviewdemo.R;
import com.upuai.recyclerviewdemo.adapter.HistoryAdapter;
import com.upuai.recyclerviewdemo.controller.MainController;
import com.upuai.recyclerviewdemo.gson.History;
import com.upuai.recyclerviewdemo.gson.HistoryResult;
import com.upuai.recyclerviewdemo.model.MainModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainView extends AppCompatActivity implements View.OnClickListener {

    private MainController mainController;
    private MainModel mainModel;

    private Button btnHistory;
    private ProgressBar pbHistory;
    // RecyclerView
    private RecyclerView rvHistory;
    private HistoryAdapter historyAdapter;
    private History history;

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
        // 初始化Model和Controller
        mainController=new MainController();
        mainModel=new MainModel(mainController);
        mainController.initView(this);
        mainController.initModel(mainModel);
        // 初始化控件id
        btnHistory=findViewById(R.id.btn_history);
        pbHistory=findViewById(R.id.pb_history);
        rvHistory=findViewById(R.id.rv_history);
        // 初始化RecyclerView
        LinearLayoutManager layoutManager=new LinearLayoutManager(rvHistory.getContext());
        rvHistory.setLayoutManager(layoutManager);
        historyAdapter=new HistoryAdapter();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_history){
            Calendar calendar=Calendar.getInstance();
            int month=calendar.get(Calendar.MONTH);
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            mainController.getDataFromServer(month,day);
        }
    }

    public void showProgressBar() {
        pbHistory.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pbHistory.setVisibility(View.GONE);
            }
        });
    }

    public void showData(){
        history=mainModel.getHistory();
        historyAdapter.updateList(history.getResult());
        rvHistory.setAdapter(historyAdapter);
    }

}