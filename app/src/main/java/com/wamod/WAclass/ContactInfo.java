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

/**
 * Created by brianvalente on 9/20/15.
 */
public class ContactInfo extends AppCompatActivity {
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
                    //if (media_card_2 != null) media_card_2.setBackground(utils.tintToColor(media_card_2.getBackground(), utils.getDarkColor(3)));
                    if (media_card_2 != null) media_card_2.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
                    if (media_title != null) media_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    if (media_info != null) media_info.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));

                    TextView status = (TextView) a.findViewById(Resources.id.status);
                    TextView status_info = (TextView) a.findViewById(Resources.id.status_info);
                    TextView title_tv = (TextView) a.findViewById(Resources.id.title_tv);
                    TextView subtitle_tv = (TextView) a.findViewById(Resources.id.subtitle_tv);
                    ImageView primary_action_icon = (ImageView) a.findViewById(Resources.id.primary_action_icon);
                    ImageView secondary_action_btn = (ImageView) a.findViewById(Resources.id.secondary_action_btn);
                    ViewGroup phoneAndStatusContainer = (ViewGroup) status.getParent();
                    TextView phoneAndStatusTV = (TextView) ((ViewGroup) phoneAndStatusContainer.getChildAt(0)).getChildAt(0);
                    phoneAndStatusContainer.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
                    primary_action_icon.setImageDrawable(Utils.tintToColor(primary_action_icon.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY)));
                    secondary_action_btn.setImageDrawable(Utils.tintToColor(secondary_action_btn.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY)));
                    phoneAndStatusTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    status.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
                    status_info.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
                    title_tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
                    subtitle_tv.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));

                    ViewGroup mute_layout = (ViewGroup) a.findViewById(Resources.id.mute_layout);
                    ViewGroup muteContainer = (ViewGroup) mute_layout.getParent();
                    TextView muteTV = (TextView) ((ViewGroup) mute_layout.getChildAt(0)).getChildAt(0);
                    TextView customNotificationsTV = (TextView) ((ViewGroup) a.findViewById(Resources.id.notifications_layout)).getChildAt(0);

                    ViewGroup starred_messages_layout = (ViewGroup) a.findViewById(Resources.getID("starred_messages_layout"));
                    TextView starred_messages = (TextView) starred_messages_layout.getChildAt(0);
                    TextView starred_messages_count = (TextView) a.findViewById(Resources.id.starred_messages_count);
                    if (starred_messages != null) starred_messages.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    if (starred_messages_count != null) starred_messages_count.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));

                    TextView encryption_info = (TextView) a.findViewById(Resources.id.encryption_info);
                    if (encryption_info != null) {
                        TextView encryption_title = (TextView) ((ViewGroup) encryption_info.getParent()).getChildAt(0);
                        ImageView encryption_indicator = (ImageView) a.findViewById(Resources.id.encryption_indicator);

                        if (encryption_title != null) encryption_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                        if (encryption_indicator != null) encryption_indicator.setImageDrawable(Utils.tintToColor(encryption_indicator.getDrawable(), ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY)));
                        encryption_info.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
                    }

                    muteContainer.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
                    muteTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    customNotificationsTV.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));

                    TextView groups_title = (TextView) a.findViewById(Resources.id.groups_title);
                    ViewGroup groupsheader = (ViewGroup) groups_title.getParent();
                    groups_title.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
                    groupsheader.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));

                    content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        } catch (Exception e) {
            Utils.manageException(e);
        }

        Privacy.initPrivacyOnChatInfo(a);
    }


    /* Called on
     *    com.whatsapp.zv.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     *    com.whatsapp.nc.getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
     * Before
     *    return-object p2
     * Smali
     *    invoke-static {p2}, Lcom/wamod/WAclass/ContactInfo;->_getView(Landroid/view/View;)V
     */
    public static void _getView(View v) {
        try {
            v.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_CARD_BACKGROUND));
            TextView name = (TextView) v.findViewById(Resources.id.name);
            TextView status = (TextView) v.findViewById(Resources.id.status);
            name.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_PRIMARY));
            status.setTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TEXT_SECONDARY));
        } catch (Exception e) {
            Utils.manageException(e);
        }
    }
}
