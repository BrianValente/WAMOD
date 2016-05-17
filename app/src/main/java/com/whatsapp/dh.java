package com.whatsapp;

import android.view.View;

/**
 * Created by brianvalente on 9/21/15.
 */
public class dh implements View.OnLongClickListener {
    // OPENS ANDROID GALLERY
    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public dh(Conversation conversation) {}

    public dh() {}
}
