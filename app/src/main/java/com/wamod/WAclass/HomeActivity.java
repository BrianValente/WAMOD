package com.wamod.WAclass;

import android.content.Context;
import android.content.DialogInterface;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by brianvalente on 7/9/15.
 */
public class HomeActivity extends AppCompatActivity {

    public static void initHomeActivity(final com.whatsapp.HomeActivity a) {
        if (Utils.prefs.getBoolean("crash", false)) {
            Utils.edit.putBoolean("crash", false);
            Utils.edit.apply();

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

        tabs.setBackgroundColor(Utils.getUIColor(Utils.COLOR_TOOLBAR));

        // Check if dark mode is activated and change the background
        View pager = a.findViewById(Resources.id.pager);
        if (Utils.nightModeShouldRun()) {
            pager.setBackgroundColor(Utils.getDarkColor(2));
        } else {
            pager.setBackgroundColor(Color.WHITE);
        }


        // Load bottom navbar
        ViewStub wamod_bottomnav_viewstub = (ViewStub) a.findViewById(Resources.id.wamod_bottomnav_viewstub);
        if (wamod_bottomnav_viewstub != null) wamod_bottomnav_viewstub.inflate();

        Utils.initWAMODfromHome(a);

        ActionBar actionbar = a.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        Drawable upIndicator = a.getResources().getDrawable(Resources.drawable.wamod_ic_menu);
        upIndicator.setColorFilter(Color.parseColor("#" + Utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        actionbar.setHomeAsUpIndicator(upIndicator);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = a.getWindow(); // in Activity's onCreate() for instance
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setStatusBarColor(Color.parseColor("#00000000"));

            int padding = Utils.getStatusBarHeight(a);
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) a.findViewById(Resources.id.wamod_drawer_overlay);
            coordinatorLayout.setPadding(0,padding,0,0);
            coordinatorLayout.setBackgroundColor(Utils.getUIColor(Utils.COLOR_STATUSBAR));
        }
    }

    /* Called on
     *    com.whatsapp.HomeActivity.onPrepareOptionsMenu(Landroid/view/Menu;)Z
     * Where
     *    Replace the entire method
     * Smali
     *    .locals 1
     *    .prologue
     *    invoke-static {p1}, Lcom/wamod/WAclass/HomeActivity;->_onPrepareOptionsMenu(Landroid/view/Menu;)V
     *    invoke-super {p0, p1}, Lcom/whatsapp/DialogToastActivity;->onPrepareOptionsMenu(Landroid/view/Menu;)Z
     *    move-result v0
     *    return v0
     */
    public static void _onPrepareOptionsMenu(Menu menu) {
        menu.clear();

        // Search
        Drawable searchIcon = Utils.context.getResources().getDrawable(Resources.drawable.ic_action_search);
        searchIcon.setColorFilter(Utils.getUIColor(Utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
        menu.add(0, 0, 0, Resources.string.search).setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }


    /* Called on
     *    com.whatsapp.HomeActivity.onOptionsItemSelected(Landroid/view/MenuItem;)Z
     * Where
     *    After prologue
     * Smali
     *    invoke-static {p0, p1}, Lcom/wamod/WAclass/HomeActivity;->_onOptionsItemSelected(Lcom/whatsapp/HomeActivity;Landroid/view/MenuItem;)Z
     *    move-result v0
     *    if-nez v0, :cond_4
     *
     *    ...
     *
     *    :cond_4
     *    const/4 v0, 0x1
     *    :goto_0
     *    return v0
     */
    public static boolean _onOptionsItemSelected(com.whatsapp.HomeActivity a, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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


    /* Called on
     *    com.whatsapp.HomeActivity.onBackPressed()V
     * Where
     *    After prologue
     * Smali
     *    invoke-static {p0}, Lcom/wamod/WAclass/HomeActivity;->_onBackPressed(Lcom/whatsapp/HomeActivity;)Z
     *    move-result v0
     *    if-nez v0, :cond_2
     *
     *    ...
     *
     *    :cond_2
     *    return-void
     */
    public static boolean _onBackPressed(com.whatsapp.HomeActivity a) {
        NavigationView navigationView = (NavigationView) a.findViewById(Resources.id.wamod_drawer);
        DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(Resources.id.wamod_drawer_parent);
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
            return true;
        } else return false;
    }


    /* Called on
     *    com.whatsapp.v.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Where
     *    Where 0x7f030033 is located
     * Smali
     *    const/4 v3, 0x1
     *    invoke-static {v3}, Lcom/wamod/WAclass/HomeActivity;->getHomeTheme(I)I
     *    move-result v3
     */

    /* Called on
     *    com.whatsapp.a91.<init>(Lcom/whatsapp/ContactPicker;Landroid/content/Context;Ljava/util/ArrayList;)V
     *    com.whatsapp.pe.<init>(Lcom/whatsapp/ContactsFragment;Landroid/content/Context;Ljava/util/ArrayList;)V
     * Where
     *    ??
     * Smali
     *    const/4 v0, 0x2
     *    invoke-static {v0}, Lcom/wamod/WAclass/HomeActivity;->getHomeTheme(I)I
     *    move-result v0
     */

    /* Called on
     *    com.whatsapp.wu.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Where
     *    Where 0x7f030070 is located
     * Smali
     *    Replace with:
     *    const/4 v4, 0x0
     *    invoke-static {v4}, Lcom/wamod/WAclass/HomeActivity;->getHomeTheme(I)I
     *    move-result v4
     */
    public static int getHomeTheme(int id) {
        int homeThemeID = Integer.parseInt(Utils.prefs.getString("home_theme", "0"));
        int conversationsRow, callsRow, contactPickerRow;
        switch (homeThemeID) {
            case 0:
                // Stock
                conversationsRow = Resources.getLayout("conversations_row");
                callsRow = Resources.getLayout("calls_row");
                contactPickerRow = Resources.getLayout("contact_picker_row");
                break;
            default:
            case 1:
                // WAMOD
                conversationsRow = Resources.getLayout("wamod_theme_wamod_conversations_row");
                callsRow = Resources.getLayout("wamod_theme_wamod_calls_row");
                contactPickerRow = Resources.getLayout("wamod_theme_wamod_contact_picker_row");
                break;
            case 2:
                // Stock w/ counter in photo
                conversationsRow = Resources.getLayout("wamod_theme_stockwcounterphoto_conversations_row");
                callsRow = Resources.getLayout("calls_row");
                contactPickerRow = Resources.getLayout("contact_picker_row");
                break;
            case 3:
                // Telegram
                conversationsRow = Resources.getLayout("wamod_theme_telegram_conversations_row");
                callsRow = Resources.getLayout("calls_row");
                contactPickerRow = Resources.getLayout("contact_picker_row");
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


    public static void styleFAB(ImageView fab) {
        Drawable bg = fab.getBackground();
        bg.setColorFilter(Utils.getUIColor(Utils.COLOR_TOOLBAR), PorterDuff.Mode.MULTIPLY);
        fab.setBackground(bg);
        Drawable icon = fab.getDrawable();
        icon.setColorFilter(Utils.getUIColor(Utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
        fab.setImageDrawable(icon);
    }
}
