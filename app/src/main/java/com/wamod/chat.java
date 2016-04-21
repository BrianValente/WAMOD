package com.wamod;

import android.app.Activity;
import android.content.Context;

/**
 * Created by brianvalente on 9/18/15.
 */
public class chat {
    String name;
    Context context;
    Activity activity;
    public chat(String nme, Activity act) {
        name = nme;
        //ctx = context;
        activity = act;
    }
}
