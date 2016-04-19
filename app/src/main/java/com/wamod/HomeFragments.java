package com.wamod;

import android.app.ListFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by brianvalente on 12/20/15.
 */
public class HomeFragments {
    public static void ConversationsFragment(View a) {
        if (utils.darkMode()) {
            try {
                TextView chatNameTV = (TextView) a.findViewById(Resources.id.conversations_row_contact_name);
                TextView dateTV = (TextView) a.findViewById(Resources.id.conversations_row_date);
                TextView msgTV = (TextView) a.findViewById(Resources.id.single_msg_tv);
                TextView senderTV = (TextView) a.findViewById(Resources.id.msg_from_tv);

                chatNameTV.setTextColor(utils.getDarkColor(0));
                dateTV.setTextColor(utils.getDarkColor(1));
                msgTV.setTextColor(utils.getDarkColor(1));
                senderTV.setTextColor(utils.getDarkColor(1));
            } catch (Exception e) {
                if (utils.debug) throw new RuntimeException(e);
            }
        }
    }

    public static void CallsFragment(View a) {
        if (utils.darkMode()) {
            try {
                TextView contactNameTV = (TextView) a.findViewById(Resources.id.contact_name);
                TextView dateTV = (TextView) a.findViewById(Resources.id.date_time);

                contactNameTV.setTextColor(utils.getDarkColor(0));
                dateTV.setTextColor(utils.getDarkColor(1));
            } catch (Exception e) {
                if (utils.debug) throw new RuntimeException(e);
            }
        }
    }

    public static void ContactsFragment(View a) {
        if (utils.darkMode()) {
            try {
                TextView contactNameTV = (TextView) a.findViewById(Resources.id.contactpicker_row_name);
                TextView statusTV = (TextView) a.findViewById(Resources.id.contactpicker_row_status);
                TextView phoneTypeTV = (TextView) a.findViewById(Resources.id.contactpicker_row_phone_type);

                contactNameTV.setTextColor(utils.getDarkColor(0));
                statusTV.setTextColor(utils.getDarkColor(1));
                phoneTypeTV.setTextColor(utils.getDarkColor(1));
            } catch (Exception e) {
                if (utils.debug) throw new RuntimeException(e);
            }
        }
    }
}
