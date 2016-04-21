package com.wamod;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

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
        Fabric.with(this, new Crashlytics());
        setContext(getApplicationContext());
        utils.initWAMOD();
    }

    public static Context getContext(){
        return context;
    }
    public static void setContext(Context ctx){
        context = ctx;
        utils.context = ctx;
        utils.prefs = context.getSharedPreferences("wamod", 0);
        utils.edit = utils.prefs.edit();
    }

    public void freezeLastSeen() {
        if (!utils.getPrivacyConfig(0)) {
            String doSomething = "Do something.";
        }
    }
}
