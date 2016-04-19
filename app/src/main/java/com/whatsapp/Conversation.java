package com.whatsapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Conversation extends DialogToastActivity {
    public String bv; // Contact's number

    public void callInitConversation() {
        com.wamod.Conversation.initConversation(this);
    }

    // Opens Android Gallery
    public static void l(Conversation c) {}

    // Send message
    public static void I(Conversation c) {}

    // For voice notes
    public static nv W(Conversation c) {
        return new nv(c,c);
    }

    // Init attachments
    public void E() {}
}
