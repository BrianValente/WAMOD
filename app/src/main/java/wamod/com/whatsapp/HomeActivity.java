package wamod.com.whatsapp;

import android.content.Intent;
import android.os.Bundle;

import com.whatsapp.*;
import com.whatsapp.Activity;

/**
 * Created by brianvalente on 11/27/17.
 */

public class HomeActivity extends ActivityInjector {

    @Override
    public void onCreate_Before(Activity context, Bundle savedInstanceState) {
        Bundle extras = context.getIntent().getExtras();
        if (extras == null || !extras.containsKey("wamod_show")) {
            Intent intent = new Intent(context, wamod.activity.HomeActivity.class);
            context.startActivity(intent);
            context.overridePendingTransition(0, 0);
            context.finish();
        }
    }

    @Override
    public void onCreate_After(Activity context, Bundle savedInstanceState) {

    }

    @Override
    public void onResumeBefore(Activity context) {

    }

    @Override
    public void onResume_After(Activity context) {

    }
}
