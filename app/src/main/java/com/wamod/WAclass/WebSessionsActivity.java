package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by BrianValente on 3/28/16.
 */
public class WebSessionsActivity extends AppCompatActivity {
    public static void _onCreate(final AppCompatActivity a) {
        final ListView list = (ListView) a.findViewById(android.R.id.list);
        list.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((ViewGroup)list.getParent()).setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
                list.setDivider(null);

                ViewGroup header = (ViewGroup) a.findViewById(Resources.id.header);
                ViewGroup footer = (ViewGroup) a.findViewById(Resources.id.footer);
                ViewGroup logout_all = (ViewGroup) a.findViewById(Resources.id.logout_all);
                TextView sessions_title = (TextView) a.findViewById(Resources.id.sessions_title);
                TextView hint = (TextView) a.findViewById(Resources.id.hint);
                ImageView logo = (ImageView)((ViewGroup) list.getChildAt(0)).getChildAt(0);

                header.setBackground(Utils.tintToColor(header.getBackground(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND)));
                footer.setBackground(Utils.tintToColor(footer.getBackground(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND)));
                logo.setImageDrawable(Utils.tintToColor(logo.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
                sessions_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                hint.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));

                for (int i=0;i<logout_all.getChildCount(); i++) {
                    View v = logout_all.getChildAt(i);
                    if (v instanceof ImageView) ((ImageView) v).setImageDrawable(Utils.tintToColor(((ImageView) v).getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
                    else if (v instanceof TextView) ((TextView) v).setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                }

                list.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


    /* Called on
     *    com.whatsapp.iq.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Before
     *    return-object p2
     * Smali
     *    invoke-static {p2}, Lcom/wamod/WAclass/WebSessionsActivity;->_getView(Landroid/view/View;)V
     */
    public static void _getView(View v) {
        v.setBackground(Utils.tintToColor(v.getBackground(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND)));
        TextView name   = (TextView) v.findViewById(Resources.id.name);
        TextView status = (TextView) v.findViewById(Resources.id.status);

        name.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
        status.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
    }
}
