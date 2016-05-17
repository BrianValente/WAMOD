package com.wamod.entry;

import android.view.View;
import android.widget.Button;

import com.wamod.Utils;
import com.whatsapp.*;
import com.whatsapp.Conversation;

/**
 * Created by brianvalente on 2/15/16.
 */
public class Events {
    public static class VoiceNoteManager {
        m VoiceNote;
        public VoiceNoteManager(Conversation conversation) {
            VoiceNote = new n(conversation, conversation, conversation);
            VoiceNote.a(conversation.V);
        }
        public void StartRecording() {
            VoiceNote.i();
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
        public AttachmentsManager(Conversation conversation) {
            this.conversation = conversation;
            this.btn = new Button(conversation);
        }
        public void attachFromGallery() {
            if (Utils.prefs.getBoolean("conversation_androidgallery", true))
                 attachPhotoUsingAndroidGallery();
            else attachPhotoUsingStockGallery();
        }
        private void performClick(View.OnClickListener onClickListener) {
            btn.setOnClickListener(onClickListener);
            btn.performClick();
        }
        private void attachPhotoUsingAndroidGallery() {
            conversation.a(conversation);
        }
        private void attachPhotoUsingStockGallery() {
            performClick(new _y(conversation));
        }
        public void attachFromCamera() {
            performClick(new av(conversation, true));
        }
        public void attachAudio() {
            performClick(new y5(conversation));
        }
        public void attachContact() {
            performClick(new b8(conversation));
        }
        public void attachLocation() {
            performClick(new a5i(conversation));
        }
    }
}
