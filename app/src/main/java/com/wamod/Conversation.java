package com.wamod;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wamod.entry.Aran;
import com.wamod.entry.Hangouts;
import com.wamod.entry.Mood;
import com.wamod.entry.Simple;
import com.wamod.entry.Stock;
import com.wamod.entry.Test;
import com.wamod.entry.WAMOD;
import com.whatsapp.DialogToastListActivity;

/**
 * Created by brianvalente on 7/9/15.
 */
public class Conversation extends DialogToastListActivity {
    public static void _onBackPressed(DialogToastListActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && utils.prefs.getBoolean("overview_multiplechats", true)) {
            activity.moveTaskToBack(true);
        } else {
            activity.finish();
        }
    }

    public static int getDateTVColor(TextView dateTV) {
        if (dateTV.getPaddingBottom() < 1) return Color.parseColor("#" + getBubbleColor(2));
        else return Color.parseColor("#" + getBubbleColor(5));
    }

    public static void call_getDateTVColor() {
        TextView tv = null;
        tv.setTextColor(getDateTVColor(tv));
    }


    public static void changeConversationTitleTextColor(TextView tv) {
        tv.setTextColor(utils.getUIColor(utils.COLOR_TOOLBARTEXT));
    }

    public static void changeConversationSubtitleTextColor(TextView tv) {
        tv.setTextColor(utils.getUIColor(utils.COLOR_TOOLBARTEXT));
    }


    public static boolean getConversationAttachButtonBoolean() {
        return utils.prefs.getBoolean("conversation_hidetoolbarattach", false);
    }

    public static void changeConversationBackButtonColor(LinearLayout back) {
        ImageView up = (ImageView) back.getChildAt(0);
        Drawable arrow = up.getDrawable();
        arrow.setColorFilter(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        up.setImageDrawable(arrow);
    }

    public static String getBubbleColor(int optionID) {
        String value = "";
        switch (optionID) {
            case 0:
                value = utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF");
                break;
            case 1:
                value = utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF");
                break;
            case 2:
                value = utils.prefs.getString("conversation_rightbubbledatecolor", "FFFFFF");
                break;
            case 3:
                value = utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF");
                break;
            case 4:
                value = utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF");
                break;
            case 5:
                value = utils.prefs.getString("conversation_leftbubbledatecolor", "FFFFFF");
                break;
            case 6:
                value = utils.prefs.getString("conversation_customparticipantcolor", "FFFFFF");
                break;
            default:
                break;
        }
        return value;
    }

    public static int getBubbleDrawableHex(int optionID) {
        String bubbleID = utils.prefs.getString("conversation_style_bubble", "0");
        int incoming_normal = 0, incoming_normal_ext = 0, outgoing_normal = 0, outgoing_normal_ext = 0;
        switch (bubbleID) {
            case "0":
            default:
                incoming_normal = utils.getHexID("balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("balloon_outgoing_normal_ext", "drawable");
                break;
            case "1":
                incoming_normal = utils.getHexID("wamod_style_bubble_wamod_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_wamod_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_wamod_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_wamod_balloon_outgoing_normal_ext", "drawable");
                break;
            case "2":
                incoming_normal = utils.getHexID("wamod_style_bubble_materialized_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_materialized_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_materialized_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_materialized_balloon_outgoing_normal_ext", "drawable");
                break;
            case "3":
                incoming_normal = utils.getHexID("wamod_style_bubble_lb_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_lb_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_lb_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_lb_balloon_outgoing_normal_ext", "drawable");
                break;
            case "4":
                incoming_normal = utils.getHexID("wamod_style_bubble_hangouts_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_hangouts_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_hangouts_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_hangouts_balloon_outgoing_normal_ext", "drawable");
                break;
            case "5":
                incoming_normal = utils.getHexID("wamod_style_bubble_rounded_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_rounded_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_rounded_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_rounded_balloon_outgoing_normal_ext", "drawable");
                break;
            case "6":
                incoming_normal = utils.getHexID("wamod_style_bubble_fbm_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_fbm_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_fbm_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_fbm_balloon_outgoing_normal_ext", "drawable");
                break;
            case "7":
                incoming_normal = utils.getHexID("wamod_style_bubble_newhangouts_balloon_incoming_normal", "drawable");
                incoming_normal_ext = utils.getHexID("wamod_style_bubble_newhangouts_balloon_incoming_normal_ext", "drawable");
                outgoing_normal = utils.getHexID("wamod_style_bubble_newhangouts_balloon_outgoing_normal", "drawable");
                outgoing_normal_ext = utils.getHexID("wamod_style_bubble_newhangouts_balloon_outgoing_normal_ext", "drawable");
                break;
        }

        switch (optionID) {
            case 0:
                return incoming_normal;
            case 1:
                return incoming_normal_ext;
            case 2:
                return outgoing_normal;
            case 3:
                return outgoing_normal_ext;
        }

        return incoming_normal;
    }

    public static Drawable getBubbleDrawable(int id) {
        int color = 0;
        if (id == 0 || id == 1) color = Color.parseColor("#" + getBubbleColor(3));
        else if (id == 2 || id == 3) color = Color.parseColor("#" + getBubbleColor(0));
        Drawable bubble = app.getContext().getResources().getDrawable(getBubbleDrawableHex(id));
        bubble.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        return bubble;
    }

    public void callingGetBubbleDrawable() {
        Drawable drawable = getBubbleDrawable(0);
    }

    public static int getConversationEntryID() {
        return Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"));
    }

    public static Intent conversationMultitask(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && utils.prefs.getBoolean("overview_multiplechats", true)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }

    public static int getActionBarStyle() {
        String config = utils.prefs.getString("conversation_style_toolbar", "0");
        int id = 0;
        switch (config) {
            case "0":
                id = 0x7f03100c;
                break;
            case "1":
                id = 0x7f03100d;
                break;
            case "2":
                id = 0x7f03100e;
                break;
            case "3":
                id = 0x7f03100f;
                break;
        }
        return id;
    }

    public static void setTaskDescription(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String contactName = ((TextView)activity.findViewById(Resources.id.conversation_contact_name)).getText().toString();
            String title = activity.getString(Resources.string.app_name);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getString(Resources.string.chat_with, contactName);
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            try {
                ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), Resources.drawable.icon), color);
                activity.setTaskDescription(taskDesc);
            } catch (Exception e) {
                Toast.makeText(utils.context, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            for (int i=0; i < utils.openedChats.size(); i++) {
                chat chat = utils.openedChats.get(i);
                if (chat.name.contentEquals(contactName)) {
                    chat.activity.finishAndRemoveTask();
                    utils.openedChats.remove(i);
                }
            }
            utils.openedChats.add(new chat(contactName,activity));
        }
    }


    public static void initConversation(com.whatsapp.Conversation a) {

        ViewGroup cntnt = (ViewGroup) a.findViewById(android.R.id.content);
        if (cntnt.getTag(Resources.id.bullet) == null) cntnt.setTag(Resources.id.bullet, true);
        else return;


        // Init attachments
        a.u();

        // Load contact
        a.i(a);

        // Load colors
        setTaskDescription(a);


        // Hides profile photo if activated
        if (utils.prefs.getBoolean("conversation_hideprofilephoto", false)) {
            ImageView profilePhoto = (ImageView) a.findViewById(Resources.id.conversation_contact_photo);
            if (profilePhoto != null) {
                profilePhoto.getLayoutParams().width = 0;
                profilePhoto.getLayoutParams().height = 0;
                profilePhoto.setVisibility(View.GONE);
            }
        }


        // Change background color if activated
        if (utils.prefs.getBoolean("conversation_custombackcolorbool", false)) {
            ImageView bg = (ImageView) a.findViewById(Resources.id.conversation_background);
            bg.setVisibility(View.GONE);
            View content = a.findViewById(Resources.id.conversation_layout);
            content.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("conversation_custombackcolor", "FFFFFF")));
        }


        // Initializes the entry style
        switch (utils.prefs.getString("conversation_style_entry","0")) {
            case "0":
                // Stock
                Stock.init(a);
                break;
            case "1":
                // WAMOD
                WAMOD.init(a);
                break;
            case "2":
                // Hangouts
                Hangouts.init(a);
                break;
            case "3":
                // Simple
                Simple.init(a);
                break;
            case "4":
                // Aran
                Aran.init(a);
                break;
            case "5":
                // Mood
                Mood.init(a);
                break;
            case "6":
                // Test
                Test.init(a);
                break;
        }


        if (utils.prefs.getBoolean("conversation_toolbarexit", false)) {
            ImageView back = (ImageView) a.findViewById(Resources.id.up);
            //back.setImageBitmap(new BitmapFactory(activity.getResources().getDrawable(replace_ids_here.ic_action_close)));
            Drawable x = a.getResources().getDrawable(Resources.drawable.wamod_action_close);
            x.setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
            back.setImageDrawable(x);
        }
    }

    public void callInitConversation() {
        initConversation(null);
    }

    public static void tintToolbarButtons(com.whatsapp.Conversation a) {
        ViewGroup toolbar = (ViewGroup) a.findViewById(Resources.id.toolbar);
        View linearLayoutCompat = toolbar.getChildAt(2);
        if (linearLayoutCompat != null) {
            final LinearLayoutCompat LinearLayoutCompat2 = (LinearLayoutCompat) linearLayoutCompat;
            linearLayoutCompat.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    for (int i = 0; i < LinearLayoutCompat2.getChildCount(); i++) {
                        final View child = LinearLayoutCompat2.getChildAt(i);
                        child.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                if (child instanceof TextView) {
                                    Log.i("WAMOD", "Attach button");
                                    TextView tv = (TextView) child;
                                    Drawable[] icon = tv.getCompoundDrawables();
                                    icon[0].setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
                                    tv.setCompoundDrawables(icon[0], icon[1], icon[2], icon[3]);
                                } else if (child instanceof ImageButton) {
                                    Log.i("WAMOD", "Call button");
                                    ImageButton im = (ImageButton) child;
                                    im.setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND));
                                } else if (child instanceof ImageView) {
                                    Log.i("WAMOD", "More button");
                                    ImageView im = (ImageView) child;
                                    im.setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND));
                                }
                                child.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        });
                    }
                    LinearLayoutCompat2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    public static void _startSupportActionMode(final com.whatsapp.Conversation a) {
        ViewGroup action_mode_bar = (ViewGroup) a.findViewById(Resources.id.action_mode_bar);
        if (action_mode_bar != null) {
            action_mode_bar.setBackgroundColor(utils.getUIColor(utils.COLOR_TOOLBAR));
            final ImageView action_mode_close_button = (ImageView) a.findViewById(Resources.id.action_mode_close_button);
            final TextView action_bar_title = (TextView) a.findViewById(Resources.id.action_bar_title);
            final TextView menuitem_delete  = (TextView) a.findViewById(Resources.id.menuitem_delete);

            if (menuitem_delete != null) menuitem_delete.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    ViewGroup parent = (ViewGroup) menuitem_delete.getParent();

                    for (int i=0; i<parent.getChildCount(); i++) {
                        View v = parent.getChildAt(i);
                        if (v instanceof TextView) {
                            TextView tv = (TextView) v;
                            Drawable[] icon = tv.getCompoundDrawables();
                            icon[0] = utils.tintToColor(icon[0], utils.getUIColor(utils.COLOR_FOREGROUND));
                            tv.setCompoundDrawables(icon[0], null, null, null);
                        } else if (v instanceof ImageView) {
                            ImageView im = (ImageView) v;
                            im.setImageDrawable(utils.tintToColor(im.getDrawable(), utils.COLOR_FOREGROUND));
                        }
                    }

                    action_mode_close_button.setImageDrawable(utils.tintToColor(action_mode_close_button.getDrawable(), utils.getUIColor(utils.COLOR_FOREGROUND)));
                    if (action_bar_title != null) action_bar_title.setTextColor(utils.getUIColor(utils.COLOR_TOOLBARTEXT));

                    //menuitem_delete.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) a.getWindow().setStatusBarColor(utils.getUIColor(utils.COLOR_STATUSBAR));
    }

    private void callSAM() {
        _startSupportActionMode(null);
    }

    public static int getConversationEntry(int id) {
        int activeTheme = Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"));
        int conversation = 0, emoji_picker_horizontal = 0;
        switch (activeTheme) {
            case 0:
                conversation = 0x7f030047;
                emoji_picker_horizontal = 0x7f03008a;
                break;
            case 1:
                conversation = 0x7f031004;
                emoji_picker_horizontal = 0x7f03008a;
                break;
            case 2:
                conversation = 0x7f031006;
                emoji_picker_horizontal = 0x7f03008a;
                break;
            case 3:
                conversation = 0x7f031008;
                emoji_picker_horizontal = 0x7f03008a;
                break;
            case 4:
                conversation = 0x7f031009;
                emoji_picker_horizontal = 0x7f03008a;
                break;
            case 5:
                conversation = 0x7f03100a;
                emoji_picker_horizontal = 0x7f03008a;
                break;
            case 6:
                conversation = 0x7f03100b;
                emoji_picker_horizontal = 0x7f03008a;
                break;
        }
        switch (id) {
            case 0:
                return conversation;
            case 1:
                return emoji_picker_horizontal;
        }
        return conversation;
    }
}
