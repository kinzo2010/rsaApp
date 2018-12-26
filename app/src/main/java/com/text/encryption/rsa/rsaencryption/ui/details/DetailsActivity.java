package com.text.encryption.rsa.rsaencryption.ui.details;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import com.text.encryption.rsa.rsaencryption.DataBase.MainDataBase;
import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding activityDetailsBinding;
    DetailsViewModel detailsViewModel;

    public static String MAIN_DATA_OBJECT_EXTRA_KEY = "main_data_object_extra_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Intent intent = getIntent();
        int objectId = intent.getIntExtra(MAIN_DATA_OBJECT_EXTRA_KEY, -1);
        Log.d(getClass().getSimpleName(), "object id --> "+objectId);
        if(objectId >= 0) {

            detailsViewModel = new DetailsViewModel(this, this, MainDataBase.getInstance(this).getMainDataBaseDAO().getDataById(objectId), activityDetailsBinding);
            activityDetailsBinding.setViewModel(detailsViewModel);

        } else {

            finish();
        }

        activityDetailsBinding.contentText.setMovementMethod(new ScrollingMovementMethod());
    }
}
