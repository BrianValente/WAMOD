package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.wamod.App;
import com.wamod.Resources;

/**
 * Created by BrianValente on 2/28/16.
 */
public class EULA extends AppCompatActivity {
    public static void _onCreate(final AppCompatActivity a) {
        Button switchAccountBtn = (Button) a.findViewById(Resources.id.wamod_switchaccount);
        switchAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getAccountsManager().showAccountsList(a);
            }
        });
    }
}
