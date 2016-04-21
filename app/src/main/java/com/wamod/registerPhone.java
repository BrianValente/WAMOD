package com.wamod;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;

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
public class registerPhone extends AsyncTask<String, Void, String> {
    String internalResponse = "";

    @Override
    protected String doInBackground(String... string) {
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

        String wamodversion = utils.wamodVersionName;

        String ip = "";

        try {
            URL url = new URL("http://brianvalente.tk/wamod/register.php");
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
        return internalResponse;
    }

    @Override
    protected void onPostExecute(String result) {
        /*if (internalResponse.contentEquals("true")) {
            utils.edit.putBoolean("registered", true);
            utils.edit.apply();
        }*/
    }

    @Override
    protected void onPreExecute() {

    }
}
