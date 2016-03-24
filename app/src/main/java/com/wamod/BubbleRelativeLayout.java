package com.wamod;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BrianValente on 3/1/16.
 */
public class BubbleRelativeLayout {
    public static void init(final com.whatsapp.BubbleRelativeLayout bubble) {
        ImageView status = (ImageView) bubble.findViewById(Resources.id.status);
        boolean rightBubble = false;
        if (status != null) rightBubble = true;
        boolean changeDateColor = false;
        boolean extension = bubble.extension;
        //if (bubble.getTag(Resources.id.wamod_drawer_debug1) != null) extension = true;

        /*int paddingHorizontal = utils.Nexus6PResToActualDevice(bubble.getContext(), 20, 0)[0];
        int paddingVertical = utils.Nexus6PResToActualDevice(bubble.getContext(), 0, 7)[1];
        bubble.setPadding(paddingHorizontal, paddingVertical, paddingHorizontal, paddingVertical);*/

        LinearLayout date_wrapper = (LinearLayout) bubble.findViewById(Resources.id.date_wrapper);

        // Text message
        LinearLayout main_layout = (LinearLayout) bubble.findViewById(Resources.id.main_layout);
        if (main_layout != null) {

            /*Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            main_layout.setBackground(bubbleBackground);*/

            /*main_layout.setPadding(0,0,0,0);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) main_layout.getLayoutParams();
            params.setMargins(0,0,0,0);
            main_layout.setLayoutParams(params);
            main_layout.offsetLeftAndRight(0);*/
        }

        // Text message
        TextView message_text = (TextView) bubble.findViewById(Resources.id.message_text);
        if (message_text != null) {
            if (rightBubble) message_text.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF")));
            else message_text.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF")));
            changeDateColor = true;

            /*int left = message_text.getWidth();
            int top = date_wrapper.getPaddingTop();
            int right = date_wrapper.getPaddingRight();
            int bottom = date_wrapper.getPaddingBottom();
            date_wrapper.setPadding(left, top, right, bottom);*/
        }

        // Image
        FrameLayout image_holder = (FrameLayout) bubble.findViewById(Resources.id.image_holder);
        if (image_holder != null) {
            /*LinearLayout parent = (LinearLayout) image_holder.getParent();
            Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            parent.setBackground(bubbleBackground);*/
            int paddingRight = utils.Nexus6PResToActualDevice(bubble.getContext(), 70, 0)[0];
            int paddingBottom = utils.Nexus6PResToActualDevice(bubble.getContext(), 0, 25)[1];
            date_wrapper.setPadding(date_wrapper.getPaddingLeft(), date_wrapper.getPaddingTop(), paddingRight, paddingBottom);
        }

        // Video
        ImageView thumb = (ImageView) bubble.findViewById(Resources.id.thumb);
        if (thumb != null) {
            /*View parent = (View) thumb.getParent().getParent();
            Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            parent.setBackground(bubbleBackground);*/
        }

        // Audio
        FrameLayout control_btn_holder = (FrameLayout) bubble.findViewById(Resources.id.control_btn_holder);
        if (control_btn_holder != null) {
            /*View parent = (View) control_btn_holder.getParent();
            Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            parent.setBackground(bubbleBackground);*/

            changeDateColor = true;
        }

        // Image/video caption
        TextView caption = (TextView) bubble.findViewById(Resources.id.caption);
        if (caption != null) {
            if (rightBubble) caption.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF")));
            else caption.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF")));

            if (thumb == null) changeDateColor = true;
        }

        // Documents
        LinearLayout content = (LinearLayout) bubble.findViewById(Resources.id.content);
        if (content != null) {
            /*Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            content.setBackground(bubbleBackground);*/

            TextView title = (TextView) bubble.findViewById(Resources.id.title);
            TextView info = (TextView) bubble.findViewById(Resources.id.info);
            TextView bullet_info = (TextView) bubble.findViewById(Resources.id.bullet_info);
            TextView file_type = (TextView) bubble.findViewById(Resources.id.file_type);
            int infoTextColor;
            if (rightBubble) infoTextColor = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbledtextcolor", "FFFFFF"));
            else infoTextColor = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF"));
            if (title != null) title.setTextColor(infoTextColor);
            if (info != null) info.setTextColor(infoTextColor);
            if (bullet_info != null) bullet_info.setTextColor(infoTextColor);
            if (file_type != null) file_type.setTextColor(infoTextColor);

            changeDateColor = true;
        }

        // Web links
        FrameLayout web_page_preview_holder = (FrameLayout) bubble.findViewById(Resources.id.web_page_preview_holder);
        if (web_page_preview_holder != null) {
            int color;
            if (rightBubble) color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF"));
            else color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF"));
            TextView title = (TextView) bubble.findViewById(Resources.id.title);
            TextView url = (TextView) bubble.findViewById(Resources.id.url);
            if (title != null) title.setTextColor(color);
            if (url != null) url.setTextColor(color);
        }

        // Contacts
        LinearLayout contact_card = (LinearLayout) bubble.findViewById(Resources.id.contact_card);
        if (contact_card != null) {
            /*Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            ((View)contact_card.getParent()).setBackground(bubbleBackground);*/

            TextView vcard_text = (TextView) bubble.findViewById(Resources.id.vcard_text);
            TextView msg_contact_btn = (TextView) bubble.findViewById(Resources.id.msg_contact_btn);
            TextView add_contact_btn = (TextView) bubble.findViewById(Resources.id.add_contact_btn);
            int infoTextColor;
            if (rightBubble) infoTextColor = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF"));
            else infoTextColor = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF"));
            if (vcard_text != null) vcard_text.setTextColor(infoTextColor);
            if (msg_contact_btn != null) msg_contact_btn.setTextColor(infoTextColor);
            if (add_contact_btn != null) add_contact_btn.setTextColor(infoTextColor);

            changeDateColor = true;
        }

        // Voice note
        FrameLayout thumbnail = (FrameLayout) bubble.findViewById(Resources.id.thumbnail);
        if (thumbnail != null) {
            /*View parent = (View) thumbnail.getParent();
            Drawable bubbleBackground;
            int color;
            if (rightBubble) {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 3:2));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF"));
            }
            else {
                bubbleBackground = bubble.getResources().getDrawable(Conversation.getBubbleDrawableHex(extension? 1:0));
                color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF"));
            }
            bubbleBackground.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            parent.setBackground(bubbleBackground);*/

            int color;
            TextView duration = (TextView) bubble.findViewById(Resources.id.duration);
            if (rightBubble) color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF"));
            else color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF"));
            if (duration != null) duration.setTextColor(color);

            changeDateColor = true;
        }

        if (changeDateColor) {
            TextView date = (TextView) bubble.findViewById(Resources.id.date);
            if (date != null) {
                int color;
                if (rightBubble) color = Color.parseColor("#" + utils.prefs.getString("conversation_rightbubbledatecolor", "FFFFFF"));
                else color = Color.parseColor("#" + utils.prefs.getString("conversation_leftbubbledatecolor", "FFFFFF"));
                date.setTextColor(color);
            }
        }

        TextView name_in_group_tv = (TextView) bubble.findViewById(Resources.id.name_in_group_tv);
        if (name_in_group_tv != null && utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)) {
            name_in_group_tv.setTextColor(Color.parseColor("#" + utils.prefs.getString("conversation_customparticipantcolor", "FFFFFF")));
        }
    }

    private void callInit() {
        init(null);
    }

    public static void isExtension(com.whatsapp.BubbleRelativeLayout bubble) {
        bubble.setTag(Resources.id.wamod_drawer_debug1, true);
    }

    public static Drawable getBalloonDrawable(int balloon) {
        Drawable bubble = utils.context.getResources().getDrawable(Conversation.getBubbleDrawableHex(balloon));
        switch (balloon) {
            case 0:
            case 1:
                bubble.setColorFilter(Color.parseColor("#" + utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
            case 3:
                bubble.setColorFilter(Color.parseColor("#" + utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        }
        return bubble;
    }

    public void call_getBalloonDrawable() {
        Drawable drawable = getBalloonDrawable(0);
    }
}
