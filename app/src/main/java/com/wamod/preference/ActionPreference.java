package com.wamod.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import com.wamod.AccountsManager;
import com.wamod.App;
import com.wamod.Resources;
import com.wamod.Utils;
import com.wamod.entry.ConfigurationActivity;
import com.wamod.themes.CheckIn;
import com.wamod.themes.QTS.Download;
import com.wamod.themes.QTS.Upload;
import com.wamod.themes.UnlinkWAMODThemes;

import java.util.ArrayList;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ActionPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (AppCompatActivity) context;
        onCreate();
    }

    private void onCreate() {
        switch (getKey()) {
            case "device_id":
                setSummary(Utils.getDeviceID());
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
                alertDialog.setNegativeButton(activity.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
            case "conversation_style_entry_config":
                Intent intent = new Intent(App.getContext(), ConfigurationActivity.class);
                activity.startActivity(intent);
                break;
            case "checkforupdates":
                new CheckIn().execute(activity);
                break;
            case "restoredefaults":
                alertDialog = new AlertDialog.Builder(activity);
                alertDialog.setTitle(activity.getResources().getString(Resources.string.wamod_settings_miscellaneous_app_restoredefaults_title));
                alertDialog.setMessage(activity.getResources().getString(Resources.string.wamod_settings_miscellaneous_app_restoredefaults_message));
                alertDialog.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utils.edit.putInt("wamodversion", 0);
                        Utils.edit.apply();
                        Utils.initWAMOD();
                        Toast.makeText(activity, activity.getResources().getString(Resources.string.wamod_restartwamod), Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton(activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
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
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                alertDialog.show();
                break;
            case "wamodthemes_qts_upload":
                if (!Utils.isOfficialWAMOD()) {
                    AlertDialog.Builder unofficial = new AlertDialog.Builder(activity);
                    unofficial.setMessage(activity.getResources().getString(Resources.getString("wamod_qts_upload_unofficial_message")));
                    unofficial.show();
                    return;
                }

                AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
                builder2.setTitle(Utils.context.getResources().getString(Resources.string.wamod_qts_upload));
                builder2.setMessage(Utils.context.getResources().getString(Resources.string.wamod_qts_upload_prompt));
                builder2.setPositiveButton(activity.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Upload qtsUpload = new Upload();
                        Upload.activity = activity;
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
                builder.setTitle(Utils.context.getResources().getString(Resources.string.wamod_qts_download));

                final EditText input = new EditText(activity);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setHint(Utils.context.getResources().getString(Resources.string.wamod_qts_download_themeid));
                builder.setView(input);

                builder.setPositiveButton(activity.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Download qtsDownload = new Download();
                        Download.activity = activity;
                        Download.themeid = input.getText().toString();
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
            case "wamod_privacy_restorecustomprivacy":
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
                dialog.setTitle(activity.getResources().getString(Resources.getString("wamod_settings_privacy_restorecustomprivacy")));
                dialog.setMessage(activity.getResources().getString(Resources.getString("wamod_settings_privacy_restorecustomprivacy_message")));
                dialog.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences privacyPrefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
                        SharedPreferences.Editor privacyPrefs_Edit = privacyPrefs.edit();
                        boolean general_freezelastseen = privacyPrefs.getBoolean("general_freezelastseen", false);
                        boolean general_alwaysonline = privacyPrefs.getBoolean("general_alwaysonline", false);
                        boolean general_reportreceived = privacyPrefs.getBoolean("general_reportreceived", true);
                        boolean general_reportread = privacyPrefs.getBoolean("general_reportread", true);
                        boolean general_reporttyping = privacyPrefs.getBoolean("general_reporttyping", true);
                        privacyPrefs_Edit.clear();
                        privacyPrefs_Edit.putBoolean("general_freezelastseen", general_freezelastseen);
                        privacyPrefs_Edit.putBoolean("general_alwaysonline", general_alwaysonline);
                        privacyPrefs_Edit.putBoolean("general_reportreceived", general_reportreceived);
                        privacyPrefs_Edit.putBoolean("general_reportread", general_reportread);
                        privacyPrefs_Edit.putBoolean("general_reporttyping", general_reporttyping);
                        privacyPrefs_Edit.apply();
                    }
                });
                dialog.setNegativeButton(activity.getResources().getString(android.R.string.no), null);
                Utils.tintAndShowDialog(dialog);
                break;
            case "wamod_privacy_restoredefaultprivacy":
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(activity);
                dialog2.setTitle(activity.getResources().getString(Resources.getString("wamod_settings_privacy_restoredefaultprivacy")));
                dialog2.setMessage(activity.getResources().getString(Resources.getString("wamod_settings_privacy_restoredefaultprivacy_message")));
                dialog2.setPositiveButton(activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences privacyPrefs2 = Utils.context.getSharedPreferences("wamod_privacy", 0);
                        SharedPreferences.Editor privacyPrefs_Edit2 = privacyPrefs2.edit();
                        privacyPrefs_Edit2.clear();
                        privacyPrefs_Edit2.putBoolean("general_freezelastseen", false);
                        privacyPrefs_Edit2.putBoolean("general_alwaysonline", false);
                        privacyPrefs_Edit2.putBoolean("general_reportreceived", true);
                        privacyPrefs_Edit2.putBoolean("general_reportread", true);
                        privacyPrefs_Edit2.putBoolean("general_reporttyping", true);
                        privacyPrefs_Edit2.apply();
                    }
                });
                dialog2.setNegativeButton(activity.getResources().getString(android.R.string.no), null);
                Utils.tintAndShowDialog(dialog2);
                break;
            case "debugging_getfirebasetoken":
                String token = com.google.firebase.iid.FirebaseInstanceId.getInstance().getToken();
                Utils.copyToClipboard(token);
                Toast.makeText(activity, "Token copied to clipboard: " + token, Toast.LENGTH_LONG).show();
                break;
            case "debugging_switchaccount0":
                AccountsManager accountsManager = App.getAccountsManager();
                ArrayList<AccountsManager.Account> accounts = accountsManager.getAccounts();
                App.getAccountsManager().switchToAccount(accounts.get(0));
                break;
            case "debugging_showaccounts":
                App.getAccountsManager().showAccountsList(activity);
                break;
        }
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        com.wamod.preference.Preference.loadColors(view);
    }
}