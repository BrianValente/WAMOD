package com.wamod.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.ListPreference;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.wamod.Utils;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ThemePickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (AppCompatActivity) context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ThemePickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (AppCompatActivity) context;
    }

    private void init() {
        try {
            int arrayId = activity.getResources().getIdentifier(getKey(), "array", activity.getPackageName());
            String[] arrayStrings = activity.getResources().getStringArray(arrayId);

            int valuesArrayId = activity.getResources().getIdentifier(getKey() + "_values", "array", activity.getPackageName());
            String[] valuesArrayStrings = activity.getResources().getStringArray(valuesArrayId);

            String value = getValue();

            int valueIndex;

            for (valueIndex = 0; valueIndex<valuesArrayStrings.length; valueIndex++) {
                if (valuesArrayStrings[valueIndex].contentEquals(value))
                    break;
            }

            setSummary(arrayStrings[valueIndex]);
        } catch (Exception e) {
            Utils.manageException(e);
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
        com.wamod.preference.Preference.loadColors(view);
    }
}
