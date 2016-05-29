package com.wamod;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.UiModeManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.wamod.settings.Activity;
import com.wamod.themes.CheckIn;
import com.whatsapp.*;

import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

import io.fabric.sdk.android.Fabric;

/**
 * Created by brianvalente on 7/8/15.
 */
public class Utils extends android.app.Activity {
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor edit;
    public static String wamodVersionName = "1.3.3 Release Candidate 2";
    public static int wamodVersionCode = 34;
    public static Context context;
    public static boolean debug = false;

    public static long timeSinceLastCheckin = 0;

    public static final int COLOR_STATUSBAR = 0;
    public static final int COLOR_TOOLBAR = 1;
    public static final int COLOR_NAVBAR = 2;
    public static final int COLOR_FOREGROUND = 3;
    public static final int COLOR_TOOLBARTEXT = 4;



    public static List<Chat> openedChats = new ArrayList<Chat>();


    public static void loadColors(android.support.v7.app.ActionBar actionBar, Window window) {
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + Utils.prefs.getString("general_toolbarcolor", "ffffff"))));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.setStatusBarColor(Color.parseColor("#" + Utils.prefs.getString("general_statusbarcolor", "ffffff")));
            window.setNavigationBarColor(Color.parseColor("#" + Utils.prefs.getString("general_navbarcolor", "ffffff")));
        }
    }

    public static void loadColors(Toolbar toolbar, Window window) {
        toolbar.setTitleTextColor(Color.parseColor("#" + Utils.prefs.getString("general_toolbarforeground", "ffffff")));
        toolbar.setBackgroundColor(Color.parseColor("#" + Utils.prefs.getString("general_toolbarcolor", "ffffff")));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.setStatusBarColor(Color.parseColor("#" + Utils.prefs.getString("general_statusbarcolor", "ffffff")));
            window.setNavigationBarColor(Color.parseColor("#" + Utils.prefs.getString("general_navbarcolor", "ffffff")));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Utils.prefs.getBoolean("general_darkstatusbaricons", false)) {
            toolbar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static void loadColors(AppCompatActivity activity) {
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + Utils.prefs.getString("general_toolbarcolor", "ffffff"))));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().setStatusBarColor(Color.parseColor("#" + Utils.prefs.getString("general_statusbarcolor", "ffffff")));
            activity.getWindow().setNavigationBarColor(Color.parseColor("#" + Utils.prefs.getString("general_navbarcolor", "ffffff")));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Utils.prefs.getBoolean("general_darkstatusbaricons", false)) {
            activity.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    public static void loadColorsV2(final AppCompatActivity a) {
        try {
            //if (a instanceof MediaView) return;
            ActionBar actionbar = a.getSupportActionBar();
            boolean coloredToolbarColor = !(a instanceof ChatInfoActivity) &&
                                          !(a instanceof MediaView) &&
                                          !(a instanceof ViewProfilePhoto) &&
                                          !(a instanceof QuickContactActivity);
            if (actionbar != null && coloredToolbarColor) {
                actionbar.setBackgroundDrawable(new ColorDrawable(getUIColor(COLOR_TOOLBAR)));
                int actionbarid = a.getResources().getIdentifier("action_bar", "id", a.getPackageName());
                final ViewGroup actionbarVG = (ViewGroup) a.findViewById(actionbarid);
                if (actionbarVG != null) {
                    actionbarVG.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            for (int i = 0; i < actionbarVG.getChildCount(); i++) {
                                View child = actionbarVG.getChildAt(i);
                                if (child instanceof TextView)
                                    ((TextView) child).setTextColor(getUIColor(COLOR_TOOLBARTEXT));
                                if (child instanceof ImageButton || child instanceof ImageView)
                                    ((ImageView) child).setColorFilter(getUIColor(COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
                            }
                            actionbarVG.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }

            }

            final Toolbar toolbar = (Toolbar) a.findViewById(Resources.id.toolbar);
            if (toolbar != null && coloredToolbarColor) {
                toolbar.setBackgroundColor(getUIColor(COLOR_TOOLBAR));
                toolbar.setTitleTextColor(getUIColor(COLOR_TOOLBARTEXT));
                toolbar.setOverflowIcon(tintToColor(toolbar.getOverflowIcon(), getUIColor(COLOR_FOREGROUND)));

                toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        View up2 = toolbar.getChildAt(1);
                        if (up2 != null && up2 instanceof ImageButton) ((ImageButton) up2).setImageDrawable(tintToColor(((ImageButton) up2).getDrawable(), getUIColor(COLOR_FOREGROUND)));
                        toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                /*toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Log.i("WAMOD_COLORING", "hello");
                        for (int i=0; i<toolbar.getChildCount(); i++) {
                            View linearLayoutCompat = toolbar.getChildAt(i);
                            if (linearLayoutCompat != null && linearLayoutCompat instanceof LinearLayoutCompat) {
                                Log.i("WAMOD_COLORING", "layout found");
                                for (int i2 = 0; i2 < ((LinearLayoutCompat) linearLayoutCompat).getChildCount(); i2++) {
                                    View more = ((ViewGroup) linearLayoutCompat).getChildAt(i2);
                                    if (more != null && more instanceof ImageView) {
                                        Log.i("WAMOD_COLORING", "imageview found");
                                        ImageView moreIV = ((ImageView) more);
                                        moreIV.setImageDrawable(context.getResources().getDrawable(Resources.getDrawable("wamod_brian_")));
                                        moreIV.setColorFilter(getUIColor(COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
                                    }
                                }
                            }
                        }
                        toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });*/

                if (a instanceof com.whatsapp.Conversation) {
                    ImageView up = (ImageView) toolbar.findViewById(Resources.id.up);
                    TextView conversation_contact_name = (TextView) toolbar.findViewById(Resources.id.conversation_contact_name);
                    TextView conversation_contact_status = (TextView) toolbar.findViewById(Resources.id.conversation_contact_status);
                    up.setColorFilter(getUIColor(COLOR_FOREGROUND));
                    conversation_contact_name.setTextColor(getUIColor(Utils.COLOR_TOOLBARTEXT));
                    conversation_contact_status.setTextColor(getUIColor(Utils.COLOR_TOOLBARTEXT));
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (coloredToolbarColor) a.getWindow().setStatusBarColor(getUIColor(COLOR_STATUSBAR));
                a.getWindow().setNavigationBarColor(getUIColor(COLOR_NAVBAR));
                if (Utils.prefs.getBoolean("general_darkstatusbaricons", false))
                    a.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                else
                    a.findViewById(android.R.id.content).setSystemUiVisibility(0);
                if (a instanceof QuickContactActivity) a.getWindow().setStatusBarColor(Color.TRANSPARENT);

                if (Utils.prefs.getBoolean("overview_cardcolor", true) && !(a instanceof com.whatsapp.Conversation)) {
                    a.setTaskDescription(new ActivityManager.TaskDescription(a.getResources().getString(Resources.string.app_name), BitmapFactory.decodeResource(a.getResources(), Resources.drawable.icon), getUIColor(COLOR_TOOLBAR)));
                }
            }
        } catch (Exception e) {
            manageException(e);
        }

        try {
            if (a instanceof com.whatsapp.HomeActivity) {
                com.wamod.WAclass.HomeActivity.initHomeActivity((com.whatsapp.HomeActivity) a);
            } else if (a instanceof com.whatsapp.Conversation) {
                com.wamod.WAclass.Conversation.initConversation((com.whatsapp.Conversation) a);
            } else if (a instanceof com.whatsapp.Settings) {
                com.wamod.WAclass.Settings._onCreate(a);
            } else if (a instanceof com.whatsapp.ProfileInfoActivity) {
                com.wamod.WAclass.ProfileInfoActivity._onCreate(a);
            } else if (a instanceof com.whatsapp.NewGroup) {
                com.wamod.WAclass.NewGroup._onCreate(a);
            } else if (a instanceof com.whatsapp.StarredMessagesActivity) {
                com.wamod.WAclass.StarredMessagesActivity._onCreate(a);
            } else if (a instanceof com.whatsapp.SetStatus) {
                com.wamod.WAclass.SetStatus._onCreate(a);
            } else if (a instanceof com.whatsapp.WebSessionsActivity) {
                com.wamod.WAclass.WebSessionsActivity._onCreate(a);
            } else if (a instanceof com.whatsapp.ContactInfo) {
                com.wamod.WAclass.ContactInfo._onCreate(a);
            } else if (a instanceof com.whatsapp.GroupChatInfo) {
                com.wamod.WAclass.GroupChatInfo._onCreate(a);
            } else if (a instanceof com.whatsapp.ContactPicker) {
                com.wamod.WAclass.ContactPicker._onCreate(a);
            } else if (a instanceof com.whatsapp.EULA) {
                com.wamod.WAclass.EULA._onCreate(a);
            } else if (a instanceof com.whatsapp.ArchivedConversationsActivity) {
                com.wamod.WAclass.ArchivedConversationsActivity._onCreate(a);
            }
        } catch (Exception e) {
            manageException(e);
        }

        
    }

    public static void loadColorsV2(PreferenceActivity a) {
        try {
            android.app.ActionBar actionbar = a.getActionBar();
            if (actionbar != null) {
                actionbar.setBackgroundDrawable(new ColorDrawable(getUIColor(COLOR_TOOLBAR)));
                int actionbarid = a.getResources().getIdentifier("action_bar", "id", a.getPackageName());
                ViewGroup actionbarVG = (ViewGroup) a.findViewById(actionbarid);
                if (actionbarVG != null) {
                    for (int i = 0; i < actionbarVG.getChildCount(); i++) {
                        View child = actionbarVG.getChildAt(i);
                        if (child instanceof TextView)
                            ((TextView) child).setTextColor(getUIColor(COLOR_TOOLBARTEXT));
                        if (child instanceof ImageButton)
                            ((ImageButton) child).setColorFilter(getUIColor(COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
                    }
                }
            }

            Toolbar toolbar = (Toolbar) a.findViewById(Resources.id.toolbar);
            if (toolbar != null) {
                toolbar.setBackgroundColor(getUIColor(COLOR_TOOLBAR));
                toolbar.setTitleTextColor(getUIColor(COLOR_TOOLBARTEXT));
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                a.getWindow().setStatusBarColor(getUIColor(COLOR_STATUSBAR));
                a.getWindow().setNavigationBarColor(getUIColor(COLOR_NAVBAR));
                if (Utils.prefs.getBoolean("general_darkstatusbaricons", false))
                    a.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                else
                    a.findViewById(android.R.id.content).setSystemUiVisibility(0);
            }
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (a instanceof com.whatsapp.DialogToastPreferenceActivity) {
            ListView list = (ListView) a.findViewById(android.R.id.list);
            if (list != null && Utils.nightModeShouldRun()) {
                list.setBackgroundColor(Utils.getDarkColor(2));
            }
        }
    }

    public static void tintDialog(Dialog dialog) {
        View content = dialog.findViewById(android.R.id.content);
        if (Utils.nightModeShouldRun()) {
            content.setBackgroundColor(Utils.getDarkColor(2));
        }
    }

    public static void tintAndShowDialog(AlertDialog.Builder dialog) {
        Dialog dialog1 = dialog.create();
        dialog1.show();
        View content = dialog1.findViewById(android.R.id.content);
        if (Utils.nightModeShouldRun()) {
            content.setBackgroundColor(Utils.getDarkColor(2));
        }
    }

    public static int getUIColor(int color) {
        String value = null;
        switch (color) {
            case COLOR_STATUSBAR:
                value = "general_statusbarcolor";
                break;
            case COLOR_TOOLBAR:
                value = "general_toolbarcolor";
                break;
            case COLOR_NAVBAR:
                value = "general_navbarcolor";
                break;
            case COLOR_FOREGROUND:
                value = "general_toolbarforeground";
                break;
            case COLOR_TOOLBARTEXT:
                value = "general_toolbartextcolor";
                break;
        }
        int colorint = Color.parseColor("#" + Utils.prefs.getString(value, "ffffff"));
        return colorint;
    }

    public static void tintHomeTabs(HorizontalScrollView tabs) {
        tabs.setBackgroundColor(Color.parseColor("#" + Utils.prefs.getString("general_toolbarcolor", "ffffff")));
    }

    public static String getDeviceID() {
        return Utils.prefs.getString("device_id", "");
    }

    public static void setDeviceID(String deviceID) {
        Utils.edit.putString("device_id", deviceID);
        Utils.edit.apply();
    }

    public static void initWAMOD() {
        SharedPreferences privacyPrefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        SharedPreferences.Editor privacyPrefs_Edit = privacyPrefs.edit();
        switch (Utils.prefs.getInt("wamodversion", 0)) {
            case 0:
                Utils.edit.putString("general_statusbarcolor", "0d8ed3");
                Utils.edit.putString("general_toolbarcolor", "0dacf4");
                Utils.edit.putString("general_toolbarforeground", "053954");
                Utils.edit.putString("general_navbarcolor", "2a2a2a");
                Utils.edit.putBoolean("general_darkmode", true);
                Utils.edit.putString("home_theme", "1");
                Utils.edit.putBoolean("conversation_hideprofilephoto", true);
                Utils.edit.putBoolean("conversation_hidetoolbarattach", true);
                Utils.edit.putBoolean("conversation_proximitysensor", true);
                Utils.edit.putString("conversation_rightbubblecolor", "404040");
                Utils.edit.putString("conversation_rightbubbletextcolor", "ffffff");
                Utils.edit.putString("conversation_rightbubbledatecolor", "dadada");
                Utils.edit.putString("conversation_leftbubblecolor", "303030");
                Utils.edit.putString("conversation_leftbubbletextcolor", "ffffff");
                Utils.edit.putString("conversation_leftbubbledatecolor", "dadada");
                Utils.edit.putBoolean("conversation_customparticipantcolorbool", false);
                Utils.edit.putString("conversation_customparticipantcolor", "ffffff");
                Utils.edit.putString("conversation_style_bubble", "7");
                Utils.edit.putString("conversation_style_tick", "1");

                Utils.edit.putBoolean("privacy_freezelastseen", false);
                Utils.edit.putBoolean("privacy_no2ndTick", false);
                Utils.edit.putBoolean("privacy_noBlueTick", false);
                Utils.edit.putBoolean("privacy_hideTyping", false);
                Utils.edit.putBoolean("privacy_alwaysOnline", false);

                Utils.edit.putString("theme_wamod_conversation_entry_bgcolor", "2a2a2a");
                Utils.edit.putString("theme_wamod_conversation_entry_entrybgcolor", "303030");
                Utils.edit.putString("theme_wamod_conversation_entry_hinttextcolor", "ffffff");
                Utils.edit.putString("theme_wamod_conversation_entry_textcolor", "ffffff");
                Utils.edit.putString("theme_wamod_conversation_entry_emojibtncolor", "ffffff");
                Utils.edit.putString("theme_wamod_conversation_entry_btncolor", "ffffff");
                Utils.edit.putString("theme_wamod_conversation_entry_sendbtncolor", "0dacf4");
            case 1:
                Utils.edit.putString("theme_hangouts_conversation_entry_bgcolor", "ffffff");
                Utils.edit.putString("theme_hangouts_conversation_entry_hintcolor", "607d8b");
                Utils.edit.putString("theme_hangouts_conversation_entry_textcolor", "607d8b");
                Utils.edit.putString("theme_hangouts_conversation_attach_color", "307d8b");
                Utils.edit.putString("theme_hangouts_conversation_mic_bg", "eceff1");
                Utils.edit.putString("theme_hangouts_conversation_mic_color", "98aab4");
                Utils.edit.putString("theme_hangouts_conversation_send_bg", "0f9d58");
                Utils.edit.putString("theme_hangouts_conversation_send_color", "ffffff");

                Utils.edit.putBoolean("debug_disablecolorpicker", false);
            case 2:
                Utils.edit.putBoolean("overview_cardcolor", true);
                Utils.edit.putBoolean("overview_multiplechats", true);
                Utils.edit.putString("conversation_style_entry", "1");
                Utils.edit.putString("conversation_style_bubble_layout", "0");
            case 3:
                Utils.edit.putBoolean("conversation_custombackcolorbool", true);
                Utils.edit.putString("conversation_custombackcolor", "202020");
                Utils.edit.putString("conversation_style_toolbar", "2");
                Utils.edit.putBoolean("conversation_toolbarexit", false);
            case 4:
                Utils.edit.putBoolean("general_darkstatusbaricons", true);
            case 5:
                Utils.edit.putBoolean("wamodthemes_constantlycheck", true);
            case 6:
                Utils.edit.putBoolean("conversation_androidgallery", true);
            case 7:
                Utils.edit.putString("theme_aran_conversation_bgcolor", "000000");
                Utils.edit.putString("theme_aran_conversation_entry_bgcolor", "222222");
                Utils.edit.putString("theme_aran_conversation_entry_hintcolor", "ffffff");
                Utils.edit.putString("theme_aran_conversation_entry_textcolor", "ffffff");
                Utils.edit.putString("theme_aran_conversation_mic_color", "ee5555");
                Utils.edit.putString("theme_aran_conversation_send_color", "ffffff");
                Utils.edit.putString("theme_aran_conversation_emoji_color", "ffffff");
            case 8:
                Utils.edit.putString("theme_simple_conversation_bgcolor", "ffffff");
                Utils.edit.putString("theme_simple_conversation_entry_hintcolor", "606060");
                Utils.edit.putString("theme_simple_conversation_entry_textcolor", "2a2a2a");
                Utils.edit.putString("theme_simple_conversation_mic_color", "606060");
                Utils.edit.putString("theme_simple_conversation_send_color", "606060");
                Utils.edit.putFloat("theme_simple_conversation_entry_textsize", 20);

                Utils.edit.putString("theme_mood_conversation_background_color", "55ffffff");
                Utils.edit.putString("theme_mood_conversation_entry_hintcolor", "000000");
                Utils.edit.putString("theme_mood_conversation_entry_textcolor", "000000");
                Utils.edit.putString("theme_mood_conversation_mic_color", "000000");
                Utils.edit.putString("theme_mood_conversation_send_color", "000000");
                Utils.edit.putString("theme_mood_conversation_emoji_color", "000000");
            case 9:
                Utils.edit.putString("home_tabsindicatorcolor", "ffffff");
            case 12:
                Utils.edit.putBoolean("privacy_no2ndTick", false);
                Utils.edit.putBoolean("home_drawer_blackheadertext", false);
                Utils.edit.putBoolean("home_drawer_dark", true);
            case 13:
                Utils.edit.putString("general_toolbartextcolor", "255974");
            case 23:
                Utils.edit.putBoolean("home_bottomnavigationbar", true);
                Utils.edit.putBoolean("home_bottomnavigationbar_autocolor", false);
                Utils.edit.putString("home_bottomnavigationbar_colors_bg", "2a2a2a");
                Utils.edit.putString("home_bottomnavigationbar_colors_activeitem", "0dacf4");
                Utils.edit.putString("home_bottomnavigationbar_colors_inactiveitem", "828c91");
            case 24:
                Utils.edit.putBoolean("home_drawer_showsecondaccount", true);
            case 27:
                Utils.edit.putBoolean("nightmode_enable", true);
                Utils.edit.putString("nightmode_primarytextcolor", "ffffff");
                Utils.edit.putString("nightmode_secondarytextcolor", "aaaaaa");
                Utils.edit.putString("nightmode_backgroundcolor", "202020");
                Utils.edit.putString("nightmode_cardsbackgroundcolor", "2a2a2a");
                Utils.edit.putString("drawer_light_background", "fefefe");
                Utils.edit.putString("drawer_dark_background", "2a2a2a");
            case 31:
                privacyPrefs_Edit.putBoolean("general_reportreceived", !prefs.getBoolean("privacy_no2ndTick", false));
                privacyPrefs_Edit.putBoolean("general_reportread", !prefs.getBoolean("privacy_noBlueTick", false));
                privacyPrefs_Edit.putBoolean("general_reporttyping", !prefs.getBoolean("privacy_hideTyping", false));
            case 32:
                privacyPrefs_Edit.putBoolean("general_freezelastseen", prefs.getBoolean("privacy_freezelastseen", false));
                privacyPrefs_Edit.putBoolean("general_alwaysonline", prefs.getBoolean("privacy_alwaysOnline", false));
                break;
        }
        Utils.edit.putInt("wamodversion", wamodVersionCode);
        Utils.edit.apply();
        privacyPrefs_Edit.apply();

        if (!debug) {
            try {
                Signature sign = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures[0];
                if (sign.hashCode() == -282729318) Fabric.with(Utils.context, new Crashlytics());
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void initWAMODfromHome(AppCompatActivity a) {
        initWAMOD();

        // Connect with the WAMOD server
        new CheckIn().execute(a);
    }

    public static boolean nightModeShouldRun() {
        if (!Utils.prefs.getBoolean("nightmode_enable", false)) return false;
        if (!Utils.prefs.getBoolean("nightmode_atnightonly", false)) return true;
        Boolean isNight;
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        isNight = hour < 6 || hour > 18;
        return isNight;
    }

    public static boolean ddarkMode() {
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService(Context.UI_MODE_SERVICE);
        return uiModeManager.getNightMode() == UiModeManager.MODE_NIGHT_YES;
        //return utils.prefs.getBoolean("general_darkmode", false);
    }

    public static int getTickDrawableHex(int optionID) {
        String bubbleID = Utils.prefs.getString("conversation_style_tick", "0");
        int message_unsent,
            message_got_receipt_from_server,
            message_got_receipt_from_target,
            message_got_read_receipt_from_target,
            message_unsent_onmedia,
            message_got_receipt_from_server_onmedia,
            message_got_receipt_from_target_onmedia,
            message_got_read_receipt_from_target_onmedia;

        switch (bubbleID) {
            case "0":
            default:
                message_unsent = Resources.getDrawable("message_unsent");
                message_got_receipt_from_server = Resources.getDrawable("message_got_receipt_from_server");
                message_got_receipt_from_target = Resources.getDrawable("message_got_receipt_from_target");
                message_got_read_receipt_from_target = Resources.getDrawable("message_got_read_receipt_from_target");
                message_unsent_onmedia = Resources.getDrawable("message_unsent_onmedia");
                message_got_receipt_from_server_onmedia = Resources.getDrawable("message_got_receipt_from_server_onmedia");
                message_got_receipt_from_target_onmedia = Resources.getDrawable("message_got_receipt_from_target_onmedia");
                message_got_read_receipt_from_target_onmedia = Resources.getDrawable("message_got_read_receipt_from_target_onmedia");
                break;
            case "1":
                message_unsent = Resources.getDrawable("wamod_style_tick_ios_message_unsent");
                message_got_receipt_from_server = Resources.getDrawable("wamod_style_tick_ios_message_got_receipt_from_server");
                message_got_receipt_from_target = Resources.getDrawable("wamod_style_tick_ios_message_got_receipt_from_target");
                message_got_read_receipt_from_target = Resources.getDrawable("wamod_style_tick_ios_message_got_read_receipt_from_target");
                message_unsent_onmedia = Resources.getDrawable("wamod_style_tick_ios_message_unsent_onmedia");
                message_got_receipt_from_server_onmedia = Resources.getDrawable("wamod_style_tick_ios_message_got_receipt_from_server_onmedia");
                message_got_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_ios_message_got_receipt_from_target_onmedia");
                message_got_read_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_ios_message_got_read_receipt_from_target_onmedia");
                break;
            case "2":
                message_unsent = Resources.getDrawable("wamod_style_tick_oldwaca_message_unsent");
                message_got_receipt_from_server = Resources.getDrawable("wamod_style_tick_oldwaca_message_got_receipt_from_server");
                message_got_receipt_from_target = Resources.getDrawable("wamod_style_tick_oldwaca_message_got_receipt_from_target");
                message_got_read_receipt_from_target = Resources.getDrawable("wamod_style_tick_oldwaca_message_got_read_receipt_from_target");
                message_unsent_onmedia = Resources.getDrawable("wamod_style_tick_oldwaca_message_unsent_onmedia");
                message_got_receipt_from_server_onmedia = Resources.getDrawable("wamod_style_tick_oldwaca_message_got_receipt_from_server_onmedia");
                message_got_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_oldwaca_message_got_receipt_from_target_onmedia");
                message_got_read_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_oldwaca_message_got_read_receipt_from_target_onmedia");
                break;
            case "3":
                message_unsent = Resources.getDrawable("wamod_style_tick_newwaca_message_unsent");
                message_got_receipt_from_server = Resources.getDrawable("wamod_style_tick_newwaca_message_got_receipt_from_server");
                message_got_receipt_from_target = Resources.getDrawable("wamod_style_tick_newwaca_message_got_receipt_from_target");
                message_got_read_receipt_from_target = Resources.getDrawable("wamod_style_tick_newwaca_message_got_read_receipt_from_target");
                message_unsent_onmedia = Resources.getDrawable("wamod_style_tick_newwaca_message_unsent_onmedia");
                message_got_receipt_from_server_onmedia = Resources.getDrawable("wamod_style_tick_newwaca_message_got_receipt_from_server_onmedia");
                message_got_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_newwaca_message_got_receipt_from_target_onmedia");
                message_got_read_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_newwaca_message_got_read_receipt_from_target_onmedia");
                break;
            case "4":
                message_unsent = Resources.getDrawable("wamod_style_tick_oldwamd_message_unsent");
                message_got_receipt_from_server = Resources.getDrawable("wamod_style_tick_oldwamd_message_got_receipt_from_server");
                message_got_receipt_from_target = Resources.getDrawable("wamod_style_tick_oldwamd_message_got_receipt_from_target");
                message_got_read_receipt_from_target = Resources.getDrawable("wamod_style_tick_oldwamd_message_got_read_receipt_from_target");
                message_unsent_onmedia = Resources.getDrawable("wamod_style_tick_oldwamd_message_unsent_onmedia");
                message_got_receipt_from_server_onmedia = Resources.getDrawable("wamod_style_tick_oldwamd_message_got_receipt_from_server_onmedia");
                message_got_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_oldwamd_message_got_receipt_from_target_onmedia");
                message_got_read_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_oldwamd_message_got_read_receipt_from_target_onmedia");
                break;
            case "5":
                message_unsent = Resources.getDrawable("wamod_style_tick_circles_message_unsent");
                message_got_receipt_from_server = Resources.getDrawable("wamod_style_tick_circles_message_got_receipt_from_server");
                message_got_receipt_from_target = Resources.getDrawable("wamod_style_tick_circles_message_got_receipt_from_target");
                message_got_read_receipt_from_target = Resources.getDrawable("wamod_style_tick_circles_message_got_read_receipt_from_target");
                message_unsent_onmedia = Resources.getDrawable("wamod_style_tick_circles_message_unsent_onmedia");
                message_got_receipt_from_server_onmedia = Resources.getDrawable("wamod_style_tick_circles_message_got_receipt_from_server_onmedia");
                message_got_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_circles_message_got_receipt_from_target_onmedia");
                message_got_read_receipt_from_target_onmedia = Resources.getDrawable("wamod_style_tick_circles_message_got_read_receipt_from_target_onmedia");
                break;
        }

        switch (optionID) {
            case 0:
                return message_unsent;
            case 1:
                return message_got_receipt_from_server;
            case 2:
                return message_got_receipt_from_target;
            case 3:
                return message_got_read_receipt_from_target;
            case 4:
                return message_unsent_onmedia;
            case 5:
                return message_got_receipt_from_server_onmedia;
            case 6:
                return message_got_receipt_from_target_onmedia;
            case 7:
                return message_got_read_receipt_from_target_onmedia;
        }

        return message_unsent;
    }

    public static int getDarkColor(int id) {
        String colorStr;
        int color;
        switch (id) {
            case 0:
                colorStr = Utils.prefs.getString("nightmode_primarytextcolor", "ffffff");
                if (colorStr.contentEquals("")) colorStr = "ffffff";
                break;
            case 1:
                colorStr = Utils.prefs.getString("nightmode_secondarytextcolor", "aaaaaa");
                if (colorStr.contentEquals("")) colorStr = "aaaaaa";
                break;
            default:
            case 2:
                colorStr = Utils.prefs.getString("nightmode_backgroundcolor", "303030");
                if (colorStr.contentEquals("")) colorStr = "303030";
                break;
            case 3:
                colorStr = Utils.prefs.getString("nightmode_cardsbackgroundcolor", "424242");
                if (colorStr.contentEquals("")) colorStr = "424242";
                break;
        }
        colorStr = "#" + colorStr;
        try {
            color = Color.parseColor(colorStr);
        } catch (Exception e) {
            manageException(e);
            color = Color.WHITE;
        }
        return color;
    }

    public static boolean getPrivacyConfig(int id) {
        SharedPreferences privacyPrefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        boolean value = false;
        switch (id) {
            case 0:
                value = privacyPrefs.getBoolean("general_freezelastseen", false);
                break;
            default:
            case 4:
                value = privacyPrefs.getBoolean("general_alwaysonline", false);
                break;
        }
        return value;
    }

    public static void tintToolbarItems(final ViewGroup actionbar,final android.content.res.Resources resources) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int color = Color.parseColor("#" + Utils.prefs.getString("general_toolbarforeground", "FFFFFF"));

                LinearLayoutCompat icons;
                if ((actionbar.getChildAt(1) instanceof LinearLayoutCompat)) {
                    icons = (LinearLayoutCompat) actionbar.getChildAt(1);
                } else if ((actionbar.getChildAt(2) instanceof LinearLayoutCompat)) {
                    icons = (LinearLayoutCompat) actionbar.getChildAt(2);
                } else {
                    icons = (LinearLayoutCompat) actionbar.getChildAt(3);
                }

                if ((actionbar.getChildAt(0)) instanceof TextView) {
                    ((TextView) actionbar.getChildAt(0)).setTextColor(color);
                }

                for (int i = 0; i < icons.getChildCount(); i++) {
                    if ((icons.getChildAt(i)) instanceof ImageView) {
                        ImageView icon = (ImageView) icons.getChildAt(i);
                        Drawable overflow = icon.getDrawable();
                        overflow.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                        icon.setImageDrawable(overflow);
                    }
                }
            }
        }, 0);
    }

    public static String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public static void setTaskDescription(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String title = activity.getString(Resources.string.app_name);
            if (Utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getTitle().toString();
            int color = Color.parseColor("#075e54");
            if (Utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + Utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), Resources.drawable.icon), color);
            activity.setTaskDescription(taskDesc);
        }
    }

    public static boolean parseJsonBoolean(String bool) {
        if (bool.contentEquals("1")) return true;
        else return false;
    }

    public static String parseBooleanToJson(boolean bool) {
        if (bool) return "1";
        else return "0";
    }

    public static void restartWAMOD(AppCompatActivity a) {
        PendingIntent intent = PendingIntent.getActivity(a.getBaseContext(), 0, new Intent(a.getIntent()), PendingIntent.FLAG_ONE_SHOT);
        AlarmManager manager = (AlarmManager) a.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 0, intent);
        System.exit(2);
    }

    public static void restartWAMOD(Context ctx) {
        PendingIntent intent = PendingIntent.getActivity(ctx, 0, new Intent(ctx, com.wamod.WAclass.HomeActivity.class), PendingIntent.FLAG_ONE_SHOT);
        AlarmManager manager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 0, intent);
        System.exit(2);
    }

    public static void log(AppCompatActivity a, String message) {
        if (Utils.prefs.getBoolean("log_in_toasts", false)) Toast.makeText(a, message, Toast.LENGTH_LONG).show();
        else Log.i("WAMOD", message);
    }

    public static void log(String message) {
        Log.i("WAMOD", message);
    }

    public static void logSignatures(Signature[] sign) {
        for (int i=0; i<sign.length; i++) {
            final byte[] rawCert = sign[i].toByteArray();
            InputStream certStream = new ByteArrayInputStream(rawCert);

            final CertificateFactory certFactory;
            final X509Certificate x509Cert;
            try {
                certFactory = CertificateFactory.getInstance("X509");
                x509Cert = (X509Certificate) certFactory.generateCertificate(certStream);

                log("Certificate subject: " + x509Cert.getSubjectDN() + "<br>");
                log("Certificate issuer: " + x509Cert.getIssuerDN() + "<br>");
                log("Certificate serial number: " + x509Cert.getSerialNumber() + "<br>");
                log("<br>");
            }
            catch (CertificateException e) {
                // e.printStackTrace();
            }
        }
    }

    public static Signature[] getSignature() {
        Signature[] LeakedSignatureArray = new Signature[1];
        String LeakedSignature_String = "30820332308202f0a00302010202044c2536a4300b06072a8648ce3804030500307c310b3009060355040613025553311330110603550408130a43616c69666f726e6961311430120603550407130b53616e746120436c61726131163014060355040a130d576861747341707020496e632e31143012060355040b130b456e67696e656572696e67311430120603550403130b427269616e204163746f6e301e170d3130303632353233303731365a170d3434303231353233303731365a307c310b3009060355040613025553311330110603550408130a43616c69666f726e6961311430120603550407130b53616e746120436c61726131163014060355040a130d576861747341707020496e632e31143012060355040b130b456e67696e656572696e67311430120603550403130b427269616e204163746f6e308201b83082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a0381850002818100d1198b4b81687bcf246d41a8a725f0a989a51bce326e84c828e1f556648bd71da487054d6de70fff4b49432b6862aa48fc2a93161b2c15a2ff5e671672dfb576e9d12aaff7369b9a99d04fb29d2bbbb2a503ee41b1ff37887064f41fe2805609063500a8e547349282d15981cdb58a08bede51dd7e9867295b3dfb45ffc6b259300b06072a8648ce3804030500032f00302c021400a602a7477acf841077237be090df436582ca2f0214350ce0268d07e71e55774ab4eacd4d071cd1efad";
        LeakedSignatureArray[0] = new Signature(LeakedSignature_String);
        return LeakedSignatureArray;
    }

    public static void logByteArray(byte[] bytes1, byte[] bytes2, int int1, int int2) {
        String bytesString1 = Base64.encodeToString(bytes1, Base64.DEFAULT);
        String bytesString2 = Base64.encodeToString(bytes2, Base64.DEFAULT);
        String message = "Starting: " + bytesString1 +
                "\n[SPACE]\n" + bytesString2 + "\n" +
                "\n[SPACE]\n" + bytesString2 + "\n" +
                "[SPACE]\n" + int1 + "\n" +
                "[SPACE]\n" + int2;
        toTxt(message);
    }

    public static void toTxt(String str) {
        try
        {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, "wamod.txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(str);
            writer.flush();
            writer.close();
            log("File saved! " + gpxfile.getAbsolutePath());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static SecretKey getSecretKey() {
        return new SecretKey() {
            @Override
            public String getAlgorithm() {
                return "PBKDF2WithHmacSHA1And8bit";
            }

            @Override
            public String getFormat() {
                return "RAW";
            }

            @Override
            public byte[] getEncoded() {
                byte[] bytes = Base64.decode("eQV5aq/Cg63Gsq1sshN9T3gh+UUp0wIw0xgHYT1bnCjEqOJQKCRrWxdAe2yvsDeCJL+Y4G3PRD2H\n" +
                        "UF7oUgiGow==", Base64.DEFAULT);
                return bytes;
            }
        };
    }

    public static void logMac(Mac mac) {
        log("Final: " + Base64.encodeToString(mac.doFinal(), Base64.DEFAULT));
    }

    public static byte[] getb9() {
        //byte[] official = Base64.decode("ACkLRN4OqtS0sFb/1aGVDQ==", Base64.DEFAULT);
        // 2.12.489 byte[] official = Base64.decode("w8Ar4WLq2n9/S5aonWMCGQ==", Base64.DEFAULT);
        // 2.12.551 byte[] official = Base64.decode("1E2kZOex25HvKMQPFpG1ZQ==", Base64.DEFAULT);
        // 2.16.21 byte[] official = Base64.decode("HQ3bHbhJnKQdh+B/qpAHhQ==", Base64.DEFAULT);
        // 2.16.43 byte[] official = Base64.decode("qioEf1LzV3gfqCATDwgzGg==", Base64.DEFAULT);
        // 2.16.81 byte[] official = Base64.decode("0M5VxNVpLgsnyqdzqdpCmQ==", Base64.DEFAULT); // 2.16.81
        byte[] official = Base64.decode("3Tqs7W3qktjj2MEiM/ierw==", Base64.DEFAULT); // 2.16.91
        return official;
    }

    public static String getUserName(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("com.whatsapp_preferences", 0);
        return prefs.getString("push_name", "");
    }

    public static String getUserPhoneNumber(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("RegisterPhone", 0);
        String number = prefs.getString("com.whatsapp.RegisterPhone.input_phone_number", "");
        String country = prefs.getString("com.whatsapp.RegisterPhone.country_code", "");
        String entireNumber = "+" + country + " " + number;
        return entireNumber;
    }

    public static Drawable getUserPicture(Context ctx) {
        String s = getApplicationPath(ctx);
        String pathName = s + "/files/me.jpg";
        Drawable d = Drawable.createFromPath(pathName);
        return d;
    }

    public static String getApplicationPath(Context ctx) {
        try {
            PackageManager m = ctx.getPackageManager();
            String s = ctx.getPackageName();
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
            return s;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static Drawable getDrawerBackground(Context ctx) {
        String s = getApplicationPath(ctx);
        String pathName = s + "/files/wamod_drawer_bg.png";
        try {
            Drawable d = Drawable.createFromPath(pathName);
            if (d == null) d = ctx.getResources().getDrawable(Resources.drawable.wamod_drawer_bg);
            return d;
        } catch (OutOfMemoryError e) {
            return ctx.getResources().getDrawable(Resources.drawable.wamod_drawer_bg);
        }
    }

    public static int getStatusBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /*public static com.whatsapp.registration.a getRegistrationClass(String number) {
        Mac mac = null;
        try {
            SecretKey secretKey = getSecretKey();
            mac = Mac.getInstance("HMACSHA1");
            mac.init(secretKey);
            Signature sign = getSignature()[0];
            byte[] signBytes = sign.toByteArray();
            mac.update(signBytes);
            mac.update(getb9());
            mac.update(number.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {}
          catch (InvalidKeyException e) {}
          catch (UnsupportedEncodingException e) {}
        byte[] _final = mac.doFinal();
        Log.i("WAMOD", Base64.encodeToString(_final, Base64.DEFAULT));
        return new a(_final);
    }*/

    public static int[] Nexus6PResToActualDevice(Context ctx, int x, int y) {
        int[] newValues = new int[2];
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();

        newValues[0] = (metrics.widthPixels * x) / 1440;
        newValues[1] = (metrics.heightPixels * y) / 2560;

        return newValues;
    }



    public static String getVersionName() {
        return wamodVersionName;
    }

    public static int getVersionCode() {
        return wamodVersionCode;
    }

    public static void loadColorsBeforeSuper(AppCompatActivity a) {
        Log.i("WAMOD", "Loaded activity: " + a.getClass().getName());
        if (a instanceof Activity) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Settings);
            else a.setTheme(Resources.style.WAMOD_Theme_Settings_Day);
        } else if (a instanceof com.whatsapp.HomeActivity) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Home);
            else a.setTheme(Resources.style.WAMOD_Theme_Home_Day);
        } else if (a instanceof com.whatsapp.StarredMessagesActivity) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Home);
            else a.setTheme(Resources.style.WAMOD_Theme_Home_Day);
        } else if (a instanceof com.whatsapp.VoipActivity) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Home);
            else a.setTheme(Resources.style.WAMOD_Theme_Home_Day);
        } else if (a instanceof com.whatsapp.QuickContactActivity) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Home);
            else a.setTheme(Resources.style.WAMOD_Theme_Home_Day);
        } else if (a instanceof com.whatsapp.wallpaper.SolidColorWallpaper) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Home);
            else a.setTheme(Resources.style.WAMOD_Theme_Home_Day);
        } else if (a instanceof com.whatsapp.wallpaper.SolidColorWallpaperPreview) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Home);
            else a.setTheme(Resources.style.WAMOD_Theme_Home_Day);
        } else if (a instanceof com.whatsapp.Conversation) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Conversation);
            else a.setTheme(Resources.style.WAMOD_Theme_Conversation_Day);
        } else if (a instanceof com.whatsapp.ContactInfo) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Conversation);
            else a.setTheme(Resources.style.WAMOD_Theme_Conversation_Day);
        } else if (a instanceof com.whatsapp.GroupChatInfo) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Conversation);
            else a.setTheme(Resources.style.WAMOD_Theme_Conversation_Day);
        } else if (a instanceof com.whatsapp.MediaGallery) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme_Conversation);
            else a.setTheme(Resources.style.WAMOD_Theme_Conversation_Day);
        }/* else if (a instanceof com.whatsapp.WAAppCompatActivity) {
            if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme);
            else a.setTheme(Resources.style.WAMOD_Theme_Day);
        }*/
    }

    public static void loadColorsBeforeSuper(PreferenceActivity a) {
        Log.i("WAMOD", "Loaded activity: " + a.getClass().getName());
        if (nightModeShouldRun()) a.setTheme(Resources.style.WAMOD_Theme);
        else a.setTheme(Resources.style.WAMOD_Theme_Day);
    }

    public static Drawable tintToColor(Drawable drawable, int color) {
        if (drawable == null) return null;
        int red   = (color & 0xFF0000) / 0xFFFF;
        int green = (color & 0xFF00) / 0xFF;
        int blue  = color & 0xFF;
        float[] matrix = { 0, 0, 0, 0, red,
                0, 0, 0, 0, green,
                0, 0, 0, 0, blue,
                0, 0, 0, 1, 0 };
        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);

        drawable.setColorFilter(colorFilter);

        return drawable;
    }

    public static void manageException(Exception e) {
        if (debug) throw new RuntimeException(e);
        else Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    public static void copyToClipboard(String s) {
        ClipboardManager clipboard = (ClipboardManager) Utils.context.getSystemService(Utils.context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", s);
        clipboard.setPrimaryClip(clip);
    }

    public static void setTranslationYZero(View v) {
        v.setTranslationY(0);
    }

    public static boolean isOfficialWAMOD() {
        boolean official = false;
        try {
            Signature sign = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures[0];
            if (sign.hashCode() == -282729318) official = true;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
        return official;
    }
}
