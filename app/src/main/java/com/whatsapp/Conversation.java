package com.whatsapp;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Conversation extends DialogToastActivity {
    public String V; // Contact's number

    public void callInitConversation() {
        com.wamod.WAclass.Conversation.initConversation(this);
    }

    // Opens Android Gallery
    public static void a(Conversation c) {}

    // Send message
    public static void W(Conversation c) {}

    // For voice notes
    public static m s(Conversation c) {
        return new m(c,c);
    }

    // Init attachments
    public void r() {}

    public void onCreateOptionsMenu() {
        com.wamod.WAclass.Conversation.tintToolbarButtons(null);
    }

    // Load contact
    public static void aq(Conversation c) {}
}

