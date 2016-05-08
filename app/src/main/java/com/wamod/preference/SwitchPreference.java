package com.wamod.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.wamod.utils;

/**
 * Created by BrianValente on 4/18/16.
 */
public class SwitchPreference extends android.preference.SwitchPreference {
    public SwitchPreference(Context context) {
        super(context);
    }

    public SwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
