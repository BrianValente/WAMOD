package com.whatsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.whatsapp.data.ContactInfo;

/**
 * Created by brianvalente on 11/24/17.
 */

public class Activity extends AppCompatActivity {

    public MeManager mMeManager = new MeManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wamod.com.whatsapp.Activity.onCreate_Before(this, savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        wamod.com.whatsapp.Activity._onResume(this);
    }
}
