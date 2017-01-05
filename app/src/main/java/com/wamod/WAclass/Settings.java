package com.wamod.WAclass;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;
import com.wamod.view.SeparatorView;

public class Settings extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
        ScrollView scrollView = (ScrollView) content.getChildAt(0);
        scrollView.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
        LinearLayout linearLayout = (LinearLayout) scrollView.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);
            if (child instanceof SeparatorView) {
                child.setBackgroundColor(Color.TRANSPARENT);
            } else if (child instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) child;
                ImageView icon = (ImageView) frameLayout.getChildAt(0);
                TextView label = (TextView) frameLayout.getChildAt(1);
                icon.setImageDrawable(Utils.tintToColor(icon.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
                label.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            }
        }
        TextView profile_info_name = (TextView) content.findViewById(Resources.id.profile_info_name);
        TextView profile_info_status = (TextView) content.findViewById(Resources.id.profile_info_status);
        profile_info_name.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
        profile_info_status.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
    }
}
