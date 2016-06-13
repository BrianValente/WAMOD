package com.wamod.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wamod.Resources;

/**
 * Created by brianvalente on 6/12/16.
 */
public class SetupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout("wamod_setup_main_activity"));
    }
}
