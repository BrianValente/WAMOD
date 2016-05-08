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
        agq VoiceNote;
        public VoiceNoteManager(Conversation conversation) {
            VoiceNote = new agg(conversation, conversation, conversation);
            VoiceNote.a(conversation.a8);
        }
        public void StartRecording() {
            VoiceNote.c();
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
        public AttachmentsManager(Conversation conversation) {
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
            conversation.ac(conversation);
        }
        private void attachPhotoUsingStockGallery() {
            performClick(new fz(conversation));
        }
        public void attachFromCamera() {
            performClick(new aec(conversation, true));
        }
        public void attachAudio() {
            performClick(new a8s(conversation));
        }
        public void attachContact() {
            performClick(new ajg(conversation));
        }
        public void attachLocation() {
            performClick(new aqy(conversation));
        }
    }
}
