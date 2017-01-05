package com.wamod;

import android.net.Uri;
import com.whatsapp.aam;
import com.whatsapp.rv;

import java.util.Map;

/**
 * Created by brianvalente on 9/23/16.
 */

public class Contact {
    public transient boolean a; // Always true? ...
    public  String  fullName; // Complete name
    public  String  status; // Status
    public  String  firstName; // First name
    public  long    lastInteraction; // Last interaction
    public  String  lastName; // Last name
    public  String  phoneType; // Phone type (Mobile, Home, Tuenti, etc.)
    public  String  pushName; // Push name
    public  String  jabberId; // Jabber ID
    public long     registrationDate; // Registration date

    public rv whatsappClass;

    public static Contact getContactFromUri(Uri uri) {
        aam uriParser = aam.a(Utils.context);
        return new Contact(uriParser.a(uri));
    }

    public static Contact getContactFromJabberId(String jabberId) {
        aam uriParser = aam.a(Utils.context);
        return new Contact(uriParser.i(jabberId));
    }

    public Contact(rv contact) {
        whatsappClass    = contact;
        fullName         = contact.b;
        firstName        = contact.k;
        lastName         = contact.n;
        lastInteraction  = contact.l;
        phoneType        = contact.o;
        pushName         = contact.s;
        jabberId         = contact.v;
        registrationDate = contact.z;
    }

    public static Contact parse(rv contact) {
        return new Contact(contact);
    }
}
