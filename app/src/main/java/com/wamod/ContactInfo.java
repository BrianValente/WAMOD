package com.wamod;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by brianvalente on 9/20/15.
 */
public class ContactInfo extends Activity {
    Activity activity;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    public void callTaskDescription() {
        String name = "name";
        setProfileTaskDescription(name, this);
    }

    public static void setProfileTaskDescription(String name, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String title = activity.getString(id.appname);
            if (utils.prefs.getBoolean("overview_multiplechats", true)) title = activity.getString(id.profileof, name);
            int color = Color.parseColor("#075e54");
            if (utils.prefs.getBoolean("overview_cardcolor", true)) color = Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"));
            ActivityManager.TaskDescription taskDesc = new ActivityManager.TaskDescription(title, BitmapFactory.decodeResource(activity.getResources(), id.appicon), color);
            activity.setTaskDescription(taskDesc);
        }
    }
}
