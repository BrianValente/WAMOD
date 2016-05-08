package com.wamod.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wamod.utils;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by BrianValente on 3/3/16.
 */
public class ThemePickerPreference extends ListPreference {
    AppCompatActivity activity;

    public ThemePickerPreference(Context context) {
        super(context);
        activity = (AppCompatActivity) context;
    }

    public ThemePickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (AppCompatActivity) context;
    }

    public ThemePickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (AppCompatActivity) context;
    }

    public ThemePickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (AppCompatActivity) context;
    }

    private void init() {
        try {
            int arrayID = activity.getResources().getIdentifier(getKey(), "array", activity.getPackageName());
            String[] items = activity.getResources().getStringArray(arrayID);
            int themeid = Integer.parseInt(getValue());
            String themeName = items[themeid];
            setSummary(themeName);
        } catch (Exception e) {
            utils.manageException(e);
        }
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        init();
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        if (utils.nightModeShouldRun()) {
            TextView title = (TextView) view.findViewById(android.R.id.title);
            if (title != null) title.setTextColor(utils.getDarkColor(0));

            TextView summary = (TextView) view.findViewById(android.R.id.summary);
            if (summary != null) summary.setTextColor(utils.getDarkColor(1));
        }
    }
}
