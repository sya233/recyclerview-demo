package com.upuai.recyclerviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnHistory;
    private RecyclerView rvHistory;
    private ProgressBar pbHistory;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private HistoryAdapter mHistoryAdapter;

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
        rvHistory = findViewById(R.id.rv_history);
        pbHistory = findViewById(R.id.pb_history);
        // 设置RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(rvHistory.getContext());
        mHistoryAdapter = new HistoryAdapter();
        rvHistory.setLayoutManager(layoutManager);
        rvHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (v.getId() == R.id.btn_history) {
            findAddress("0d696f99614d80195334a654faa9fc66", "1.0", month, day);
        }
    }

    private void findAddress(String key, String v, int month, int day) {
        Disposable disposable = fetchAddress(key, v, month, day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<History>() {

            @Override
            public void onNext(History history) {
                hideProgressBar();
                updateRecyclerView(history.result);
            }

            @Override
            public void onError(Throwable e) {
                hideProgressBar();
            }

            @Override
            protected void onStart() {
                showProgressBar();
            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete()方法");
            }
        });
        mCompositeDisposable.add(disposable);
    }

    private void updateRecyclerView(List<History.Result> list) {
        mHistoryAdapter.updateList(list);
        mHistoryAdapter.notifyDataSetChanged();
    }

    private Retrofit mRetrofit = null;

    private Observable<History> fetchAddress(String key, String v, int month, int day) {
        if (mRetrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
            mRetrofit = new Retrofit.Builder().baseUrl("http://api.juheapi.com/japi/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client).build();
        }
        return mRetrofit.create(TodayOfHistory.class).getTodayOfHistory(key, v, month, day);
    }

    private void showProgressBar() {
        pbHistory.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        pbHistory.setVisibility(View.GONE);
    }

    interface TodayOfHistory {
        @POST("toh")
        Observable<History> getTodayOfHistory(@Query("key") String key, @Query("v") String v,
                                              @Query("month") int month, @Query("day") int day);
    }
}