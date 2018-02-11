package com.whatsapp.data;

/**
 * Created by brianvalente on 12/9/17.
 */

public class ContactsManager {

    public static ContactsManager getContactsManager() {
        return new ContactsManager();
    }

    public ContactInfo getContactByJabberId(String jabberId) {
        ContactInfo contactInfo = new ContactInfo();

        switch (jabberId) {
            case "":
                contactInfo.mJabberId = "";
                contactInfo.mFullName = "Brian Valente";
                break;
            case "someone":
                contactInfo.mJabberId = "someone";
                contactInfo.mPushName = "Bri";
                contactInfo.mFullName = "Brian Valente";
                break;
            case "5491100000000@s.whatsapp.net":
                contactInfo.mPushName = "Push Name";
                contactInfo.mJabberId = "5491100000000@s.whatsapp.net";
                break;
        }

        return contactInfo;
    }

}
