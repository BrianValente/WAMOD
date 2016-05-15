package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by BrianValente on 3/28/16.
 */
public class SetStatus extends AppCompatActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (Utils.nightModeShouldRun()) {
            ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
            ViewGroup container = (ViewGroup) content.getChildAt(0);
            container.setBackgroundColor(Utils.getDarkColor(2));

            ViewGroup myStatus = (ViewGroup) container.getChildAt(0);
            ViewGroup suggestedStatus = (ViewGroup) container.getChildAt(1);

            myStatus.setBackground(Utils.tintToColor(myStatus.getBackground(), Utils.getDarkColor(3)));
            suggestedStatus.setBackground(Utils.tintToColor(suggestedStatus.getBackground(), Utils.getDarkColor(3)));

            TextView status_title = (TextView) a.findViewById(Resources.id.status_title);
            TextView select_status_title = (TextView) a.findViewById(Resources.id.select_status_title);
            TextView status_tv = (TextView) a.findViewById(Resources.id.status_tv);
            ImageView round_more_btn = (ImageView) a.findViewById(Resources.id.round_more_btn);

            status_title.setTextColor(Utils.getDarkColor(0));
            select_status_title.setTextColor(Utils.getDarkColor(0));
            status_tv.setTextColor(Utils.getDarkColor(1));
            round_more_btn.setImageDrawable(Utils.tintToColor(round_more_btn.getDrawable(), Utils.getDarkColor(1)));

            ListView list = (ListView) a.findViewById(Resources.id.list);
            list.setDivider(null);
        }
    }

    public static void _getView(View v) {
        if (Utils.nightModeShouldRun()) {
            TextView status_row = (TextView) v.findViewById(Resources.id.status_row);
            status_row.setTextColor(Utils.getDarkColor(1));
        }
    }

    public static void callGetView() {
        _getView(null);
    }
}
