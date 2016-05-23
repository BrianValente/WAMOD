package com.wamod.WAclass;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.wamod.R;
import com.wamod.Utils;

/**
 * Created by brianvalente on 9/20/15.
 */
public class ArchivedConversationsActivity {
    public static void _onCreate(AppCompatActivity a) {
        if (Utils.nightModeShouldRun()) {
            ListView list = (ListView) a.findViewById(android.R.id.list);
            list.setBackgroundColor(Utils.getDarkColor(2));
        }
    }
}
