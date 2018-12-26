package com.text.encryption.rsa.rsaencryption.ui.add;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.databinding.ActivityAddContentBinding;
import com.text.encryption.rsa.rsaencryption.ui.BaseActivity;
import com.text.encryption.rsa.rsaencryption.ui.main.MainViewModel;
import com.text.encryption.rsa.rsaencryption.util.AddContentState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AddContentActivity extends BaseActivity {


    /**
     * Tương tự như màn hình main hai cái bên dưới này để quản lý màn hình có file layout là activity_add_content.xml
     */
    ActivityAddContentBinding activityAddContentBinding;
    AddContentViewModel addContentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddContentBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_content);
        addContentViewModel = new AddContentViewModel(this, this, activityAddContentBinding);
        activityAddContentBinding.setViewModel(addContentViewModel);
    }


    /**
     * Xử lý sự kiện khi nhấn phím back
     *
     */
    @Override
    public void onBackPressed() {

        if(addContentViewModel.addContentState == AddContentState.NONE) {
            finish();
        }

    }


    /**
     *
     *Xử lý sự kiện khi chọn xong file, lấy đường dẫ file và gọi đến hàm readFile để đọc file
     */
    public void onActivityResult(int requestCode, int resultCode, Intent result)
    {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == AddContentViewModel.OPEN_FILE_REQUEST_CODE)
            {
                if(result != null) {
                    Uri uri = result.getData();
                    assert uri != null;
                    String path = uri.getPath();
                    path = path.substring(path.indexOf(":")+1);
                    String path2 = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+path;
                    readFile(path2);

                }


            }
        }
    }

    /**
     *
     *
     * Đọc file và hiển thị lên màn hình
     */
    private void readFile(String path) {

        File file = new File(path);
        StringBuilder text = new StringBuilder();

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
            bufferedReader.close();
            addContentViewModel.textContent.set(text.toString());
            Log.d("TAG", "content --> "+text);

        } catch (Exception e) {

        }

    }
}
