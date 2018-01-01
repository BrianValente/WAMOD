package com.whatsapp;

import com.whatsapp.data.ContactInfo;

/**
 * Created by brianvalente on 12/9/17.
 */

public class MeManager {

    public Me getMe() {
        return new Me();
    }

    public MeInfo getMeInfo() {
        MeInfo meInfo = new MeInfo();


        return meInfo;
    }

    public class MeInfo extends ContactInfo {}

}
