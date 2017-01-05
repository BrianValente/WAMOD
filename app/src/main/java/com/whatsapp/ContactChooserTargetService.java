package com.whatsapp;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.*;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;
import android.util.Log;

import java.util.List;

/**
 * Created by brianvalente on 12/14/16.
 */

@TargetApi(Build.VERSION_CODES.M)
public class ContactChooserTargetService extends ChooserTargetService {
    private final IBinder mBinder = new MyBinder();
    private Messenger outMessenger;

    @Override
    public List<ChooserTarget> onGetChooserTargets(ComponentName componentName, IntentFilter intentFilter) {
        return null;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        Bundle extras = arg0.getExtras();
        if (extras != null) {
            outMessenger = (Messenger) extras.get("MESSENGER");
        }
        return mBinder;
    }

    public class MyBinder extends Binder {
        public ContactChooserTargetService getService() {
            return ContactChooserTargetService.this;
        }
    }
}
