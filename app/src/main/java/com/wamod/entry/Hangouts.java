package com.wamod.entry;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wamod.Events;
import com.wamod.Resources;
import com.wamod.utils;
import com.whatsapp.*;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Hangouts {
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;

    public static void init(final com.whatsapp.Conversation activity) {
        ImageButton gallery = (ImageButton) activity.findViewById(Resources.id.wamod_theme_hangouts_conversation_gallery);
        ImageButton camera = (ImageButton) activity.findViewById(Resources.id.wamod_theme_hangouts_conversation_camera);
        ImageButton emoji = (ImageButton) activity.findViewById(Resources.id.wamod_theme_hangouts_conversation_emoji);
        ImageButton location = (ImageButton) activity.findViewById(Resources.id.wamod_theme_hangouts_conversation_location);

        gallery.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));
        camera.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));
        emoji.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));
        location.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_attach_color", "ffffff")));

        RelativeLayout inputlayout = (RelativeLayout) activity.findViewById(Resources.id.input_layout);
        inputlayout.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_entry_bgcolor", "FFFFFF")));

        ImageButton voicenotebtn = (ImageButton) activity.findViewById(Resources.id.voice_note_btn);
        voicenotebtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_mic_color", "ffffff")));
        Drawable bg = voicenotebtn.getBackground();
        bg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_mic_bg", "ffffff")), PorterDuff.Mode.MULTIPLY);
        voicenotebtn.setBackground(bg);

        ImageButton sendbtn = (ImageButton) activity.findViewById(Resources.id.send);
        sendbtn.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_send_color", "ffffff")));
        bg = sendbtn.getBackground();
        bg.setColorFilter(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_send_bg", "ffffff")), PorterDuff.Mode.MULTIPLY);
        sendbtn.setBackground(bg);

        EditText entry = (EditText) activity.findViewById(Resources.id.entry);
        entry.setTextColor(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_entry_textcolor", "FFFFFF")));
        entry.setHintTextColor(Color.parseColor("#" + utils.prefs.getString("theme_hangouts_conversation_entry_hintcolor", "FFFFFF")));
        entry.clearFocus();


        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Method to open Android gallery
                new Events.AttachmentsManager(activity).attachFromGallery();
            }
        });

        camera.setOnClickListener(new aec(activity, true));
        emoji.setOnClickListener(new api(activity));
        location.setOnClickListener(new aqy(activity));
    }
}
