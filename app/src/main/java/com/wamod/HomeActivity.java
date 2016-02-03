package com.wamod;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

/**
 * Created by brianvalente on 7/9/15.
 */
public class HomeActivity extends AppCompatActivity {

    public static void changeActiveTabTextColor(TextView tv) {
        try {
            tv.setTextColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")));
        } catch (RuntimeException e) {
            utils.crashWAMOD();
            throw new RuntimeException(e);
        }
    }

    public static void changeInactiveTabTextColor(TextView tv) {
        try {
            tv.setTextColor(Color.parseColor("#" + "66" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")));
        } catch (RuntimeException e) {
            utils.crashWAMOD();
            throw new RuntimeException(e);
        }
    }

    public static void initHomeActivity(AppCompatActivity a) {

        if (utils.prefs.getBoolean("crash", false)) {
            utils.edit.putBoolean("crash", false);
            utils.edit.apply();

            AlertDialog dialog = new AlertDialog.Builder(a).create();

            dialog.setTitle(a.getResources().getString(id.wamod_crash_title));
            dialog.setMessage(a.getResources().getString(id.wamod_crash_message));

            dialog.setButton(AlertDialog.BUTTON_POSITIVE, a.getResources().getString(id.wamod_crash_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            dialog.show();
        }

        Toolbar toolbar = (Toolbar) a.findViewById(id.toolbar);
        HorizontalScrollView tabs = (HorizontalScrollView) a.findViewById(id.tabs);

        try {
            utils.loadColors(toolbar, a.getWindow());
            tabs.setBackgroundColor(utils.getUIColor(utils.COLOR_TOOLBAR));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String title = a.getString(id.appname);
                int color = Color.parseColor("#075e54");
                if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
                ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(a.getResources(), id.appicon), color);
                a.setTaskDescription(taskDesc);
            }

            // Check if dark mode is activated and change the background
            if (utils.darkMode()) {
                View pager = a.findViewById(id.pager);
                pager.setBackgroundColor(utils.getDarkColor(2));
            }
        } catch (RuntimeException e) {
            utils.crashWAMOD(a);
        }

        utils.initWAMODfromHome(a);
    }

    public void call() {
        initHomeActivity(this);
    }




    public static int getHomeTheme(int id) {
        int homeThemeID = Integer.parseInt(utils.prefs.getString("home_theme", "0"));
        int conversationsRow, callsRow, contactPickerRow;
        switch (homeThemeID) {
            case 0:
                conversationsRow = 0x7f030068;
                callsRow = 0x7f030031;
                contactPickerRow = 0x7f030043;
                break;
            default:
            case 1:
                conversationsRow = 0x7f0300ec;
                callsRow = 0x7f0300ed;
                contactPickerRow = 0x7f0300ee;
                break;
            case 2:
                // Stock w/ counter in photo
                conversationsRow = 0x7f0300f7;
                callsRow = 0x7f030031;
                contactPickerRow = 0x7f030043;
                break;
            case 3:
                // Telegram
                conversationsRow = 0x7f0300f8;
                callsRow = 0x7f030031;
                contactPickerRow = 0x7f030043;
                break;
        }
        switch (id) {
            case 0:
                return conversationsRow;
            case 1:
                return callsRow;
            case 2:
                return contactPickerRow;
        }
        return conversationsRow;
    }

    private void call_getHomeTheme() {
        int hex = getHomeTheme(0);
    }


}
