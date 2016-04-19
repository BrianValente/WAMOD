package com.wamod;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by BrianValente on 2/28/16.
 */
public class EULA extends AppCompatActivity {
    public static void _onCreate(final AppCompatActivity a) {
        Button switchAccountBtn = (Button) a.findViewById(Resources.id.wamod_switchaccount);
        switchAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.switchAccount(a);
            }
        });
    }

    public static void callOnCreate() {
        _onCreate(null);
    }
}
