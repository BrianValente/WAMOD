package com.wamod;

import android.util.Log;

import com.whatsapp.protocol.l;

/**
 * Created by brianvalente on 5/8/16.
 */
public class Privacy {
    static boolean stringsDecoded = false;

    public static boolean blueTick(com.whatsapp.protocol.q q, com.whatsapp.protocol.p p, String str1, String str2, String[] str3, String str4) {
        // Returns TRUE if the "Hide blue tick" option is DISABLED
        log(p, str1, str2, str3, str4);
        if (utils.prefs.getBoolean("privacy_noBlueTick", false)) {
            str1 = str4;
            //str2 = "";
            q.a(p, str1, str2, str3, str4);
            return false;
        } else return true;
    }

    private void call_blueTick() {
        if (blueTick(null, null, null, null, null, null)) {
            String doSomething = "bitch";
        }
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
        log(null,null,null,null,null);
    }

    public static void log(com.whatsapp.protocol.p p, String str1, String str2, String[] str3, String str4) {
        int i = 1;
        Log.i("WAMOD_PRIVACY_READ", "a: " + p.a + " -- b: " + (p.b? "true" : "false") + " -- c: " + p.c);
        Log.i("WAMOD_PRIVACY_READ", "1: " + str1 + " -- 2: " + str2 + " -- 4: " + str4);
        if (str3 != null)
            for (String s : str3) {
                Log.i("WAMOD_PRIVACY_READ", i + ": " + s);
                i++;
            }
        else Log.i("WAMOD_PRIVACY_READ", "String 3: NULL");
    }

    public static void logReceived(com.whatsapp.protocol.p p, String str1, String str2, String[] str3, String str4) {
        int i = 1;
        Log.i("WAMOD_PRIVACY_RECEIVED", "a: " + p.a + " -- b: " + (p.b? "true" : "false") + " -- c: " + p.c);
        Log.i("WAMOD_PRIVACY_RECEIVED", "1: " + str1 + " -- 2: " + str2 + " -- 4: " + str4);
        if (str3 != null)
            for (String s : str3) {
                Log.i("WAMOD_PRIVACY_RECEIVED", i + ": " + s);
                i++;
            }
    }

    public static void logStrings(String[] strings) {
        if (stringsDecoded) return;
        int i = 1;
        for (String s : strings) {
            Log.i("WAMOD_PRIVACY_STRINGS", i + ": " + s);
            i++;
        }
        stringsDecoded = true;
    }

    public static void call_logStrings(String[] strings) {
        logStrings(strings);
    }
}
