package com.whatsapp;

import android.view.View;

/**
 * Created by brianvalente on 9/21/15.
 */
public class tk implements View.OnClickListener {
    // SEND LOCATION
    @Override
    public void onClick(View v) {}

    private tk(Conversation conversation) {}

    public tk() {}

    public static View.OnClickListener a(Conversation conversation) {
        return new tk(conversation);
    }
}
