package com.whatsapp;

import android.view.View;

/**
 * Created by brianvalente on 9/21/15.
 */
public class aic implements View.OnClickListener {
    // SEND AUDIO
    @Override
    public void onClick(View v) {}

    private aic(Conversation conversation) {}

    public aic() {}

    public static View.OnClickListener a(Conversation conversation) {
        return new aic(conversation);
    }
}
