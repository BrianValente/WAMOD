package com.wamod;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by brianvalente on 7/9/15.
 */
public class HomeActivity extends AppCompatActivity {

    public static void changeActiveTabTextColor(TextView tv) {
        try {
            tv.setTextColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")));
        } catch (Exception e) {
            Toast.makeText(tv.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void changeInactiveTabTextColor(TextView tv) {
        try {
            tv.setTextColor(Color.parseColor("#" + "66" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")));
        } catch (Exception e) {
            Toast.makeText(tv.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public static void initHomeActivity(final com.whatsapp.HomeActivity a) {
        if (utils.prefs.getBoolean("crash", false)) {
            utils.edit.putBoolean("crash", false);
            utils.edit.apply();

            AlertDialog.Builder dialog = new AlertDialog.Builder(a);

            dialog.setTitle(a.getResources().getString(Resources.string.wamod_crash_title));
            dialog.setMessage(a.getResources().getString(Resources.string.wamod_crash_message));

            dialog.setPositiveButton(a.getResources().getString(Resources.string.wamod_crash_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

            dialog.show();
        }

        Toolbar toolbar = (Toolbar) a.findViewById(Resources.id.toolbar);
        HorizontalScrollView tabs = (HorizontalScrollView) a.findViewById(Resources.id.tabs);

        //utils.loadColorsV2(a);
        tabs.setBackgroundColor(utils.getUIColor(utils.COLOR_TOOLBAR));

        // Check if dark mode is activated and change the background
        View pager = a.findViewById(Resources.id.pager);
        if (utils.nightModeShouldRun()) {
            pager.setBackgroundColor(utils.getDarkColor(2));
        } else {
            pager.setBackgroundColor(Color.WHITE);
        }


        // Load bottom navbar
        ViewStub wamod_bottomnav_viewstub = (ViewStub) a.findViewById(Resources.id.wamod_bottomnav_viewstub);
        if (wamod_bottomnav_viewstub != null) wamod_bottomnav_viewstub.inflate();

        utils.initWAMODfromHome(a);

        ActionBar actionbar = a.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        Drawable upIndicator = a.getResources().getDrawable(Resources.drawable.wamod_ic_menu);
        upIndicator.setColorFilter(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        actionbar.setHomeAsUpIndicator(upIndicator);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = a.getWindow(); // in Activity's onCreate() for instance
            //w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setStatusBarColor(Color.parseColor("#00000000"));

            /*DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(Resources.id.wamod_drawer_parent);
            drawerLayout.setBackgroundColor(utils.getUIColor(utils.COLOR_STATUSBAR));*/


            int padding = utils.getStatusBarHeight(a);
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) a.findViewById(Resources.id.wamod_drawer_overlay);
            coordinatorLayout.setPadding(0,padding,0,0);
            coordinatorLayout.setBackgroundColor(utils.getUIColor(utils.COLOR_STATUSBAR));
        }
    }

    public static void _onPrepareOptionsMenu(Menu menu) {
        menu.clear();

        // Search
        Drawable searchIcon = utils.context.getResources().getDrawable(Resources.drawable.ic_action_search);
        searchIcon.setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
        menu.add(0, 0, 0, Resources.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        _onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    public static boolean _onOptionsItemSelected(com.whatsapp.HomeActivity a, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                /*NavigationDrawer drawer = (NavigationDrawer) a.findViewById(Resources.id.wamod_drawer_parent);
                drawer.openDrawer2(true);*/
                NavigationView navigationView = (NavigationView) a.findViewById(Resources.id.wamod_drawer);
                DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(Resources.id.wamod_drawer_parent);
                drawerLayout.openDrawer(navigationView);
                return true;
            case 0:
                a.onSearchRequested();
                return true;
        }
        return false;
    }

    public static boolean _onBackPressed(com.whatsapp.HomeActivity a) {
        NavigationView navigationView = (NavigationView) a.findViewById(Resources.id.wamod_drawer);
        DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(Resources.id.wamod_drawer_parent);
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
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
                conversationsRow = 0x7f03006c;
                callsRow = 0x7f030031;
                contactPickerRow = 0x7f030045;
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
                callsRow = 0x7f030031;
                contactPickerRow = 0x7f030044;
                break;
            case 3:
                // Telegram
                conversationsRow = 0x7f031011;
                callsRow = 0x7f030031;
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

    public static void styleFAB(ImageView fab) {
        Drawable bg = fab.getBackground();
        bg.setColorFilter(utils.getUIColor(utils.COLOR_TOOLBAR), PorterDuff.Mode.MULTIPLY);
        fab.setBackground(bg);
        Drawable icon = fab.getDrawable();
        icon.setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
        fab.setImageDrawable(icon);
    }

    public void _onCreate(Context ctx) {

    }
}
