package com.whatsapp;

import android.support.v7.app.AppCompatActivity;

import com.wamod.utils;

/**
 * Created by BrianValente on 3/25/16.
 */
public class WAAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        utils.loadColorsV2(this);
    }
}
