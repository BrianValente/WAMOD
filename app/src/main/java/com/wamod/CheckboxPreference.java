package com.wamod;

import android.content.Context;
import android.preference.CheckBoxPreference;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;

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
}
