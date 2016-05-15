package com.wamod.themes;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wamod.Resources;
import com.wamod.settings.Miscellaneous;
import com.wamod.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by brianvalente on 12/19/15.
 */
public class CheckIn extends AsyncTask<AppCompatActivity, AppCompatActivity, AppCompatActivity> {
    String internalResponse = "";
    AppCompatActivity activity;
    boolean firstCheckin = true;

    @Override
    protected AppCompatActivity doInBackground(AppCompatActivity... activity) {
        String SetServerString = "";

        // Get phone info
        String androidVersion = Build.VERSION.RELEASE;
        String deviceModel = Build.MODEL;
        String wamodVersion = Utils.wamodVersionName;
        String deviceID = Utils.getDeviceID();

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
                params.put("general_statusbarcolor", Utils.prefs.getString("general_statusbarcolor", ""));
                params.put("general_toolbarcolor", Utils.prefs.getString("general_toolbarcolor", ""));
                params.put("general_toolbarforeground", Utils.prefs.getString("general_toolbarforeground", ""));
                params.put("general_navbarcolor", Utils.prefs.getString("general_navbarcolor", ""));
                params.put("general_darkmode", Utils.parseBooleanToJson(Utils.prefs.getBoolean("general_darkmode", false)));
                params.put("general_darkstatusbaricons", Utils.parseBooleanToJson(Utils.prefs.getBoolean("general_darkstatusbaricons", false)));
                params.put("home_theme", Utils.prefs.getString("home_theme", ""));
                params.put("conversation_hideprofilephoto", Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_hideprofilephoto", false)));
                params.put("conversation_hidetoolbarattach", Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_hidetoolbarattach", false)));
                params.put("conversation_proximitysensor", Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_proximitysensor", false)));
                params.put("conversation_rightbubblecolor", Utils.prefs.getString("conversation_rightbubblecolor", ""));
                params.put("conversation_rightbubbletextcolor", Utils.prefs.getString("conversation_rightbubbletextcolor", ""));
                params.put("conversation_rightbubbledatecolor", Utils.prefs.getString("conversation_rightbubbledatecolor", ""));
                params.put("conversation_leftbubblecolor", Utils.prefs.getString("conversation_leftbubblecolor", ""));
                params.put("conversation_leftbubbletextcolor", Utils.prefs.getString("conversation_leftbubbletextcolor", ""));
                params.put("conversation_leftbubbledatecolor", Utils.prefs.getString("conversation_leftbubbledatecolor", ""));
                params.put("conversation_customparticipantcolorbool", Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)));
                params.put("conversation_customparticipantcolor", Utils.prefs.getString("conversation_customparticipantcolor", ""));
                params.put("conversation_style_bubble", Utils.prefs.getString("conversation_style_bubble", ""));
                params.put("conversation_style_tick", Utils.prefs.getString("conversation_style_tick", ""));
                params.put("conversation_theme", Utils.prefs.getString("conversation_theme", ""));
                params.put("privacy_freezelastseen", Utils.parseBooleanToJson(Utils.prefs.getBoolean("privacy_freezelastseen", false)));
                params.put("privacy_no2ndTick", Utils.parseBooleanToJson(Utils.prefs.getBoolean("privacy_no2ndTick", false)));
                params.put("privacy_noBlueTick", Utils.parseBooleanToJson(Utils.prefs.getBoolean("privacy_noBlueTick", false)));
                params.put("privacy_hideTyping", Utils.parseBooleanToJson(Utils.prefs.getBoolean("privacy_hideTyping", false)));
                params.put("privacy_alwaysOnline", Utils.parseBooleanToJson(Utils.prefs.getBoolean("privacy_alwaysOnline", false)));
                params.put("theme_wamod_conversation_entry_bgcolor", Utils.prefs.getString("theme_wamod_conversation_entry_bgcolor", ""));
                params.put("theme_wamod_conversation_entry_entrybgcolor", Utils.prefs.getString("theme_wamod_conversation_entry_entrybgcolor", ""));
                params.put("theme_wamod_conversation_entry_hinttextcolor", Utils.prefs.getString("theme_wamod_conversation_entry_hinttextcolor", ""));
                params.put("theme_wamod_conversation_entry_textcolor", Utils.prefs.getString("theme_wamod_conversation_entry_textcolor", ""));
                params.put("theme_wamod_conversation_entry_emojibtncolor", Utils.prefs.getString("theme_wamod_conversation_entry_emojibtncolor", ""));
                params.put("theme_wamod_conversation_entry_btncolor", Utils.prefs.getString("theme_wamod_conversation_entry_btncolor", ""));
                params.put("theme_wamod_conversation_entry_sendbtncolor", Utils.prefs.getString("theme_wamod_conversation_entry_sendbtncolor", ""));
                params.put("theme_hangouts_conversation_entry_bgcolor", Utils.prefs.getString("theme_hangouts_conversation_entry_bgcolor", ""));
                params.put("theme_hangouts_conversation_entry_hintcolor", Utils.prefs.getString("theme_hangouts_conversation_entry_hintcolor", ""));
                params.put("theme_hangouts_conversation_entry_textcolor", Utils.prefs.getString("theme_hangouts_conversation_entry_textcolor", ""));
                params.put("theme_hangouts_conversation_attach_color", Utils.prefs.getString("theme_hangouts_conversation_attach_color", ""));
                params.put("theme_hangouts_conversation_mic_bg", Utils.prefs.getString("theme_hangouts_conversation_mic_bg", ""));
                params.put("theme_hangouts_conversation_mic_color", Utils.prefs.getString("theme_hangouts_conversation_mic_color", ""));
                params.put("theme_hangouts_conversation_send_bg", Utils.prefs.getString("theme_hangouts_conversation_send_bg", ""));
                params.put("theme_hangouts_conversation_send_color", Utils.prefs.getString("theme_hangouts_conversation_send_color", ""));
                params.put("overview_cardcolor", Utils.parseBooleanToJson(Utils.prefs.getBoolean("overview_cardcolor", false)));
                params.put("overview_multiplechats", Utils.parseBooleanToJson(Utils.prefs.getBoolean("overview_multiplechats", false)));
                params.put("conversation_style_entry", Utils.prefs.getString("conversation_style_entry", ""));
                params.put("conversation_style_bubble_layout", Utils.prefs.getString("conversation_style_bubble_layout", ""));
                params.put("conversation_custombackcolorbool", Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_custombackcolorbool", false)));
                params.put("conversation_custombackcolor", Utils.prefs.getString("conversation_custombackcolor", ""));
                params.put("conversation_style_toolbar", Utils.prefs.getString("conversation_style_toolbar", ""));
                params.put("conversation_toolbarexit", Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_toolbarexit", false)));
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
                conn.setConnectTimeout(Utils.prefs.getInt("wamodthemes_timeout", 4000));
                conn.setReadTimeout(Utils.prefs.getInt("wamodthemes_timeout", 4000));
            }
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            internalResponse = Utils.readStream(conn.getInputStream());

        } catch (IOException e) {}
        return activity[0];
    }

    @Override
    protected void onPostExecute(final AppCompatActivity a) {

        Log.i("WAMOD", "Server response: " + internalResponse);

        Utils.timeSinceLastCheckin = System.currentTimeMillis();

        try {
            JSONObject jObj = new JSONObject(internalResponse);

            // Set device ID if necessary
            if (Utils.getDeviceID().contentEquals("")) Utils.setDeviceID(jObj.getString("deviceid"));

            // Set new theme if necessary
            if (jObj.has("general_statusbarcolor")) {
                Utils.edit.putString("general_statusbarcolor", jObj.getString("general_statusbarcolor"));
                Utils.edit.putString("general_toolbarcolor", jObj.getString("general_toolbarcolor"));
                Utils.edit.putString("general_toolbarforeground", jObj.getString("general_toolbarforeground"));
                Utils.edit.putString("general_navbarcolor", jObj.getString("general_navbarcolor"));
                Utils.edit.putBoolean("general_darkmode", (jObj.getString("general_darkmode").contentEquals("1")));
                Utils.edit.putBoolean("general_darkstatusbaricons", (jObj.getString("general_darkstatusbaricons").contentEquals("1")));
                Utils.edit.putString("home_theme", jObj.getString("home_theme"));
                Utils.edit.putBoolean("conversation_hideprofilephoto", (jObj.getString("conversation_hideprofilephoto").contentEquals("1")));
                Utils.edit.putBoolean("conversation_hidetoolbarattach", (jObj.getString("conversation_hidetoolbarattach").contentEquals("1")));
                Utils.edit.putBoolean("conversation_proximitysensor", (jObj.getString("conversation_proximitysensor").contentEquals("1")));
                Utils.edit.putString("conversation_rightbubblecolor", jObj.getString("conversation_rightbubblecolor"));
                Utils.edit.putString("conversation_rightbubbletextcolor", jObj.getString("conversation_rightbubbletextcolor"));
                Utils.edit.putString("conversation_rightbubbledatecolor", jObj.getString("conversation_rightbubbledatecolor"));
                Utils.edit.putString("conversation_leftbubblecolor", jObj.getString("conversation_leftbubblecolor"));
                Utils.edit.putString("conversation_leftbubbletextcolor", jObj.getString("conversation_leftbubbletextcolor"));
                Utils.edit.putString("conversation_leftbubbledatecolor", jObj.getString("conversation_leftbubbledatecolor"));
                Utils.edit.putBoolean("conversation_customparticipantcolorbool", (jObj.getString("conversation_customparticipantcolorbool").contentEquals("1")));
                Utils.edit.putString("conversation_customparticipantcolor", jObj.getString("conversation_customparticipantcolor"));
                Utils.edit.putString("conversation_style_bubble", jObj.getString("conversation_style_bubble"));
                Utils.edit.putString("conversation_style_tick", jObj.getString("conversation_style_tick"));

                Utils.edit.putString("theme_wamod_conversation_entry_bgcolor", jObj.getString("theme_wamod_conversation_entry_bgcolor"));
                Utils.edit.putString("theme_wamod_conversation_entry_entrybgcolor", jObj.getString("theme_wamod_conversation_entry_entrybgcolor"));
                Utils.edit.putString("theme_wamod_conversation_entry_hinttextcolor", jObj.getString("theme_wamod_conversation_entry_hinttextcolor"));
                Utils.edit.putString("theme_wamod_conversation_entry_textcolor", jObj.getString("theme_wamod_conversation_entry_textcolor"));
                Utils.edit.putString("theme_wamod_conversation_entry_emojibtncolor", jObj.getString("theme_wamod_conversation_entry_emojibtncolor"));
                Utils.edit.putString("theme_wamod_conversation_entry_btncolor", jObj.getString("theme_wamod_conversation_entry_btncolor"));
                Utils.edit.putString("theme_wamod_conversation_entry_sendbtncolor", jObj.getString("theme_wamod_conversation_entry_sendbtncolor"));

                Utils.edit.putString("theme_hangouts_conversation_entry_bgcolor", jObj.getString("theme_hangouts_conversation_entry_bgcolor"));
                Utils.edit.putString("theme_hangouts_conversation_entry_hintcolor", jObj.getString("theme_hangouts_conversation_entry_hintcolor"));
                Utils.edit.putString("theme_hangouts_conversation_entry_textcolor", jObj.getString("theme_hangouts_conversation_entry_textcolor"));
                Utils.edit.putString("theme_hangouts_conversation_attach_color", jObj.getString("theme_hangouts_conversation_attach_color"));
                Utils.edit.putString("theme_hangouts_conversation_mic_bg", jObj.getString("theme_hangouts_conversation_mic_bg"));
                Utils.edit.putString("theme_hangouts_conversation_mic_color", jObj.getString("theme_hangouts_conversation_mic_color"));
                Utils.edit.putString("theme_hangouts_conversation_send_bg", jObj.getString("theme_hangouts_conversation_send_bg"));
                Utils.edit.putString("theme_hangouts_conversation_send_color", jObj.getString("theme_hangouts_conversation_send_color"));

                Utils.edit.putBoolean("overview_cardcolor", (jObj.getString("overview_cardcolor").contentEquals("1")));
                Utils.edit.putBoolean("overview_multiplechats", (jObj.getString("overview_multiplechats").contentEquals("1")));
                Utils.edit.putString("conversation_style_entry", jObj.getString("conversation_style_entry"));

                Utils.edit.putBoolean("conversation_custombackcolorbool", (jObj.getString("conversation_custombackcolorbool").contentEquals("1")));
                Utils.edit.putString("conversation_custombackcolor", jObj.getString("conversation_custombackcolor"));
                Utils.edit.putString("conversation_style_toolbar", jObj.getString("conversation_style_toolbar"));
                Utils.edit.putBoolean("conversation_toolbarexit", (jObj.getString("conversation_toolbarexit").contentEquals("1")));


                // Restart when the theme is saved
                if (Utils.edit.commit()) Utils.restartWAMOD(a);
                else Toast.makeText(a, "There was an error installing your theme", Toast.LENGTH_SHORT).show();
            }

            // Check if there's a new version available
            //Log.i("WAMOD", "Everything fine before checking for updates");
            if (firstCheckin || !(a instanceof com.whatsapp.HomeActivity)) {
                final String latestWAMODCodename = jObj.getString("latestversion_codename");
                final String latestWAMODDescription = jObj.getString("latestversion_description");
                final String latestWAMODDescriptionES = jObj.getString("latestversion_description-es");
                final String latestWAMODLink = jObj.getString("latestversion_link");
                final int    latestWAMODCode = jObj.getInt("latestversion_code");
                Update update = new Update();
                update.codename = latestWAMODCodename;
                update.description = latestWAMODDescription;
                update.descriptionES = latestWAMODDescriptionES;
                update.code = latestWAMODCode;
                update.link = Uri.parse(latestWAMODLink);
                updateDialog(a, update);
            }

            if (jObj.has("connectiondelay") && jObj.has("timeout")) {
                int connectiondelay = jObj.getInt("connectiondelay");
                int timeout = jObj.getInt("timeout");
                if (timeout != Utils.prefs.getInt("wamodthemes_timeout", 0) || connectiondelay != Utils.prefs.getInt("wamodthemes_connectiondelay", 0)) {
                    Utils.edit.putInt("wamodthemes_connectiondelay", connectiondelay);
                    Utils.edit.putInt("wamodthemes_timeout", timeout);
                    Utils.edit.apply();
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
        if (Utils.timeSinceLastCheckin != 0) firstCheckin = false;
    }

    private void updateDialog(final AppCompatActivity activity, final Update update) {

        Log.i("WAMOD", "Activity inside the update method: " + activity.toString());

        //if (!update.codename.contentEquals(utils.wamodVersionName) && !(activity instanceof com.whatsapp.HomeActivity && utils.prefs.getString("ignoreupdate", "").contentEquals(update.codename))) {
        if (Utils.getVersionCode() < update.code && !(activity instanceof com.whatsapp.HomeActivity && Utils.prefs.getString("ignoreupdate", "").contentEquals(update.codename))) {
            // Show an update dialog

            try {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);

                alertDialog.setTitle(activity.getResources().getString(Resources.string.wamod_updater_title));
                String description = update.description;
                if (Locale.getDefault().getLanguage().contentEquals("es") && !update.descriptionES.contentEquals(""))
                    description = update.descriptionES;
                String message = activity.getResources().getString(Resources.string.wamod_updater_message, update.codename, Utils.wamodVersionName) + " " + description;
                alertDialog.setMessage(message);

                alertDialog.setPositiveButton(activity.getResources().getString(Resources.string.wamod_updater_download), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, update.link);
                        activity.startActivity(browserIntent);
                    }
                });

                alertDialog.setNegativeButton(activity.getResources().getString(Resources.string.wamod_updater_later), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //...
                    }
                });

                alertDialog.setNeutralButton(activity.getResources().getString(Resources.string.wamod_updater_ignore), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utils.edit.putString("ignoreupdate", update.codename);
                        Utils.edit.apply();
                    }
                });

                alertDialog.show();

                Log.i("WAMOD", "Dialog shown");

            } catch (Exception e) {}
        } else if (activity instanceof Miscellaneous) {
            Toast.makeText(activity, activity.getResources().getString(Resources.string.wamod_updater_uptodate), Toast.LENGTH_LONG).show();
        }
    }

    class Update {
        public String codename;
        public String description;
        public String descriptionES;
        public Uri link;
        public int code;
    }
}
