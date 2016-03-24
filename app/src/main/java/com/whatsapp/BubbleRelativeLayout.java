package com.whatsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by BrianValente on 3/1/16.
 */
public class BubbleRelativeLayout extends RelativeLayout {
    public static boolean extension = false;

    public BubbleRelativeLayout(Context context) {
        super(context);
    }

    public BubbleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BubbleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public int d() {
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setExtension();
    }

    private void setExtension() {
        extension = true;
    }
}
