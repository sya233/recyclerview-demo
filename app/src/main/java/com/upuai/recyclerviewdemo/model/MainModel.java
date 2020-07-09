package com.upuai.recyclerviewdemo.model;

import android.util.Log;

import com.google.gson.Gson;
import com.upuai.recyclerviewdemo.controller.MainController;
import com.upuai.recyclerviewdemo.gson.History;
import com.upuai.recyclerviewdemo.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainModel {

    private MainController mainController;
    private History history;

    private final static String TAG = "MainModel";

    public MainModel(MainController controller) {
        mainController = controller;
    }

    public void getDataFromServer(int month, int day) {
        HttpUtil.sendOkHttpRequest(month, day, new okhttp3.Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mainController.doWhenResponseError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d(TAG, "返回的字符串是: " + responseData);
                history = new Gson().fromJson(responseData, History.class);
                Log.d(TAG, "解析之后: " + history.getResult().get(0).getDes());
                mainController.doWhenResponseReady();
            }
        });
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
