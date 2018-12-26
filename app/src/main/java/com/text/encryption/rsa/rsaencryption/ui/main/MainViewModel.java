package com.text.encryption.rsa.rsaencryption.ui.main;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.text.encryption.rsa.rsaencryption.DataBase.MainDataBase;
import com.text.encryption.rsa.rsaencryption.DataBase.MainDataObject;
import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.adapter.MainDataListAdapter;
import com.text.encryption.rsa.rsaencryption.databinding.ActivityMainBinding;
import com.text.encryption.rsa.rsaencryption.ui.add.AddContentActivity;
import com.text.encryption.rsa.rsaencryption.util.BaseViewModel;

import java.util.List;


public class MainViewModel extends BaseViewModel{

    /**
     * {@link MainDataListAdapter} để quản lý, lưu dữ liệu cho  ở màn hình main
     *
     */
    private MainDataListAdapter mainDataListAdapter = new MainDataListAdapter();

    private ActivityMainBinding activityMainBinding;

    public MainViewModel(Context context, Activity activity, ActivityMainBinding activityMainBinding) {
        super(context, activity);
        this.activityMainBinding = activityMainBinding;

        /**
         * set adapter cho recycleView
         */
        activityMainBinding.mainDataList.setAdapter(mainDataListAdapter);
        activityMainBinding.mainDataList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


    }

    /**
     * Cập nhật dữ liệu cho recycleView ở màn hình main
     */
    public void refreshRecycleList() {

        List<MainDataObject> mainDataObjectList = MainDataBase.getInstance(context).getMainDataBaseDAO().getAllDataObject();
        if(mainDataObjectList.size() > 0) {
            activityMainBinding.emptyContent.setVisibility(View.GONE);
            activityMainBinding.mainDataList.setVisibility(View.VISIBLE);

            this.mainDataListAdapter.setItemList(mainDataObjectList);

        } else {
            activityMainBinding.emptyContent.setVisibility(View.VISIBLE);
            activityMainBinding.mainDataList.setVisibility(View.GONE);
        }

    }

    /**
     *
     * xử lý sự kiện nhấn vào button (+)
     * khi nhấn vào sẽ chuyển sang màn hình khác để thêm dữ liệu
     * màn hình chuyển tới là {@link AddContentActivity}
     */
    public void onClickAddNew(View view) {

        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, AddContentActivity.class);
                activity.startActivity(intent);
            }
        }, 200);

    }

    public MainDataListAdapter getMainDataListAdapter() {
        return mainDataListAdapter;
    }

    public void setMainDataListAdapter(MainDataListAdapter mainDataListAdapter) {
        this.mainDataListAdapter = mainDataListAdapter;
    }
}
