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
                contactInfo.mFullName = "Sexy Guy";
                break;
        }

        return contactInfo;
    }

}
