package com.wamod.settings;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by BrianValente on 3/3/16.
 */
public class Settings extends Activity {
    static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Utils.loadColorsBeforeSuper(this);
        super.onCreate(savedInstanceState);
        ctx = this;
        getFragmentManager().beginTransaction().replace(android.R.id.content, new WAMODSettingsFragment()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.loadColorsV2(this);
    }

    public static class WAMODSettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("wamod");
            addPreferencesFromResource(Resources.xml.wamodsettings);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}