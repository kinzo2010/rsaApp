package com.text.encryption.rsa.rsaencryption.ui.add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.text.encryption.rsa.rsaencryption.databinding.ActivityAddContentBinding;
import com.text.encryption.rsa.rsaencryption.rsa.RSAAlgorithm;
import com.text.encryption.rsa.rsaencryption.util.AddContentState;
import com.text.encryption.rsa.rsaencryption.util.BaseViewModel;
import com.text.encryption.rsa.rsaencryption.util.EncryptionLevel;
import com.text.encryption.rsa.rsaencryption.util.Utils;

public class AddContentViewModel extends BaseViewModel {

    public static int OPEN_FILE_REQUEST_CODE = 100;

    private ActivityAddContentBinding activityAddContentBinding;
    AddContentState addContentState = AddContentState.NONE;
    private EncryptionLevel encryptionLevel = EncryptionLevel.LEVEL_I;

    public ObservableField<String> textContent = new ObservableField<>("");
    public ObservableField<String> title = new ObservableField<>("");

    AddContentViewModel(Context context, Activity activity, ActivityAddContentBinding activityAddContentBinding) {
        super(context, activity);
        this.activityAddContentBinding = activityAddContentBinding;
    }


    /**
    *
    * xử lý sự kiện khi nhấn mũi tên quay lại
    *
    *
    */
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

    /*
    *
    * khi nhấn nút mã hóahóa
    * kiểm tra xem đã có tiêu đề và nộ dung chưa. nếu đã có sẽ cho người dùng chọn mức dộ mã hóa
 *
    * */
    public void onClickSave(View view) {

        if (title.get().length() > 0 && textContent.get().length() > 0) {
            if (addContentState == AddContentState.NONE) {
                addContentState = AddContentState.CHOOSE_LEVEL;
                activityAddContentBinding.encryptionChooseLevelLayout.setAlpha(1);

                Animation animation;
                animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
                animation.setFillEnabled(false);
                animation.setFillAfter(false);
                view.startAnimation(animation);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activityAddContentBinding.encryptionChooseLevelLayout.setAlpha(1);
                        activityAddContentBinding.encryptionChooseLevelLayout.setVisibility(View.VISIBLE);
                        activityAddContentBinding.levelButtonContainer.setVisibility(View.VISIBLE);
                        activityAddContentBinding.inputLayout.setVisibility(View.INVISIBLE);


                    }
                }, 200);
            }
        } else {

            Toast.makeText(context, "Hãy nhập tiêu đề và nội dung", Toast.LENGTH_LONG).show();

        }

    }

    /*
    *
    * nhấn mở file
    *
    *
    * */
    public void onClickOpenTextFile(View view) {

        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                //intent.setType("txt*//*");
                intent.setType("text/plain");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                activity.startActivityForResult(intent, OPEN_FILE_REQUEST_CODE);
            }
        }, 200);



    }

    public void onClickBlackView(View view) {

        if (addContentState != AddContentState.ENCRYPTING) {
            addContentState = AddContentState.NONE;
            activityAddContentBinding.inputLayout.setVisibility(View.VISIBLE);
            final Animation animation;
            animation = AnimationUtils.loadAnimation(context, R.anim.to_alpha_0);
            animation.setFillEnabled(false);
            animation.setFillAfter(false);
            activityAddContentBinding.encryptionChooseLevelLayout.startAnimation(animation);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    activityAddContentBinding.encryptionChooseLevelLayout.setVisibility(View.INVISIBLE);
                }
            }, 125);
        }


    }

    /*
    *
    * 3 hàm bên dưới ứng với 3 cấp độ mà người dùng chọn
    * cấp 1: private key có kích thước là 512 bits ~ 156 kí tự
    * cấp 2: private key có kích thước là 1024 bits ~ 300 kí tự
    * cấp 3: private key có kích thước là 2048 bits ~ 900 kí tự
    *
    * */
    public void onClickLevel_1(View view) {
        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);
        this.encryptionLevel = EncryptionLevel.LEVEL_I;
        encrypt();
    }

    public void onClickLevel_2(View view) {

        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);
        this.encryptionLevel = EncryptionLevel.LEVEL_II;
        encrypt();
    }

    public void onClickLevel_3(View view) {
        Animation animation;
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        animation.setFillEnabled(false);
        animation.setFillAfter(false);
        view.startAnimation(animation);
        this.encryptionLevel = EncryptionLevel.LEVEL_III;
        encrypt();
    }

    private void encrypt() {
        addContentState = AddContentState.ENCRYPTING;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                activityAddContentBinding.levelButtonContainer.setVisibility(View.GONE);
                activityAddContentBinding.progressLayout.setVisibility(View.VISIBLE);

                EncryptThread encryptThread = new EncryptThread(encryptionLevel);
                encryptThread.start();

            }
        }, 125);


    }

    class EncryptThread extends Thread {

        EncryptionLevel encryptionLevel;

        EncryptThread(EncryptionLevel encryptionLevel) {
            this.encryptionLevel = encryptionLevel;

        }

        @Override
        public void run() {
            RSAAlgorithm rsaAlgorithm = new RSAAlgorithm(encryptionLevel);
            String encryptResult = rsaAlgorithm.encrypt(textContent.get());

            MainDataObject mainDataObject = new MainDataObject();
            mainDataObject.setTitle(title.get());
            mainDataObject.setContent(encryptResult);
            mainDataObject.setPrivateKey(rsaAlgorithm.getPrivateKey().toString());
            mainDataObject.setPublicKey(rsaAlgorithm.getPublicKey().toString());
            mainDataObject.setD(rsaAlgorithm.getD().toString());
            mainDataObject.setN(rsaAlgorithm.getN().toString());
            mainDataObject.setE(rsaAlgorithm.getE().toString());
            mainDataObject.setDate(Utils.getCurrentTime());
            MainDataBase.getInstance(context).getMainDataBaseDAO().addData(mainDataObject);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Mã hóa và lưu thành công", Toast.LENGTH_LONG).show();
                    activity.finish();
                }
            });

        }

    }

}
