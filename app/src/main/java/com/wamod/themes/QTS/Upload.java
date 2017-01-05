package com.wamod.themes.QTS;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.wamod.Resources;
import com.wamod.Utils;
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
            params.put("general_statusbarcolor",                       Utils.prefs.getString("general_statusbarcolor", ""));
            params.put("general_toolbarcolor",                         Utils.prefs.getString("general_toolbarcolor", ""));
            params.put("general_toolbartextcolor",                     Utils.prefs.getString("general_toolbartextcolor", ""));
            params.put("general_toolbarforeground",                    Utils.prefs.getString("general_toolbarforeground", ""));
            params.put("general_navbarcolor",                          Utils.prefs.getString("general_navbarcolor", ""));
            params.put("general_darkstatusbaricons",                   Utils.parseBooleanToJson(Utils.prefs.getBoolean("general_darkstatusbaricons", false)));

            params.put("nightmode_enable",                             Utils.parseBooleanToJson(Utils.prefs.getBoolean("nightmode_enable", false)));
            params.put("nightmode_atnightonly",                        Utils.parseBooleanToJson(Utils.prefs.getBoolean("nightmode_atnightonly", false)));


            // Home

            params.put("home_theme",                                   Utils.prefs.getString("home_theme", ""));
            params.put("home_drawer_header_style",                     Utils.prefs.getString("home_drawer_header_style", ""));
            params.put("home_tabsindicatorcolor",                      Utils.prefs.getString("home_tabsindicatorcolor", ""));
            params.put("home_drawer_dark",                             Utils.parseBooleanToJson(Utils.prefs.getBoolean("home_drawer_dark", false)));
            params.put("home_drawer_blackheadertext",                  Utils.parseBooleanToJson(Utils.prefs.getBoolean("home_drawer_blackheadertext", false)));
            params.put("home_drawer_showsecondaccount",                Utils.parseBooleanToJson(Utils.prefs.getBoolean("home_drawer_showsecondaccount", false)));

            params.put("home_bottomnavigationbar_autocolor",           Utils.parseBooleanToJson(Utils.prefs.getBoolean("home_bottomnavigationbar_autocolor", false)));
            params.put("home_bottomnavigationbar_colors_bg",           Utils.prefs.getString("home_bottomnavigationbar_colors_bg", ""));
            params.put("home_bottomnavigationbar_colors_activeitem",   Utils.prefs.getString("home_bottomnavigationbar_colors_activeitem", ""));
            params.put("home_bottomnavigationbar_colors_inactiveitem", Utils.prefs.getString("home_bottomnavigationbar_colors_inactiveitem", ""));


            // Conversation

            params.put("conversation_style_toolbar",                   Utils.prefs.getString("conversation_style_toolbar", ""));
            params.put("conversation_hideprofilephoto",                Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_hideprofilephoto", false)));
            params.put("conversation_hidetoolbarattach",               Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_hidetoolbarattach", false)));
            params.put("conversation_toolbarexit",                     Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_toolbarexit", false)));
            params.put("conversation_rightbubblecolor",                Utils.prefs.getString("conversation_rightbubblecolor", ""));
            params.put("conversation_rightbubbletextcolor",            Utils.prefs.getString("conversation_rightbubbletextcolor", ""));
            params.put("conversation_rightbubbledatecolor",            Utils.prefs.getString("conversation_rightbubbledatecolor", ""));
            params.put("conversation_leftbubblecolor",                 Utils.prefs.getString("conversation_leftbubblecolor", ""));
            params.put("conversation_leftbubbletextcolor",             Utils.prefs.getString("conversation_leftbubbletextcolor", ""));
            params.put("conversation_leftbubbledatecolor",             Utils.prefs.getString("conversation_leftbubbledatecolor", ""));
            params.put("conversation_customparticipantcolorbool",      Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)));
            params.put("conversation_customparticipantcolor",          Utils.prefs.getString("conversation_customparticipantcolor", ""));
            params.put("conversation_custombackcolorbool",             Utils.parseBooleanToJson(Utils.prefs.getBoolean("conversation_custombackcolorbool", false)));
            params.put("conversation_custombackcolor",                 Utils.prefs.getString("conversation_custombackcolor", ""));
            params.put("conversation_style_bubble",                    Utils.prefs.getString("conversation_style_bubble", ""));
            params.put("conversation_style_tick",                      Utils.prefs.getString("conversation_style_tick", ""));
            params.put("conversation_style_entry",                     Utils.prefs.getString("conversation_style_entry", ""));


            // Advanced
            params.put("nightmode_primarytextcolor",                   Utils.prefs.getString("nightmode_primarytextcolor", ""));
            params.put("nightmode_secondarytextcolor",                 Utils.prefs.getString("nightmode_secondarytextcolor", ""));
            params.put("nightmode_backgroundcolor",                    Utils.prefs.getString("nightmode_backgroundcolor", ""));
            params.put("nightmode_cardsbackgroundcolor",               Utils.prefs.getString("nightmode_cardsbackgroundcolor", ""));
            params.put("drawer_light_background",                      Utils.prefs.getString("drawer_light_background", ""));
            params.put("drawer_dark_background",                       Utils.prefs.getString("drawer_dark_background", ""));

            // WAMOD

            params.put("theme_wamod_conversation_entry_bgcolor",       Utils.prefs.getString("theme_wamod_conversation_entry_bgcolor", ""));
            params.put("theme_wamod_conversation_entry_entrybgcolor",  Utils.prefs.getString("theme_wamod_conversation_entry_entrybgcolor", ""));
            params.put("theme_wamod_conversation_entry_hinttextcolor", Utils.prefs.getString("theme_wamod_conversation_entry_hinttextcolor", ""));
            params.put("theme_wamod_conversation_entry_textcolor",     Utils.prefs.getString("theme_wamod_conversation_entry_textcolor", ""));
            params.put("theme_wamod_conversation_entry_emojibtncolor", Utils.prefs.getString("theme_wamod_conversation_entry_emojibtncolor", ""));
            params.put("theme_wamod_conversation_entry_btncolor",      Utils.prefs.getString("theme_wamod_conversation_entry_btncolor", ""));
            params.put("theme_wamod_conversation_entry_sendbtncolor",  Utils.prefs.getString("theme_wamod_conversation_entry_sendbtncolor", ""));


            // Hangouts

            params.put("theme_hangouts_conversation_entry_bgcolor",    Utils.prefs.getString("theme_hangouts_conversation_entry_bgcolor", ""));
            params.put("theme_hangouts_conversation_entry_hintcolor",  Utils.prefs.getString("theme_hangouts_conversation_entry_hintcolor", ""));
            params.put("theme_hangouts_conversation_entry_textcolor",  Utils.prefs.getString("theme_hangouts_conversation_entry_textcolor", ""));
            params.put("theme_hangouts_conversation_attach_color",     Utils.prefs.getString("theme_hangouts_conversation_attach_color", ""));
            params.put("theme_hangouts_conversation_mic_bg",           Utils.prefs.getString("theme_hangouts_conversation_mic_bg", ""));
            params.put("theme_hangouts_conversation_mic_color",        Utils.prefs.getString("theme_hangouts_conversation_mic_color", ""));
            params.put("theme_hangouts_conversation_send_bg",          Utils.prefs.getString("theme_hangouts_conversation_send_bg", ""));
            params.put("theme_hangouts_conversation_send_color",       Utils.prefs.getString("theme_hangouts_conversation_send_color", ""));


            // Simple

            params.put("theme_simple_conversation_bgcolor",            Utils.prefs.getString("theme_simple_conversation_bgcolor", ""));
            params.put("theme_simple_conversation_entry_hintcolor",    Utils.prefs.getString("theme_simple_conversation_entry_hintcolor", ""));
            params.put("theme_simple_conversation_entry_textcolor",    Utils.prefs.getString("theme_simple_conversation_entry_textcolor", ""));
            params.put("theme_simple_conversation_mic_color",          Utils.prefs.getString("theme_simple_conversation_mic_color", ""));
            params.put("theme_simple_conversation_send_color",         Utils.prefs.getString("theme_simple_conversation_send_color", ""));


            // Mood

            params.put("theme_mood_conversation_background_color",     Utils.prefs.getString("theme_mood_conversation_background_color", ""));
            params.put("theme_mood_conversation_entry_hintcolor",      Utils.prefs.getString("theme_mood_conversation_entry_hintcolor", ""));
            params.put("theme_mood_conversation_entry_textcolor",      Utils.prefs.getString("theme_mood_conversation_entry_textcolor", ""));
            params.put("theme_mood_conversation_mic_color",            Utils.prefs.getString("theme_mood_conversation_mic_color", ""));
            params.put("theme_mood_conversation_send_color",           Utils.prefs.getString("theme_mood_conversation_send_color", ""));
            params.put("theme_mood_conversation_emoji_color",          Utils.prefs.getString("theme_mood_conversation_emoji_color", ""));


            // Aran

            params.put("theme_aran_conversation_bgcolor",              Utils.prefs.getString("theme_aran_conversation_bgcolor", ""));
            params.put("theme_aran_conversation_entry_bgcolor",        Utils.prefs.getString("theme_aran_conversation_entry_bgcolor", ""));
            params.put("theme_aran_conversation_entry_hintcolor",      Utils.prefs.getString("theme_aran_conversation_entry_hintcolor", ""));
            params.put("theme_aran_conversation_entry_textcolor",      Utils.prefs.getString("theme_aran_conversation_entry_textcolor", ""));
            params.put("theme_aran_conversation_emoji_color",          Utils.prefs.getString("theme_aran_conversation_emoji_color", ""));
            params.put("theme_aran_conversation_mic_color",            Utils.prefs.getString("theme_aran_conversation_mic_color", ""));
            params.put("theme_aran_conversation_send_color",           Utils.prefs.getString("theme_aran_conversation_send_color", ""));

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
            internalResponse = Utils.readStream(conn.getInputStream());

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
            Utils.copyToClipboard(themeID);
            Toast.makeText(activity, Utils.context.getResources().getString(Resources.string.wamod_qts_upload_success, themeID), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(activity, Utils.context.getResources().getString(Resources.string.wamod_error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(activity, activity.getResources().getString(Resources.getString("wamod_loading")), "", true);
        try {
            Signature sign = Utils.context.getPackageManager().getPackageInfo(Utils.context.getPackageName(), PackageManager.GET_SIGNATURES).signatures[0];
            if (sign.hashCode() == -282729318) official = true;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
