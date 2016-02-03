package com.wamod;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.whatsapp.*;
import com.whatsapp.Conversation;

/**
 * Created by brianvalente on 9/21/15.
 */
public class conversationStyleEntryHangouts {
    public static void init(final com.whatsapp.Conversation activity) {
        ImageButton gallery = (ImageButton) activity.findViewById(id.conversationentryhangouts_gallery);
        ImageButton camera = (ImageButton) activity.findViewById(id.conversationentryhangouts_camera);
        ImageButton emoji = (ImageButton) activity.findViewById(id.conversationentryhangouts_emoji);
        ImageButton location = (ImageButton) activity.findViewById(id.conversationentryhangouts_location);

        gallery.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));
        camera.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));
        emoji.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));
        location.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));

        RelativeLayout inputlayout = (RelativeLayout) activity.findViewById(id.input_layout);
        inputlayout.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_entry_bgcolor", "FFFFFF")));

        ImageButton voicenotebtn = (ImageButton) activity.findViewById(id.voice_note_btn);
        voicenotebtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_mic_color", "ffffff")));
        Drawable bg = voicenotebtn.getBackground();
        bg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_mic_bg", "ffffff")), PorterDuff.Mode.MULTIPLY);
        voicenotebtn.setBackground(bg);

        ImageButton sendbtn = (ImageButton) activity.findViewById(id.send);
        sendbtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_send_color", "ffffff")));
        bg = sendbtn.getBackground();
        bg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_send_bg", "ffffff")), PorterDuff.Mode.MULTIPLY);
        sendbtn.setBackground(bg);

        EditText entry = (EditText) activity.findViewById(id.entry);
        entry.setTextColor(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_entry_textcolor", "FFFFFF")));
        entry.setHintTextColor(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_entry_hintcolor", "FFFFFF")));


        if (utils.prefs.getBoolean("conversation_androidgallery", true)) {
            gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Method to open Android gallery
                    Conversation.U(activity);
                }
            });
        } else gallery.setOnClickListener(new bi(activity));

        camera.setOnClickListener(new ac2(activity));
        emoji.setOnClickListener(new av5(activity));
        location.setOnClickListener(new ahw(activity));
    }
}
