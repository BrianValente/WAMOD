package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wamod.Resources;
import com.wamod.utils;

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
        tabs.setBackgroundColor(utils.getUIColor(utils.COLOR_TOOLBAR));

        tab1.setImageDrawable(utils.tintToColor(tab1.getDrawable(), utils.getUIColor(utils.COLOR_TOOLBARTEXT)));
        tab2.setImageDrawable(utils.tintToColor(tab2.getDrawable(), utils.getUIColor(utils.COLOR_TOOLBARTEXT)));
        tab3.setImageDrawable(utils.tintToColor(tab3.getDrawable(), utils.getUIColor(utils.COLOR_TOOLBARTEXT)));
    }
}
