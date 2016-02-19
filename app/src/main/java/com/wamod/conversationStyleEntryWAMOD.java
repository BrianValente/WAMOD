package com.wamod;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.whatsapp.*;
import com.whatsapp.Conversation;

/**
 * Created by brianvalente on 9/21/15.
 */
public class conversationStyleEntryWAMOD {
    public static void init(final com.whatsapp.Conversation activity) {
        final View footer = activity.findViewById(id.conversationentrywamod_footer);
        final Drawable bg = activity.getResources().getDrawable(id.conversationentrywamod_bg);
        final FrameLayout inputBg = (FrameLayout) activity.findViewById(id.input_layout);
        final EditText entry = (EditText) activity.findViewById(id.entry);
        final ImageButton gallery = (ImageButton) activity.findViewById(id.conversationentrywamod_gallery);
        final ImageButton audio = (ImageButton) activity.findViewById(id.conversationentrywamod_audio);
        final ImageButton location = (ImageButton) activity.findViewById(id.conversationentrywamod_location);
        final ImageButton contact = (ImageButton) activity.findViewById(id.conversationentrywamod_contact);
        final ImageButton camera = (ImageButton) activity.findViewById(id.conversationentrywamod_camera);
        final ImageButton send = (ImageButton) activity.findViewById(id.send);
        final ImageButton voicenote = (ImageButton) activity.findViewById(id.voice_note_btn);
        final ImageButton emojibtn = (ImageButton) activity.findViewById(id.emojibtn);

        entry.clearFocus();


        footer.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_bgcolor", "ffffff")));
        bg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_entrybgcolor", "ffffff")), PorterDuff.Mode.MULTIPLY);
        inputBg.setBackground(bg);

        entry.setHintTextColor(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_hinttextcolor", "ffffff")));
        entry.setTextColor(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_textcolor", "ffffff")));

        gallery.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_btncolor", "ffffff")));
        audio.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_btncolor", "ffffff")));
        location.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_btncolor", "ffffff")));
        contact.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_btncolor", "ffffff")));
        camera.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_btncolor", "ffffff")));

        send.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_sendbtncolor", "ffffff")));
        voicenote.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_sendbtncolor", "ffffff")));

        emojibtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_wamod_conversation_entry_emojibtncolor", "ffffff")));


        final int originalPadding = entry.getPaddingRight();
        entry.setGravity(Gravity.LEFT | Gravity.CENTER);
        if (entry.getText().toString().trim().length() == 0) {
            entry.setGravity(Gravity.CENTER);
            entry.setPadding(entry.getPaddingLeft(), entry.getPaddingTop(), entry.getPaddingLeft(), entry.getPaddingBottom());
            //entry.setCursorVisible(false);
        }
        entry.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    entry.setGravity(Gravity.LEFT | Gravity.CENTER);
                    entry.setPadding(entry.getPaddingLeft(), entry.getPaddingTop(), originalPadding, entry.getPaddingBottom());
                    entry.setCursorVisible(true);
                } else {
                    entry.setGravity(Gravity.CENTER);
                    entry.setPadding(entry.getPaddingLeft(), entry.getPaddingTop(), entry.getPaddingLeft(), entry.getPaddingBottom());
                    //entry.setCursorVisible(false);
                }
            }
        });

        if (utils.prefs.getBoolean("conversation_androidgallery", true)) {
            gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Method to open Android gallery
                    Conversation.ag(activity);
                }
            });
        } else gallery.setOnClickListener(new nb(activity));

        gallery.setOnLongClickListener(new _d(activity));
        audio.setOnClickListener(new alh(activity));
        location.setOnClickListener(new jq(activity));
        contact.setOnClickListener(new al6(activity));
        camera.setOnClickListener(new a55(activity, true));
    }
}
