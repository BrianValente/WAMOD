package com.wamod.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by BrianValente on 4/18/16.
 */
public class ListPreference extends android.preference.ListPreference {
    public ListPreference(Context context) {
        super(context);
    }

    public ListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        Preference.loadColors(view);
    }
}
