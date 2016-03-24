package com.wamod;

import android.view.View;
import android.widget.Button;

import com.whatsapp.*;
import com.whatsapp.Conversation;

/**
 * Created by brianvalente on 2/15/16.
 */
public class Events {
    public static class VoiceNoteManager {
        nv VoiceNote;
        VoiceNoteManager(Conversation conversation) {
            VoiceNote = new nr(conversation, conversation, conversation);
            VoiceNote.a(conversation.bv);
        }
        public void StartRecording() {
            VoiceNote.e();
        }
        public void StopRecording() {
            VoiceNote.b(true);
        }
        public void DiscardRecord() {
            VoiceNote.b(false);
        }
    }

    public static class AttachmentsManager {
        Conversation conversation;
        Button btn;
        AttachmentsManager(Conversation conversation) {
            this.conversation = conversation;
            this.btn = new Button(conversation);
        }
        public void attachFromGallery() {
            if (utils.prefs.getBoolean("conversation_androidgallery", true))
                 attachPhotoUsingAndroidGallery();
            else attachPhotoUsingStockGallery();
        }
        private void performClick(View.OnClickListener onClickListener) {
            btn.setOnClickListener(onClickListener);
            btn.performClick();
        }
        private void attachPhotoUsingAndroidGallery() {
            conversation.l(conversation);
        }
        private void attachPhotoUsingStockGallery() {
            performClick(new sf(conversation));
        }
        public void attachFromCamera() {
            performClick(new ask(conversation, true));
        }
        public void attachAudio() {
            performClick(new zo(conversation));
        }
        public void attachContact() {
            performClick(new gw(conversation));
        }
        public void attachLocation() {
            performClick(new x3(conversation));
        }
    }
}
