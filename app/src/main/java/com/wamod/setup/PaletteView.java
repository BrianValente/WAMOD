package com.wamod.setup;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wamod.R;
import com.wamod.Resources;
import com.wamod.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.AttributedCharacterIterator;

/**
 * Created by brianvalente on 6/14/16.
 */
public class PaletteView extends LinearLayout {
    SetupActivity setupActivity;
    JSONObject themeObj;

    public PaletteView(Context context) {
        super(context);
    }

    public PaletteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PaletteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PaletteView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs) {
        if (isInEditMode()) {
            inflate(ctx, R.layout.wamod_setup_paletteview, this);
            return;
        }

        inflate(ctx, Resources.getLayout("wamod_setup_paletteview"), this);

        setupActivity = (SetupActivity) ctx;
        int themeID = Utils.getAttribute_Int(attrs, "theme_id");

        String theme;
        View color1 = findViewById(Resources.getID("color1"));
        View color2 = findViewById(Resources.getID("color2"));
        View color3 = findViewById(Resources.getID("color3"));
        View color4 = findViewById(Resources.getID("color4"));
        View color5 = findViewById(Resources.getID("color5"));

        switch (themeID) {
            case 0:
            default:
                theme = "{\"id\":\"12393\",\"general_statusbarcolor\":\"cb5450\",\"general_toolbarcolor\":\"db6460\",\"general_toolbartextcolor\":\"582826\",\"general_toolbarforeground\":\"582826\",\"general_navbarcolor\":\"12192b\",\"general_darkstatusbaricons\":\"1\",\"nightmode_enable\":\"1\",\"nightmode_atnightonly\":\"0\",\"home_theme\":\"1\",\"home_drawer_header_style\":\"0\",\"home_tabsindicatorcolor\":\"ffffff\",\"home_drawer_dark\":\"1\",\"home_drawer_blackheadertext\":\"1\",\"home_drawer_showsecondaccount\":\"1\",\"home_bottomnavigationbar_autocolor\":\"0\",\"home_bottomnavigationbar_colors_bg\":\"12192b\",\"home_bottomnavigationbar_colors_activeitem\":\"db6460\",\"home_bottomnavigationbar_colors_inactiveitem\":\"999999\",\"conversation_style_toolbar\":\"2\",\"conversation_hideprofilephoto\":\"1\",\"conversation_hidetoolbarattach\":\"1\",\"conversation_toolbarexit\":\"0\",\"conversation_rightbubblecolor\":\"db6460\",\"conversation_rightbubbletextcolor\":\"12192b\",\"conversation_rightbubbledatecolor\":\"12192b\",\"conversation_leftbubblecolor\":\"12192b\",\"conversation_leftbubbletextcolor\":\"ffffffff\",\"conversation_leftbubbledatecolor\":\"db6460\",\"conversation_customparticipantcolorbool\":\"1\",\"conversation_customparticipantcolor\":\"db6460\",\"conversation_custombackcolorbool\":\"1\",\"conversation_custombackcolor\":\"22293b\",\"conversation_style_bubble\":\"7\",\"conversation_style_tick\":\"1\",\"conversation_style_entry\":\"2\",\"nightmode_primarytextcolor\":\"ffffff\",\"nightmode_secondarytextcolor\":\"aaaaaa\",\"nightmode_backgroundcolor\":\"22293b\",\"nightmode_cardsbackgroundcolor\":\"12192b\",\"drawer_light_background\":\"ffffff\",\"drawer_dark_background\":\"22293b\",\"theme_wamod_conversation_entry_bgcolor\":\"00ededed\",\"theme_wamod_conversation_entry_entrybgcolor\":\"f7f7f7\",\"theme_wamod_conversation_entry_hinttextcolor\":\"bbbbbb\",\"theme_wamod_conversation_entry_textcolor\":\"4a2455\",\"theme_wamod_conversation_entry_emojibtncolor\":\"bbbbbb\",\"theme_wamod_conversation_entry_btncolor\":\"f48b5e\",\"theme_wamod_conversation_entry_sendbtncolor\":\"4a2455\",\"theme_hangouts_conversation_entry_bgcolor\":\"12192b\",\"theme_hangouts_conversation_entry_hintcolor\":\"ffffffff\",\"theme_hangouts_conversation_entry_textcolor\":\"ffffffff\",\"theme_hangouts_conversation_attach_color\":\"db6460\",\"theme_hangouts_conversation_mic_bg\":\"db6460\",\"theme_hangouts_conversation_mic_color\":\"12192b\",\"theme_hangouts_conversation_send_bg\":\"db6460\",\"theme_hangouts_conversation_send_color\":\"12192b\",\"theme_simple_conversation_bgcolor\":\"ffffff\",\"theme_simple_conversation_entry_hintcolor\":\"606060\",\"theme_simple_conversation_entry_textcolor\":\"2a2a2a\",\"theme_simple_conversation_mic_color\":\"606060\",\"theme_simple_conversation_send_color\":\"606060\",\"theme_mood_conversation_background_color\":\"55ffffff\",\"theme_mood_conversation_entry_hintcolor\":\"000000\",\"theme_mood_conversation_entry_textcolor\":\"000000\",\"theme_mood_conversation_mic_color\":\"000000\",\"theme_mood_conversation_send_color\":\"000000\",\"theme_mood_conversation_emoji_color\":\"000000\",\"theme_aran_conversation_bgcolor\":\"000000\",\"theme_aran_conversation_entry_bgcolor\":\"222222\",\"theme_aran_conversation_entry_hintcolor\":\"ffffff\",\"theme_aran_conversation_entry_textcolor\":\"ffffff\",\"theme_aran_conversation_emoji_color\":\"ffffff\",\"theme_aran_conversation_mic_color\":\"ee5555\",\"theme_aran_conversation_send_color\":\"ffffff\"}";
                break;
            case 1:
                theme = "{\"id\":\"15728\",\"general_statusbarcolor\":\"e4e7e9\",\"general_toolbarcolor\":\"eceff1\",\"general_toolbartextcolor\":\"4db6ac\",\"general_toolbarforeground\":\"4db6ac\",\"general_navbarcolor\":\"e4e7e9\",\"general_darkstatusbaricons\":\"1\",\"nightmode_enable\":\"1\",\"nightmode_atnightonly\":\"0\",\"home_theme\":\"1\",\"home_drawer_header_style\":\"0\",\"home_tabsindicatorcolor\":\"ffffff\",\"home_drawer_dark\":\"0\",\"home_drawer_blackheadertext\":\"1\",\"home_drawer_showsecondaccount\":\"1\",\"home_bottomnavigationbar_autocolor\":\"0\",\"home_bottomnavigationbar_colors_bg\":\"e4e7e9\",\"home_bottomnavigationbar_colors_activeitem\":\"4db6ac\",\"home_bottomnavigationbar_colors_inactiveitem\":\"888888\",\"conversation_style_toolbar\":\"2\",\"conversation_hideprofilephoto\":\"1\",\"conversation_hidetoolbarattach\":\"1\",\"conversation_toolbarexit\":\"0\",\"conversation_rightbubblecolor\":\"e4e7e9\",\"conversation_rightbubbletextcolor\":\"4db6ac\",\"conversation_rightbubbledatecolor\":\"4db6ac\",\"conversation_leftbubblecolor\":\"4db6ac\",\"conversation_leftbubbletextcolor\":\"e4e7e9\",\"conversation_leftbubbledatecolor\":\"eceff1\",\"conversation_customparticipantcolorbool\":\"1\",\"conversation_customparticipantcolor\":\"eceff1\",\"conversation_custombackcolorbool\":\"1\",\"conversation_custombackcolor\":\"eceff1\",\"conversation_style_bubble\":\"7\",\"conversation_style_tick\":\"1\",\"conversation_style_entry\":\"2\",\"nightmode_primarytextcolor\":\"4db6ac\",\"nightmode_secondarytextcolor\":\"000000\",\"nightmode_backgroundcolor\":\"eceff1\",\"nightmode_cardsbackgroundcolor\":\"eceff1\",\"drawer_light_background\":\"eceff1\",\"drawer_dark_background\":\"eceff1\",\"theme_wamod_conversation_entry_bgcolor\":\"ffffff\",\"theme_wamod_conversation_entry_entrybgcolor\":\"cfd8dc\",\"theme_wamod_conversation_entry_hinttextcolor\":\"263238\",\"theme_wamod_conversation_entry_textcolor\":\"263238\",\"theme_wamod_conversation_entry_emojibtncolor\":\"263238\",\"theme_wamod_conversation_entry_btncolor\":\"263238\",\"theme_wamod_conversation_entry_sendbtncolor\":\"263238\",\"theme_hangouts_conversation_entry_bgcolor\":\"e4e7e9\",\"theme_hangouts_conversation_entry_hintcolor\":\"4db6ac\",\"theme_hangouts_conversation_entry_textcolor\":\"4db6ac\",\"theme_hangouts_conversation_attach_color\":\"4db6ac\",\"theme_hangouts_conversation_mic_bg\":\"4db6ac\",\"theme_hangouts_conversation_mic_color\":\"eceff1\",\"theme_hangouts_conversation_send_bg\":\"4db6ac\",\"theme_hangouts_conversation_send_color\":\"eceff1\",\"theme_simple_conversation_bgcolor\":\"ffffff\",\"theme_simple_conversation_entry_hintcolor\":\"606060\",\"theme_simple_conversation_entry_textcolor\":\"2a2a2a\",\"theme_simple_conversation_mic_color\":\"606060\",\"theme_simple_conversation_send_color\":\"606060\",\"theme_mood_conversation_background_color\":\"55ffffff\",\"theme_mood_conversation_entry_hintcolor\":\"000000\",\"theme_mood_conversation_entry_textcolor\":\"000000\",\"theme_mood_conversation_mic_color\":\"000000\",\"theme_mood_conversation_send_color\":\"000000\",\"theme_mood_conversation_emoji_color\":\"000000\",\"theme_aran_conversation_bgcolor\":\"000000\",\"theme_aran_conversation_entry_bgcolor\":\"222222\",\"theme_aran_conversation_entry_hintcolor\":\"ffffff\",\"theme_aran_conversation_entry_textcolor\":\"ffffff\",\"theme_aran_conversation_emoji_color\":\"ffffff\",\"theme_aran_conversation_mic_color\":\"ee5555\",\"theme_aran_conversation_send_color\":\"ffffff\"}";
                break;
            case 2:
                theme = "{\"id\":\"12406\",\"general_statusbarcolor\":\"054d44\",\"general_toolbarcolor\":\"075e54\",\"general_toolbartextcolor\":\"ffffff\",\"general_toolbarforeground\":\"ffffff\",\"general_navbarcolor\":\"075e54\",\"general_darkstatusbaricons\":\"0\",\"nightmode_enable\":\"0\",\"nightmode_atnightonly\":\"0\",\"home_theme\":\"1\",\"home_drawer_header_style\":\"0\",\"home_tabsindicatorcolor\":\"ffffff\",\"home_drawer_dark\":\"0\",\"home_drawer_blackheadertext\":\"0\",\"home_drawer_showsecondaccount\":\"1\",\"home_bottomnavigationbar_autocolor\":\"0\",\"home_bottomnavigationbar_colors_bg\":\"075e54\",\"home_bottomnavigationbar_colors_activeitem\":\"ffffff\",\"home_bottomnavigationbar_colors_inactiveitem\":\"57aea4\",\"conversation_style_toolbar\":\"0\",\"conversation_hideprofilephoto\":\"0\",\"conversation_hidetoolbarattach\":\"0\",\"conversation_toolbarexit\":\"0\",\"conversation_rightbubblecolor\":\"e2ffc8\",\"conversation_rightbubbletextcolor\":\"12192b\",\"conversation_rightbubbledatecolor\":\"12192b\",\"conversation_leftbubblecolor\":\"ffffffff\",\"conversation_leftbubbletextcolor\":\"ff000000\",\"conversation_leftbubbledatecolor\":\"ff706d75\",\"conversation_customparticipantcolorbool\":\"0\",\"conversation_customparticipantcolor\":\"db6460\",\"conversation_custombackcolorbool\":\"0\",\"conversation_custombackcolor\":\"22293b\",\"conversation_style_bubble\":\"0\",\"conversation_style_tick\":\"0\",\"conversation_style_entry\":\"0\",\"nightmode_primarytextcolor\":\"ffffff\",\"nightmode_secondarytextcolor\":\"aaaaaa\",\"nightmode_backgroundcolor\":\"22293b\",\"nightmode_cardsbackgroundcolor\":\"12192b\",\"drawer_light_background\":\"ffffff\",\"drawer_dark_background\":\"22293b\",\"theme_wamod_conversation_entry_bgcolor\":\"00ededed\",\"theme_wamod_conversation_entry_entrybgcolor\":\"f7f7f7\",\"theme_wamod_conversation_entry_hinttextcolor\":\"bbbbbb\",\"theme_wamod_conversation_entry_textcolor\":\"4a2455\",\"theme_wamod_conversation_entry_emojibtncolor\":\"bbbbbb\",\"theme_wamod_conversation_entry_btncolor\":\"f48b5e\",\"theme_wamod_conversation_entry_sendbtncolor\":\"4a2455\",\"theme_hangouts_conversation_entry_bgcolor\":\"12192b\",\"theme_hangouts_conversation_entry_hintcolor\":\"ffffffff\",\"theme_hangouts_conversation_entry_textcolor\":\"ffffffff\",\"theme_hangouts_conversation_attach_color\":\"db6460\",\"theme_hangouts_conversation_mic_bg\":\"db6460\",\"theme_hangouts_conversation_mic_color\":\"12192b\",\"theme_hangouts_conversation_send_bg\":\"db6460\",\"theme_hangouts_conversation_send_color\":\"12192b\",\"theme_simple_conversation_bgcolor\":\"ffffff\",\"theme_simple_conversation_entry_hintcolor\":\"606060\",\"theme_simple_conversation_entry_textcolor\":\"2a2a2a\",\"theme_simple_conversation_mic_color\":\"606060\",\"theme_simple_conversation_send_color\":\"606060\",\"theme_mood_conversation_background_color\":\"55ffffff\",\"theme_mood_conversation_entry_hintcolor\":\"000000\",\"theme_mood_conversation_entry_textcolor\":\"000000\",\"theme_mood_conversation_mic_color\":\"000000\",\"theme_mood_conversation_send_color\":\"000000\",\"theme_mood_conversation_emoji_color\":\"000000\",\"theme_aran_conversation_bgcolor\":\"000000\",\"theme_aran_conversation_entry_bgcolor\":\"222222\",\"theme_aran_conversation_entry_hintcolor\":\"ffffff\",\"theme_aran_conversation_entry_textcolor\":\"ffffff\",\"theme_aran_conversation_emoji_color\":\"ffffff\",\"theme_aran_conversation_mic_color\":\"ee5555\",\"theme_aran_conversation_send_color\":\"ffffff\"}";
                break;
            case 3:
                theme = "{\"id\":\"11121\",\"general_statusbarcolor\":\"0D8ED3\",\"general_toolbarcolor\":\"0dacf4\",\"general_toolbartextcolor\":\"053954\",\"general_toolbarforeground\":\"255974\",\"general_navbarcolor\":\"2a2a2a\",\"general_darkstatusbaricons\":\"1\",\"nightmode_enable\":\"1\",\"nightmode_atnightonly\":\"0\",\"home_theme\":\"1\",\"home_drawer_header_style\":\"0\",\"home_tabsindicatorcolor\":\"ffffff\",\"home_drawer_dark\":\"1\",\"home_drawer_blackheadertext\":\"0\",\"home_drawer_showsecondaccount\":\"1\",\"home_bottomnavigationbar_autocolor\":\"0\",\"home_bottomnavigationbar_colors_bg\":\"2a2a2a\",\"home_bottomnavigationbar_colors_activeitem\":\"0dacf4\",\"home_bottomnavigationbar_colors_inactiveitem\":\"828C91\",\"conversation_style_toolbar\":\"2\",\"conversation_hideprofilephoto\":\"1\",\"conversation_hidetoolbarattach\":\"1\",\"conversation_toolbarexit\":\"0\",\"conversation_rightbubblecolor\":\"404040\",\"conversation_rightbubbletextcolor\":\"ffffffff\",\"conversation_rightbubbledatecolor\":\"dadada\",\"conversation_leftbubblecolor\":\"303030\",\"conversation_leftbubbletextcolor\":\"ffffffff\",\"conversation_leftbubbledatecolor\":\"dadada\",\"conversation_customparticipantcolorbool\":\"0\",\"conversation_customparticipantcolor\":\"0dacf4\",\"conversation_custombackcolorbool\":\"1\",\"conversation_custombackcolor\":\"202020\",\"conversation_style_bubble\":\"7\",\"conversation_style_tick\":\"1\",\"conversation_style_entry\":\"1\",\"nightmode_primarytextcolor\":\"ffffff\",\"nightmode_secondarytextcolor\":\"aaaaaa\",\"nightmode_backgroundcolor\":\"202020\",\"nightmode_cardsbackgroundcolor\":\"2a2a2a\",\"drawer_light_background\":\"bcbfbf\",\"drawer_dark_background\":\"2a2a2a\",\"theme_wamod_conversation_entry_bgcolor\":\"2a2a2a\",\"theme_wamod_conversation_entry_entrybgcolor\":\"303030\",\"theme_wamod_conversation_entry_hinttextcolor\":\"ffffff\",\"theme_wamod_conversation_entry_textcolor\":\"ffffff\",\"theme_wamod_conversation_entry_emojibtncolor\":\"ffffff\",\"theme_wamod_conversation_entry_btncolor\":\"ffffff\",\"theme_wamod_conversation_entry_sendbtncolor\":\"0dacf4\",\"theme_hangouts_conversation_entry_bgcolor\":\"00000000\",\"theme_hangouts_conversation_entry_hintcolor\":\"ffa8aaad\",\"theme_hangouts_conversation_entry_textcolor\":\"ffffffff\",\"theme_hangouts_conversation_attach_color\":\"0dacf4\",\"theme_hangouts_conversation_mic_bg\":\"00000000\",\"theme_hangouts_conversation_mic_color\":\"ffef3851\",\"theme_hangouts_conversation_send_bg\":\"000caaf2\",\"theme_hangouts_conversation_send_color\":\"0dacf4\",\"theme_simple_conversation_bgcolor\":\"ffffff\",\"theme_simple_conversation_entry_hintcolor\":\"606060\",\"theme_simple_conversation_entry_textcolor\":\"2a2a2a\",\"theme_simple_conversation_mic_color\":\"606060\",\"theme_simple_conversation_send_color\":\"606060\",\"theme_mood_conversation_background_color\":\"55ffffff\",\"theme_mood_conversation_entry_hintcolor\":\"000000\",\"theme_mood_conversation_entry_textcolor\":\"000000\",\"theme_mood_conversation_mic_color\":\"000000\",\"theme_mood_conversation_send_color\":\"000000\",\"theme_mood_conversation_emoji_color\":\"000000\",\"theme_aran_conversation_bgcolor\":\"000000\",\"theme_aran_conversation_entry_bgcolor\":\"222222\",\"theme_aran_conversation_entry_hintcolor\":\"ffffff\",\"theme_aran_conversation_entry_textcolor\":\"ffffff\",\"theme_aran_conversation_emoji_color\":\"ffffff\",\"theme_aran_conversation_mic_color\":\"ee5555\",\"theme_aran_conversation_send_color\":\"ffffff\"}";
                break;
            case 4:
                theme = "{\"id\":\"14975\",\"general_statusbarcolor\":\"ea1e63\",\"general_toolbarcolor\":\"ea1e63\",\"general_toolbartextcolor\":\"ffffff\",\"general_toolbarforeground\":\"ffffff\",\"general_navbarcolor\":\"ea1e63\",\"general_darkstatusbaricons\":\"0\",\"nightmode_enable\":\"1\",\"nightmode_atnightonly\":\"0\",\"home_theme\":\"1\",\"home_drawer_header_style\":\"1\",\"home_tabsindicatorcolor\":\"ffffff\",\"home_drawer_dark\":\"0\",\"home_drawer_blackheadertext\":\"1\",\"home_drawer_showsecondaccount\":\"1\",\"home_bottomnavigationbar_autocolor\":\"0\",\"home_bottomnavigationbar_colors_bg\":\"ea1e63\",\"home_bottomnavigationbar_colors_activeitem\":\"ffffffff\",\"home_bottomnavigationbar_colors_inactiveitem\":\"252525\",\"conversation_style_toolbar\":\"0\",\"conversation_hideprofilephoto\":\"1\",\"conversation_hidetoolbarattach\":\"1\",\"conversation_toolbarexit\":\"1\",\"conversation_rightbubblecolor\":\"affcfcfc\",\"conversation_rightbubbletextcolor\":\"0a0217\",\"conversation_rightbubbledatecolor\":\"ea1e63\",\"conversation_leftbubblecolor\":\"affcfcfc\",\"conversation_leftbubbletextcolor\":\"0a0217\",\"conversation_leftbubbledatecolor\":\"ea1e63\",\"conversation_customparticipantcolorbool\":\"0\",\"conversation_customparticipantcolor\":\"000000\",\"conversation_custombackcolorbool\":\"0\",\"conversation_custombackcolor\":\"2f2f2f\",\"conversation_style_bubble\":\"1\",\"conversation_style_tick\":\"1\",\"conversation_style_entry\":\"6\",\"nightmode_primarytextcolor\":\"ffffff\",\"nightmode_secondarytextcolor\":\"aaaaaa\",\"nightmode_backgroundcolor\":\"252525\",\"nightmode_cardsbackgroundcolor\":\"303030\",\"drawer_light_background\":\"cfcfcf\",\"drawer_dark_background\":\"19275b\",\"theme_wamod_conversation_entry_bgcolor\":\"0E0F2B\",\"theme_wamod_conversation_entry_entrybgcolor\":\"0E0F2B\",\"theme_wamod_conversation_entry_hinttextcolor\":\"ffffffff\",\"theme_wamod_conversation_entry_textcolor\":\"ffffffff\",\"theme_wamod_conversation_entry_emojibtncolor\":\"F4125C\",\"theme_wamod_conversation_entry_btncolor\":\"F4125C\",\"theme_wamod_conversation_entry_sendbtncolor\":\"F4125C\",\"theme_hangouts_conversation_entry_bgcolor\":\"102e41\",\"theme_hangouts_conversation_entry_hintcolor\":\"ffffffff\",\"theme_hangouts_conversation_entry_textcolor\":\"ffffffff\",\"theme_hangouts_conversation_attach_color\":\"ffffffff\",\"theme_hangouts_conversation_mic_bg\":\"9e2f42\",\"theme_hangouts_conversation_mic_color\":\"ffffffff\",\"theme_hangouts_conversation_send_bg\":\"cd4613\",\"theme_hangouts_conversation_send_color\":\"ffffffff\",\"theme_simple_conversation_bgcolor\":\"ffffff\",\"theme_simple_conversation_entry_hintcolor\":\"606060\",\"theme_simple_conversation_entry_textcolor\":\"2a2a2a\",\"theme_simple_conversation_mic_color\":\"606060\",\"theme_simple_conversation_send_color\":\"606060\",\"theme_mood_conversation_background_color\":\"55ffffff\",\"theme_mood_conversation_entry_hintcolor\":\"000000\",\"theme_mood_conversation_entry_textcolor\":\"000000\",\"theme_mood_conversation_mic_color\":\"000000\",\"theme_mood_conversation_send_color\":\"000000\",\"theme_mood_conversation_emoji_color\":\"000000\",\"theme_aran_conversation_bgcolor\":\"38161616\",\"theme_aran_conversation_entry_bgcolor\":\"49141414\",\"theme_aran_conversation_entry_hintcolor\":\"ffffffff\",\"theme_aran_conversation_entry_textcolor\":\"ffffff\",\"theme_aran_conversation_emoji_color\":\"ffffff\",\"theme_aran_conversation_mic_color\":\"ffffffff\",\"theme_aran_conversation_send_color\":\"ffffffff\"}";
                break;
        }

        try {
            themeObj = new JSONObject(theme);

            color1.setBackgroundColor(Color.parseColor("#" + themeObj.getString("general_statusbarcolor")));
            color2.setBackgroundColor(Color.parseColor("#" + themeObj.getString("general_toolbarcolor")));
            color3.setBackgroundColor(Color.parseColor("#" + themeObj.getString("general_toolbartextcolor")));
            color4.setBackgroundColor(Color.parseColor("#" + themeObj.getString("general_navbarcolor")));
            if (themeObj.getString("nightmode_enable").contentEquals("1"))
                color5.setBackgroundColor(Color.parseColor("#" + themeObj.getString("nightmode_backgroundcolor")));
            else
                color5.setBackgroundColor(Color.WHITE);
        } catch (JSONException e) {
            Utils.manageException(e);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP)
            setupActivity.changeTheme(ev, themeObj);
        return super.onInterceptTouchEvent(ev);
    }
}
