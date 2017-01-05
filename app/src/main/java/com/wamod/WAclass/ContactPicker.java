package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by brianvalente on 5/8/16.
 */
public class ContactPicker {
    public static void _onCreate(AppCompatActivity a) {
        ViewGroup tabs = (ViewGroup) a.findViewById(Resources.id.tabs);
        ViewGroup tabsContainer = (ViewGroup) tabs.getChildAt(0);
        ImageView tab1 = (ImageView) tabsContainer.getChildAt(0);
        ImageView tab2 = (ImageView) tabsContainer.getChildAt(1);
        ImageView tab3 = (ImageView) tabsContainer.getChildAt(2);

        a.getSupportActionBar().setElevation(0);
        if (tabs != null) tabs.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR));

        if (tab1 != null) tab1.setImageDrawable(Utils.tintToColor(tab1.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE)));
        if (tab2 != null) tab2.setImageDrawable(Utils.tintToColor(tab2.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE)));
        if (tab3 != null) tab3.setImageDrawable(Utils.tintToColor(tab3.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE)));

        ViewGroup pager = (ViewGroup) a.findViewById(Resources.getID("pager"));
        if (pager != null) pager.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
    }
}
