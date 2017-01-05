package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

public class ProfileInfoActivity extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
        ScrollView scrollView = (ScrollView) content.getChildAt(0);
        scrollView.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
        LinearLayout linearLayout = (LinearLayout) scrollView.getChildAt(0);
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            View child = linearLayout.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout linearLayout1 = (LinearLayout) child;
                linearLayout1.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));

                if (linearLayout1.getChildCount() == 4)
                    ((TextView) ((ViewGroup) linearLayout1.getChildAt(0)).getChildAt(0)).setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            }
        }
        ((TextView) content.findViewById(Resources.id.status)).setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        ((TextView) content.findViewById(Resources.id.phone)).setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        ((TextView) content.findViewById(Resources.id.registration_name)).setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        ImageButton change_registration_name_btn = (ImageButton) content.findViewById(Resources.id.change_registration_name_btn);
        change_registration_name_btn.setImageDrawable(Utils.tintToColor(change_registration_name_btn.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
    }
}
