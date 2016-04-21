package com.whatsapp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by brianvalente on 12/23/15.
 */
public class HomeActivity extends AppCompatActivity {
    // Search
    public boolean onSearchRequested() {
        return false;
    }

    public class TabsPager extends com.wamod.ViewPager {
        public TabsPager(Context context) {
            super(context);
        }

        public TabsPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public void setCurrentItem(int id) {}
    }
}
