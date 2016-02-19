package com.wamod;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Trace;
import android.util.Log;

/**
 * Created by brianvalente on 2/12/16.
 */
public class test {
    private void test(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);

            Signature[] sigs = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;

            for (Signature sig : sigs) {
                Log.i("MyApp", "Signature hashcode : " + sig.hashCode());
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
