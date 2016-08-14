package com.wamod;

import android.app.Application;
import android.content.Context;

/**
 * Created by brianvalente on 7/8/15.
 */
public class App extends Application {
    private static Context context;


    public static Context getContext() {
        return context;
    }


    /* Called on
     *    com.facebook.buck.android.support.exopackage.ExopackageApplication.onCreate
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
    }
}
