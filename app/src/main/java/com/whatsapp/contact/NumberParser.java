package com.whatsapp.contact;

import com.whatsapp.data.ContactInfo;

/**
 * Created by brianvalente on 12/9/17.
 */

public class NumberParser {

    public static String parseNumber(ContactInfo contactInfo) {
        return contactInfo.mJabberId;
    }

}
