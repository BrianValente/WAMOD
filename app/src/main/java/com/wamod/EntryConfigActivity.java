package com.wamod;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by brianvalente on 12/22/15.
 */
public class EntryConfigActivity extends WAMODSettings {
    static Context ctx;
    static AppCompatActivity a;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        utils.loadColorsBeforeSuper(this);
        super.onCreate(savedInstanceState);
        ctx = this;
        getFragmentManager().beginTransaction().replace(android.R.id.content, new WAMODSettingsFragment()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        a = this;
    }

    public static class WAMODSettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("wamod");
            switch (utils.prefs.getString("conversation_style_entry", "")) {
                case "1":
                    WAMOD();
                    break;
                case "2":
                    Hangouts();
                    break;
                case "3":
                    Simple();
                    break;
                case "4":
                    Aran();
                    break;
                case "5":
                    Mood();
                    break;
                default:
                    Toast.makeText(a, "Theme not supported", Toast.LENGTH_LONG).show();
                    a.finish();
            }
        }

        private void WAMOD() {
            addPreferencesFromResource(id.theme_wamod_conversation_config);
        }

        private void Hangouts() {
            addPreferencesFromResource(id.theme_hangouts_conversation_config);
        }

        private void Aran() {
            addPreferencesFromResource(id.theme_aran_conversation_config);
        }

        private void Simple() {
            addPreferencesFromResource(id.theme_simple_conversation_config);
        }

        private void Mood() {
            addPreferencesFromResource(id.theme_mood_conversation_config);
        }
    }
}
