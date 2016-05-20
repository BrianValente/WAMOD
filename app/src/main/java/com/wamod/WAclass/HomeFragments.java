package com.wamod.WAclass;

import android.view.View;
import android.widget.TextView;

import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by brianvalente on 12/20/15.
 */
public class HomeFragments {
    public static void ConversationsFragment(View a) {
        if (Utils.nightModeShouldRun()) {
            try {
                TextView chatNameTV = (TextView) a.findViewById(Resources.id.conversations_row_contact_name);
                TextView dateTV = (TextView) a.findViewById(Resources.id.conversations_row_date);
                TextView msgTV = (TextView) a.findViewById(Resources.id.single_msg_tv);
                TextView senderTV = (TextView) a.findViewById(Resources.id.msg_from_tv);

                chatNameTV.setTextColor(Utils.getDarkColor(0));
                dateTV.setTextColor(Utils.getDarkColor(1));
                msgTV.setTextColor(Utils.getDarkColor(1));
                senderTV.setTextColor(Utils.getDarkColor(1));
            } catch (Exception e) {
                Utils.manageException(e);
            }
        }
    }

    public static void CallsFragment(View a) {
        if (Utils.nightModeShouldRun()) {
            try {
                TextView contactNameTV = (TextView) a.findViewById(Resources.id.contact_name);
                TextView dateTV = (TextView) a.findViewById(Resources.id.date_time);

                contactNameTV.setTextColor(Utils.getDarkColor(0));
                dateTV.setTextColor(Utils.getDarkColor(1));
            } catch (Exception e) {
                Utils.manageException(e);
            }
        }
    }

    public static void ContactsFragment(View a) {
        if (Utils.nightModeShouldRun()) {
            try {
                TextView contactNameTV = (TextView) a.findViewById(Resources.id.contactpicker_row_name);
                TextView statusTV = (TextView) a.findViewById(Resources.id.contactpicker_row_status);
                TextView phoneTypeTV = (TextView) a.findViewById(Resources.id.contactpicker_row_phone_type);

                contactNameTV.setTextColor(Utils.getDarkColor(0));
                statusTV.setTextColor(Utils.getDarkColor(1));
                phoneTypeTV.setTextColor(Utils.getDarkColor(1));
            } catch (Exception e) {
                Utils.manageException(e);
            }
        }
    }
}
