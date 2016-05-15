package com.wamod;

import android.app.Application;
import android.content.Context;

/**
 * Created by brianvalente on 7/8/15.
 */
public class App extends Application {
    private static Context context;
    public static String time = "";
    //public static List<chat> openedChats = new ArrayList<chat>();

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.initWAMOD();
    }

    public static Context getContext(){
        return context;
    }
    public static void setContext(Context ctx){
        context = ctx;
        Utils.context = ctx;
        Utils.prefs = context.getSharedPreferences("wamod", 0);
        Utils.edit = Utils.prefs.edit();
    }
}
