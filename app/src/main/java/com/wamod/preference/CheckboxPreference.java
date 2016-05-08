package com.wamod.preference;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wamod.utils;

/**
 * Created by BrianValente on 3/3/16.
 */
public class CheckboxPreference extends android.preference.CheckBoxPreference {
    AppCompatActivity activity;

    public CheckboxPreference(Context context) {
        super(context);
        activity = (AppCompatActivity) context;
    }

    public CheckboxPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (AppCompatActivity) context;
    }

    public CheckboxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (AppCompatActivity) context;
    }

    public CheckboxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (AppCompatActivity) context;
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        switch (getKey()) {
            case "general_darkstatusbaricons":
                utils.loadColorsV2(activity);
                break;
        }
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
