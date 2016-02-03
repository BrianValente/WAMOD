package com.wamod;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by brianvalente on 9/19/15.
 */
public class ConversationEntryConfig extends AppCompatActivity {
    static ActionBar actionBar;
    static Window window;
    static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        window = getWindow();
        ctx = this;

        utils.loadColors(getSupportActionBar(), getWindow());

        setContentView(id.wamodsettingslayout);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("wamod");

            switch (utils.prefs.getString("conversation_style_entry", "")) {
                case "1":
                    addPreferencesFromResource(id.theme_wamod_conversation_config);

                    ArrayList<Preference> preferences = new ArrayList<Preference>();
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_bgcolor"));
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_entrybgcolor"));
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_hinttextcolor"));
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_textcolor"));
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_emojibtncolor"));
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_btncolor"));
                    preferences.add(0, findPreference("theme_wamod_conversation_entry_sendbtncolor"));

                    for(int i=0; i < preferences.size(); i++) {
                        final Preference pref = preferences.get(i);
                        pref.setSummary("#" + utils.prefs.getString(pref.getKey(), "0"));
                        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                            @Override
                            public boolean onPreferenceClick(Preference preference) {
                                // initialColor is the initially-selected color to be shown in the rectangle on the left of the arrow.
                                // for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware of the initial 0xff which is the alpha.
                                Integer color = Color.parseColor("#" + utils.prefs.getString(pref.getKey(), "ffffff"));
                                if (!(utils.prefs.getBoolean("debug_disablecolorpicker", false))) {
                                    final AmbilWarnaDialog dialog = new AmbilWarnaDialog(ctx, color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                                        @Override
                                        public void onOk(AmbilWarnaDialog dialog, int color) {
                                            String hex = Integer.toHexString(color).substring(2).toLowerCase();
                                            utils.edit.putString(pref.getKey(), hex);
                                            pref.setSummary("#" + hex);
                                            utils.edit.apply();
                                        }
                                        @Override
                                        public void onCancel(AmbilWarnaDialog dialog) {}
                                    });
                                    dialog.show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                                    builder.setTitle("Insert a HEX color");

                                    final EditText input = new EditText(ctx);
                                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                                    input.setText(utils.prefs.getString(pref.getKey(), "ffffff"));
                                    builder.setView(input);

                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String hex = input.getText().toString().toLowerCase();
                                            try {
                                                int color = Color.parseColor("#" + hex);
                                                utils.edit.putString(pref.getKey(), hex);
                                                pref.setSummary("#" + hex);
                                                utils.edit.apply();
                                            } catch (IllegalArgumentException e) {
                                                Toast.makeText(ctx, "Error lol", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder.show();
                                }
                                return false;
                            }
                        });
                    }
                    break;
                case "2":
                    addPreferencesFromResource(id.theme_hangouts_conversation_config);

                    ArrayList<Preference> hangouts_preferences = new ArrayList<Preference>();
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_entry_bgcolor"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_entry_hintcolor"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_entry_textcolor"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_attach_color"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_mic_bg"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_mic_color"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_send_bg"));
                    hangouts_preferences.add(0, findPreference("theme_hangouts_conversation_send_color"));

                    for(int i=0; i < hangouts_preferences.size(); i++) {
                        final Preference pref = hangouts_preferences.get(i);
                        pref.setSummary("#" + utils.prefs.getString(pref.getKey(), "0"));
                        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                            @Override
                            public boolean onPreferenceClick(Preference preference) {
                                // initialColor is the initially-selected color to be shown in the rectangle on the left of the arrow.
                                // for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware of the initial 0xff which is the alpha.
                                Integer color = Color.parseColor("#" + utils.prefs.getString(pref.getKey(), "ffffff"));
                                if (!(utils.prefs.getBoolean("debug_disablecolorpicker", false))) {
                                    final AmbilWarnaDialog dialog = new AmbilWarnaDialog(ctx, color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                                        @Override
                                        public void onOk(AmbilWarnaDialog dialog, int color) {
                                            String hex = Integer.toHexString(color).substring(2).toLowerCase();
                                            utils.edit.putString(pref.getKey(), hex);
                                            pref.setSummary("#" + hex);
                                            utils.edit.apply();
                                        }
                                        @Override
                                        public void onCancel(AmbilWarnaDialog dialog) {}
                                    });
                                    dialog.show();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                                    builder.setTitle("Insert a HEX color");

                                    final EditText input = new EditText(ctx);
                                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                                    input.setText(utils.prefs.getString(pref.getKey(), "ffffff"));
                                    builder.setView(input);

                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String hex = input.getText().toString().toLowerCase();
                                            try {
                                                int color = Color.parseColor("#" + hex);
                                                utils.edit.putString(pref.getKey(), hex);
                                                pref.setSummary("#" + hex);
                                                utils.edit.apply();
                                            } catch (IllegalArgumentException e) {
                                                Toast.makeText(ctx, "Error lol", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder.show();
                                }
                                return false;
                            }
                        });
                    }
                    break;
            }

        }
    }
}
