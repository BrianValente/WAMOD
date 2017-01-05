package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.wamod.ColorsManager;
import com.wamod.Utils;

/**
 * Created by brianvalente on 9/20/15.
 */
public class ArchivedConversationsActivity {
    public static void _onCreate(AppCompatActivity a) {
        ListView list = (ListView) a.findViewById(android.R.id.list);
        list.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));
    }
}
