package com.wamod.WAclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.wamod.R;
import com.wamod.Utils;

/**
 * Created by brianvalente on 9/20/15.
 */
public class ArchivedConversationsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        initDarkMode(this);
        Utils.loadColors(this);
        Utils.setTaskDescription(this);
    }

    public static void initDarkMode(Activity activity) {
        if (Utils.nightModeShouldRun()) {
            FrameLayout container = (FrameLayout) activity.findViewById(R.id.action0);
            container.setBackgroundColor(Utils.getDarkColor(2));
        }
    }
}
