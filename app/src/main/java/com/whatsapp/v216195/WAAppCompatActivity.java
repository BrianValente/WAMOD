package com.whatsapp.v216195;

import android.support.v7.app.AppCompatActivity;
import com.wamod.Utils;

/**
 * Created by BrianValente on 3/25/16.
 */
public class WAAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        Utils.loadColorsV2(this);
    }
}
