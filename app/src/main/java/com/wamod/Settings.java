package com.wamod;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (utils.nightModeShouldRun()) {
            final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
            ScrollView scrollView = (ScrollView) content.getChildAt(0);
            scrollView.setBackgroundColor(utils.getDarkColor(2));
            LinearLayout linearLayout = (LinearLayout) scrollView.getChildAt(0);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View child = linearLayout.getChildAt(i);
                if (child instanceof SeparatorView) {
                    child.setBackgroundColor(Color.TRANSPARENT);
                } else if (child instanceof FrameLayout) {
                    FrameLayout frameLayout = (FrameLayout) child;
                    ImageView icon = (ImageView) frameLayout.getChildAt(0);
                    TextView label = (TextView) frameLayout.getChildAt(1);
                    icon.setImageDrawable(utils.tintToColor(icon.getDrawable(), utils.getDarkColor(0)));
                    label.setTextColor(utils.getDarkColor(0));
                }
            }
            TextView profile_info_name = (TextView) content.findViewById(Resources.id.profile_info_name);
            TextView profile_info_status = (TextView) content.findViewById(Resources.id.profile_info_status);
            profile_info_name.setTextColor(utils.getDarkColor(0));
            profile_info_status.setTextColor(utils.getDarkColor(1));
        }
    }
}
