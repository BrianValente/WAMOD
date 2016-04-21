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
        ts VoiceNote;
        VoiceNoteManager(Conversation conversation) {
            VoiceNote = new tv(conversation, conversation, conversation);
            VoiceNote.a(conversation.aa);
        }
        public void StartRecording() {
            VoiceNote.f();
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
            conversation.U(conversation);
        }
        private void attachPhotoUsingStockGallery() {
            performClick(new fi(conversation));
        }
        public void attachFromCamera() {
            performClick(new eg(conversation, true));
        }
        public void attachAudio() {
            performClick(new alt(conversation));
        }
        public void attachContact() {
            performClick(new xq(conversation));
        }
        public void attachLocation() {
            performClick(new abt(conversation));
        }
    }
}
