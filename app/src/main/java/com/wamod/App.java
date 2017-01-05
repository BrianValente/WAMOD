package com.wamod;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.whatsapp.AppShell;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by brianvalente on 7/8/15.
 */
public class App {
    private static Context context;
    private static AccountsManager  accountsManager;
    public static  ShortcutsManager shortcutsManager;
    public static  ContactsManager  contactsManager;

    public static Map<String, Drawable> cachedProfilePictures = new LinkedHashMap<>();
    public static Context getContext() {
        return context;
    }


    /* Called on
     *    com.c.a.a.a.a.c.onCreate()V
     * Before
     *    return-void
     * Smali
     *    invoke-static {p0}, Lcom/wamod/App;->setContext(Landroid/content/Context;)V
     */
    public static void setContext(Context ctx){
        context = ctx;
        Utils.context = ctx;
        Utils.prefs = context.getSharedPreferences("wamod", 0);
        Utils.edit = Utils.prefs.edit();
        accountsManager  = new AccountsManager(context);
        shortcutsManager = ShortcutsManager.getShortcutsManager(context);
        contactsManager  = new ContactsManager();

        if (shortcutsManager != null)
            shortcutsManager.loadShortcuts();

        Utils.initWAMOD();
    }

    public static AccountsManager getAccountsManager() {
        return accountsManager;
    }
    public static ContactsManager getContactsManager() {
        return contactsManager;
    }
}
