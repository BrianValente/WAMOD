package com.wamod;

import android.util.Log;

import com.whatsapp.protocol.l;

/**
 * Created by brianvalente on 5/8/16.
 */
public class Privacy {
    public static boolean blueTick(com.whatsapp.protocol.q q, com.whatsapp.protocol.p p, String string, String[] strings) {
        /*if (string == null) return null;
        if (string.contentEquals("read") && utils.prefs.getBoolean("privacy_noBlueTick", false)) return null;
        else return string*/;
        /*Log.i("WAMOD_PRIVACY", "P: Message ID: " + p.a + " ---- JID: " + p.c + " ---- Boolean: " + (p.b? "true" : "false") + "\n" +
                               "String: " + string + "\n"
                               //"Strings[0]: " + (strings[0] != null? strings[0] : "null")
                                );*/
        Log.i("WAMOD_PRIVACY", "Blue tick: Analyzing");
        if (utils.prefs.getBoolean("privacy_noBlueTick", false)) {
            com.whatsapp.protocol.l l = new l();
            l.O = "";
            l.P = null;
            l.I = p;
            q.a(l, null);
            Log.i("WAMOD_PRIVACY", "Blue tick: l.I.a: " + l.I.a + " -- l.I.c: " + l.I.c);
            return true;
        } return false;
    }

    private void call_blueTick() {
        if (blueTick(null, null, null, null)) return;
    }

    public static void logStringArray(String[] strings) {
        if (strings == null) {
            Log.i("WAMOD_PRIVACY", "NULL STRING LOL");
            return;
        }

        String str = "";
        for (String s : strings) {
            str += s + " ---- ";
        }
        Log.i("WAMOD_PRIVACY", str);
    }

    private void call_logStringArray() {
        logStringArray(null);
    }

    public static void logP(com.whatsapp.protocol.p p) {
        Log.i("WAMOD_PRIVACY", "a: " + p.a + " -- b: " + (p.b? "true" : "false") + " -- c: " + p.c);
    }

    public static void call_logP() {
        logP(null);
    }
}
