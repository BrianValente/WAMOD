package com.wamod.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wamod.Resources;
import com.wamod.app;
import com.wamod.themes.CheckIn;
import com.wamod.themes.UnlinkWAMODThemes;
import com.wamod.entry.ConfigurationActivity;
import com.wamod.themes.QTS.Download;
import com.wamod.themes.QTS.Upload;
import com.wamod.utils;

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
                Intent intent = new Intent(app.getContext(), ConfigurationActivity.class);
                activity.startActivity(intent);
                break;
            case "checkforupdates":
                new CheckIn().execute(activity);
                break;
            case "restoredefaults":
                alertDialog  = new AlertDialog.Builder(activity);
                alertDialog.setTitle(activity.getResources().getString(Resources.string.wamod_settings_miscellaneous_app_restoredefaults_title));
                alertDialog.setMessage(activity.getResources().getString(Resources.string.wamod_settings_miscellaneous_app_restoredefaults_message));
                alertDialog.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        utils.edit.putInt("wamodversion", 0);
                        utils.edit.apply();
                        utils.initWAMOD();
                        Toast.makeText(activity, activity.getResources().getString(Resources.string.wamod_restartwamod), Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton(activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                alertDialog.show();
                break;
            case "wamodthemes_unlinkdevice":
                alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle(activity.getResources().getString(Resources.string.wamod_settings_wamodthemes_unlink_title));
                alertDialog.setMessage(activity.getResources().getString(Resources.string.wamod_settings_wamodthemes_unlink_message));
                alertDialog.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        UnlinkWAMODThemes async = new UnlinkWAMODThemes();
                        async.activity = activity;
                        async.execute();
                    }
                });
                alertDialog.setNegativeButton(activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                alertDialog.show();
                break;
            case "wamodthemes_qts_upload":
                AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
                builder2.setTitle(utils.context.getResources().getString(Resources.string.wamod_qts_upload));
                builder2.setMessage(utils.context.getResources().getString(Resources.string.wamod_qts_upload_prompt));
                builder2.setPositiveButton(activity.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Upload qtsUpload = new Upload();
                        qtsUpload.activity = activity;
                        qtsUpload.execute();
                    }
                });
                builder2.setNegativeButton(activity.getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder2.show();
                break;
            case "wamodthemes_qts_download":
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(utils.context.getResources().getString(Resources.string.wamod_qts_download));

                final EditText input = new EditText(activity);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setHint(utils.context.getResources().getString(Resources.string.wamod_qts_download_themeid));
                builder.setView(input);

                builder.setPositiveButton(activity.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Download qtsDownload = new Download();
                        qtsDownload.activity = activity;
                        qtsDownload.themeid = input.getText().toString();
                        qtsDownload.execute();
                    }
                });
                builder.setNegativeButton(activity.getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        if (utils.nightModeShouldRun()) {
            TextView title = (TextView) view.findViewById(android.R.id.title);
            if (title != null) title.setTextColor(utils.getDarkColor(0));

            TextView summary = (TextView) view.findViewById(android.R.id.summary);
            if (summary != null) summary.setTextColor(utils.getDarkColor(1));
        }
    }
}
