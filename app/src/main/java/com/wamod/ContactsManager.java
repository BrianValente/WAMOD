package com.wamod;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by brianvalente on 11/30/16.
 */
public class ContactsManager {
    public ContactsManager() {

    }

    public List<Contact> getContacts() {
        return null;
    }

    public Bitmap getContactPhoto(String jabberId) {
        String s = Utils.getApplicationPath(Utils.context);
        String pathName = s + "/files/Avatars/" + jabberId + ".j";
        Bitmap photo = BitmapFactory.decodeFile(pathName);
        return photo;
    }
}
