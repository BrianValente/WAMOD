package com.wamod.settings;

import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.wamod.ColorsManager;
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

        /*Drawable divider = list.getDivider();
        divider.setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND), PorterDuff.Mode.MULTIPLY);
        list.setDivider(divider);*/

        list.setDivider(null);

        list.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
    }
}
