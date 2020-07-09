package com.upuai.recyclerviewdemo.gson;

import java.util.List;

public class History {

    private String reason;
    private String error_code;
    private List<HistoryResult> result;

    public History() {
    }

    public History(String reason, String error_code, List<HistoryResult> result) {
        this.reason = reason;
        this.error_code = error_code;
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<HistoryResult> getResult() {
        return result;
    }

    public void setResult(List<HistoryResult> result) {
        this.result = result;
    }

}
