package com.wamod.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Utils;

/**
 * Created by BrianValente on 4/18/16.
 */
public class Preference extends android.preference.Preference {
    public Preference(Context context) {
        super(context);
    }

    public Preference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Preference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        loadColors(view);
    }

    public static void loadColors(View v) {
        TextView title = (TextView) v.findViewById(android.R.id.title);
        if (title != null) title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));

        TextView summary = (TextView) v.findViewById(android.R.id.summary);
        if (summary != null) summary.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
    }
}
