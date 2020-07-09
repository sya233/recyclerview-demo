package com.upuai.recyclerviewdemo.gson;

import java.util.List;

public class History {

    private String error_code;
    private String reason;
    private List<HistoryResult> result;

    public History() {
    }

    public History(String error_code, String reason, List<HistoryResult> historyResult) {
        this.error_code = error_code;
        this.reason = reason;
        this.result = historyResult;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<HistoryResult> getResult() {
        return result;
    }

    public void setResult(List<HistoryResult> historyResult) {
        this.result = historyResult;
    }
}
