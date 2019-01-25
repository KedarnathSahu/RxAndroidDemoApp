package com.cumulations.rxandroiddemoapp;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Observable<String> mObservable;
    private Observer<String> mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        //Step1: Create Observable that emits the data
        mObservable = Observable.just("Radiating data 4m RxAndroid...");

        //Step2: Create an Observer
        mObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("@@@", "onSubscribe.");
            }

            @Override
            public void onNext(String s) {
                Log.i("@@@", "onNext.");
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("@@@", "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("@@@", "onComplete.");
            }
        };
    }

    public void subscribe2Text(View view) {
        //Step3: Subscribe 2 the Observable
        mObservable.subscribe(mObserver);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Step4: Unsubscribe 4m an Observable
        if (mObservable != null) {
            Log.i("@@@", "unsubscribe");
            mObservable.unsubscribeOn(Schedulers.newThread());
        }
    }
}
