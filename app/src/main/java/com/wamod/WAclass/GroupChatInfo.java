package com.wamod.WAclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Privacy;
import com.wamod.Resources;
import com.wamod.Utils;

public class GroupChatInfo extends AppCompatActivity {
    public static void _onCreate(final AppCompatActivity a) {
        try {
            final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
            content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ListView list = (ListView) a.findViewById(android.R.id.list);
                    list.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));

                    ViewGroup media_card = (ViewGroup) a.findViewById(Resources.id.media_card);
                    ViewGroup media_card_2 = (ViewGroup) media_card.getChildAt(0);
                    TextView media_title = (TextView) a.findViewById(Resources.id.media_title);
                    TextView media_info = (TextView) a.findViewById(Resources.id.media_info);
                    if (media_card_2 != null)
                        media_card_2.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
                    if (media_title != null) media_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    if (media_info != null) media_info.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));


                    ViewGroup mute_layout = (ViewGroup) a.findViewById(Resources.id.mute_layout);
                    ViewGroup muteContainer = (ViewGroup) mute_layout.getParent();
                    TextView muteTV = (TextView) ((ViewGroup) mute_layout.getChildAt(0)).getChildAt(0);
                    TextView customNotificationsTV = (TextView) ((ViewGroup) a.findViewById(Resources.id.notifications_layout)).getChildAt(0);


                    TextView encryption_info = (TextView) a.findViewById(Resources.id.encryption_info);
                    if (encryption_info != null) {
                        TextView encryption_title = (TextView) ((ViewGroup) encryption_info.getParent()).getChildAt(0);
                        ImageView encryption_indicator = (ImageView) a.findViewById(Resources.id.encryption_indicator);

                        if (encryption_title != null) encryption_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                        if (encryption_info != null) encryption_info.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
                        if (encryption_indicator != null) encryption_indicator.setImageDrawable(Utils.tintToColor(encryption_indicator.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
                    }


                    ViewGroup starred_messages_layout = (ViewGroup) a.findViewById(Resources.getID("starred_messages_layout"));
                    TextView starred_messages = (TextView) starred_messages_layout.getChildAt(0);
                    TextView starred_messages_count = (TextView) a.findViewById(Resources.id.starred_messages_count);
                    if (starred_messages != null) starred_messages.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    if (starred_messages_count != null) starred_messages_count.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));


                    muteContainer.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
                    muteTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    customNotificationsTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));


                    TextView participants_title = (TextView) a.findViewById(Resources.id.participants_title);
                    ViewGroup participants_header = (ViewGroup) participants_title.getParent();
                    participants_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    participants_header.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));


                    ViewGroup exit_group_btn = (ViewGroup) a.findViewById(Resources.id.exit_group_btn) ;
                    exit_group_btn.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));


                    ViewGroup add_participant_layout = (ViewGroup) a.findViewById(Resources.id.add_participant_layout);
                    if (add_participant_layout != null) add_participant_layout.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
                    TextView add_participant_text = (TextView) a.findViewById(Resources.id.add_participant_text);
                    ImageView add_participant_icon = (ImageView) a.findViewById(Resources.id.add_participant_icon);
                    if (add_participant_text != null) add_participant_text.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    if (add_participant_icon != null) add_participant_icon.setImageDrawable(Utils.tintToColor(add_participant_icon.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));


                    content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } catch (Exception e) {
            Utils.manageException(e);
        }

        Privacy.initPrivacyOnChatInfo(a);
    }


    /* Called on
     *    com.whatsapp.z4.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Before
     *    return-object p2
     * Smali
     *    invoke-static {p2}, Lcom/wamod/WAclass/GroupChatInfo;->_getView(Landroid/view/View;)V
     */
    public static void _getView(View v) {
        try {
            v.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
            TextView name = (TextView) v.findViewById(Resources.id.name);
            TextView status = (TextView) v.findViewById(Resources.id.status);
            TextView owner = (TextView) v.findViewById(Resources.id.owner);
            TextView push_name = (TextView) v.findViewById(Resources.id.push_name);
            name.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            status.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));

            if (owner != null) {
                owner.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
                owner.setBackground(null);
            }

            if (push_name != null) push_name.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        } catch (Exception e) {}
    }
}
