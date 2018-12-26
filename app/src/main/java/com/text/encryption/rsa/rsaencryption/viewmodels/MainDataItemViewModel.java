package com.text.encryption.rsa.rsaencryption.viewmodels;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.text.encryption.rsa.rsaencryption.DataBase.MainDataObject;
import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.ui.details.DetailsActivity;

public class MainDataItemViewModel extends BaseObservable{

    private MainDataObject mainDataObject;

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> date = new ObservableField<>("");

    public MainDataItemViewModel(MainDataObject mainDataObject) {

        this.mainDataObject = mainDataObject;
        this.title.set(mainDataObject.getTitle());
        this.date.set(mainDataObject.getDate());

    }

    public void onClickItem(View view) {
        final Activity activity = (Activity)view.getContext();
        Animation animation;
        animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra(DetailsActivity.MAIN_DATA_OBJECT_EXTRA_KEY, mainDataObject.getId());
                activity.startActivity(intent);
            }
        }, 200);



    }


}
