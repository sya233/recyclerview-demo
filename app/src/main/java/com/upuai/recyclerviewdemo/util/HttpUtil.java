package com.upuai.recyclerviewdemo.util;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    public static void sendOkHttpRequest(int month, int day, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();

        String version = "1.0";
        String key = "0d696f99614d80195334a654faa9fc66";
        RequestBody requestBody = new FormBody.Builder()
                .add("v", version)
                .add("month", String.valueOf(month))
                .add("day", String.valueOf(day))
                .add("key", key)
                .build();

        String address = "http://api.juheapi.com/japi/toh";
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
