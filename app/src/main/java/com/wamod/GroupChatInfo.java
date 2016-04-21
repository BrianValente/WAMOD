package com.wamod;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class GroupChatInfo extends AppCompatActivity {
    public static void _onCreate(final AppCompatActivity a) {
        try {
            if (utils.nightModeShouldRun()) {
                final ViewGroup content = (ViewGroup) a.findViewById(android.R.id.content);
                content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        ListView list = (ListView) a.findViewById(android.R.id.list);
                        list.setBackgroundColor(utils.getDarkColor(2));

                        ViewGroup media_card = (ViewGroup) a.findViewById(Resources.id.media_card);
                        ViewGroup media_card_2 = (ViewGroup) media_card.getChildAt(0);
                        TextView media_title = (TextView) a.findViewById(Resources.id.media_title);
                        TextView media_info = (TextView) a.findViewById(Resources.id.media_info);
                        if (media_card_2 != null)
                            media_card_2.setBackground(utils.tintToColor(media_card_2.getBackground(), utils.getDarkColor(3)));
                        if (media_title != null) media_title.setTextColor(utils.getDarkColor(0));
                        if (media_info != null) media_info.setTextColor(utils.getDarkColor(1));


                        ViewGroup mute_layout = (ViewGroup) a.findViewById(Resources.id.mute_layout);
                        ViewGroup muteContainer = (ViewGroup) mute_layout.getParent();
                        TextView muteTV = (TextView) ((ViewGroup) mute_layout.getChildAt(0)).getChildAt(0);
                        TextView customNotificationsTV = (TextView) ((ViewGroup) a.findViewById(Resources.id.notifications_layout)).getChildAt(0);


                        TextView encryption_info = (TextView) a.findViewById(Resources.id.encryption_info);
                        if (encryption_info != null) {
                            TextView encryption_title = (TextView) ((ViewGroup) encryption_info.getParent()).getChildAt(0);
                            ImageView encryption_indicator = (ImageView) a.findViewById(Resources.id.encryption_indicator);

                            encryption_title.setTextColor(utils.getDarkColor(0));
                            encryption_info.setTextColor(utils.getDarkColor(1));
                            encryption_indicator.setImageDrawable(utils.tintToColor(encryption_indicator.getDrawable(), utils.getDarkColor(0)));
                        }


                        TextView starred_messages_btn = (TextView) a.findViewById(Resources.id.starred_messages_btn);
                        TextView starred_messages_count = (TextView) a.findViewById(Resources.id.starred_messages_count);
                        starred_messages_btn.setTextColor(utils.getDarkColor(0));
                        starred_messages_count.setTextColor(utils.getDarkColor(1));


                        muteContainer.setBackground(utils.tintToColor(muteContainer.getBackground(), utils.getDarkColor(3)));
                        muteTV.setTextColor(utils.getDarkColor(0));
                        customNotificationsTV.setTextColor(utils.getDarkColor(0));


                        TextView participants_title = (TextView) a.findViewById(Resources.id.participants_title);
                        ViewGroup participants_header = (ViewGroup) participants_title.getParent();
                        participants_title.setTextColor(utils.getDarkColor(0));
                        participants_header.setBackground(utils.tintToColor(participants_header.getBackground(), utils.getDarkColor(3)));


                        ViewGroup exit_group_btn = (ViewGroup) a.findViewById(Resources.id.exit_group_btn) ;
                        exit_group_btn.setBackgroundColor(utils.getDarkColor(3));


                        ViewGroup add_participant_layout = (ViewGroup) a.findViewById(Resources.id.add_participant_layout);
                        if (add_participant_layout != null) add_participant_layout.setBackgroundColor(utils.getDarkColor(3));
                        TextView add_participant_text = (TextView) a.findViewById(Resources.id.add_participant_text);
                        ImageView add_participant_icon = (ImageView) a.findViewById(Resources.id.add_participant_icon);
                        if (add_participant_text != null) add_participant_text.setTextColor(utils.getDarkColor(0));
                        if (add_participant_icon != null) add_participant_icon.setImageDrawable(utils.tintToColor(add_participant_icon.getDrawable(),utils.getDarkColor(0)));


                        content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        } catch (Exception e) {
            utils.manageException(e);
        }
    }

    public static void _getView(View v) {
        try {
            if (utils.nightModeShouldRun()) {
                v.setBackground(utils.tintToColor(v.getBackground(), utils.getDarkColor(3)));
                TextView name = (TextView) v.findViewById(Resources.id.name);
                TextView status = (TextView) v.findViewById(Resources.id.status);
                TextView owner = (TextView) v.findViewById(Resources.id.owner);
                TextView push_name = (TextView) v.findViewById(Resources.id.push_name);
                name.setTextColor(utils.getDarkColor(0));
                status.setTextColor(utils.getDarkColor(1));

                if (owner != null) {
                    owner.setTextColor(utils.getDarkColor(1));
                    owner.setBackground(null);
                }

                if (push_name != null) push_name.setTextColor(utils.getDarkColor(1));
            }
        } catch (Exception e) {}
    }
}
