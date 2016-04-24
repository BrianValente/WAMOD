package com.whatsapp;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Conversation extends DialogToastActivity {
    public String aa; // Contact's number

    public void callInitConversation() {
        com.wamod.Conversation.initConversation(this);
    }

    // Opens Android Gallery
    public static void U(Conversation c) {}

    // Send message
    public static void j(Conversation c) {}

    // For voice notes
    public static agq W(Conversation c) {
        return new agq(c,c);
    }

    // Init attachments
    public void X() {}

    public void onCreateOptionsMenu() {
        com.wamod.Conversation.tintToolbarButtons(null);
    }

    // Load contact
    public static void k(Conversation c) {}
}
