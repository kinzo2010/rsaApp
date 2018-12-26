package com.text.encryption.rsa.rsaencryption.util;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;

public class BaseViewModel extends BaseObservable{

    protected Context context;
    protected Activity activity;

    public BaseViewModel(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;


    }

}
