package com.upuai.recyclerviewdemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class History {

    @SerializedName("reason")
    public String reason;
    @SerializedName("error_code")
    public String error_code;
    @SerializedName("result")
    public List<Result> result;

    static class Result {
        @SerializedName("_id")
        public String _id;
        @SerializedName("title")
        public String title;
        @SerializedName("pic")
        public String pic;
        @SerializedName("year")
        public String year;
        @SerializedName("month")
        public String month;
        @SerializedName("day")
        public String day;
        @SerializedName("des")
        public String dec;
        @SerializedName("lunar")
        public String lunar;
    }
}
