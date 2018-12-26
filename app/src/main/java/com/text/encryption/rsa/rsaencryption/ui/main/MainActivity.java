package com.text.encryption.rsa.rsaencryption.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.text.encryption.rsa.rsaencryption.DataBase.MainDataBase;
import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.databinding.ActivityMainBinding;
import com.text.encryption.rsa.rsaencryption.ui.BaseActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    /**
     * class {@link MainActivity} với class {@link MainViewModel} cùng quản lý màn hình main với file layout là activity_main.xml
     *
     *
     */

    ActivityMainBinding activityMainBinding;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this, this, activityMainBinding);
        activityMainBinding.setViewModel(mainViewModel);


        /**
         * Kiểm tra xem đã cấp quền đọc bộ nhớ chưa
         * Nếu chưa sẽ xin quyền đọc bộ nhớ
         *
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainViewModel.refreshRecycleList();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
