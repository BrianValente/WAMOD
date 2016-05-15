package com.wamod.settings;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.wamod.Utils;

/**
 * Created by BrianValente on 3/27/16.
 */
public class Activity extends AppCompatActivity {
    /*@Override
    public void setTheme(@StyleRes int resid) {
        if (utils.nightModeShouldRun()) super.setTheme(Resources.style.WAMOD_Theme_Settings);
        else super.setTheme(Resources.style.WAMOD_Theme_Settings_Day);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        ListView list = (ListView) findViewById(android.R.id.list);
        if (list != null && Utils.nightModeShouldRun()) {
            list.setBackgroundColor(Utils.getDarkColor(2));
        }
    }
}
