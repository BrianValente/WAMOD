package com.wamod.themes;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.wamod.Resources;
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
public class UnlinkWAMODThemes extends AsyncTask<Void, Void, Void> {
    String internalResponse = "";
    public AppCompatActivity activity;
    ProgressDialog dialog;

    @Override
    protected Void doInBackground(Void... paramss) {
        // Get phone info
        String deviceID = utils.getDeviceID();

        try {
            URL url = new URL("http://wamod.ml/api/api.php");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("action", "unlinkdevice");
            params.put("deviceid", deviceID);

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
        //dialog.hide();
        try {
            JSONObject json = new JSONObject(internalResponse);
            String message;
            if (!json.getString("status").contentEquals("done")) {
                message = activity.getResources().getString(Resources.string.wamod_error);
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(activity, activity.getResources().getString(Resources.string.wamod_error), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        //dialog = ProgressDialog.show(activity, "", activity.getResources().getString(id.wamod_loading), true);
    }
}
