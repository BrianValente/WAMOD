package com.whatsapp.protocol;

/**
 * Created by brianvalente on 5/9/16.
 */
public class at {
    public final String a;  // Message ID
    public final boolean b; // 0x0?
    public final String c;  // JID (Jabber ID?)

    //public g I;             // com.whatsapp.protocol.p 2.16.81

    public at(String a, boolean b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
