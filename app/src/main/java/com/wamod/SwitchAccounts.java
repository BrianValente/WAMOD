package com.wamod;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by brianvalente on 5/18/16.
 */
public class SwitchAccounts {
    public static boolean switchReady = false;

    public static boolean switchAccount(final Context ctx) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setTitle(ctx.getResources().getString(Resources.string.wamod_switchaccount_prompt_title));
        builder.setMessage(ctx.getResources().getString(Resources.string.wamod_switchaccount_prompt_message));
        builder.setPositiveButton(ctx.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int dialogID) {
                copyWhatsAppFolderTemporary(ctx, "cache");
                copyWhatsAppFolderTemporary(ctx, "databases");
                copyWhatsAppFolderTemporary(ctx, "files");
                copyWhatsAppFolderTemporary(ctx, "no_backup");
                copyWhatsAppFolderTemporary(ctx, "shared_prefs");
                deleteWhatsAppFolder(ctx, "cache");
                deleteWhatsAppFolder(ctx, "databases");
                deleteWhatsAppFolder(ctx, "files");
                deleteWhatsAppFolder(ctx, "no_backup");
                deleteWhatsAppFolder(ctx, "shared_prefs");
                copyToWhatsAppFolder(ctx, "cache");
                copyToWhatsAppFolder(ctx, "databases");
                copyToWhatsAppFolder(ctx, "files");
                copyToWhatsAppFolder(ctx, "no_backup");
                copyToWhatsAppFolder(ctx, "shared_prefs");
                deleteWhatsAppFolder(ctx, "WAMOD/cache");
                deleteWhatsAppFolder(ctx, "WAMOD/databases");
                deleteWhatsAppFolder(ctx, "WAMOD/files");
                deleteWhatsAppFolder(ctx, "WAMOD/no_backup");
                deleteWhatsAppFolder(ctx, "WAMOD/shared_prefs");
                copyWhatsAppFolderFromTemp(ctx, "cache");
                copyWhatsAppFolderFromTemp(ctx, "databases");
                copyWhatsAppFolderFromTemp(ctx, "files");
                copyWhatsAppFolderFromTemp(ctx, "no_backup");
                copyWhatsAppFolderFromTemp(ctx, "shared_prefs");
                deleteWAMODTempFolder(ctx);
                while (true) if (switchReady) Utils.restartWAMOD(ctx);
            }
        });
        builder.setNegativeButton(ctx.getResources().getString(android.R.string.cancel), null);
        builder.show();
        return true;
    }

    private static void copyWhatsAppFolderFromTemp(Context ctx, String name) {
        switchReady = false;
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File dest = new File(appPath + "/WAMOD/" + name);
            File source = new File(appPath + "/WAMOD_temp/" + name);
            if (!dest.exists()) dest.mkdirs();
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {}
        switchReady = true;
    }

    private static void copyToWhatsAppFolder(Context ctx, String name) {
        switchReady = false;
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File dest = new File(appPath + "/" + name);
            File source = new File(appPath + "/WAMOD/" + name);
            if (!dest.exists()) dest.mkdirs();
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {}
        switchReady = true;
    }

    private static void copyWhatsAppFolderTemporary(Context ctx, String name) {
        switchReady = false;
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File dest = new File(appPath + "/WAMOD_temp/" + name);
            File source = new File(appPath + "/" + name);
            if (!dest.exists()) dest.mkdirs();
            FileUtils.copyDirectory(source, dest);
        } catch (IOException e) {}
        switchReady = true;
    }


    private static void deleteWhatsAppFolder(Context ctx, String name) {
        switchReady = false;
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File source = new File(appPath + "/" + name);
            FileUtils.deleteDirectory(source);
        } catch (IOException e) {}
        switchReady = true;
    }

    private static void deleteWAMODTempFolder(Context ctx) {
        switchReady = false;
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File source = new File(appPath + "/WAMOD_temp");
            FileUtils.deleteDirectory(source);
        } catch (IOException e) {}
        switchReady = true;
    }

    public static String get2ndNumberUserName(Context ctx) {
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File dest = new File(appPath + "/shared_prefs/com.whatsapp_preferences_2nd.xml");
            File source = new File(appPath + "/WAMOD/shared_prefs/com.whatsapp_preferences.xml");
            FileUtils.copyDirectory(source, dest);
            SharedPreferences prefs = ctx.getSharedPreferences("com.whatsapp_preferences_2nd", 0);
            return prefs.getString("push_name", "");
        } catch (IOException e) {}
        return null;
    }

    public static String get2ndNumberUserPhoneNumber(Context ctx) {
        try {
            String appPath = Utils.getApplicationPath(ctx);
            File dest = new File(appPath + "/shared_prefs/RegisterPhone_2nd.xml");
            File source = new File(appPath + "/WAMOD/shared_prefs/RegisterPhone.xml");
            FileUtils.copyDirectory(source, dest);
            SharedPreferences prefs = ctx.getSharedPreferences("RegisterPhone_2nd", 0);
            String number = prefs.getString("com.whatsapp.RegisterPhone.input_phone_number", "");
            String country = prefs.getString("com.whatsapp.RegisterPhone.country_code", "");
            String entireNumber = "+" + country + " " + number;
            return entireNumber;
        } catch (IOException e) {}
        return null;
    }

    public static Drawable get2ndNumberUserPicture(Context ctx) {
        String s = Utils.getApplicationPath(ctx);
        String pathName = s + "/WAMOD/files/me.jpg";
        Drawable d = Drawable.createFromPath(pathName);
        return d;
    }
}
