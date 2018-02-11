package com.whatsapp.contact.a;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.wamod.R;
import com.whatsapp.data.ContactInfo;

import wamod.App;

/**
 * Created by brianvalente on 12/9/17.
 */

public class Picture {

    public static Picture getPicture() {
        return new Picture();
    }

    public Bitmap getBitmap(ContactInfo contactInfo, int pixelSize, float idk, boolean idk2) {

        Drawable myDrawable = null;

        switch (contactInfo.mJabberId) {
            case "":
                myDrawable = App.mContext.getDrawable(R.drawable.brian2);
                break;
            case "someone":
                myDrawable = App.mContext.getDrawable(R.drawable.brian3);
        }

        if (myDrawable != null) {
            return ((BitmapDrawable) myDrawable).getBitmap();
        }

        return null;
    }
}
