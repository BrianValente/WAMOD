package com.wamod;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.whatsapp.ContactInfo;
import com.whatsapp.GroupChatInfo;
import com.whatsapp.protocol.a;
import com.whatsapp.protocol.g;

/**
 * Created by brianvalente on 5/8/16.
 */
public class Privacy {
    static boolean stringsDecoded = false;

    public static boolean blueTick(a a, g g, String str1, String str2, String[] str3, String str4) {
        // Returns TRUE if the "Hide blue tick" option is DISABLED
        log(g, str1, str2, str3, str4);
        String JabberID = g.a;
        if (contactAffectedByBlueTickMod(JabberID)) {
            str1 = str4;
            if (!contactAffectedBySecondTickMod(JabberID)) a.a(g, str1, str2, str3, str4);
            return false;
        } else return true;
    }

    public static boolean hideTyping(String JabberID) {
        // Returns TRUE if the "Hide typing" option is DISABLED
        return !contactAffectedByHideTypingMod(JabberID);
        /*if (Utils.prefs.getBoolean("privacy_hideTyping", false)) return true;
        else return false;*/
    }

    public static boolean secondTick(g g) {
        // Returns TRUE if the "Hide second tick" option is DISABLED
        String JabberID = g.a;
        return !contactAffectedBySecondTickMod(JabberID);
    }


    public static boolean contactAffectedByBlueTickMod(String JabberID) {
        SharedPreferences prefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        boolean bool = prefs.getBoolean(JabberID + "_hidebluetick", false);
        return bool;
    }

    public static boolean contactAffectedBySecondTickMod(String JabberID) {
        SharedPreferences prefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        boolean bool = prefs.getBoolean(JabberID + "_hidesecondtick", false);
        return bool;
    }

    public static boolean contactAffectedByHideTypingMod(String JabberID) {
        SharedPreferences prefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        boolean bool = prefs.getBoolean(JabberID + "_hidetyping", false);
        return bool;
    }

    public static void setContactAffectedByBlueTickMod(String JabberID, boolean affected) {
        SharedPreferences prefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(JabberID + "_hidebluetick", affected);
        edit.apply();
    }

    public static void setContactAffectedBySecondTickMod(String JabberID, boolean affected) {
        SharedPreferences prefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(JabberID + "_hidesecondtick", affected);
        edit.apply();
    }

    public static void setContactAffectedByHideTypingMod(String JabberID, boolean affected) {
        SharedPreferences prefs = Utils.context.getSharedPreferences("wamod_privacy", 0);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(JabberID + "_hidetyping", affected);
        edit.apply();
    }

    public static void initPrivacyOnChatInfo(AppCompatActivity activity) {
        SwitchCompat reportReadSwitch     = (SwitchCompat) activity.findViewById(Resources.getID("wamod_privacy_card_reportread_switch"));
        SwitchCompat reportReceivedSwitch = (SwitchCompat) activity.findViewById(Resources.getID("wamod_privacy_card_reportreceived_switch"));
        SwitchCompat hideTypingSwitch     = (SwitchCompat) activity.findViewById(Resources.getID("wamod_privacy_card_hidetyping_switch"));
        if (reportReadSwitch == null || reportReceivedSwitch == null || hideTypingSwitch == null) return;

        com.whatsapp.qj contact = null;
        if (activity instanceof GroupChatInfo) contact = ((GroupChatInfo) activity).getContact((GroupChatInfo) activity);
        else if (activity instanceof ContactInfo) contact = ((ContactInfo) activity).f((ContactInfo) activity);
        if (contact == null) return;

        final String JabberID = contact.r;

        if (Privacy.contactAffectedByBlueTickMod(JabberID))
            reportReadSwitch.setChecked(false);
        else
            reportReadSwitch.setChecked(true);

        if (Privacy.contactAffectedBySecondTickMod(JabberID))
            reportReceivedSwitch.setChecked(false);
        else
            reportReceivedSwitch.setChecked(true);

        if (Privacy.contactAffectedByHideTypingMod(JabberID))
            hideTypingSwitch.setChecked(false);
        else
            hideTypingSwitch.setChecked(true);

        reportReadSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Privacy.setContactAffectedByBlueTickMod(JabberID, !b);
            }
        });

        reportReceivedSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Privacy.setContactAffectedBySecondTickMod(JabberID, !b);
            }
        });

        hideTypingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Privacy.setContactAffectedByHideTypingMod(JabberID, !b);
            }
        });
    }


    /* Trash */

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

    public static void logP(g g) {
        Log.i("WAMOD_PRIVACY", "a: " + g.a + " -- b: " + (g.b? "true" : "false") + " -- c: " + g.c);
    }

    public static void log(g g, String str1, String str2, String[] str3, String str4) {
        int i = 1;
        Log.i("WAMOD_PRIVACY_READ", "a: " + g.a + " -- b: " + (g.b? "true" : "false") + " -- c: " + g.c);
        Log.i("WAMOD_PRIVACY_READ", "1: " + str1 + " -- 2: " + str2 + " -- 4: " + str4);
        if (str3 != null)
            for (String s : str3) {
                Log.i("WAMOD_PRIVACY_READ", i + ": " + s);
                i++;
            }
        else Log.i("WAMOD_PRIVACY_READ", "String 3: NULL");
    }

    public static void logReceived(g g, String str1, String str2, String[] str3, String str4) {
        int i = 1;
        Log.i("WAMOD_PRIVACY_RECEIVED", "a: " + g.a + " -- b: " + (g.b? "true" : "false") + " -- c: " + g.c);
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
}
