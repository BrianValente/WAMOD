package com.whatsapp.contact;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.wamod.R;
import com.whatsapp.data.ContactInfo;

import wamod.App;
import wamod.utils.Utils;

/**
 * Created by brianvalente on 12/9/17.
 */

public class StockPicture {

    public Bitmap getStockPicture(ContactInfo contactInfo) {
        Drawable myDrawable = App.mContext.getDrawable(R.drawable.ic_people);
        return Utils.drawableToBitmap(myDrawable);
    }

    public static StockPicture getStockPicture() {
        return new StockPicture();
    }

}
