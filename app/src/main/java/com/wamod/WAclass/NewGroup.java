package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wamod.Resources;
import com.wamod.Utils;

public class NewGroup extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (Utils.nightModeShouldRun()) {
            ImageView change_photo_btn = (ImageView) a.findViewById(Resources.id.change_photo_btn);
            ImageButton emoji_btn = (ImageButton) a.findViewById(Resources.id.emoji_btn);
            EditText group_name = (EditText) a.findViewById(Resources.id.group_name);
            TextView subject_counter_tv = (TextView) a.findViewById(Resources.id.subject_counter_tv);

            change_photo_btn.setImageDrawable(Utils.tintToColor(change_photo_btn.getDrawable(), Utils.getDarkColor(0)));
            emoji_btn.setColorFilter(Utils.getDarkColor(0));
            group_name.setHintTextColor(Utils.getDarkColor(1));
            subject_counter_tv.setTextColor(Utils.getDarkColor(1));

            ((ViewGroup) ((ViewGroup) a.findViewById(android.R.id.content)).getChildAt(0)).getChildAt(0).setBackgroundColor(Utils.getDarkColor(2));

            //((TextView) ((ViewGroup) (ViewGroup) a.findViewById(Resources.id.image_frame_layout).getParent()).getChildAt(1)).setTextColor(Utils.getDarkColor(0));
        }
    }
}
