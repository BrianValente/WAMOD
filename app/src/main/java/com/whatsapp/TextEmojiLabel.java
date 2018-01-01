package com.whatsapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by brianvalente on 11/27/17.
 */

public class TextEmojiLabel extends TextView {

    public TextEmojiLabel(Context context) {
        super(context);
    }

    public TextEmojiLabel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextEmojiLabel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextEmojiLabel(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
