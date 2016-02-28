package com.wamod;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

/**
 * Created by brianvalente on 7/8/15.
 */
public class utils extends Activity {
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor edit;
    public static String wamodversion = "1.0.5.2";

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
            case 9:
                utils.edit.putString("home_tabsindicatorcolor", "ffffff");
            case 12:
                utils.edit.putBoolean("privacy_no2ndTick", false);
                utils.edit.putBoolean("home_drawer_blackheadertext", false);
                utils.edit.putBoolean("home_drawer_dark", true);
                break;
        }
        utils.edit.putInt("wamodversion", 13);
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
                message_unsent = 0x7f020896;
                message_got_receipt_from_server = 0x7f02088e;
                message_got_receipt_from_target = 0x7f020890;
                message_got_read_receipt_from_target = 0x7f02088c;
                message_unsent_onmedia = 0x7f020897;
                message_got_receipt_from_server_onmedia = 0x7f02088f;
                message_got_receipt_from_target_onmedia = 0x7f020891;
                message_got_read_receipt_from_target_onmedia = 0x7f02088d;
                break;
            case "1":
                message_unsent = 0x7f021023;
                message_got_receipt_from_server = 0x7f021024;
                message_got_receipt_from_target = 0x7f021025;
                message_got_read_receipt_from_target = 0x7f021026;
                message_unsent_onmedia = 0x7f021027;
                message_got_receipt_from_server_onmedia = 0x7f021028;
                message_got_receipt_from_target_onmedia = 0x7f021029;
                message_got_read_receipt_from_target_onmedia = 0x7f02102a;
                break;
            case "2":
                message_unsent = 0x7f02102b;
                message_got_receipt_from_server = 0x7f02102c;
                message_got_receipt_from_target = 0x7f02102d;
                message_got_read_receipt_from_target = 0x7f02102e;
                message_unsent_onmedia = 0x7f02102f;
                message_got_receipt_from_server_onmedia = 0x7f021030;
                message_got_receipt_from_target_onmedia = 0x7f021031;
                message_got_read_receipt_from_target_onmedia = 0x7f021032;
                break;
            case "3":
                message_unsent = 0x7f021033;
                message_got_receipt_from_server = 0x7f021034;
                message_got_receipt_from_target = 0x7f021035;
                message_got_read_receipt_from_target = 0x7f021036;
                message_unsent_onmedia = 0x7f021037;
                message_got_receipt_from_server_onmedia = 0x7f021038;
                message_got_receipt_from_target_onmedia = 0x7f021039;
                message_got_read_receipt_from_target_onmedia = 0x7f02103a;
                break;
            case "4":
                message_unsent = 0x7f02103b;
                message_got_receipt_from_server = 0x7f02103c;
                message_got_receipt_from_target = 0x7f02103d;
                message_got_read_receipt_from_target = 0x7f02103e;
                message_unsent_onmedia = 0x7f02103f;
                message_got_receipt_from_server_onmedia = 0x7f021040;
                message_got_receipt_from_target_onmedia = 0x7f021041;
                message_got_read_receipt_from_target_onmedia = 0x7f021042;
                break;
            case "5":
                message_unsent = 0x7f021043;
                message_got_receipt_from_server = 0x7f021044;
                message_got_receipt_from_target = 0x7f021045;
                message_got_read_receipt_from_target = 0x7f021046;
                message_unsent_onmedia = 0x7f021047;
                message_got_receipt_from_server_onmedia = 0x7f021048;
                message_got_receipt_from_target_onmedia = 0x7f021049;
                message_got_read_receipt_from_target_onmedia = 0x7f02104a;
                break;
            default:
                message_unsent = 0x7f02104b;
                message_got_receipt_from_server = 0x7f02104c;
                message_got_receipt_from_target = 0x7f02104d;
                message_got_read_receipt_from_target = 0x7f02104e;
                message_unsent_onmedia = 0x7f02104f;
                message_got_receipt_from_server_onmedia = 0x7f021050;
                message_got_receipt_from_target_onmedia = 0x7f021051;
                message_got_read_receipt_from_target_onmedia = 0x7f021052;
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

    public static Drawable tintToolbarIcon(Drawable icon) {
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

    public static byte[] getCertificateBytes() {
        Signature LeakedSignature = getSignature()[0];
        return LeakedSignature.toByteArray();
    }

    public static Signature[] getSignature() {
        Signature[] LeakedSignatureArray = new Signature[1];
        String LeakedSignature_String = "30820332308202f0a00302010202044c2536a4300b06072a8648ce3804030500307c310b3009060355040613025553311330110603550408130a43616c69666f726e6961311430120603550407130b53616e746120436c61726131163014060355040a130d576861747341707020496e632e31143012060355040b130b456e67696e656572696e67311430120603550403130b427269616e204163746f6e301e170d3130303632353233303731365a170d3434303231353233303731365a307c310b3009060355040613025553311330110603550408130a43616c69666f726e6961311430120603550407130b53616e746120436c61726131163014060355040a130d576861747341707020496e632e31143012060355040b130b456e67696e656572696e67311430120603550403130b427269616e204163746f6e308201b83082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a0381850002818100d1198b4b81687bcf246d41a8a725f0a989a51bce326e84c828e1f556648bd71da487054d6de70fff4b49432b6862aa48fc2a93161b2c15a2ff5e671672dfb576e9d12aaff7369b9a99d04fb29d2bbbb2a503ee41b1ff37887064f41fe2805609063500a8e547349282d15981cdb58a08bede51dd7e9867295b3dfb45ffc6b259300b06072a8648ce3804030500032f00302c021400a602a7477acf841077237be090df436582ca2f0214350ce0268d07e71e55774ab4eacd4d071cd1efad";
        LeakedSignatureArray[0] = new Signature(LeakedSignature_String);
        return LeakedSignatureArray;
    }

    public static void decodeStrings(String[] strings) {
        for (int i=0; i<strings.length; i++) {
            log("String " + i + " : " + strings[i]);
        }
    }

    public static void decodeStrings(String className, String[] strings) {
        for (int i=0; i<strings.length; i++) {
            log(className + " string " + i + " : " + strings[i]);
        }
    }

    private void call_decodeStrings() {
        String[] strings = null;
        decodeStrings("com.whatsapp.App", strings);
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
        byte[] official = Base64.decode("ACkLRN4OqtS0sFb/1aGVDQ==", Base64.DEFAULT);
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
        Drawable d = Drawable.createFromPath(pathName);
        if (d == null) d = ctx.getResources().getDrawable(id.wamod_drawer_bg);
        return d;
    }
}
