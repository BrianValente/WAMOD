package com.wamod.WAclass;

import android.view.View;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by brianvalente on 12/20/15.
 */
public class HomeFragments {
    public static void ConversationsFragment(View a) {
        try {
            TextView chatNameTV = (TextView) a.findViewById(Resources.id.conversations_row_contact_name);
            TextView dateTV = (TextView) a.findViewById(Resources.id.conversations_row_date);
            TextView msgTV = (TextView) a.findViewById(Resources.id.single_msg_tv);
            TextView senderTV = (TextView) a.findViewById(Resources.id.msg_from_tv);

            chatNameTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            dateTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
            msgTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
            senderTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        } catch (Exception e) {
            Utils.manageException(e);
        }
    }


    /* Called on
     *    com.whatsapp.v.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Where
     *    Before return-object p2
     * Smali
     *    invoke-static {p2}, Lcom/wamod/WAclass/HomeFragments;->CallsFragment(Landroid/view/View;)V
     */
    public static void CallsFragment(View a) {
        try {
            TextView contactNameTV = (TextView) a.findViewById(Resources.id.contact_name);
            TextView dateTV = (TextView) a.findViewById(Resources.id.date_time);

            contactNameTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            dateTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        } catch (Exception e) {
            Utils.manageException(e);
        }
    }


    /* Called on
     *    com.whatsapp.a91.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     *    com.whatsapp.pe.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Where
     *    Before return-object p2
     * Smali
     *    invoke-static {p2}, Lcom/wamod/WAclass/HomeFragments;->ContactsFragment(Landroid/view/View;)V
     */
    public static void ContactsFragment(View a) {
        try {
            TextView contactNameTV = (TextView) a.findViewById(Resources.id.contactpicker_row_name);
            TextView statusTV = (TextView) a.findViewById(Resources.id.contactpicker_row_status);
            TextView phoneTypeTV = (TextView) a.findViewById(Resources.id.contactpicker_row_phone_type);

            contactNameTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            statusTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
            phoneTypeTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        } catch (Exception e) {
            Utils.manageException(e);
        }
    }
}
