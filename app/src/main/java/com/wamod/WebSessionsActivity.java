package com.wamod;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by BrianValente on 3/28/16.
 */
public class WebSessionsActivity extends AppCompatActivity {
    public static void _onCreate(final AppCompatActivity a) {
        if (utils.nightModeShouldRun()) {
            final ListView list = (ListView) a.findViewById(android.R.id.list);
            list.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ((ViewGroup)list.getParent()).setBackgroundColor(utils.getDarkColor(2));
                    list.setDivider(null);

                    ViewGroup header = (ViewGroup) a.findViewById(Resources.id.header);
                    ViewGroup footer = (ViewGroup) a.findViewById(Resources.id.footer);
                    ViewGroup logout_all = (ViewGroup) a.findViewById(Resources.id.logout_all);
                    TextView sessions_title = (TextView) a.findViewById(Resources.id.sessions_title);
                    TextView hint = (TextView) a.findViewById(Resources.id.hint);
                    ImageView logo = (ImageView)((ViewGroup) list.getChildAt(0)).getChildAt(0);

                    header.setBackground(utils.tintToColor(header.getBackground(), utils.getDarkColor(3)));
                    footer.setBackground(utils.tintToColor(footer.getBackground(), utils.getDarkColor(3)));
                    logo.setImageDrawable(utils.tintToColor(logo.getDrawable(), utils.getDarkColor(0)));
                    sessions_title.setTextColor(utils.getDarkColor(0));
                    hint.setTextColor(utils.getDarkColor(1));

                    for (int i=0;i<logout_all.getChildCount(); i++) {
                        View v = logout_all.getChildAt(i);
                        if (v instanceof ImageView) ((ImageView) v).setImageDrawable(utils.tintToColor(((ImageView) v).getDrawable(), utils.getDarkColor(0)));
                        else if (v instanceof TextView) ((TextView) v).setTextColor(utils.getDarkColor(0));
                    }

                    list.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

        }
    }

    public static void _getView(View v) {
        if (utils.nightModeShouldRun()) {
            v.setBackground(utils.tintToColor(v.getBackground(),utils.getDarkColor(3)));
            TextView name   = (TextView) v.findViewById(Resources.id.name);
            TextView status = (TextView) v.findViewById(Resources.id.status);

            name.setTextColor(utils.getDarkColor(0));
            status.setTextColor(utils.getDarkColor(1));
        }
    }
}
