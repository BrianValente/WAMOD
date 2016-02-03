package com.wamod;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brianvalente on 7/8/15.
 */
public class app extends Application {
    private static Context context;
    public static String time = "";
    //public static List<chat> openedChats = new ArrayList<chat>();

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());
        utils.initWAMOD();
    }

    public static Context getContext(){
        return context;
    }
    public static void setContext(Context ctx){
        context = ctx;
        utils.prefs = context.getSharedPreferences("wamod", 0);
        utils.edit = utils.prefs.edit();
    }

    public void freezeLastSeen() {
        if (!utils.getPrivacyConfig(0)) {
            String doSomething = "Do something.";
        }
    }
}
