package com.wamod;

import android.app.Activity;
import android.content.Context;

/**
 * Created by brianvalente on 9/18/15.
 */
public class Chat {
    public String name;
    Context context;
    public Activity activity;
    public Chat(String nme, Activity act) {
        name = nme;
        //ctx = context;
        activity = act;
    }
}
