package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

public class NewGroup extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        ImageView change_photo_btn = (ImageView) a.findViewById(Resources.id.change_photo_btn);
        ImageButton emoji_btn = (ImageButton) a.findViewById(Resources.id.emoji_btn);
        EditText group_name = (EditText) a.findViewById(Resources.id.group_name);
        TextView subject_counter_tv = (TextView) a.findViewById(Resources.id.subject_counter_tv);

        change_photo_btn.setImageDrawable(Utils.tintToColor(change_photo_btn.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
        emoji_btn.setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
        group_name.setHintTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        subject_counter_tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));

        ((ViewGroup) ((ViewGroup) a.findViewById(android.R.id.content)).getChildAt(0)).getChildAt(0).setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
    }
}
