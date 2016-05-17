package com.wamod.WAclass;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wamod.Resources;
import com.wamod.view.SeparatorView;
import com.wamod.Utils;

public class Settings extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (Utils.nightModeShouldRun()) {
            final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
            ScrollView scrollView = (ScrollView) content.getChildAt(0);
            scrollView.setBackgroundColor(Utils.getDarkColor(2));
            LinearLayout linearLayout = (LinearLayout) scrollView.getChildAt(0);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View child = linearLayout.getChildAt(i);
                if (child instanceof SeparatorView) {
                    child.setBackgroundColor(Color.TRANSPARENT);
                } else if (child instanceof FrameLayout) {
                    FrameLayout frameLayout = (FrameLayout) child;
                    ImageView icon = (ImageView) frameLayout.getChildAt(0);
                    TextView label = (TextView) frameLayout.getChildAt(1);
                    icon.setImageDrawable(Utils.tintToColor(icon.getDrawable(), Utils.getDarkColor(0)));
                    label.setTextColor(Utils.getDarkColor(0));
                }
            }
            TextView profile_info_name = (TextView) content.findViewById(Resources.id.profile_info_name);
            TextView profile_info_status = (TextView) content.findViewById(Resources.id.profile_info_status);
            profile_info_name.setTextColor(Utils.getDarkColor(0));
            profile_info_status.setTextColor(Utils.getDarkColor(1));
        }
    }
}
