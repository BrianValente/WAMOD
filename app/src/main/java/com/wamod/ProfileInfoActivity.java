package com.wamod;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ProfileInfoActivity extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (utils.nightModeShouldRun()) {
            final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
            ScrollView scrollView = (ScrollView) content.getChildAt(0);
            scrollView.setBackgroundColor(utils.getDarkColor(2));
            LinearLayout linearLayout = (LinearLayout) scrollView.getChildAt(0);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View child = linearLayout.getChildAt(i);
                if (child instanceof LinearLayout) {
                    LinearLayout linearLayout1 = (LinearLayout) child;
                    linearLayout1.setBackgroundColor(utils.getDarkColor(3));

                    if (linearLayout1.getChildCount() == 4)
                        ((TextView) ((ViewGroup) linearLayout1.getChildAt(0)).getChildAt(0)).setTextColor(utils.getDarkColor(0));
                }
            }
            ((TextView) content.findViewById(Resources.id.status)).setTextColor(utils.getDarkColor(1));
            ((TextView) content.findViewById(Resources.id.phone)).setTextColor(utils.getDarkColor(1));
            ((TextView) content.findViewById(Resources.id.registration_name)).setTextColor(utils.getDarkColor(1));
            ImageButton change_registration_name_btn = (ImageButton) content.findViewById(Resources.id.change_registration_name_btn);
            change_registration_name_btn.setImageDrawable(utils.tintToColor(change_registration_name_btn.getDrawable(), utils.getDarkColor(0)));
        }
    }
}
