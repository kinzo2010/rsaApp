package com.text.encryption.rsa.rsaencryption.ui.details;


import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.text.encryption.rsa.rsaencryption.DataBase.MainDataBase;
import com.text.encryption.rsa.rsaencryption.DataBase.MainDataObject;
import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.databinding.ActivityDetailsBinding;
import com.text.encryption.rsa.rsaencryption.rsa.RSAAlgorithm;
import com.text.encryption.rsa.rsaencryption.util.BaseViewModel;

public class DetailsViewModel extends BaseViewModel{

    private MainDataObject mainDataObject;
    private ActivityDetailsBinding activityDetailsBinding;
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> textContent = new ObservableField<>("");

    public DetailsViewModel(Context context, Activity activity, MainDataObject mainDataObject, ActivityDetailsBinding activityDetailsBinding) {
        super(context, activity);
        this.mainDataObject = mainDataObject;
        this.title.set(mainDataObject.getTitle());
        this.textContent.set(mainDataObject.getContent());
        this.activityDetailsBinding = activityDetailsBinding;
    }


    public void onClickBackToHomePage(View view) {

        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                activity.finish();
            }
        }, 200);

    }

    public void onClickDecrypt(View view) {

        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                activityDetailsBinding.blackView.setVisibility(View.VISIBLE);
                DecryptThread decryptThread = new DecryptThread();
                decryptThread.start();
            }
        }, 200);


    }

    public void onClickDelete(View view) {

        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MainDataBase.getInstance(context).getMainDataBaseDAO().deleteObject(mainDataObject.getId());
                activity.finish();
            }
        }, 200);


    }

    public void onClickBlackView(View view) {


    }

    class DecryptThread extends Thread {

        DecryptThread() {

        }

        @Override
        public void run() {
            RSAAlgorithm rsaAlgorithm = new RSAAlgorithm(mainDataObject.getD(), mainDataObject.getN());
            final String decryptResult = rsaAlgorithm.decrypt(mainDataObject.getContent());

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Giải mã thành công", Toast.LENGTH_LONG).show();
                    textContent.set(decryptResult);
                    activityDetailsBinding.blackView.setVisibility(View.GONE);


                }
            });

        }

    }
}
