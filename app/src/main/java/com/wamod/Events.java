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
        vj VoiceNote;
        VoiceNoteManager(Conversation conversation) {
            VoiceNote = conversation.m(conversation);
        }
        public void StartRecording() {
            VoiceNote.a();
        }
        public void StopRecording() {
            VoiceNote.a(true);
        }
        public void DiscardRecord() {
            VoiceNote.a(false);
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
            conversation.ag(conversation);
        }
        private void attachPhotoUsingStockGallery() {
            performClick(new nb(conversation));
        }
        public void attachFromCamera() {
            performClick(new a55(conversation, true));
        }
        public void attachAudio() {
            performClick(new alh(conversation));
        }
        public void attachContact() {
            performClick(new al6(conversation));
        }
        public void attachLocation() {
            performClick(new jq(conversation));
        }
    }
}
