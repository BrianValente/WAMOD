package com.wamod;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by brianvalente on 9/20/15.
 */
public class ContactInfo extends AppCompatActivity {
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
                        if (media_card_2 != null) media_card_2.setBackground(utils.tintToColor(media_card_2.getBackground(), utils.getDarkColor(3)));
                        if (media_title != null) media_title.setTextColor(utils.getDarkColor(0));
                        if (media_info != null) media_info.setTextColor(utils.getDarkColor(1));

                        TextView status = (TextView) a.findViewById(Resources.id.status);
                        TextView status_info = (TextView) a.findViewById(Resources.id.status_info);
                        TextView title_tv = (TextView) a.findViewById(Resources.id.title_tv);
                        TextView subtitle_tv = (TextView) a.findViewById(Resources.id.subtitle_tv);
                        ImageView primary_action_icon = (ImageView) a.findViewById(Resources.id.primary_action_icon);
                        ImageView secondary_action_btn = (ImageView) a.findViewById(Resources.id.secondary_action_btn);
                        ViewGroup phoneAndStatusContainer = (ViewGroup) status.getParent();
                        TextView phoneAndStatusTV = (TextView) ((ViewGroup) phoneAndStatusContainer.getChildAt(0)).getChildAt(0);
                        phoneAndStatusContainer.setBackground(utils.tintToColor(phoneAndStatusContainer.getBackground(), utils.getDarkColor(3)));
                        primary_action_icon.setImageDrawable(utils.tintToColor(primary_action_icon.getDrawable(), utils.getDarkColor(1)));
                        secondary_action_btn.setImageDrawable(utils.tintToColor(secondary_action_btn.getDrawable(), utils.getDarkColor(1)));
                        phoneAndStatusTV.setTextColor(utils.getDarkColor(0));
                        status.setTextColor(utils.getDarkColor(1));
                        status_info.setTextColor(utils.getDarkColor(1));
                        title_tv.setTextColor(utils.getDarkColor(1));
                        subtitle_tv.setTextColor(utils.getDarkColor(1));

                        ViewGroup mute_layout = (ViewGroup) a.findViewById(Resources.id.mute_layout);
                        ViewGroup muteContainer = (ViewGroup) mute_layout.getParent();
                        TextView muteTV = (TextView) ((ViewGroup) mute_layout.getChildAt(0)).getChildAt(0);
                        TextView customNotificationsTV = (TextView) ((ViewGroup) a.findViewById(Resources.id.notifications_layout)).getChildAt(0);


                        TextView starred_messages_btn = (TextView) a.findViewById(Resources.id.starred_messages_btn);
                        TextView starred_messages_count = (TextView) a.findViewById(Resources.id.starred_messages_count);
                        starred_messages_btn.setTextColor(utils.getDarkColor(0));
                        starred_messages_count.setTextColor(utils.getDarkColor(1));


                        TextView encryption_info = (TextView) a.findViewById(Resources.id.encryption_info);
                        if (encryption_info != null) {
                            TextView encryption_title = (TextView) ((ViewGroup) encryption_info.getParent()).getChildAt(0);
                            ImageView encryption_indicator = (ImageView) a.findViewById(Resources.id.encryption_indicator);

                            encryption_title.setTextColor(utils.getDarkColor(0));
                            encryption_info.setTextColor(utils.getDarkColor(1));
                            encryption_indicator.setImageDrawable(utils.tintToColor(encryption_indicator.getDrawable(), utils.getDarkColor(0)));
                        }


                        muteContainer.setBackground(utils.tintToColor(muteContainer.getBackground(), utils.getDarkColor(3)));
                        muteTV.setTextColor(utils.getDarkColor(0));
                        customNotificationsTV.setTextColor(utils.getDarkColor(0));

                        TextView groups_title = (TextView) a.findViewById(Resources.id.groups_title);
                        ViewGroup groupsheader = (ViewGroup) groups_title.getParent();
                        groups_title.setTextColor(utils.getDarkColor(0));
                        groupsheader.setBackground(utils.tintToColor(groupsheader.getBackground(), utils.getDarkColor(3)));

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
                name.setTextColor(utils.getDarkColor(0));
                status.setTextColor(utils.getDarkColor(1));
            }
        } catch (Exception e) {}
    }
}
