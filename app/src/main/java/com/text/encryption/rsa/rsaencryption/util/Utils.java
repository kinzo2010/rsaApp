package com.text.encryption.rsa.rsaencryption.util;


import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String getCurrentTime() {

        String pattern = "HH:mm dd/MM/yyy";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date date = Calendar.getInstance().getTime();

        Log.d("TAG", "current time --> "+simpleDateFormat.format(date));

        return simpleDateFormat.format(date);

    }

}
