package wamod.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import wamod.utils.Resources;

public class Toolbar extends android.widget.Toolbar {

    public Toolbar(Context context) {
        super(context);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setTitleTextColor(int color) {
        TextView tv = findViewById(Resources.getId(getContext(), "title"));
        tv.setTextColor(color);
    }

    @Override
    public void setTitle(CharSequence title) {
        TextView tv = findViewById(Resources.getId(getContext(), "title"));
        tv.setText(title);
    }

    public void setTitleTypeface(Typeface typeface) {
        TextView tv = findViewById(Resources.getId(getContext(), "title"));
        tv.setTypeface(typeface);
    }
}
