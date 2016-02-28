package com.wamod;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by brianvalente on 12/19/15.
 */
public class checkinv2 extends AsyncTask<AppCompatActivity, AppCompatActivity, AppCompatActivity> {
    String internalResponse = "";
    AppCompatActivity activity;
    boolean firstCheckin = true;

    @Override
    protected AppCompatActivity doInBackground(AppCompatActivity... activity) {
        String SetServerString = "";

        // Get phone info
        String androidVersion = Build.VERSION.RELEASE;
        String deviceModel = Build.MODEL;
        String wamodVersion = utils.wamodversion;
        String deviceID = utils.getDeviceID();

        this.activity = activity[0];

        try {
            URL url = new URL("http://wamod.ml/api/api.php");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("action", "checkin");
            params.put("androidversion", androidVersion);
            params.put("devicemodel", deviceModel);
            params.put("wamodversion", wamodVersion);
            params.put("deviceid", deviceID);

            if (firstCheckin) {
                params.put("firstcheckin", 1);

                // Get actual theme config ;_;
                params.put("general_statusbarcolor", utils.prefs.getString("general_statusbarcolor", ""));
                params.put("general_toolbarcolor", utils.prefs.getString("general_toolbarcolor", ""));
                params.put("general_toolbarforeground", utils.prefs.getString("general_toolbarforeground", ""));
                params.put("general_navbarcolor", utils.prefs.getString("general_navbarcolor", ""));
                params.put("general_darkmode", utils.parseBooleanToJson(utils.prefs.getBoolean("general_darkmode", false)));
                params.put("general_darkstatusbaricons", utils.parseBooleanToJson(utils.prefs.getBoolean("general_darkstatusbaricons", false)));
                params.put("home_theme", utils.prefs.getString("home_theme", ""));
                params.put("conversation_hideprofilephoto", utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_hideprofilephoto", false)));
                params.put("conversation_hidetoolbarattach", utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_hidetoolbarattach", false)));
                params.put("conversation_proximitysensor", utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_proximitysensor", false)));
                params.put("conversation_rightbubblecolor", utils.prefs.getString("conversation_rightbubblecolor", ""));
                params.put("conversation_rightbubbletextcolor", utils.prefs.getString("conversation_rightbubbletextcolor", ""));
                params.put("conversation_rightbubbledatecolor", utils.prefs.getString("conversation_rightbubbledatecolor", ""));
                params.put("conversation_leftbubblecolor", utils.prefs.getString("conversation_leftbubblecolor", ""));
                params.put("conversation_leftbubbletextcolor", utils.prefs.getString("conversation_leftbubbletextcolor", ""));
                params.put("conversation_leftbubbledatecolor", utils.prefs.getString("conversation_leftbubbledatecolor", ""));
                params.put("conversation_customparticipantcolorbool", utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)));
                params.put("conversation_customparticipantcolor", utils.prefs.getString("conversation_customparticipantcolor", ""));
                params.put("conversation_style_bubble", utils.prefs.getString("conversation_style_bubble", ""));
                params.put("conversation_style_tick", utils.prefs.getString("conversation_style_tick", ""));
                params.put("conversation_theme", utils.prefs.getString("conversation_theme", ""));
                params.put("privacy_freezelastseen", utils.parseBooleanToJson(utils.prefs.getBoolean("privacy_freezelastseen", false)));
                params.put("privacy_no2ndTick", utils.parseBooleanToJson(utils.prefs.getBoolean("privacy_no2ndTick", false)));
                params.put("privacy_noBlueTick", utils.parseBooleanToJson(utils.prefs.getBoolean("privacy_noBlueTick", false)));
                params.put("privacy_hideTyping", utils.parseBooleanToJson(utils.prefs.getBoolean("privacy_hideTyping", false)));
                params.put("privacy_alwaysOnline", utils.parseBooleanToJson(utils.prefs.getBoolean("privacy_alwaysOnline", false)));
                params.put("theme_wamod_conversation_entry_bgcolor", utils.prefs.getString("theme_wamod_conversation_entry_bgcolor", ""));
                params.put("theme_wamod_conversation_entry_entrybgcolor", utils.prefs.getString("theme_wamod_conversation_entry_entrybgcolor", ""));
                params.put("theme_wamod_conversation_entry_hinttextcolor", utils.prefs.getString("theme_wamod_conversation_entry_hinttextcolor", ""));
                params.put("theme_wamod_conversation_entry_textcolor", utils.prefs.getString("theme_wamod_conversation_entry_textcolor", ""));
                params.put("theme_wamod_conversation_entry_emojibtncolor", utils.prefs.getString("theme_wamod_conversation_entry_emojibtncolor", ""));
                params.put("theme_wamod_conversation_entry_btncolor", utils.prefs.getString("theme_wamod_conversation_entry_btncolor", ""));
                params.put("theme_wamod_conversation_entry_sendbtncolor", utils.prefs.getString("theme_wamod_conversation_entry_sendbtncolor", ""));
                params.put("theme_hangouts_conversation_entry_bgcolor", utils.prefs.getString("theme_hangouts_conversation_entry_bgcolor", ""));
                params.put("theme_hangouts_conversation_entry_hintcolor", utils.prefs.getString("theme_hangouts_conversation_entry_hintcolor", ""));
                params.put("theme_hangouts_conversation_entry_textcolor", utils.prefs.getString("theme_hangouts_conversation_entry_textcolor", ""));
                params.put("theme_hangouts_conversation_attach_color", utils.prefs.getString("theme_hangouts_conversation_attach_color", ""));
                params.put("theme_hangouts_conversation_mic_bg", utils.prefs.getString("theme_hangouts_conversation_mic_bg", ""));
                params.put("theme_hangouts_conversation_mic_color", utils.prefs.getString("theme_hangouts_conversation_mic_color", ""));
                params.put("theme_hangouts_conversation_send_bg", utils.prefs.getString("theme_hangouts_conversation_send_bg", ""));
                params.put("theme_hangouts_conversation_send_color", utils.prefs.getString("theme_hangouts_conversation_send_color", ""));
                params.put("overview_cardcolor", utils.parseBooleanToJson(utils.prefs.getBoolean("overview_cardcolor", false)));
                params.put("overview_multiplechats", utils.parseBooleanToJson(utils.prefs.getBoolean("overview_multiplechats", false)));
                params.put("conversation_style_entry", utils.prefs.getString("conversation_style_entry", ""));
                params.put("conversation_style_bubble_layout", utils.prefs.getString("conversation_style_bubble_layout", ""));
                params.put("conversation_custombackcolorbool", utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_custombackcolorbool", false)));
                params.put("conversation_custombackcolor", utils.prefs.getString("conversation_custombackcolor", ""));
                params.put("conversation_style_toolbar", utils.prefs.getString("conversation_style_toolbar", ""));
                params.put("conversation_toolbarexit", utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_toolbarexit", false)));
            } else {
                params.put("firstcheckin", 0);
            }

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if (!firstCheckin) {
                conn.setConnectTimeout(utils.prefs.getInt("wamodthemes_timeout", 4000));
                conn.setReadTimeout(utils.prefs.getInt("wamodthemes_timeout", 4000));
            }
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            internalResponse = utils.readStream(conn.getInputStream());

        } catch (IOException e) {}
        return activity[0];
    }

    @Override
    protected void onPostExecute(final AppCompatActivity a) {

        utils.timeSinceLastCheckin = System.currentTimeMillis();

        try {
            JSONObject jObj = new JSONObject(internalResponse);

            // Set device ID if necessary
            if (utils.getDeviceID().contentEquals("")) utils.setDeviceID(jObj.getString("deviceid"));

            // Set new theme if necessary
            if (jObj.has("general_statusbarcolor")) {
                utils.edit.putString("general_statusbarcolor", jObj.getString("general_statusbarcolor"));
                utils.edit.putString("general_toolbarcolor", jObj.getString("general_toolbarcolor"));
                utils.edit.putString("general_toolbarforeground", jObj.getString("general_toolbarforeground"));
                utils.edit.putString("general_navbarcolor", jObj.getString("general_navbarcolor"));
                utils.edit.putBoolean("general_darkmode", (jObj.getString("general_darkmode").contentEquals("1")));
                utils.edit.putBoolean("general_darkstatusbaricons", (jObj.getString("general_darkstatusbaricons").contentEquals("1")));
                utils.edit.putString("home_theme", jObj.getString("home_theme"));
                utils.edit.putBoolean("conversation_hideprofilephoto", (jObj.getString("conversation_hideprofilephoto").contentEquals("1")));
                utils.edit.putBoolean("conversation_hidetoolbarattach", (jObj.getString("conversation_hidetoolbarattach").contentEquals("1")));
                utils.edit.putBoolean("conversation_proximitysensor", (jObj.getString("conversation_proximitysensor").contentEquals("1")));
                utils.edit.putString("conversation_rightbubblecolor", jObj.getString("conversation_rightbubblecolor"));
                utils.edit.putString("conversation_rightbubbletextcolor", jObj.getString("conversation_rightbubbletextcolor"));
                utils.edit.putString("conversation_rightbubbledatecolor", jObj.getString("conversation_rightbubbledatecolor"));
                utils.edit.putString("conversation_leftbubblecolor", jObj.getString("conversation_leftbubblecolor"));
                utils.edit.putString("conversation_leftbubbletextcolor", jObj.getString("conversation_leftbubbletextcolor"));
                utils.edit.putString("conversation_leftbubbledatecolor", jObj.getString("conversation_leftbubbledatecolor"));
                utils.edit.putBoolean("conversation_customparticipantcolorbool", (jObj.getString("conversation_customparticipantcolorbool").contentEquals("1")));
                utils.edit.putString("conversation_customparticipantcolor", jObj.getString("conversation_customparticipantcolor"));
                utils.edit.putString("conversation_style_bubble", jObj.getString("conversation_style_bubble"));
                utils.edit.putString("conversation_style_tick", jObj.getString("conversation_style_tick"));

                utils.edit.putString("theme_wamod_conversation_entry_bgcolor", jObj.getString("theme_wamod_conversation_entry_bgcolor"));
                utils.edit.putString("theme_wamod_conversation_entry_entrybgcolor", jObj.getString("theme_wamod_conversation_entry_entrybgcolor"));
                utils.edit.putString("theme_wamod_conversation_entry_hinttextcolor", jObj.getString("theme_wamod_conversation_entry_hinttextcolor"));
                utils.edit.putString("theme_wamod_conversation_entry_textcolor", jObj.getString("theme_wamod_conversation_entry_textcolor"));
                utils.edit.putString("theme_wamod_conversation_entry_emojibtncolor", jObj.getString("theme_wamod_conversation_entry_emojibtncolor"));
                utils.edit.putString("theme_wamod_conversation_entry_btncolor", jObj.getString("theme_wamod_conversation_entry_btncolor"));
                utils.edit.putString("theme_wamod_conversation_entry_sendbtncolor", jObj.getString("theme_wamod_conversation_entry_sendbtncolor"));

                utils.edit.putString("theme_hangouts_conversation_entry_bgcolor", jObj.getString("theme_hangouts_conversation_entry_bgcolor"));
                utils.edit.putString("theme_hangouts_conversation_entry_hintcolor", jObj.getString("theme_hangouts_conversation_entry_hintcolor"));
                utils.edit.putString("theme_hangouts_conversation_entry_textcolor", jObj.getString("theme_hangouts_conversation_entry_textcolor"));
                utils.edit.putString("theme_hangouts_conversation_attach_color", jObj.getString("theme_hangouts_conversation_attach_color"));
                utils.edit.putString("theme_hangouts_conversation_mic_bg", jObj.getString("theme_hangouts_conversation_mic_bg"));
                utils.edit.putString("theme_hangouts_conversation_mic_color", jObj.getString("theme_hangouts_conversation_mic_color"));
                utils.edit.putString("theme_hangouts_conversation_send_bg", jObj.getString("theme_hangouts_conversation_send_bg"));
                utils.edit.putString("theme_hangouts_conversation_send_color", jObj.getString("theme_hangouts_conversation_send_color"));

                utils.edit.putBoolean("overview_cardcolor", (jObj.getString("overview_cardcolor").contentEquals("1")));
                utils.edit.putBoolean("overview_multiplechats", (jObj.getString("overview_multiplechats").contentEquals("1")));
                utils.edit.putString("conversation_style_entry", jObj.getString("conversation_style_entry"));

                utils.edit.putBoolean("conversation_custombackcolorbool", (jObj.getString("conversation_custombackcolorbool").contentEquals("1")));
                utils.edit.putString("conversation_custombackcolor", jObj.getString("conversation_custombackcolor"));
                utils.edit.putString("conversation_style_toolbar", jObj.getString("conversation_style_toolbar"));
                utils.edit.putBoolean("conversation_toolbarexit", (jObj.getString("conversation_toolbarexit").contentEquals("1")));


                // Restart when the theme is saved
                if (utils.edit.commit()) utils.restartWAMOD(a);
                else Toast.makeText(a, "There was an error installing your theme", Toast.LENGTH_SHORT).show();
            }

            // Check if there's a new version available
            //Log.i("WAMOD", "Everything fine before checking for updates");
            if (firstCheckin || a instanceof com.wamod.Settings) {
                final String latestWAMODCodename = jObj.getString("latestversion_codename");
                final String latestWAMODDescription = jObj.getString("latestversion_description");
                final String latestWAMODDescriptionES = jObj.getString("latestversion_description-es");
                final String latestWAMODLink = jObj.getString("latestversion_link");
                Update update = new Update();
                update.codename = latestWAMODCodename;
                update.description = latestWAMODDescription;
                update.descriptionES = latestWAMODDescriptionES;
                update.link = Uri.parse(latestWAMODLink);
                updateDialog(a, update);
            }

            if (jObj.has("connectiondelay") && jObj.has("timeout")) {
                int connectiondelay = jObj.getInt("connectiondelay");
                int timeout = jObj.getInt("timeout");
                if (timeout != utils.prefs.getInt("wamodthemes_timeout", 0) || connectiondelay != utils.prefs.getInt("wamodthemes_connectiondelay", 0)) {
                    utils.edit.putInt("wamodthemes_connectiondelay", connectiondelay);
                    utils.edit.putInt("wamodthemes_timeout", timeout);
                    utils.edit.apply();
                }
            }
        } catch (JSONException e) {}

        // Delay for a new checkin
        /*if (a instanceof com.whatsapp.HomeActivity && utils.prefs.getBoolean("wamodthemes_constantlycheck", false)) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    new checkinv2().execute(a);
                }
            }, utils.prefs.getInt("wamodthemes_connectiondelay", 2500));
        }*/
    }

    @Override
    protected void onPreExecute() {
        if (utils.timeSinceLastCheckin != 0) firstCheckin = false;
    }

    private void updateDialog(final AppCompatActivity activity, final Update update) {

        Log.i("WAMOD", "Activity inside the update method: " + activity.toString());

        if (!update.codename.contentEquals(utils.wamodversion) && !(activity instanceof com.whatsapp.HomeActivity && utils.prefs.getString("ignoreupdate", "").contentEquals(update.codename))) {
            // Show an update dialog

            AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

            alertDialog.setTitle(activity.getResources().getString(id.updateavailable));
            String description = update.description;
            if (Locale.getDefault().getLanguage().contentEquals("es") && !update.descriptionES.contentEquals("")) description = update.descriptionES;
            String message = activity.getResources().getString(id.updateavailablemessage, update.codename, utils.wamodversion) + " " + description;
            alertDialog.setMessage(message);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, activity.getResources().getString(id.download), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, update.link);
                    activity.startActivity(browserIntent);
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, activity.getResources().getString(id.later), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //...
                }
            });

            if (activity instanceof com.whatsapp.HomeActivity) {
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, activity.getResources().getString(id.ignorethisupdate), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        utils.edit.putString("ignoreupdate", update.codename);
                        utils.edit.apply();
                    }
                });
            }

            alertDialog.show();

            Log.i("WAMOD", "Dialog shown");
        } else if (activity instanceof com.wamod.Settings) {
            Toast.makeText(activity, activity.getResources().getString(id.uptodate), Toast.LENGTH_LONG).show();
        }
    }

    class Update {
        public String codename;
        public String description;
        public String descriptionES;
        public Uri link;
    }
}
