package com.whatsapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by brianvalente on 9/21/15.
 */
public class Conversation extends AppCompatActivity {
    public void callInitConversation() {
        com.wamod.Conversation.initConversation(this);
    }

    // ??
    public static void I(Conversation c) {}

    // Opens Android Gallery
    public static void ag(Conversation c) {}

    // Send message
    public static void al(Conversation c) {}

    // For voice notes
    public static vj m(Conversation c) {
        return new vj();
    }
}
