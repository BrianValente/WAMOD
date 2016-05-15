package com.wamod.settings;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wamod.WAclass.Conversation;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by brianvalente on 5/7/16.
 */
public class Bubbles extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.layout.wamod_activity_settings_bubbles);
        Utils.loadColorsV2(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ConfigurationFragment configurationFragment = new ConfigurationFragment();
        configurationFragment.a = this;
        getFragmentManager().beginTransaction().replace(Resources.id.wamod_fragment, configurationFragment).commit();
        reloadColors();
    }

    public void reloadColors() {
        RelativeLayout bubble1 = (RelativeLayout) findViewById(Utils.getHexID("bubble1", "id"));
        RelativeLayout bubble2 = (RelativeLayout) findViewById(Utils.getHexID("bubble2", "id"));
        RelativeLayout bubble3 = (RelativeLayout) findViewById(Utils.getHexID("bubble3", "id"));
        RelativeLayout bubble4 = (RelativeLayout) findViewById(Utils.getHexID("bubble4", "id"));
        RelativeLayout bubble5 = (RelativeLayout) findViewById(Utils.getHexID("bubble5", "id"));

        TextView participant1 = (TextView) findViewById(Utils.getHexID("participant1", "id"));
        TextView participant5 = (TextView) findViewById(Utils.getHexID("participant5", "id"));

        TextView msg1 = (TextView) findViewById(Utils.getHexID("msg1", "id"));
        TextView msg2 = (TextView) findViewById(Utils.getHexID("msg2", "id"));
        TextView msg3 = (TextView) findViewById(Utils.getHexID("msg3", "id"));
        TextView msg4 = (TextView) findViewById(Utils.getHexID("msg4", "id"));
        TextView msg5 = (TextView) findViewById(Utils.getHexID("msg5", "id"));

        TextView date1 = (TextView) findViewById(Utils.getHexID("date1", "id"));
        TextView date2 = (TextView) findViewById(Utils.getHexID("date2", "id"));
        TextView date3 = (TextView) findViewById(Utils.getHexID("date3", "id"));
        TextView date4 = (TextView) findViewById(Utils.getHexID("date4", "id"));
        TextView date5 = (TextView) findViewById(Utils.getHexID("date5", "id"));

        ImageView tick3 = (ImageView) findViewById(Utils.getHexID("tick3", "id"));
        ImageView tick4 = (ImageView) findViewById(Utils.getHexID("tick4", "id"));


        bubble1.setBackground(Conversation.getBubbleDrawable(0));
        bubble2.setBackground(Conversation.getBubbleDrawable(1));
        bubble3.setBackground(Conversation.getBubbleDrawable(2));
        bubble4.setBackground(Conversation.getBubbleDrawable(3));
        bubble5.setBackground(Conversation.getBubbleDrawable(0));

        int participantColor = Utils.prefs.getBoolean("conversation_customparticipantcolorbool", false)? Color.parseColor("#" + Utils.prefs.getString("conversation_customparticipantcolor", "FFFFFF")) : Color.parseColor("#dd5020");
        participant1.setTextColor(participantColor);
        participant5.setTextColor(participantColor);

        int rightTextColor = Color.parseColor("#" + Utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF"));
        int leftTextColor  = Color.parseColor("#" + Utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF"));
        msg1.setTextColor(leftTextColor);
        msg2.setTextColor(leftTextColor);
        msg3.setTextColor(rightTextColor);
        msg4.setTextColor(rightTextColor);
        msg5.setTextColor(leftTextColor);

        int rightDateColor = Color.parseColor("#" + Utils.prefs.getString("conversation_rightbubbledatecolor", "FFFFFF"));
        int leftDateColor = Color.parseColor("#" + Utils.prefs.getString("conversation_leftbubbledatecolor", "FFFFFF"));
        date1.setTextColor(leftDateColor);
        date2.setTextColor(leftDateColor);
        date3.setTextColor(rightDateColor);
        date4.setTextColor(rightDateColor);
        date5.setTextColor(leftDateColor);

        tick3.setImageDrawable(getResources().getDrawable(Utils.getTickDrawableHex(3)));
        tick4.setImageDrawable(getResources().getDrawable(Utils.getTickDrawableHex(3)));
    }

    public static class ConfigurationFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        public static Bubbles a;

        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("wamod");
            addPreferencesFromResource(Resources.xml.wamodsettings_conversation_bubbles);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            a.reloadColors();
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}
