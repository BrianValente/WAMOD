package com.wamod;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by brianvalente on 7/8/15.
 */
public class utils extends Activity {
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor edit;
    public static String wamodversion = "1.0.3";

    public static long timeSinceLastCheckin = 0;

    public static final int COLOR_STATUSBAR = 0;
    public static final int COLOR_TOOLBAR = 1;
    public static final int COLOR_NAVBAR = 2;
    
    public static void loadColors(android.support.v7.app.ActionBar actionBar, Window window) {
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"))));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.setStatusBarColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));
            window.setNavigationBarColor(Color.parseColor("#" + utils.prefs.getString("general_navbarcolor", "ffffff")));
        }
    }

    public static void loadColors(Toolbar toolbar, Window window) {
        toolbar.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff")));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.setStatusBarColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));
            window.setNavigationBarColor(Color.parseColor("#" + utils.prefs.getString("general_navbarcolor", "ffffff")));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && utils.prefs.getBoolean("general_darkstatusbaricons", false)) {
            toolbar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static void loadColors(AppCompatActivity activity) {
        //activity.findViewById(R.id.action_bar).setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff")));
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"))));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.getWindow().setStatusBarColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));
            activity.getWindow().setNavigationBarColor(Color.parseColor("#" + utils.prefs.getString("general_navbarcolor", "ffffff")));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && utils.prefs.getBoolean("general_darkstatusbaricons", false)) {
            activity.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
        }
        int colorint = Color.parseColor("#" + utils.prefs.getString(value, "ffffff"));
        return colorint;
    }

    public static void tintHomeTabs(HorizontalScrollView tabs) {
        tabs.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff")));
    }

    public static String getDeviceID() {
        return utils.prefs.getString("device_id", "");
    }

    public static void setDeviceID(String deviceID) {
        utils.edit.putString("device_id", deviceID);
        utils.edit.apply();
    }

    public static void initWAMOD() {
        switch (utils.prefs.getInt("wamodversion", 0)) {
            case 0:
                utils.edit.putString("general_statusbarcolor", "0b8043");
                utils.edit.putString("general_toolbarcolor", "0f9d58");
                utils.edit.putString("general_toolbarforeground", "ffffff");
                utils.edit.putString("general_navbarcolor", "555555");
                utils.edit.putBoolean("general_darkmode", false);
                utils.edit.putString("home_theme", "1");
                utils.edit.putBoolean("conversation_hideprofilephoto", true);
                utils.edit.putBoolean("conversation_hidetoolbarattach", true);
                utils.edit.putBoolean("conversation_proximitysensor", true);
                utils.edit.putString("conversation_rightbubblecolor", "cfd8dc");
                utils.edit.putString("conversation_rightbubbletextcolor", "263238");
                utils.edit.putString("conversation_rightbubbledatecolor", "263238");
                utils.edit.putString("conversation_leftbubblecolor", "ffffff");
                utils.edit.putString("conversation_leftbubbletextcolor", "263238");
                utils.edit.putString("conversation_leftbubbledatecolor", "263238");
                utils.edit.putBoolean("conversation_customparticipantcolorbool", false);
                utils.edit.putString("conversation_customparticipantcolor", "000000");
                utils.edit.putString("conversation_style_bubble", "7");
                utils.edit.putString("conversation_style_tick", "1");

                utils.edit.putBoolean("privacy_freezelastseen", false);
                utils.edit.putBoolean("privacy_no2ndTick", false);
                utils.edit.putBoolean("privacy_noBlueTick", false);
                utils.edit.putBoolean("privacy_hideTyping", false);
                utils.edit.putBoolean("privacy_alwaysOnline", false);

                utils.edit.putString("theme_wamod_conversation_entry_bgcolor", "ffffff");
                utils.edit.putString("theme_wamod_conversation_entry_entrybgcolor", "cfd8dc");
                utils.edit.putString("theme_wamod_conversation_entry_hinttextcolor", "263238");
                utils.edit.putString("theme_wamod_conversation_entry_textcolor", "263238");
                utils.edit.putString("theme_wamod_conversation_entry_emojibtncolor", "263238");
                utils.edit.putString("theme_wamod_conversation_entry_btncolor", "263238");
                utils.edit.putString("theme_wamod_conversation_entry_sendbtncolor", "263238");
            case 1:
                utils.edit.putString("theme_hangouts_conversation_entry_bgcolor", "ffffff");
                utils.edit.putString("theme_hangouts_conversation_entry_hintcolor", "607d8b");
                utils.edit.putString("theme_hangouts_conversation_entry_textcolor", "607d8b");
                utils.edit.putString("theme_hangouts_conversation_attach_color", "307d8b");
                utils.edit.putString("theme_hangouts_conversation_mic_bg", "eceff1");
                utils.edit.putString("theme_hangouts_conversation_mic_color", "98aab4");
                utils.edit.putString("theme_hangouts_conversation_send_bg", "0f9d58");
                utils.edit.putString("theme_hangouts_conversation_send_color", "ffffff");

                utils.edit.putBoolean("debug_disablecolorpicker", false);
            case 2:
                utils.edit.putBoolean("overview_cardcolor", true);
                utils.edit.putBoolean("overview_multiplechats", true);
                utils.edit.putString("conversation_style_entry", "2");
                utils.edit.putString("conversation_style_bubble_layout", "0");
            case 3:
                utils.edit.putBoolean("conversation_custombackcolorbool", true);
                utils.edit.putString("conversation_custombackcolor", "eceff1");
                utils.edit.putString("conversation_style_toolbar", "2");
                utils.edit.putBoolean("conversation_toolbarexit", false);
            case 4:
                utils.edit.putBoolean("general_darkstatusbaricons", false);
            case 5:
                utils.edit.putBoolean("wamodthemes_constantlycheck", true);
            case 6:
                utils.edit.putBoolean("conversation_androidgallery", true);
            case 7:
                utils.edit.putString("theme_aran_conversation_bgcolor", "000000");
                utils.edit.putString("theme_aran_conversation_entry_bgcolor", "222222");
                utils.edit.putString("theme_aran_conversation_entry_hintcolor", "ffffff");
                utils.edit.putString("theme_aran_conversation_entry_textcolor", "ffffff");
                utils.edit.putString("theme_aran_conversation_mic_color", "ee5555");
                utils.edit.putString("theme_aran_conversation_send_color", "ffffff");
                utils.edit.putString("theme_aran_conversation_emoji_color", "ffffff");
            case 8:
                utils.edit.putString("theme_simple_conversation_bgcolor", "ffffff");
                utils.edit.putString("theme_simple_conversation_entry_hintcolor", "606060");
                utils.edit.putString("theme_simple_conversation_entry_textcolor", "2a2a2a");
                utils.edit.putString("theme_simple_conversation_mic_color", "606060");
                utils.edit.putString("theme_simple_conversation_send_color", "606060");
                utils.edit.putFloat("theme_simple_conversation_entry_textsize", 20);

                utils.edit.putString("theme_mood_conversation_background_color", "55ffffff");
                utils.edit.putString("theme_mood_conversation_entry_hintcolor", "000000");
                utils.edit.putString("theme_mood_conversation_entry_textcolor", "000000");
                utils.edit.putString("theme_mood_conversation_mic_color", "000000");
                utils.edit.putString("theme_mood_conversation_send_color", "000000");
                utils.edit.putString("theme_mood_conversation_emoji_color", "000000");
                break;
            case 9:
                break;
        }
        utils.edit.putInt("wamodversion", 9);
        utils.edit.apply();
    }

    public static void initWAMODfromHome(AppCompatActivity a) {
        initWAMOD();

        // Connect with the WAMOD server
        new checkinv2().execute(a);
    }

    public static boolean darkMode() {
        return utils.prefs.getBoolean("general_darkmode", false);
    }

    public static int getTickDrawableHex(int optionID) {
        String bubbleID = utils.prefs.getString("conversation_style_tick", "0");
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
                message_unsent = 0x7f02088a;
                message_got_receipt_from_server = 0x7f020882;
                message_got_receipt_from_target = 0x7f020884;
                message_got_read_receipt_from_target = 0x7f020880;
                message_unsent_onmedia = 0x7f02088b;
                message_got_receipt_from_server_onmedia = 0x7f020883;
                message_got_receipt_from_target_onmedia = 0x7f020885;
                message_got_read_receipt_from_target_onmedia = 0x7f020881;
                break;
            case "1":
                message_unsent = 0x7f020964;
                message_got_receipt_from_server = 0x7f020965;
                message_got_receipt_from_target = 0x7f020966;
                message_got_read_receipt_from_target = 0x7f020967;
                message_unsent_onmedia = 0x7f020968;
                message_got_receipt_from_server_onmedia = 0x7f020969;
                message_got_receipt_from_target_onmedia = 0x7f02096a;
                message_got_read_receipt_from_target_onmedia = 0x7f02096b;
                break;
            case "2":
                message_unsent = 0x7f02096c;
                message_got_receipt_from_server = 0x7f02096d;
                message_got_receipt_from_target = 0x7f02096e;
                message_got_read_receipt_from_target = 0x7f02096f;
                message_unsent_onmedia = 0x7f020970;
                message_got_receipt_from_server_onmedia = 0x7f020971;
                message_got_receipt_from_target_onmedia = 0x7f020972;
                message_got_read_receipt_from_target_onmedia = 0x7f020973;
                break;
            case "3":
                message_unsent = 0x7f020974;
                message_got_receipt_from_server = 0x7f020975;
                message_got_receipt_from_target = 0x7f020976;
                message_got_read_receipt_from_target = 0x7f020977;
                message_unsent_onmedia = 0x7f020978;
                message_got_receipt_from_server_onmedia = 0x7f020979;
                message_got_receipt_from_target_onmedia = 0x7f02097a;
                message_got_read_receipt_from_target_onmedia = 0x7f02097b;
                break;
            case "4":
                message_unsent = 0x7f02097c;
                message_got_receipt_from_server = 0x7f02097d;
                message_got_receipt_from_target = 0x7f02097e;
                message_got_read_receipt_from_target = 0x7f02097f;
                message_unsent_onmedia = 0x7f020980;
                message_got_receipt_from_server_onmedia = 0x7f020981;
                message_got_receipt_from_target_onmedia = 0x7f020982;
                message_got_read_receipt_from_target_onmedia = 0x7f020983;
                break;
            case "5":
                message_unsent = 0x7f020984;
                message_got_receipt_from_server = 0x7f020985;
                message_got_receipt_from_target = 0x7f020986;
                message_got_read_receipt_from_target = 0x7f020987;
                message_unsent_onmedia = 0x7f020988;
                message_got_receipt_from_server_onmedia = 0x7f020989;
                message_got_receipt_from_target_onmedia = 0x7f02098a;
                message_got_read_receipt_from_target_onmedia = 0x7f02098b;
                break;
            default:
                message_unsent = 0x7f02088a;
                message_got_receipt_from_server = 0x7f020882;
                message_got_receipt_from_target = 0x7f020884;
                message_got_read_receipt_from_target = 0x7f020880;
                message_unsent_onmedia = 0x7f02088b;
                message_got_receipt_from_server_onmedia = 0x7f020883;
                message_got_receipt_from_target_onmedia = 0x7f020885;
                message_got_read_receipt_from_target_onmedia = 0x7f020881;
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
        switch (id) {
            case 0:
                colorStr = "#ffffff";
                break;
            case 1:
                colorStr = "#aaaaaa";
                break;
            default:
            case 2:
                colorStr = "#303030";
        }
        int color = Color.parseColor(colorStr);
        return color;
    }

    public static boolean getPrivacyConfig(int id) {
        boolean value = false;
        switch (id) {
            case 0:
                value = utils.prefs.getBoolean("privacy_freezelastseen", false);
                break;
            case 1:
                value = utils.prefs.getBoolean("privacy_no2ndTick", false);
                break;
            case 2:
                value = utils.prefs.getBoolean("privacy_noBlueTick", false);
                break;
            case 3:
                value = utils.prefs.getBoolean("privacy_hideTyping", false);
                break;
            default:
            case 4:
                value = utils.prefs.getBoolean("privacy_alwaysOnline", false);
                break;
        }
        return value;
    }

    public static void tintToolbarItems(final ViewGroup actionbar,final Resources resources) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final int color = Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF"));

                LinearLayoutCompat icons;
                if ((actionbar.getChildAt(1) instanceof LinearLayoutCompat)) {
                    icons = (LinearLayoutCompat) actionbar.getChildAt(1);
                } else {
                    icons = (LinearLayoutCompat) actionbar.getChildAt(2);
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

    public static Drawable tintToolbarIcon (Drawable icon) {
        icon.setColorFilter(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        return icon;
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
            String title = activity.getString(id.app_name);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getTitle().toString();
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), id.icon), color);
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

    public static void crashWAMOD(AppCompatActivity a) {
        utils.edit.putInt("wamodversion", 0);
        utils.edit.putBoolean("crash", true);
        utils.edit.apply();

        restartWAMOD(a);
    }

    public static void crashWAMOD() {
        utils.edit.putInt("wamodversion", 0);
        utils.edit.putBoolean("crash", true);
        utils.edit.apply();
    }

    public static void log(AppCompatActivity a, String message) {
        if (utils.prefs.getBoolean("log_in_toasts", false)) Toast.makeText(a, message, 500).show();
        else Log.i("WAMOD", message);
    }
}
