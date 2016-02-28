package com.wamod;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.whatsapp.Broadcasts;
import com.whatsapp.NewGroup;
import com.whatsapp.ProfileInfoActivity;
import com.whatsapp.SetStatus;
import com.whatsapp.StarredMessagesActivity;
import com.whatsapp.WebSessionsActivity;

import java.util.ArrayList;

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

    public static void initHomeActivity(final com.whatsapp.HomeActivity a) {

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

        ActionBar actionbar = a.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        Drawable upIndicator = a.getResources().getDrawable(id.wamod_ic_menu);
        upIndicator.setColorFilter(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        actionbar.setHomeAsUpIndicator(upIndicator);
    }

    public static boolean _onOptionsItemSelected(com.whatsapp.HomeActivity a, MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavigationDrawer drawer = (NavigationDrawer) a.findViewById(id.wamod_drawer_parent);
            drawer.openDrawer2(true);
            return true;
        }
        return false;
    }

    public static boolean _onBackPressed(com.whatsapp.HomeActivity a) {
        NavigationDrawer drawer = (NavigationDrawer) a.findViewById(id.wamod_drawer_parent);
        if (drawer.drawerOpen) {
            drawer.openDrawer2(false);
            return true;
        } else return false;
    }

    private void callOnBackPressed() {
        if (_onBackPressed(null)) return;
    }

    public static void test(MenuItem item) {
        if (_onOptionsItemSelected(null, item)) return;
    }


    public static int getHomeTheme(int id) {
        int homeThemeID = Integer.parseInt(utils.prefs.getString("home_theme", "0"));
        int conversationsRow, callsRow, contactPickerRow;
        switch (homeThemeID) {
            case 0:
                conversationsRow = 0x7f03006a;
                callsRow = 0x7f030032;
                contactPickerRow = 0x7f030044;
                break;
            default:
            case 1:
                conversationsRow = 0x7f031001;
                callsRow = 0x7f031002;
                contactPickerRow = 0x7f031003;
                break;
            case 2:
                // Stock w/ counter in photo
                conversationsRow = 0x7f031010;
                callsRow = 0x7f030032;
                contactPickerRow = 0x7f030044;
                break;
            case 3:
                // Telegram
                conversationsRow = 0x7f031011;
                callsRow = 0x7f030032;
                contactPickerRow = 0x7f030044;
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

    public static int getTabsIndicatorColor() {
        int color = Color.parseColor("#" + utils.prefs.getString("home_tabsindicatorcolor", "ffffff"));
        return color;
    }

    private void callgetcolor() {
        int color = getTabsIndicatorColor();
    }
}
