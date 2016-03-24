package com.wamod;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by BrianValente on 3/3/16.
 */
public class ActionPreference extends Preference {
    AppCompatActivity activity;

    public ActionPreference(Context context) {
        super(context);
        activity = (AppCompatActivity) context;
        onCreate();
    }

    public ActionPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (AppCompatActivity) context;
        onCreate();
    }

    public ActionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (AppCompatActivity) context;
        onCreate();
    }

    public ActionPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (AppCompatActivity) context;
        onCreate();
    }

    private void onCreate() {
        switch (getKey()) {
            case "device_id":
                setSummary(utils.getDeviceID());
                break;
        }
    }

    @Override
    protected void onClick() {
        super.onClick();
        AlertDialog.Builder alertDialog;
        switch (getKey()) {
            case "credits":
                alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle("Credits");
                WebView wv = new WebView(activity);
                wv.loadUrl("file:///android_asset/wamod_credits.html");
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                alertDialog.setView(wv);
                alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
            case "conversation_style_entry_config":
                Intent intent = new Intent(app.getContext(), EntryConfigActivity.class);
                activity.startActivity(intent);
                break;
            case "checkforupdates":
                new checkinv2().execute(activity);
                break;
            case "restoredefaults":
                alertDialog  = new AlertDialog.Builder(activity);
                alertDialog.setTitle(activity.getResources().getString(id.restoredefaults));
                alertDialog.setMessage(activity.getResources().getString(id.restoredefaultsprompt));
                alertDialog.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        utils.edit.putInt("wamodversion", 0);
                        utils.edit.apply();
                        utils.initWAMOD();
                        Toast.makeText(activity, activity.getResources().getString(com.wamod.id.donerestartwamod), Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton(activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                alertDialog.show();
                break;
            case "wamodthemes_unlinkdevice":
                alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle(activity.getResources().getString(id.wamod_unlinkdevice_title));
                alertDialog.setMessage(activity.getResources().getString(id.wamod_unlinkdevice_message));
                alertDialog.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        unlinkwamodthemes async = new unlinkwamodthemes();
                        async.activity = activity;
                        async.execute();
                    }
                });
                alertDialog.setNegativeButton(activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                alertDialog.show();
                break;
        }
    }
}
