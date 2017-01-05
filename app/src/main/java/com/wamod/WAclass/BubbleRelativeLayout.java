package com.wamod.WAclass;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by BrianValente on 3/1/16.
 */
public class BubbleRelativeLayout {

    /* Called on
     *    com.whatsapp.BubbleRelativeLayout.onDraw(Landroid/graphics/Canvas;)V
     * Where
     *    Before return-void
     * Smali
     *    invoke-static {p0}, Lcom/wamod/WAclass/BubbleRelativeLayout;->init(Lcom/whatsapp/BubbleRelativeLayout;)V
     */
    public static void init(final com.whatsapp.BubbleRelativeLayout bubble) {
        ImageView status = (ImageView) bubble.findViewById(Resources.id.status);
        boolean rightBubble = false;
        if (status != null) rightBubble = true;
        boolean changeDateColor = false;
        LinearLayout date_wrapper = (LinearLayout) bubble.findViewById(Resources.id.date_wrapper);

        // Text message
        TextView message_text = (TextView) bubble.findViewById(Resources.id.message_text);
        if (message_text != null) {
            if (rightBubble) message_text.setTextColor(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE));
            else message_text.setTextColor(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE));
            changeDateColor = true;
        }

        // Image
        FrameLayout image_holder = (FrameLayout) bubble.findViewById(Resources.id.image_holder);
        if (image_holder != null) {
            int paddingRight = Utils.Nexus6PResToActualDevice(bubble.getContext(), 70, 0)[0];
            int paddingBottom = Utils.Nexus6PResToActualDevice(bubble.getContext(), 0, 25)[1];
            date_wrapper.setPadding(date_wrapper.getPaddingLeft(), date_wrapper.getPaddingTop(), paddingRight, paddingBottom);
        }

        // Video
        ImageView thumb = (ImageView) bubble.findViewById(Resources.id.thumb);
        if (thumb != null) {
        }

        // Audio
        FrameLayout control_btn_holder = (FrameLayout) bubble.findViewById(Resources.id.control_btn_holder);
        if (control_btn_holder != null) {
            changeDateColor = true;
        }

        // Image/video caption
        TextView caption = (TextView) bubble.findViewById(Resources.id.caption);
        if (caption != null) {
            if (rightBubble)
                caption.setTextColor(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE));
            else
                caption.setTextColor(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE));

            if (thumb == null)
                changeDateColor = true;
        }

        // Documents
        LinearLayout content = (LinearLayout) bubble.findViewById(Resources.id.content);
        if (content != null) {
            TextView title = (TextView) bubble.findViewById(Resources.id.title);
            TextView info = (TextView) bubble.findViewById(Resources.id.info);
            TextView bullet_info = (TextView) bubble.findViewById(Resources.id.bullet_info);
            TextView file_type = (TextView) bubble.findViewById(Resources.id.file_type);
            int infoTextColor;
            if (rightBubble)
                infoTextColor = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE);
            else
                infoTextColor = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE);
            if (title != null)
                title.setTextColor(infoTextColor);
            if (info != null)
                info.setTextColor(infoTextColor);
            if (bullet_info != null)
                bullet_info.setTextColor(infoTextColor);
            if (file_type != null)
                file_type.setTextColor(infoTextColor);

            changeDateColor = true;
        }

        // Web links
        FrameLayout web_page_preview_holder = (FrameLayout) bubble.findViewById(Resources.id.web_page_preview_holder);
        if (web_page_preview_holder != null) {
            int color;
            if (rightBubble)
                color = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE);
            else
                color = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE);
            TextView title = (TextView) bubble.findViewById(Resources.id.title);
            TextView url = (TextView) bubble.findViewById(Resources.id.url);
            if (title != null)
                title.setTextColor(color);
            if (url != null)
                url.setTextColor(color);
        }

        // Contacts
        LinearLayout contact_card = (LinearLayout) bubble.findViewById(Resources.id.contact_card);
        if (contact_card != null) {
            TextView vcard_text = (TextView) bubble.findViewById(Resources.id.vcard_text);
            TextView msg_contact_btn = (TextView) bubble.findViewById(Resources.id.msg_contact_btn);
            TextView add_contact_btn = (TextView) bubble.findViewById(Resources.id.add_contact_btn);
            int infoTextColor;
            if (rightBubble)
                infoTextColor = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE);
            else
                infoTextColor = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE);
            if (vcard_text != null)
                vcard_text.setTextColor(infoTextColor);
            if (msg_contact_btn != null)
                msg_contact_btn.setTextColor(infoTextColor);
            if (add_contact_btn != null)
                add_contact_btn.setTextColor(infoTextColor);

            changeDateColor = true;
        }

        // Voice note
        FrameLayout thumbnail = (FrameLayout) bubble.findViewById(Resources.id.thumbnail);
        if (thumbnail != null) {
            int color;
            TextView duration = (TextView) bubble.findViewById(Resources.id.duration);
            if (rightBubble) color = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE);
            else color = ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE);
            if (duration != null) duration.setTextColor(color);

            changeDateColor = true;
        }

        // Quoted message
        View quoted_color = bubble.findViewById(Resources.getID("quoted_color"));
        if (quoted_color != null) {
            int color = rightBubble? ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE)
                                   : ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE);
            quoted_color.setBackgroundColor(color);

            TextView quoted_name = (TextView) bubble.findViewById(Resources.getID("quoted_name"));
            if (quoted_name != null) quoted_name.setTextColor(color);

            TextView quoted_text = (TextView) bubble.findViewById(Resources.getID("quoted_text"));
            if (quoted_text != null) quoted_text.setTextColor(color);
        }

        if (changeDateColor) {
            TextView date = (TextView) bubble.findViewById(Resources.id.date);
            if (date != null) {
                int color = rightBubble? ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_MESSAGE)
                        : ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_MESSAGE);
                date.setTextColor(color);
            }
        }

        TextView name_in_group_tv = (TextView) bubble.findViewById(Resources.id.name_in_group_tv);
        if (name_in_group_tv != null && Utils.prefs.getBoolean("conversation_customparticipantcolorbool", false))
            name_in_group_tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_PARTICIPANT));
    }


    public static void isExtension(com.whatsapp.BubbleRelativeLayout bubble) {
        bubble.setTag(Resources.id.wamod_drawer_debug1, true);
    }


    /* Called on
     *    com.whatsapp.BubbleRelativeLayout.<clinit>()V
     * Where
     *    Where the bubbles drawables are saved, replace the code for the four bubbles
     * Smali
     *    const/4 v0, 0x2
     *    invoke-static {v0}, Lcom/wamod/WAclass/BubbleRelativeLayout;->getBalloonDrawable(I)Landroid/graphics/drawable/Drawable;
     *    move-result-object v0
     *    sput-object v0, Lcom/whatsapp/BubbleRelativeLayout;->e:Landroid/graphics/drawable/Drawable;
     */
    public static Drawable getBalloonDrawable(int balloon) {
        Drawable bubble = Utils.context.getResources().getDrawable(Conversation.getBubbleDrawableHex(balloon));
        switch (balloon) {
            case 0:
            case 1:
                bubble.setColorFilter(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_LEFT_BACKGROUND), PorterDuff.Mode.MULTIPLY);
                break;
            case 2:
            case 3:
                bubble.setColorFilter(ColorsManager.getColor(ColorsManager.UI_CONVERSATION_BUBBLE_RIGHT_BACKGROUND), PorterDuff.Mode.MULTIPLY);
        }
        return bubble;
    }

    public void call_getBalloonDrawable() {
        Drawable drawable = getBalloonDrawable(0);
    }
}
