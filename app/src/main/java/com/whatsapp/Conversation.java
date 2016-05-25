package com.whatsapp;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Conversation extends DialogToastActivity {
    public String bk; // Contact's number

    public void callInitConversation() {
        com.wamod.WAclass.Conversation.initConversation(this);
    }

    // Opens Android Gallery
    public static void o(Conversation c) {}

    // Send message
    public static void n(Conversation c) {}

    // For voice notes
    public static ae_ aa(Conversation c) {
        return new ae_(c,c);
    }

    // Init attachments
    public void ac() {}

    public void onCreateOptionsMenu() {
        com.wamod.WAclass.Conversation.tintToolbarButtons(null);
    }

    // Load contact
    public static void s(Conversation c) {}
}

