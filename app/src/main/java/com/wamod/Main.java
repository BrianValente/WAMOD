package com.wamod;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.whatsapp.DialogToastActivity;

/**
 * Created by brianvalente on 2/6/16.
 */
public class Main extends DialogToastActivity {
    @Override
    protected Dialog onCreateDialog(int id) {
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        return super.onCreateDialog(id, args);
    }

    protected Dialog onCreateDialog(Dialog dialog) {
        return null;
    }
}
