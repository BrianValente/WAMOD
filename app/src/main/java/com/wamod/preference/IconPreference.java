package com.wamod.preference;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wamod.Utils;

/**
 * Created by BrianValente on 3/27/16.
 */
public class IconPreference extends Preference {
    public IconPreference(Context context) {
        super(context);
    }

    public IconPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IconPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IconPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        if (!Utils.nightModeShouldRun()) {
            tintIcon(Color.BLACK, view);
        } else {
            tintIcon(Utils.getDarkColor(0), view);

            TextView title = (TextView) view.findViewById(android.R.id.title);
            if (title != null) title.setTextColor(Utils.getDarkColor(0));

            TextView summary = (TextView) view.findViewById(android.R.id.summary);
            if (summary != null) summary.setTextColor(Utils.getDarkColor(1));
        }
    }

    private void tintIcon(int color, View v1) {
        ViewGroup vg = (ViewGroup) v1;
        ViewGroup vg2 = (ViewGroup) vg.getChildAt(0);
        for (int i=0; i<vg2.getChildCount();i++) {
            View v = vg2.getChildAt(i);
            if (v instanceof ImageView) {
                ImageView imageView = (ImageView) v;
                Drawable icon = imageView.getDrawable();
                icon.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
                imageView.setColorFilter(color);
                imageView.setImageDrawable(icon);
            }
        }
    }
}
