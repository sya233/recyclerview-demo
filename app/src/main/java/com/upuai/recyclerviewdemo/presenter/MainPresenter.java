package com.upuai.recyclerviewdemo.presenter;

import com.google.gson.Gson;
import com.upuai.recyclerviewdemo.gson.History;
import com.upuai.recyclerviewdemo.model.MainModel;
import com.upuai.recyclerviewdemo.util.HttpUtil;
import com.upuai.recyclerviewdemo.view.MainView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainPresenter {

    private MainModel mainModel;
    private MainView mainView;

    public MainPresenter(MainModel model, MainView view) {
        mainModel = model;
        mainView = view;
    }

    public void findDataFromServer(int month, int day) {
        mainView.showProgressBar();
        HttpUtil.sendOkHttpRequest(month,day,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                mainView.hideProgressBar();
                mainView.showErrorData(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                History history=new Gson().fromJson(responseData,History.class);
                mainView.hideProgressBar();
                mainView.showData(history);
            }
        });
    }

}
