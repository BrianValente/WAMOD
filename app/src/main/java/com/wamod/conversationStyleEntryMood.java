package com.wamod;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.whatsapp.api;

/**
 * Created by brianvalente on 10/4/15.
 */
public class conversationStyleEntryMood {
    public static void init(com.whatsapp.Conversation a) {
        EditText entry = (EditText) a.findViewById(Resources.id.entry);
        ImageButton voiceNoteBtn = (ImageButton) a.findViewById(Resources.id.voice_note_btn);
        ImageButton sendBtn = (ImageButton) a.findViewById(Resources.id.send);
        ImageButton emojiBtn = (ImageButton) a.findViewById(Resources.id.wamod_theme_hangouts_conversation_emoji);
        LinearLayout inputLayout = (LinearLayout) a.findViewById(Resources.id.input_layout);

        entry.setHintTextColor(Color.parseColor("#" + utils.prefs.getString("theme_mood_conversation_entry_hintcolor", "000000")));
        entry.setTextColor(Color.parseColor("#" + utils.prefs.getString("theme_mood_conversation_entry_textcolor", "000000")));
        entry.clearFocus();

        voiceNoteBtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_mood_conversation_mic_color", "000000")));
        sendBtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_mood_conversation_send_color", "000000")));
        emojiBtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_mood_conversation_emoji_color", "000000")));
        emojiBtn.setOnClickListener(new api(a));

        Drawable inputLayoutBg = inputLayout.getBackground();
        inputLayoutBg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_mood_conversation_background_color", "55ffffff")), PorterDuff.Mode.MULTIPLY);
        inputLayout.setBackground(inputLayoutBg);
    }
}
