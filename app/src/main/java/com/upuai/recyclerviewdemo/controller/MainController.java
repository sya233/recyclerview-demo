package com.upuai.recyclerviewdemo.controller;

import com.upuai.recyclerviewdemo.model.MainModel;
import com.upuai.recyclerviewdemo.view.MainView;

public class MainController {

    private MainView mainView;
    private MainModel mainModel;

    public void initView(MainView view) {
        mainView = view;
    }

    public void initModel(MainModel model) {
        mainModel = model;
    }

    public void getDataFromServer(int month,int day){
        mainView.showProgressBar();
        mainModel.getDataFromServer(month,day);
    }

    public void doWhenResponseReady(){
        mainView.hideProgressBar();
        mainView.showData();
    }

    public void doWhenResponseError(){
        mainView.hideProgressBar();
    }

}
