package com.wamod.entry;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.wamod.Resources;
import com.wamod.utils;
import com.whatsapp.Conversation;
import com.whatsapp.api;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Aran {
    public static void init(final Conversation a) {

        FrameLayout footer = (FrameLayout) a.findViewById(Resources.id.footer);
        FrameLayout inputLayout = (FrameLayout) a.findViewById(Resources.id.input_layout);
        ImageButton emojiBtn = (ImageButton) a.findViewById(Resources.id.wamod_theme_hangouts_conversation_emoji);
        ImageButton voiceNoteBtn = (ImageButton) a.findViewById(Resources.id.voice_note_btn);
        ImageButton sendBtn = (ImageButton) a.findViewById(Resources.id.send);
        EditText entryET = (EditText) a.findViewById(Resources.id.entry);

        footer.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_bgcolor", "000000")));

        Drawable inputLayoutBg = inputLayout.getBackground();
        inputLayoutBg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_entry_bgcolor", "222222")), PorterDuff.Mode.MULTIPLY);
        inputLayout.setBackground(inputLayoutBg);

        emojiBtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_emoji_color", "ffffff")), PorterDuff.Mode.MULTIPLY);

        voiceNoteBtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_mic_color", "ee5555")), PorterDuff.Mode.MULTIPLY);

        sendBtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_send_color", "ffffff")), PorterDuff.Mode.MULTIPLY);

        entryET.setTextColor(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_entry_textcolor", "ffffff")));
        entryET.setHintTextColor(Color.parseColor("#" + utils.prefs.getString("theme_aran_conversation_entry_hintcolor", "ffffff")));

        emojiBtn.setOnClickListener(new api(a));
    }
}
