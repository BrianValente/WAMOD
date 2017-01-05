package com.whatsapp.v216195;

import android.view.View;

/**
 * Created by brianvalente on 9/21/15.
 */
public class dk implements View.OnLongClickListener {
    // OPENS ANDROID GALLERY
    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    private dk(Conversation conversation) {}

    public dk() {}

    public static View.OnLongClickListener a(Conversation conversation) {
        return new dk(conversation);
    }
}
