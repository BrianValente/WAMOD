package com.wamod.themes;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wamod.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by brianvalente on 12/19/15.
 */
public class QTSDownload extends AsyncTask<Void, Void, Void> {
    String internalResponse = "";
    public static AppCompatActivity activity;
    ProgressDialog progress;
    public static String themeid = "1";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://wamod.ml/api/api.php");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("action", "qts_download");
            params.put("theme_id", themeid);

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
        Log.i("WAMOD", "Server response: " + internalResponse);

        try {
            JSONObject jObj = new JSONObject(internalResponse);


            // General colors

            putString(jObj, "general_statusbarcolor");
            putString(jObj, "general_toolbarcolor");
            putString(jObj, "general_toolbartextcolor");
            putString(jObj, "general_toolbarforeground");
            putString(jObj, "general_statusbarcolor");
            putString(jObj, "general_statusbarcolor");
            putString(jObj, "general_navbarcolor");

            putBoolean(jObj, "general_darkstatusbaricons");
            putBoolean(jObj, "nightmode_enable");
            putBoolean(jObj, "nightmode_atnightonly");


            // Home

            putString(jObj, "home_theme");
            putString(jObj, "home_tabsindicatorcolor");
            putBoolean(jObj, "home_drawer_dark");
            putBoolean(jObj, "home_drawer_blackheadertext");
            putBoolean(jObj, "home_drawer_showsecondaccount");
            putBoolean(jObj, "home_bottomnavigationbar_autocolor");
            putString(jObj, "home_bottomnavigationbar_colors_bg");
            putString(jObj, "home_bottomnavigationbar_colors_activeitem");
            putString(jObj, "home_bottomnavigationbar_colors_inactiveitem");


            // Conversation

            putString(jObj, "conversation_style_toolbar");
            putBoolean(jObj, "conversation_hideprofilephoto");
            putBoolean(jObj, "conversation_hidetoolbarattach");
            putBoolean(jObj, "conversation_toolbarexit");
            putString(jObj, "conversation_rightbubblecolor");
            putString(jObj, "conversation_rightbubbletextcolor");
            putString(jObj, "conversation_rightbubbledatecolor");
            putString(jObj, "conversation_leftbubblecolor");
            putString(jObj, "conversation_leftbubbletextcolor");
            putString(jObj, "conversation_leftbubbledatecolor");
            putBoolean(jObj, "conversation_customparticipantcolorbool");
            putString(jObj, "conversation_customparticipantcolor");
            putBoolean(jObj, "conversation_custombackcolorbool");
            putString(jObj, "conversation_custombackcolor");
            putString(jObj, "conversation_style_bubble");
            putString(jObj, "conversation_style_tick");
            putString(jObj, "conversation_style_entry");


            // WAMOD

            putString(jObj, "theme_wamod_conversation_entry_bgcolor");
            putString(jObj, "theme_wamod_conversation_entry_entrybgcolor");
            putString(jObj, "theme_wamod_conversation_entry_hinttextcolor");
            putString(jObj, "theme_wamod_conversation_entry_textcolor");
            putString(jObj, "theme_wamod_conversation_entry_emojibtncolor");
            putString(jObj, "theme_wamod_conversation_entry_btncolor");
            putString(jObj, "theme_wamod_conversation_entry_sendbtncolor");


            // Hangouts

            putString(jObj, "theme_hangouts_conversation_entry_bgcolor");
            putString(jObj, "theme_hangouts_conversation_entry_hintcolor");
            putString(jObj, "theme_hangouts_conversation_entry_textcolor");
            putString(jObj, "theme_hangouts_conversation_attach_color");
            putString(jObj, "theme_hangouts_conversation_mic_bg");
            putString(jObj, "theme_hangouts_conversation_mic_color");
            putString(jObj, "theme_hangouts_conversation_send_bg");
            putString(jObj, "theme_hangouts_conversation_send_color");


            // Simple

            putString(jObj, "theme_simple_conversation_bgcolor");
            putString(jObj, "theme_simple_conversation_entry_hintcolor");
            putString(jObj, "theme_simple_conversation_entry_textcolor");
            putString(jObj, "theme_simple_conversation_mic_color");
            putString(jObj, "theme_simple_conversation_send_color");


            // Mood

            putString(jObj, "theme_mood_conversation_background_color");
            putString(jObj, "theme_mood_conversation_entry_hintcolor");
            putString(jObj, "theme_mood_conversation_entry_textcolor");
            putString(jObj, "theme_mood_conversation_mic_color");
            putString(jObj, "theme_mood_conversation_send_color");
            putString(jObj, "theme_mood_conversation_emoji_color");


            // Aran

            putString(jObj, "theme_aran_conversation_bgcolor");
            putString(jObj, "theme_aran_conversation_entry_bgcolor");
            putString(jObj, "theme_aran_conversation_entry_hintcolor");
            putString(jObj, "theme_aran_conversation_entry_textcolor");
            putString(jObj, "theme_aran_conversation_emoji_color");
            putString(jObj, "theme_aran_conversation_mic_color");
            putString(jObj, "theme_aran_conversation_send_color");

            if (utils.edit.commit()) utils.restartWAMOD(utils.context);
        } catch (JSONException e) {
            Toast.makeText(activity, "There was an error, sorry about that :(", Toast.LENGTH_LONG).show();
        }

        progress.dismiss();
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(activity, "Loading...", "", true);
    }

    private void putString(JSONObject jObj, String s) {
        try {
            utils.edit.putString(s, jObj.getString(s));
        } catch (JSONException e) {}
    }

    private void putBoolean(JSONObject jObj, String s) {
        try {
            utils.edit.putBoolean(s, utils.parseJsonBoolean(jObj.getString(s)));
        } catch (JSONException e) {}
    }
}
