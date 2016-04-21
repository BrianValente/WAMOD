package com.wamod;

import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by BrianValente on 3/27/16.
 */
public class WAMODSettings extends AppCompatActivity {
    /*@Override
    public void setTheme(@StyleRes int resid) {
        if (utils.nightModeShouldRun()) super.setTheme(Resources.style.WAMOD_Theme_Settings);
        else super.setTheme(Resources.style.WAMOD_Theme_Settings_Day);
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        ListView list = (ListView) findViewById(android.R.id.list);
        if (list != null && utils.nightModeShouldRun()) {
            list.setBackgroundColor(utils.getDarkColor(2));
        }
    }
}
