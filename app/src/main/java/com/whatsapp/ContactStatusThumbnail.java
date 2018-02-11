package com.whatsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by brianvalente on 11/27/17.
 */

public class ContactStatusThumbnail extends ThumbnailButton {

    public ContactStatusThumbnail(Context context) {
        super(context);
    }

    public ContactStatusThumbnail(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ContactStatusThumbnail(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRingValues(int unreadCount, int total) {

    }

}
