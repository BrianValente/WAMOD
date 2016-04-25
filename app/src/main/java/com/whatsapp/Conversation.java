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
    public static void ac(Conversation c) {}

    // Send message
    public static void ai(Conversation c) {}

    // For voice notes
    public static agq U(Conversation c) {
        return new agq(c,c);
    }

    // Init attachments
    public void u() {}

    public void onCreateOptionsMenu() {
        com.wamod.Conversation.tintToolbarButtons(null);
    }

    // Load contact
    public static void i(Conversation c) {}
}
