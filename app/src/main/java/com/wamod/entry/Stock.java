package com.wamod.entry;

import android.graphics.Color;
import android.widget.EditText;

import com.wamod.Resources;

/**
 * Created by brianvalente on 5/8/16.
 */
public class Stock {
    public static void init(final com.whatsapp.Conversation activity) {
        final EditText entry = (EditText) activity.findViewById(Resources.id.entry);
        entry.setTextColor(Color.BLACK);
    }
}
