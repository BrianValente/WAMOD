package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by BrianValente on 3/28/16.
 */
public class SetStatus extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
        ViewGroup container = (ViewGroup) content.getChildAt(0);
        container.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));

        ViewGroup myStatus = (ViewGroup) container.getChildAt(0);
        ViewGroup suggestedStatus = (ViewGroup) container.getChildAt(1);

        myStatus.setBackground(Utils.tintToColor(myStatus.getBackground(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND)));
        suggestedStatus.setBackground(Utils.tintToColor(suggestedStatus.getBackground(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND)));

        TextView status_title = (TextView) a.findViewById(Resources.id.status_title);
        TextView select_status_title = (TextView) a.findViewById(Resources.id.select_status_title);
        TextView status_tv = (TextView) a.findViewById(Resources.id.status_tv);
        ImageView round_more_btn = (ImageView) a.findViewById(Resources.id.round_more_btn);

        if (status_title        != null) status_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
        if (select_status_title != null) select_status_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
        if (status_tv           != null) status_tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        if (round_more_btn      != null) round_more_btn.setImageDrawable(Utils.tintToColor(round_more_btn.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY)));

        ListView list = (ListView) a.findViewById(Resources.id.list);
        if (list != null) list.setDivider(null);
    }

    public static void _getView(View v) {
        TextView status_row = (TextView) v.findViewById(Resources.id.status_row);
        status_row.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
    }

    public static void callGetView() {
        _getView(null);
    }
}
