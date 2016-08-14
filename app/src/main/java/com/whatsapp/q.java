package com.whatsapp;

import android.view.View;

/**
 * Created by brianvalente on 9/21/15.
 */
public class q implements View.OnClickListener {
    // SEND CONTACT
    @Override
    public void onClick(View v) {}

    private q(Conversation conversation) {}

    public q() {}

    public static View.OnClickListener a(Conversation conversation) {
        return new q(conversation);
    }
}
