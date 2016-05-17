package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        if (tabs != null) tabs.setBackgroundColor(Utils.getUIColor(Utils.COLOR_TOOLBAR));

        if (tab1 != null) tab1.setImageDrawable(Utils.tintToColor(tab1.getDrawable(), Utils.getUIColor(Utils.COLOR_TOOLBARTEXT)));
        if (tab2 != null) tab2.setImageDrawable(Utils.tintToColor(tab2.getDrawable(), Utils.getUIColor(Utils.COLOR_TOOLBARTEXT)));
        if (tab3 != null) tab3.setImageDrawable(Utils.tintToColor(tab3.getDrawable(), Utils.getUIColor(Utils.COLOR_TOOLBARTEXT)));
    }
}
