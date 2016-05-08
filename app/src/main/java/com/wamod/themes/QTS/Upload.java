package com.wamod.themes.QTS;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.wamod.Resources;
import com.wamod.id;
import com.wamod.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import io.fabric.sdk.android.Fabric;

/**
 * Created by brianvalente on 12/19/15.
 */
public class Upload extends AsyncTask<Void, Void, Void> {
    String internalResponse = "";
    public static AppCompatActivity activity;
    ProgressDialog progress;
    boolean official = false;

    @Override
    protected Void doInBackground(Void... voids) {

        if (!official) return null;

        try {
            URL url = new URL("http://wamod.ml/api/api.php");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("action", "qts_upload");

            // Get actual theme config ;_;

            // General colors
            params.put("general_statusbarcolor",                       utils.prefs.getString("general_statusbarcolor", ""));
            params.put("general_toolbarcolor",                         utils.prefs.getString("general_toolbarcolor", ""));
            params.put("general_toolbartextcolor",                     utils.prefs.getString("general_toolbartextcolor", ""));
            params.put("general_toolbarforeground",                    utils.prefs.getString("general_toolbarforeground", ""));
            params.put("general_navbarcolor",                          utils.prefs.getString("general_navbarcolor", ""));
            params.put("general_darkstatusbaricons",                   utils.parseBooleanToJson(utils.prefs.getBoolean("general_darkstatusbaricons", false)));

            params.put("nightmode_enable",                             utils.parseBooleanToJson(utils.prefs.getBoolean("nightmode_enable", false)));
            params.put("nightmode_atnightonly",                        utils.parseBooleanToJson(utils.prefs.getBoolean("nightmode_atnightonly", false)));


            // Home

            params.put("home_theme",                                   utils.prefs.getString("home_theme", ""));
            params.put("home_tabsindicatorcolor",                      utils.prefs.getString("home_tabsindicatorcolor", ""));
            params.put("home_drawer_dark",                             utils.parseBooleanToJson(utils.prefs.getBoolean("home_drawer_dark", false)));
            params.put("home_drawer_blackheadertext",                  utils.parseBooleanToJson(utils.prefs.getBoolean("home_drawer_blackheadertext", false)));
            params.put("home_drawer_showsecondaccount",                utils.parseBooleanToJson(utils.prefs.getBoolean("home_drawer_showsecondaccount", false)));

            params.put("home_bottomnavigationbar_autocolor",           utils.parseBooleanToJson(utils.prefs.getBoolean("home_bottomnavigationbar_autocolor", false)));
            params.put("home_bottomnavigationbar_colors_bg",           utils.prefs.getString("home_bottomnavigationbar_colors_bg", ""));
            params.put("home_bottomnavigationbar_colors_activeitem",   utils.prefs.getString("home_bottomnavigationbar_colors_activeitem", ""));
            params.put("home_bottomnavigationbar_colors_inactiveitem", utils.prefs.getString("home_bottomnavigationbar_colors_inactiveitem", ""));


            // Conversation

            params.put("conversation_style_toolbar",                   utils.prefs.getString("conversation_style_toolbar", ""));
            params.put("conversation_hideprofilephoto",                utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_hideprofilephoto", false)));
            params.put("conversation_hidetoolbarattach",               utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_hidetoolbarattach", false)));
            params.put("conversation_toolbarexit",                     utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_toolbarexit", false)));
            params.put("conversation_rightbubblecolor",                utils.prefs.getString("conversation_rightbubblecolor", ""));
            params.put("conversation_rightbubbletextcolor",            utils.prefs.getString("conversation_rightbubbletextcolor", ""));
            params.put("conversation_rightbubbledatecolor",            utils.prefs.getString("conversation_rightbubbledatecolor", ""));
            params.put("conversation_leftbubblecolor",                 utils.prefs.getString("conversation_leftbubblecolor", ""));
            params.put("conversation_leftbubbletextcolor",             utils.prefs.getString("conversation_leftbubbletextcolor", ""));
            params.put("conversation_leftbubbledatecolor",             utils.prefs.getString("conversation_leftbubbledatecolor", ""));
            params.put("conversation_customparticipantcolorbool",      utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)));
            params.put("conversation_customparticipantcolor",          utils.prefs.getString("conversation_customparticipantcolor", ""));
            params.put("conversation_custombackcolorbool",             utils.parseBooleanToJson(utils.prefs.getBoolean("conversation_custombackcolorbool", false)));
            params.put("conversation_custombackcolor",                 utils.prefs.getString("conversation_custombackcolor", ""));
            params.put("conversation_style_bubble",                    utils.prefs.getString("conversation_style_bubble", ""));
            params.put("conversation_style_tick",                      utils.prefs.getString("conversation_style_tick", ""));
            params.put("conversation_style_entry",                     utils.prefs.getString("conversation_style_entry", ""));


            // WAMOD

            params.put("theme_wamod_conversation_entry_bgcolor",       utils.prefs.getString("theme_wamod_conversation_entry_bgcolor", ""));
            params.put("theme_wamod_conversation_entry_entrybgcolor",  utils.prefs.getString("theme_wamod_conversation_entry_entrybgcolor", ""));
            params.put("theme_wamod_conversation_entry_hinttextcolor", utils.prefs.getString("theme_wamod_conversation_entry_hinttextcolor", ""));
            params.put("theme_wamod_conversation_entry_textcolor",     utils.prefs.getString("theme_wamod_conversation_entry_textcolor", ""));
            params.put("theme_wamod_conversation_entry_emojibtncolor", utils.prefs.getString("theme_wamod_conversation_entry_emojibtncolor", ""));
            params.put("theme_wamod_conversation_entry_btncolor",      utils.prefs.getString("theme_wamod_conversation_entry_btncolor", ""));
            params.put("theme_wamod_conversation_entry_sendbtncolor",  utils.prefs.getString("theme_wamod_conversation_entry_sendbtncolor", ""));


            // Hangouts

            params.put("theme_hangouts_conversation_entry_bgcolor",    utils.prefs.getString("theme_hangouts_conversation_entry_bgcolor", ""));
            params.put("theme_hangouts_conversation_entry_hintcolor",  utils.prefs.getString("theme_hangouts_conversation_entry_hintcolor", ""));
            params.put("theme_hangouts_conversation_entry_textcolor",  utils.prefs.getString("theme_hangouts_conversation_entry_textcolor", ""));
            params.put("theme_hangouts_conversation_attach_color",     utils.prefs.getString("theme_hangouts_conversation_attach_color", ""));
            params.put("theme_hangouts_conversation_mic_bg",           utils.prefs.getString("theme_hangouts_conversation_mic_bg", ""));
            params.put("theme_hangouts_conversation_mic_color",        utils.prefs.getString("theme_hangouts_conversation_mic_color", ""));
            params.put("theme_hangouts_conversation_send_bg",          utils.prefs.getString("theme_hangouts_conversation_send_bg", ""));
            params.put("theme_hangouts_conversation_send_color",       utils.prefs.getString("theme_hangouts_conversation_send_color", ""));


            // Simple

            params.put("theme_simple_conversation_bgcolor",            utils.prefs.getString("theme_simple_conversation_bgcolor", ""));
            params.put("theme_simple_conversation_entry_hintcolor",    utils.prefs.getString("theme_simple_conversation_entry_hintcolor", ""));
            params.put("theme_simple_conversation_entry_textcolor",    utils.prefs.getString("theme_simple_conversation_entry_textcolor", ""));
            params.put("theme_simple_conversation_mic_color",          utils.prefs.getString("theme_simple_conversation_mic_color", ""));
            params.put("theme_simple_conversation_send_color",         utils.prefs.getString("theme_simple_conversation_send_color", ""));


            // Mood

            params.put("theme_mood_conversation_background_color",     utils.prefs.getString("theme_mood_conversation_background_color", ""));
            params.put("theme_mood_conversation_entry_hintcolor",      utils.prefs.getString("theme_mood_conversation_entry_hintcolor", ""));
            params.put("theme_mood_conversation_entry_textcolor",      utils.prefs.getString("theme_mood_conversation_entry_textcolor", ""));
            params.put("theme_mood_conversation_mic_color",            utils.prefs.getString("theme_mood_conversation_mic_color", ""));
            params.put("theme_mood_conversation_send_color",           utils.prefs.getString("theme_mood_conversation_send_color", ""));
            params.put("theme_mood_conversation_emoji_color",          utils.prefs.getString("theme_mood_conversation_emoji_color", ""));


            // Aran

            params.put("theme_aran_conversation_bgcolor",              utils.prefs.getString("theme_aran_conversation_bgcolor", ""));
            params.put("theme_aran_conversation_entry_bgcolor",        utils.prefs.getString("theme_aran_conversation_entry_bgcolor", ""));
            params.put("theme_aran_conversation_entry_hintcolor",      utils.prefs.getString("theme_aran_conversation_entry_hintcolor", ""));
            params.put("theme_aran_conversation_entry_textcolor",      utils.prefs.getString("theme_aran_conversation_entry_textcolor", ""));
            params.put("theme_aran_conversation_emoji_color",          utils.prefs.getString("theme_aran_conversation_emoji_color", ""));
            params.put("theme_aran_conversation_mic_color",            utils.prefs.getString("theme_aran_conversation_mic_color", ""));
            params.put("theme_aran_conversation_send_color",           utils.prefs.getString("theme_aran_conversation_send_color", ""));

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            internalResponse = utils.readStream(conn.getInputStream());

        } catch (IOException e) {}
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        /*
        Commented for now
l
        if (!official) {
            Toast.makeText(activity, "Please install official WAMOD to use this feature.", Toast.LENGTH_LONG).show();
            return;
        }*/

        Log.i("WAMOD", "Server response: " + internalResponse);
        progress.dismiss();

        try {
            JSONObject jObj = new JSONObject(internalResponse);
            String themeID = jObj.getString("theme_id");
            utils.copyToClipboard(themeID);
            Toast.makeText(activity, utils.context.getResources().getString(Resources.string.wamod_qts_upload_success, themeID), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(activity, utils.context.getResources().getString(Resources.string.wamod_error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(activity, "Loading...", "", true);
        try {
            Signature sign = utils.context.getPackageManager().getPackageInfo(utils.context.getPackageName(), PackageManager.GET_SIGNATURES).signatures[0];
            if (sign.hashCode() == -282729318) official = true;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
