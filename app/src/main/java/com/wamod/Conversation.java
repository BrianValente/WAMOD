package com.wamod;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whatsapp.App;
import com.whatsapp.DialogToastListActivity;

/**
 * Created by brianvalente on 7/9/15.
 */
public class Conversation extends DialogToastListActivity {
    public static void backPressed(DialogToastListActivity activity) {
        if (utils.prefs.getBoolean("conversation_toolbarexit", false)) {
            activity.finish();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && utils.prefs.getBoolean("overview_multiplechats", true)) {
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
        tv.setTextColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")));
    }

    public static void changeConversationSubtitleTextColor(TextView tv) {
        tv.setTextColor(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")));
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
        int incoming_normal = 0, incoming_normal_ext = 0, outgoing_normal = 0, outgoing_normal_ext = 0, input = 0;
        switch (bubbleID) {
            case "0":
                incoming_normal = 0x7f020076;
                incoming_normal_ext = 0x7f020077;
                outgoing_normal = 0x7f02007e;
                outgoing_normal_ext = 0x7f02007f;
                input = 0x7f020538;
                break;
            case "1":
                incoming_normal = 0x7f021000;
                incoming_normal_ext = 0x7f021001;
                outgoing_normal = 0x7f021002;
                outgoing_normal_ext = 0x7f021003;
                input = 0x7f021004;
                break;
            case "2":
                incoming_normal = 0x7f021005;
                incoming_normal_ext = 0x7f021006;
                outgoing_normal = 0x7f021007;
                outgoing_normal_ext = 0x7f021008;
                input = 0x7f021009;
                break;
            case "3":
                incoming_normal = 0x7f02100a;
                incoming_normal_ext = 0x7f02100b;
                outgoing_normal = 0x7f02100c;
                outgoing_normal_ext = 0x7f02100d;
                input = 0x7f02100e;
                break;
            case "4":
                incoming_normal = 0x7f02100f;
                incoming_normal_ext = 0x7f021010;
                outgoing_normal = 0x7f021011;
                outgoing_normal_ext = 0x7f021012;
                input = 0x7f021013;
                break;
            case "5":
                incoming_normal = 0x7f021014;
                incoming_normal_ext = 0x7f021015;
                outgoing_normal = 0x7f021016;
                outgoing_normal_ext = 0x7f021017;
                input = 0x7f021018;
                break;
            case "6":
                incoming_normal = 0x7f021019;
                incoming_normal_ext = 0x7f02101a;
                outgoing_normal = 0x7f02101b;
                outgoing_normal_ext = 0x7f02101c;
                input = 0x7f02101d;
                break;
            case "7":
                incoming_normal = 0x7f02101e;
                incoming_normal_ext = 0x7f02101f;
                outgoing_normal = 0x7f021020;
                outgoing_normal_ext = 0x7f021021;
                input = 0x7f021022;
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
            case 4:
                return input;
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
            String contactName = ((TextView)activity.findViewById(id.conversationtitle)).getText().toString();
            String title = activity.getString(id.appname);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getString(id.chatwith, contactName);
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), id.appicon), color);
            activity.setTaskDescription(taskDesc);

            for (int i=0; i < App.openedChats.size(); i++) {
                chat chat = App.openedChats.get(i);
                if (chat.name.contentEquals(contactName)) {
                    chat.activity.finish();
                    App.openedChats.remove(i);
                }
            }
            App.openedChats.add(new chat(contactName,activity));
        }
    }


    public static void initConversation(com.whatsapp.Conversation a) {

        // Load colors
        utils.loadColors(a);
        setTaskDescription(a);

        // Hides profile photo if activated
        if (utils.prefs.getBoolean("conversation_hideprofilephoto", false)) {
            ImageView profilePhoto = (ImageView) a.findViewById(id.conversationcontactphoto);
            profilePhoto.getLayoutParams().width = 0;
            profilePhoto.getLayoutParams().height = 0;
            profilePhoto.setVisibility(View.GONE);
        }

        // Center title if activated

        //if (utils.prefs.getBoolean("conversation_centeredtitle", false)) {


        // Change background color if activated
        if (utils.prefs.getBoolean("conversation_custombackcolorbool", false)) {
            ImageView bg = (ImageView) a.findViewById(id.conversation_background);
            bg.setVisibility(View.GONE);
            View content = a.findViewById(id.conversation_layout);
            content.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("conversation_custombackcolor", "FFFFFF")));
        }
        // Initializes the entry style

        switch (utils.prefs.getString("conversation_style_entry","0")) {
            case "0":
                // Stock
                break;
            case "1":
                // WAMOD
                conversationStyleEntryWAMOD.init(a);
                break;
            case "2":
                // Hangouts
                conversationStyleEntryHangouts.init(a);
                break;
            case "3":
                // Simple
                conversationStyleEntrySimple.init(a);
                break;
            case "4":
                // Aran
                conversationStyleEntryAran.init(a);
                break;
            case "5":
                // Mood
                conversationStyleEntryMood.init(a);
                break;
            case "6":
                // Test
                conversationStyleEntryTest.init(a);
                break;
        }

        if (utils.prefs.getBoolean("conversation_toolbarexit", false)) {
            ImageView back = (ImageView) a.findViewById(id.up);
            //back.setImageBitmap(new BitmapFactory(activity.getResources().getDrawable(replace_ids_here.ic_action_close)));
            back.setImageBitmap(BitmapFactory.decodeResource(a.getResources(), id.wamod_action_close));
        }
    }

    public static int getConversationEntry(int id) {
        int activeTheme = Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"));
        int conversation = 0, emoji_picker_horizontal = 0;
        switch (activeTheme) {
            case 0:
                conversation = 0x7f030046;
                emoji_picker_horizontal = 0x7f03007a;
                break;
            case 1:
                conversation = 0x7f031004;
                emoji_picker_horizontal = 0x7f03007a;
                break;
            case 2:
                conversation = 0x7f031006;
                emoji_picker_horizontal = 0x7f03007a;
                break;
            case 3:
                conversation = 0x7f031008;
                emoji_picker_horizontal = 0x7f03007a;
                break;
            case 4:
                conversation = 0x7f031009;
                emoji_picker_horizontal = 0x7f03007a;
                break;
            case 5:
                conversation = 0x7f03100a;
                emoji_picker_horizontal = 0x7f03007a;
                break;
            case 6:
                conversation = 0x7f03100b;
                emoji_picker_horizontal = 0x7f03007a;
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
