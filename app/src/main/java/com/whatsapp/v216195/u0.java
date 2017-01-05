package com.whatsapp.v216195;

import android.view.View;

/**
 * Created by brianvalente on 9/21/15.
 */
public class u0 implements View.OnClickListener {
    // OPENS EMOJI PICKER
    @Override
    public void onClick(View v) {}

    private u0(Conversation conversation) {}

    public u0() {}

    public static View.OnClickListener a(Conversation conversation) {
        return new u0(conversation);
    }
}
