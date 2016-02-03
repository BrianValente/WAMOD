package com.wamod;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by brianvalente on 11/23/15.
 */
public class ConversationRowText extends AppCompatActivity {
    boolean left = false;

    public static void changeLeftTVColor(FrameLayout fl) {
        TextView messageTV = (TextView) fl.findViewById(id.message_text);
        messageTV.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "ffffff")));
    }

    public static void changeRightTVColor(FrameLayout fl) {
        TextView messageTV = (TextView) fl.findViewById(id.message_text);
        messageTV.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbletextcolor", "ffffff")));
    }

    public void setLeft() {
        left = true;
    }

    public void callchangetvcolor() {
        FrameLayout fl = (FrameLayout) findViewById(id.text_content_layout);
        if (left) changeLeftTVColor(fl);
        else changeRightTVColor(fl);
    }
}
