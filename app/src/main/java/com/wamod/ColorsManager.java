package com.wamod;

import android.content.SharedPreferences;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by brianvalente on 12/15/16.
 */
public class ColorsManager {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences sharedPreferencesCustom;

    public static final String UI_ACTIVITY_STATUSBAR       = "ui_activity_statusbar";
    public static final String UI_ACTIVITY_TOOLBAR         = "ui_activity_toolbar";
    public static final String UI_ACTIVITY_TOOLBAR_TITLE   = "ui_activity_toolbar_title";
    public static final String UI_ACTIVITY_TOOLBAR_ICONS   = "ui_activity_toolbar_icons";
    public static final String UI_ACTIVITY_NAVBAR          = "ui_activity_navbar";
    public static final String UI_ACTIVITY_BACKGROUND      = "ui_activity_background";
    public static final String UI_ACTIVITY_TEXT_PRIMARY    = "ui_activity_text_primary";
    public static final String UI_ACTIVITY_TEXT_SECONDARY  = "ui_activity_text_secondary";
    public static final String UI_ACTIVITY_CARD_BACKGROUND = "ui_activity_card_background";

    public static final String UI_HOME_BOTTOMBAR_BACKGROUND   = "ui_home_bottombar_background";
    public static final String UI_HOME_BOTTOMBAR_ACTIVEITEM   = "ui_home_bottombar_activeitem";
    public static final String UI_HOME_BOTTOMBAR_INACTIVEITEM = "ui_home_bottombar_inactiveitem";

    public static final String UI_CONVERSATION_BACKGROUND              = "ui_conversation_background";
    public static final String UI_CONVERSATION_BUBBLE_RIGHT_BACKGROUND = "ui_conversation_bubble_right_background";
    public static final String UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE    = "ui_conversation_bubble_right_message";
    public static final String UI_CONVERSATION_BUBBLE_RIGHT_DATE       = "ui_conversation_bubble_right_date";
    public static final String UI_CONVERSATION_BUBBLE_LEFT_BACKGROUND  = "ui_conversation_bubble_left_background";
    public static final String UI_CONVERSATION_BUBBLE_LEFT_MESSAGE     = "ui_conversation_bubble_left_message";
    public static final String UI_CONVERSATION_BUBBLE_LEFT_DATE        = "ui_conversation_bubble_left_date";
    public static final String UI_CONVERSATION_BUBBLE_PARTICIPANT      = "ui_conversation_bubble_participant";

    public static final String UI_CONVERSATION_ENTRY_WAMOD_BACKGROUND           = "ui_conversation_entry_wamod_background";
    public static final String UI_CONVERSATION_ENTRY_WAMOD_TEXTFIELD_BACKGROUND = "ui_conversation_entry_wamod_textfield_background";
    public static final String UI_CONVERSATION_ENTRY_WAMOD_TEXTFIELD_HINT       = "ui_conversation_entry_wamod_textfield_hint";
    public static final String UI_CONVERSATION_ENTRY_WAMOD_TEXTFIELD_TEXT       = "ui_conversation_entry_wamod_textfield_text";
    public static final String UI_CONVERSATION_ENTRY_WAMOD_TEXTFIELD_EMOJI      = "ui_conversation_entry_wamod_textfield_emoji";
    public static final String UI_CONVERSATION_ENTRY_WAMOD_ATTACHMENTS          = "ui_conversation_entry_wamod_attachments";
    public static final String UI_CONVERSATION_ENTRY_WAMOD_SEND                 = "ui_conversation_entry_wamod_send";


    private static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null)
            sharedPreferences = App.getContext().getSharedPreferences("wamod_colors", 0);
        return sharedPreferences;
    }

    private static SharedPreferences getSharedPreferencesCustom() {
        if (sharedPreferencesCustom == null)
            sharedPreferencesCustom = App.getContext().getSharedPreferences("wamod_colors_custom", 0);
        return sharedPreferencesCustom;
    }

    public static int getColor(String colorName) {
        String colorStr = getSharedPreferences().getString(colorName, "#ffffff");

        if (colorStr.charAt(0) == '#')
            return Color.parseColor(colorStr);
        else
            return Color.parseColor(getSharedPreferencesCustom().getString(colorStr, "#ffffff"));
    }

    public static String getColorHex(String colorName) {
        String colorStr = getSharedPreferences().getString(colorName, "#ffffff");

        if (colorStr.charAt(0) == '#')
            return colorStr;
        else
            return getSharedPreferencesCustom().getString(colorStr, "#FFFFFF");
    }

    public static String getCustomColorHex(String colorName) {
        return getSharedPreferencesCustom().getString(colorName, "#FFFFFF");
    }

    public static void setColor(String colorName, String hexOrCustomName) {
        putString(sharedPreferences, colorName, hexOrCustomName);
    }

    public static void setCustomColor(String name, String hex) {
        putString(sharedPreferencesCustom, name, hex);
    }

    private static void putString(SharedPreferences sharedPreferences, String colorName, String colorValue) {
        sharedPreferences.edit().putString(colorName, colorValue).apply();
    }

    public static ArrayList<Map.Entry<String, ?>> getCustomColors() {
        ArrayList<Map.Entry<String, ?>> arrayList = new ArrayList();
        for (Map.Entry<String, ?> entry : getSharedPreferencesCustom().getAll().entrySet()) {
            arrayList.add(entry);
        }
        return arrayList;
    }
}
