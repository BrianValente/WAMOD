package com.wamod;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class NewGroup extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (utils.nightModeShouldRun()) {
            ImageView change_photo_btn = (ImageView) a.findViewById(Resources.id.change_photo_btn);
            ImageButton emoji_btn = (ImageButton) a.findViewById(Resources.id.emoji_btn);
            EditText group_name = (EditText) a.findViewById(Resources.id.group_name);
            TextView subject_counter_tv = (TextView) a.findViewById(Resources.id.subject_counter_tv);

            change_photo_btn.setImageDrawable(utils.tintToColor(change_photo_btn.getDrawable(), utils.getDarkColor(0)));
            emoji_btn.setColorFilter(utils.getDarkColor(0));
            group_name.setHintTextColor(utils.getDarkColor(1));
            subject_counter_tv.setTextColor(utils.getDarkColor(1));

            ((ViewGroup) ((ViewGroup) a.findViewById(android.R.id.content)).getChildAt(0)).getChildAt(0).setBackgroundColor(utils.getDarkColor(2));

            ((TextView) ((ViewGroup) (ViewGroup) a.findViewById(Resources.id.image_frame_layout).getParent()).getChildAt(1)).setTextColor(utils.getDarkColor(0));
        }
    }
}
