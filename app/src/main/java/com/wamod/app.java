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
	public static String WaToMD(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp").replace("com.whatsapp.util", "com.whatsapp.util").replace("com.whatsapp.Voip", "com.whatsapp.Voip").replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter").replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer").replace("com.whatsapp.proto", "com.whatsapp.proto").replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder");
    }

    public void freezeLastSeen() {
        if (!utils.getPrivacyConfig(0)) {
            String doSomething = "Do something.";
        }
    }
}
