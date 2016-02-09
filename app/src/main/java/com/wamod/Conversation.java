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

    /*boolean outgoing;

    EditText input;
    ImageButton send;

    Drawable conversation_leftbubblecolor = ContextCompat.getDrawable(app.getContext(), R.drawable.abc_ab_share_pack_mtrl_alpha);

    private void ifThemeIsSelected() {

        int n = getConversationEntryID();
        String test;
        if (n==0) {
            test = "stock";
        } else if (n==1) {
            test = "wamod";
        } else if (n==2) {
            test = "hangouts";
        }
    }

    private void load() {
        utils.loadColors(getSupportActionBar(), getWindow());
        int themeHex = getConversationEntry(0);
    }

    private void getBubble() {
        int balloon_incoming_normal = getBubbleDrawableHex(0);
    }

    private void getTick() {
        int message_got_read_receipt_from_target = utils.getTickDrawableHex(3);
    }

    private void getEmojiPicker() {
        int emoji_picker_horizontal = getConversationEntry(1);
    }

    private void hideProfilePhoto() {
        if (utils.prefs.getBoolean("conversation_hideprofilephoto", false)) {
            ImageView profilePhoto = (ImageView) findViewById(R.id.action0);
            profilePhoto.getLayoutParams().width = 0;
            profilePhoto.getLayoutParams().height = 0;
            profilePhoto.setVisibility(View.GONE);
        }
    }

    private void tintBubble() {
        int color = Color.parseColor("#" + getBubbleColor(0));
        conversation_leftbubblecolor.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
    }

    private void setOutgoingFalse() {
        outgoing = false;
    }

    private void changeTextColor() {
        ((TextView) findViewById(R.id.action0)).setTextColor(Color.parseColor("#" + getBubbleColor(4)));
        if (outgoing) ((TextView) findViewById(R.id.action0)).setTextColor(Color.parseColor("#" + getBubbleColor(1)));
    }

    private void changeDateColor() {
        ((TextView) findViewById(R.id.action0)).setTextColor(Color.parseColor("#" + getBubbleColor(5)));
        if (((TextView) findViewById(R.id.action0)).getPaddingLeft() > 2) ((TextView) findViewById(R.id.action0)).setTextColor(Color.parseColor("#" + getBubbleColor(2)));
    }

    private void changeParticipantTextColor() {
        if (utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)) ((TextView) findViewById(R.id.action0)).setTextColor(Color.parseColor("#" + getBubbleColor(6)));
    }



    private void changeConversationTitleTextColor () {
        TextView tv = (TextView) findViewById(R.id.action_bar);
        changeConversationTitleTextColor(tv);
    }

    private void hideToolbarButtons() {
        if (!(getConversationAttachButtonBoolean())) {
            String test = "Do something!";
        }
    }

    private void initSpammer() {
        input = (EditText) findViewById(R.id.action0);
        send = (ImageButton) findViewById(R.id.action_bar);
    }

    private void callSpammer() {
        spammer(input, send);
    }

    public static void spammer(final EditText input, final ImageButton send) {
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            private long time = 0;

            @Override
            public void run() {
                input.setText("Hello");
                send.performClick();
                time += 1000;
                Log.d("TimerExample", "Going for... " + time);
                h.postDelayed(this, 1000);
            }
        }, 1000);
    }

    public void voiceNoteProximitySensor() {
        if (utils.prefs.getBoolean("conversation_proximitysensor", true)) {
            String something = "Do something.";
        }
    }

    public void setConversationTaskDescription() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String title = getString(R.string.abc_action_bar_home_description);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = getString(R.string.chat_with, ((TextView) findViewById(R.id.action0)).getText());
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(getResources(), R.drawable.notification_template_icon_bg), color);
            this.setTaskDescription(taskDesc);
        }
    }

    public static int getConversationEntry(int id) {
        int activeTheme = Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"));
        int conversation = 0, emoji_picker_horizontal = 0;
        switch (activeTheme) {
            case 0:
                conversation = 0x7f030044;
                emoji_picker_horizontal = 0x7f030072;
                break;
            case 1:
                conversation = 0x7f0300e2;
                emoji_picker_horizontal = 0x7f0300e3;
                break;
            case 2:
                conversation = 0x7f0300e4;
                emoji_picker_horizontal = 0x7f0300e5;
                break;
            case 3:
                conversation = 0x7f0300ee;
                emoji_picker_horizontal = 0x7f030072;
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

    public static int getConversationEntryID() {
        return Integer.parseInt(utils.prefs.getString("conversation_style_entry", "0"));
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
                incoming_normal = 0x7f020069;
                incoming_normal_ext = 0x7f02006a;
                outgoing_normal = 0x7f020071;
                outgoing_normal_ext = 0x7f020072;
                input = 0x7f020538;
                break;
            case "1":
                incoming_normal = 0x7f020654;
                incoming_normal_ext = 0x7f020655;
                outgoing_normal = 0x7f020656;
                outgoing_normal_ext = 0x7f020657;
                input = 0x7f020658;
                break;
            case "2":
                incoming_normal = 0x7f020659;
                incoming_normal_ext = 0x7f02065a;
                outgoing_normal = 0x7f02065b;
                outgoing_normal_ext = 0x7f02065c;
                input = 0x7f02065d;
                break;
            case "3":
                incoming_normal = 0x7f02065e;
                incoming_normal_ext = 0x7f02065f;
                outgoing_normal = 0x7f020660;
                outgoing_normal_ext = 0x7f020661;
                input = 0x7f020662;
                break;
            case "4":
                incoming_normal = 0x7f020668;
                incoming_normal_ext = 0x7f020669;
                outgoing_normal = 0x7f02066a;
                outgoing_normal_ext = 0x7f02066b;
                input = 0x7f02066c;
                break;
            case "5":
                incoming_normal = 0x7f02066d;
                incoming_normal_ext = 0x7f02066e;
                outgoing_normal = 0x7f02066f;
                outgoing_normal_ext = 0x7f020670;
                input = 0x7f020671;
                break;
            case "6":
                incoming_normal = 0x7f020672;
                incoming_normal_ext = 0x7f020673;
                outgoing_normal = 0x7f020674;
                outgoing_normal_ext = 0x7f020675;
                input = 0x7f020676;
                break;
            case "7":
                incoming_normal = 0x7f020663;
                incoming_normal_ext = 0x7f020664;
                outgoing_normal = 0x7f020665;
                outgoing_normal_ext = 0x7f020666;
                input = 0x7f020667;
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

    public static void changeConversationBackButtonColor(LinearLayout back) {
        ImageView up = (ImageView) back.getChildAt(0);
        Drawable arrow = up.getDrawable();
        arrow.setColorFilter(Color.parseColor("#" + utils.prefs.getString("general_toolbarforeground", "FFFFFF")), PorterDuff.Mode.MULTIPLY);
        up.setImageDrawable(arrow);
    }


    public static boolean getConversationAttachButtonBoolean() {
        return utils.prefs.getBoolean("conversation_hidetoolbarattach", false);
    }



    public static Intent conversationMultitask(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && utils.prefs.getBoolean("overview_multiplechats", true)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }
    public void openConversation() {Intent intent = new Intent();intent = conversationMultitask(intent);startActivity(intent);}

    public static void backPressed(DialogToastListActivity activity) {
        if (utils.prefs.getBoolean("conversation_toolbarexit", false)) {
            activity.finish();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && utils.prefs.getBoolean("overview_multiplechats", true)) {
            activity.moveTaskToBack(true);
        } else {
            activity.finish();
        }
    }
    public void callBackPressed() {backPressed(this);}

    public static void initConversation(com.whatsapp.Conversation activity) {
        // Load statusbar and navbar colors
        utils.loadColors(activity);
        setTaskDescription(activity);

        // Hides profile photo if activated
        if (utils.prefs.getBoolean("conversation_hideprofilephoto", false)) {
            ImageView profilePhoto = (ImageView) activity.findViewById(replace_ids_here.conversationcontactphoto);
            profilePhoto.getLayoutParams().width = 0;
            profilePhoto.getLayoutParams().height = 0;
            profilePhoto.setVisibility(View.GONE);
        }

        // Center title if activated

        //if (utils.prefs.getBoolean("conversation_centeredtitle", false)) {


        // Change background color if activated
        if (utils.prefs.getBoolean("conversation_custombackcolorbool", false)) {
            ImageView bg = (ImageView) activity.findViewById(replace_ids_here.conversation_background);
            bg.setVisibility(View.GONE);
            View content = activity.findViewById(replace_ids_here.conversation_layout);
            content.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("conversation_custombackcolor", "FFFFFF")));
        }

        // Initializes the entry style

        switch (utils.prefs.getString("conversation_style_entry","0")) {
            case "0":
                // Stock
                break;
            case "1":
                // WAMOD
                conversationStyleEntryWAMOD.init(activity);
                break;
            case "2":
                // Hangouts
                conversationStyleEntryHangouts.init(activity);
                break;
            case "3":
                // iMessage
                conversationStyleEntrySimple.init(activity);
                break;
        }

        if (utils.prefs.getBoolean("conversation_toolbarexit", false)) {
            ImageView back = (ImageView) activity.findViewById(replace_ids_here.back);
            //back.setImageBitmap(new BitmapFactory(activity.getResources().getDrawable(replace_ids_here.ic_action_close)));
            back.setImageBitmap(BitmapFactory.decodeResource(activity.getResources(), replace_ids_here.ic_action_close));
        }
    }


    public static void setTaskDescription(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String contactName = ((TextView)activity.findViewById(replace_ids_here.conversationtitle)).getText().toString();
            String title = activity.getString(replace_ids_here.appname);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getString(replace_ids_here.chatwith, contactName);
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), replace_ids_here.appicon), color);
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


    public static int getActionBarStyle() {
        String config = utils.prefs.getString("conversation_style_toolbar", "0");
        int id = 0;
        switch (config) {
            case "0":
                id = 0x7f0300eb;
                break;
            case "1":
                id = 0x7f0300e8;
                break;
            case "2":
                id = 0x7f0300e9;
                break;
            case "3":
                id = 0x7f0300ea;
                break;
        }
        return id;
    }
    public void callGetActionBarStyle() {
        int test = getActionBarStyle();
    }


    */

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
                incoming_normal = 0x7f020075;
                incoming_normal_ext = 0x7f020076;
                outgoing_normal = 0x7f02007d;
                outgoing_normal_ext = 0x7f02007e;
                input = 0x7f020538;
                break;
            case "1":
                incoming_normal = 0x7f020941;
                incoming_normal_ext = 0x7f020942;
                outgoing_normal = 0x7f020943;
                outgoing_normal_ext = 0x7f020944;
                input = 0x7f02067d;
                break;
            case "2":
                incoming_normal = 0x7f020946;
                incoming_normal_ext = 0x7f020947;
                outgoing_normal = 0x7f020948;
                outgoing_normal_ext = 0x7f020949;
                input = 0x7f02094a;
                break;
            case "3":
                incoming_normal = 0x7f02094b;
                incoming_normal_ext = 0x7f02094c;
                outgoing_normal = 0x7f02094d;
                outgoing_normal_ext = 0x7f02094e;
                input = 0x7f02094f;
                break;
            case "4":
                incoming_normal = 0x7f020950;
                incoming_normal_ext = 0x7f020951;
                outgoing_normal = 0x7f020952;
                outgoing_normal_ext = 0x7f020953;
                input = 0x7f020954;
                break;
            case "5":
                incoming_normal = 0x7f020955;
                incoming_normal_ext = 0x7f020956;
                outgoing_normal = 0x7f020957;
                outgoing_normal_ext = 0x7f020958;
                input = 0x7f020959;
                break;
            case "6":
                incoming_normal = 0x7f02095a;
                incoming_normal_ext = 0x7f02095b;
                outgoing_normal = 0x7f02095c;
                outgoing_normal_ext = 0x7f02095d;
                input = 0x7f02095e;
                break;
            case "7":
                incoming_normal = 0x7f02095f;
                incoming_normal_ext = 0x7f020960;
                outgoing_normal = 0x7f020961;
                outgoing_normal_ext = 0x7f020962;
                input = 0x7f020963;
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
                id = 0x7f0300f3;
                break;
            case "1":
                id = 0x7f0300f4;
                break;
            case "2":
                id = 0x7f0300f5;
                break;
            case "3":
                id = 0x7f0300f6;
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
                conversation = 0x7f030045;
                emoji_picker_horizontal = 0x7f030078;
                break;
            case 1:
                conversation = 0x7f0300ef;
                emoji_picker_horizontal = 0x7f030078;
                break;
            case 2:
                conversation = 0x7f0300f1;
                emoji_picker_horizontal = 0x7f030078;
                break;
            case 3:
                conversation = 0x7f0300f9;
                emoji_picker_horizontal = 0x7f030078;
                break;
            case 4:
                conversation = 0x7f0300fc;
                emoji_picker_horizontal = 0x7f030078;
                break;
            case 5:
                conversation = 0x7f0300fd;
                emoji_picker_horizontal = 0x7f030078;
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
