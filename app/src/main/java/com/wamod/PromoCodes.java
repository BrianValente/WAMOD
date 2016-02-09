package com.wamod;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by brianvalente on 2/6/16.
 */
public class PromoCodes extends AppCompatActivity {

    public static Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
    }

    public void test() {
        String string = random();
        Log.i("PromoCodes", string);
    }

    public static String random() {
        int sizeOfRandomString = 16;
        String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public static String getUniquePsuedoID(Context ctx) {
        String string = ""+System.currentTimeMillis();
        while (string.length() < 16) string += "a";
        return string;
    }

    public void tryLog() {
        logCode(this, "code");
    }

    public void simulateTextViewClick() {
        TextView tv = null;
        tv.performClick();
    }


    public static void logCode(Context context, String code) {
        Log.i("PromoCodes", code);

        Intent mStartActivity = new Intent(context, Settings.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }

    public void tryRestart() {
        restartApp();
    }

    public static void restartApp() {
        Context context = PromoCodes.context;
        Intent mStartActivity = new Intent(context, Settings.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }
}
