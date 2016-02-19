package com.wamod;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Settings extends AppCompatActivity {

    static ActionBar actionBar;
    static Window window;
    static Preference conversation_style_entry_config;
    static Context ctx;
    static ViewGroup viewGroupActionBar;
    static AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        window = getWindow();
        ctx = this;
        activity = this;
        //app.setContext(this);

        viewGroupActionBar = (ViewGroup) findViewById(id.action_bar);

        setContentView(id.wamodsettingslayout);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String title = getString(id.appname);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = ctx.getResources().getString(id.wamodsettings);
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(getResources(), id.appicon), color);
            setTaskDescription(taskDesc);
        }

        utils.loadColors(this);
    }

    public static class SettingsFragment extends PreferenceFragment {
        static Boolean lollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("wamod");
            addPreferencesFromResource(id.wamod_settings);

            // Get device ID and show it
            String deviceID = utils.getDeviceID();
            if (!deviceID.contentEquals("")) findPreference("device_id").setSummary(deviceID);

            ArrayList<Preference> preferences = new ArrayList<Preference>();
            preferences.add(0, findPreference("general_statusbarcolor"));
            preferences.add(0, findPreference("general_toolbarcolor"));
            preferences.add(0, findPreference("general_toolbarforeground"));
            preferences.add(0, findPreference("general_navbarcolor"));
            preferences.add(0, findPreference("home_tabsindicatorcolor"));
            preferences.add(0, findPreference("conversation_rightbubblecolor"));
            preferences.add(0, findPreference("conversation_rightbubbletextcolor"));
            preferences.add(0, findPreference("conversation_rightbubbledatecolor"));
            preferences.add(0, findPreference("conversation_leftbubblecolor"));
            preferences.add(0, findPreference("conversation_leftbubbletextcolor"));
            preferences.add(0, findPreference("conversation_leftbubbledatecolor"));
            preferences.add(0, findPreference("conversation_customparticipantcolor"));
            preferences.add(0, findPreference("conversation_custombackcolor"));

            for(int i=0; i < preferences.size();i++) {
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

                                    switch (pref.getKey()) {
                                        case "general_statusbarcolor":
                                            if (lollipop)
                                            window.setStatusBarColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));
                                            break;
                                        case "general_toolbarcolor":
                                                actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"))));
                                            break;
                                        case "general_navbarcolor":
                                            if (lollipop)
                                                window.setNavigationBarColor(Color.parseColor("#" + utils.prefs.getString("general_navbarcolor", "ffffff")));
                                            break;
                                        case "general_toolbarforeground":
                                            utils.tintToolbarItems(viewGroupActionBar, getResources());
                                            break;
                                    }
                                }

                                @Override
                                public void onCancel(AmbilWarnaDialog dialog) {
                                }
                            });
                            dialog.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                            builder.setTitle(ctx.getResources().getString(id.insertahexcolorwithout));

                            final EditText input = new EditText(ctx);
                            input.setInputType(InputType.TYPE_CLASS_TEXT);
                            input.setText(utils.prefs.getString(pref.getKey(), "ffffff"));
                            builder.setView(input);

                            builder.setPositiveButton(ctx.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String hex = input.getText().toString().toLowerCase();
                                    try {
                                        int color = Color.parseColor("#" + hex);
                                        utils.edit.putString(pref.getKey(), hex);
                                        pref.setSummary("#" + hex);
                                        utils.edit.apply();

                                        switch (pref.getKey()) {
                                            case "general_statusbarcolor":
                                                window.setStatusBarColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));
                                                break;
                                            case "general_toolbarcolor":
                                                if (lollipop)
                                                    actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"))));
                                                break;
                                            case "general_navbarcolor":
                                                if (lollipop)
                                                    window.setNavigationBarColor(Color.parseColor("#" + utils.prefs.getString("general_navbarcolor", "ffffff")));
                                                break;
                                            case "general_toolbarforeground":
                                                utils.tintToolbarItems(viewGroupActionBar, getResources());
                                                break;
                                        }
                                    } catch (IllegalArgumentException e) {
                                        Toast.makeText(ctx, "Error lol", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            builder.setNegativeButton(ctx.getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
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

            Preference customParticipantNameColorSwitch = findPreference("conversation_customparticipantcolorbool");
            final Preference customParticipantNameColor = findPreference("conversation_customparticipantcolor");
            if (utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)) customParticipantNameColor.setEnabled(true);
            else customParticipantNameColor.setEnabled(false);
            customParticipantNameColorSwitch.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (utils.prefs.getBoolean("conversation_customparticipantcolorbool", false))
                        customParticipantNameColor.setEnabled(true);
                    else customParticipantNameColor.setEnabled(false);
                    return false;
                }
            });


            Preference customConversationBgColorSwitch = findPreference("conversation_custombackcolorbool");
            final Preference customConversationBgColor = findPreference("conversation_custombackcolor");
            if (utils.prefs.getBoolean("conversation_custombackcolorbool", false)) customConversationBgColor.setEnabled(true);
            else customConversationBgColor.setEnabled(false);
            customConversationBgColorSwitch.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (utils.prefs.getBoolean("conversation_custombackcolorbool", false))
                        customConversationBgColor.setEnabled(true);
                    else customConversationBgColor.setEnabled(false);
                    return false;
                }
            });



            preferences.clear();
            preferences.add(0, findPreference("conversation_style_toolbar"));
            preferences.add(0, findPreference("home_theme"));
            preferences.add(0, findPreference("conversation_style_bubble"));
            preferences.add(0, findPreference("conversation_style_tick"));

            final ArrayList<Integer> nameArray = new ArrayList<Integer>();
            nameArray.add(0, id.toolbarstylearray);
            nameArray.add(0, id.homethemearray);
            nameArray.add(0, id.conversationstylebubblearray);
            nameArray.add(0, id.conversationstyletickarray);

            final Preference conversation_theme_config = findPreference("conversation_theme_config");
            for(int i=0; i < preferences.size();i++) {
                final ListPreference pref = (ListPreference) preferences.get(i);
                String selectedItem[] = getResources().getStringArray(nameArray.get(i));
                pref.setSummary(selectedItem[Integer.parseInt(utils.prefs.getString(pref.getKey(), "0"))]);
                final int index = i;

                pref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String selectedItem[] = getResources().getStringArray(nameArray.get(index));
                        utils.edit.putString(pref.getKey(), newValue.toString());
                        utils.edit.apply();
                        ((ListPreference)preference).setValue(newValue.toString());
                        preference.setSummary(selectedItem[Integer.parseInt(utils.prefs.getString(preference.getKey(), "0"))]);

                        return false;
                    }
                });
            }



            final Preference conversation_style_entry = findPreference("conversation_style_entry");
            final int conversation_style_entry_array = id.conversationstyleentryarray;
            conversation_style_entry.setSummary(getResources().getStringArray(conversation_style_entry_array)[Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"))]);
            conversation_style_entry.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    utils.edit.putString(conversation_style_entry.getKey(), newValue.toString());
                    utils.edit.apply();
                    ((ListPreference) preference).setValue(newValue.toString());
                    conversation_style_entry.setSummary(getResources().getStringArray(conversation_style_entry_array)[Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"))]);
                    initConversationStyleEntryConfig();
                    return false;
                }
            });

            conversation_style_entry_config = findPreference("conversation_style_entry_config");
            initConversationStyleEntryConfig();
            conversation_style_entry_config.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent intent = new Intent(app.getContext(), EntryConfigActivity.class);
                    startActivity(intent);
                    return false;
                }
            });


            if (!lollipop) {
                String message = ctx.getResources().getString(id.onlylollipopandup);
                Preference pref = findPreference("general_statusbarcolor");
                pref.setEnabled(false);
                pref.setSummary(message);
                pref = findPreference("general_navbarcolor");
                pref.setEnabled(false);
                pref.setSummary(message);
            }

            Preference checkforupdates = (Preference) findPreference("checkforupdates");
            checkforupdates.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    new checkinv2().execute(activity);
                    return false;
                }
            });

            Preference restoredefaults = (Preference) findPreference("restoredefaults");
            restoredefaults.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();

                    alertDialog.setTitle(getResources().getString(id.restoredefaults));
                    alertDialog.setMessage(getResources().getString(id.restoredefaultsprompt));

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            utils.edit.putInt("wamodversion", 0);
                            utils.edit.apply();
                            utils.initWAMOD();
                            Toast.makeText(ctx, getResources().getString(com.wamod.id.donerestartwamod), Toast.LENGTH_LONG).show();
                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //...
                        }
                    });

                    alertDialog.show();
                    return false;
                }
            });

            Preference darkStatusBarIcons = findPreference("general_darkstatusbaricons");
            darkStatusBarIcons.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (utils.prefs.getBoolean("general_darkstatusbaricons", false))
                            activity.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        else activity.findViewById(android.R.id.content).setSystemUiVisibility(0);
                    }

                    return false;
                }
            });

            Preference unlinkFromWAMODThemes = findPreference("wamodthemes_unlinkdevice");
            unlinkFromWAMODThemes.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

                    alertDialog.setTitle(activity.getResources().getString(id.wamod_unlinkdevice_title));
                    alertDialog.setMessage(activity.getResources().getString(id.wamod_unlinkdevice_message));

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, activity.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            unlinkwamodthemes async = new unlinkwamodthemes();
                            async.activity = activity;
                            async.execute();
                        }
                    });

                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, activity.getResources().getString(android.R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //...
                        }
                    });

                    alertDialog.show();
                    return true;
                }
            });

            Preference credits = findPreference("credits");
            credits.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ctx);
                    alert.setTitle("Credits");

                    WebView wv = new WebView(ctx);
                    wv.loadUrl("file:///android_asset/wamod_credits.html");
                    wv.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);

                            return true;
                        }
                    });

                    alert.setView(wv);
                    alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                    return false;
                }
            });
        }
    }

    private static void initConversationStyleEntryConfig() {
        boolean enabled = true;
        switch (utils.prefs.getString("conversation_style_entry", "0")) {
            case "0":
                enabled = false;
                break;
            case "1":
                enabled = true;
                break;
            case "2":
                enabled = true;
                break;
        }
        conversation_style_entry_config.setEnabled(enabled);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        tintToolbarText();
        return true;
    }

    private void tintToolbarText() {
        utils.tintToolbarItems((ViewGroup) findViewById(id.actionbarid), getResources());
    }
}
