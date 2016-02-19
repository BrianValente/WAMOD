package com.wamod;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
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
    private static final float BITMAP_SCALE = 0.4f;
    private static final float BLUR_RADIUS = 7.5f;

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
        entry.clearFocus();


        if (utils.prefs.getBoolean("conversation_androidgallery", true)) {
            gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Method to open Android gallery
                    Conversation.ag(activity);
                }
            });
        } else gallery.setOnClickListener(new nb(activity));

        camera.setOnClickListener(new a55(activity, true));
        emoji.setOnClickListener(new vm(activity));
        location.setOnClickListener(new jq(activity));
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap RGB565toARGB888(Bitmap img) {
        int numPixels = img.getWidth()* img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }

    public static Bitmap blur(Context context, Bitmap image) {
        image = RGB565toARGB888(image);

        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static void initBK(final com.whatsapp.Conversation activity) {
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
        entry.clearFocus();


        if (utils.prefs.getBoolean("conversation_androidgallery", true)) {
            gallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Method to open Android gallery
                    Conversation.ag(activity);
                }
            });
        } else gallery.setOnClickListener(new nb(activity));

        camera.setOnClickListener(new a55(activity, true));
        emoji.setOnClickListener(new vm(activity));
        location.setOnClickListener(new jq(activity));
    }
}
