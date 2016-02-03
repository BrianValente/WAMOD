package com.wamod;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by brianvalente on 9/15/15.
 */
public class checkin extends AsyncTask<AppCompatActivity, Void, AppCompatActivity> {
    String internalResponse = "";

    @Override
    protected AppCompatActivity doInBackground(AppCompatActivity... activity) {
        String SetServerString = "";
        String address = ((WifiManager) app.getContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();

        TimeZone tz = TimeZone.getTimeZone("GMT-03:00");
        Calendar c = Calendar.getInstance(tz);
        String time = String.format("%02d", c.get(Calendar.HOUR_OF_DAY))+":"+
                String.format("%02d", c.get(Calendar.MINUTE));
        String date = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
        date = date + " " + time;

        String androidversion = Build.VERSION.RELEASE;

        String device = Build.MODEL;

        String wamodversion = utils.wamodversion;

        String ip = "";

        try {
            URL url = new URL("http://brianvalente.tk/wamod/checkin.php");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("mac", address);
            params.put("ip", ip);
            params.put("date", date);
            params.put("androidversion", androidversion);
            params.put("api", Build.VERSION.SDK_INT);
            params.put("device", device);
            params.put("wamodversion", wamodversion);

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
        return activity[0];
    }

    @Override
    protected void onPostExecute(final AppCompatActivity activity) {
        if (!internalResponse.contentEquals("")) {
            try {
                JSONObject jObj = new JSONObject(internalResponse);

                utils.edit.putString("general_statusbarcolor", jObj.getString("general_statusbarcolor"));
                utils.edit.putString("general_toolbarcolor", jObj.getString("general_toolbarcolor"));
                utils.edit.putString("general_toolbarforeground", jObj.getString("general_toolbarforeground"));
                utils.edit.putString("general_navbarcolor", jObj.getString("general_navbarcolor"));
                utils.edit.putBoolean("general_darkmode", (jObj.getString("general_darkmode").contentEquals("1")));
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

                utils.edit.apply();

                //Toast.makeText(activity, "Theme loaded, restart WAMOD", Toast.LENGTH_LONG).show();
                if (utils.edit.commit()) {
                    PendingIntent intent = PendingIntent.getActivity(activity.getBaseContext(), 0, new Intent(activity.getIntent()), PendingIntent.FLAG_ONE_SHOT);
                    AlarmManager manager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
                    manager.set(AlarmManager.RTC, System.currentTimeMillis() + 0, intent);
                    System.exit(2);
                } else {
                    Toast.makeText(activity, "There was an error loading your theme", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(activity, "There was an error loading your theme", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPreExecute() {

    }
}
