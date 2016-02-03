package com.wamod;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by brianvalente on 9/18/15.
 */
public class checkforupdates extends AsyncTask<AppCompatActivity, Void, AppCompatActivity> {
    String internalResponse = "";
    update update = null;

    @Override
    protected AppCompatActivity doInBackground(AppCompatActivity... activity) {
        try {
            URL url = new URL("http://brianvalente.tk/wamod/checklatestversion.php");
            Map<String, Object> params = new LinkedHashMap<>();
            String latestversion = null;
            String changelog = null;
            URL link;

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

            JSONObject jObj = new JSONObject(internalResponse);
            latestversion = jObj.getString("version");
            link = new URL(jObj.getString("link"));
            changelog = jObj.getString("changelog");

            update = new update(latestversion, link, changelog);

        } catch (IOException e) {}
        catch (JSONException e) {}

        return activity[0];
    }

    @Override
    protected void onPostExecute(AppCompatActivity activity) {
        boolean canbeignored = true;
        //if (activity.getSupportActionBar().getTitle().equals(activity.getResources().getString(id.wamodsettings))) canbeignored = false;
        if (activity instanceof Settings) canbeignored = false;
        updateDialog(update, canbeignored, activity);
    }

    @Override
    protected void onPreExecute() {}

    public static void updateDialog(final update update, boolean canBeIgnored, AppCompatActivity activity) {
        if (update != null) {
            if (!update.ver.contentEquals(utils.wamodversion)) {
                final AppCompatActivity finalActivity = activity;

                AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

                alertDialog.setTitle(activity.getResources().getString(id.updateavailable));
                String message = activity.getResources().getString(id.updateavailablemessage, update.ver, utils.wamodversion);
                if (update.changelog != null && !update.changelog.contentEquals("null")) message += " " + update.changelog;
                alertDialog.setMessage(message);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, activity.getResources().getString(id.download), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(update.lnk.toString()));
                        finalActivity.startActivity(browserIntent);
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, activity.getResources().getString(id.later), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //...
                    }
                });

                if (canBeIgnored) {
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, activity.getResources().getString(id.ignorethisupdate), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            utils.edit.putString("ignoreupdate", update.ver);
                            utils.edit.apply();
                        }
                    });
                }

                alertDialog.show();
            } else if (update.ver.contentEquals(utils.wamodversion) && !canBeIgnored) Toast.makeText(activity, activity.getResources().getString(id.uptodate), Toast.LENGTH_LONG).show();
        } else if (!canBeIgnored) {
            Toast.makeText(activity, activity.getResources().getString(id.errorcontactingserver), Toast.LENGTH_LONG).show();
        }
    }

    public static class update {
        String ver;
        URL lnk;
        String changelog;
        update(String version, URL link, String changes) {
            ver = version;
            lnk = link;
            changelog = changes;
        }
    }
}