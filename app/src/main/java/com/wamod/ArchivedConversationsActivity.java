package com.wamod;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

/**
 * Created by brianvalente on 9/20/15.
 */
public class ArchivedConversationsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        initDarkMode(this);
        utils.loadColors(this);
        utils.setTaskDescription(this);
    }

    public static void initDarkMode(Activity activity) {
        if (utils.darkMode()) {
            FrameLayout container = (FrameLayout) activity.findViewById(R.id.action0);
            container.setBackgroundColor(utils.getDarkColor(2));
        }
    }
}
