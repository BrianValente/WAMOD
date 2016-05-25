package com.wamod.entry;

import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wamod.Resources;
import com.wamod.Utils;
import com.whatsapp.*;
import com.whatsapp.Conversation;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;

/**
 * Created by brianvalente on 2/15/16.
 */
public class Events {
    public static class VoiceNoteManager {
        ae_ VoiceNote;
        public VoiceNoteManager(Conversation conversation) {
            VoiceNote = new aew(conversation, conversation, conversation);
            VoiceNote.a(conversation.bk);
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
            conversation.o(conversation);
        }
        private void attachPhotoUsingStockGallery() {
            performClick(new sm(conversation));
        }
        public void attachFromCamera() {
            performClick(new a5o(conversation, true));
        }
        public void attachAudio() {
            performClick(new s_(conversation));
        }
        public void attachContact() {
            performClick(new w_(conversation));
        }
        public void attachLocation() {
            performClick(new adw(conversation));
        }
    }

    public static class NewVoiceNoteManager {
        public static final int ANIMATION_DURATION = 400;
        ae_ VoiceNote;
        Conversation conversation;

        public NewVoiceNoteManager(final Conversation conversation) {
            VoiceNote = new aew(conversation, conversation, conversation);
            VoiceNote.a(conversation.bk);
            this.conversation = conversation;

            final View voice_note_btn   = conversation.findViewById(Resources.getID("voice_note_btn"));
            final ImageButton micDelete = (ImageButton)        conversation.findViewById(Resources.getID("wamod_conversation_voicenotemanager_mic_delete"));
            final ImageButton micSend   = (ImageButton)        conversation.findViewById(Resources.getID("wamod_conversation_voicenotemanager_mic_send"));
            final ImageView wave1       = (ImageView) conversation.findViewById(Resources.getID("wamod_conversation_voicenotemanager_mic_wave1"));

            if (voice_note_btn != null) {
                voice_note_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StartRecording();
                    }
                });
                voice_note_btn.setOnTouchListener(null);
            }
            micDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DiscardRecord();
                }
            });
            micSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StopRecording();
                }
            });

            Animation anim = new Animation() {
                float waveWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)279.67, conversation.getResources().getDisplayMetrics());
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    float actualMargin = waveWidth * interpolatedTime;
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) wave1.getLayoutParams();
                    params.leftMargin = -(int)actualMargin;
                    wave1.setLayoutParams(params);
                }
            };
            anim.setDuration(1250);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.INFINITE);
            wave1.setAnimation(anim);
            anim.startNow();
        }
        
        public void StartRecording() {
            VoiceNote.e();
            animate(true);
        }
        
        public void StopRecording() {
            VoiceNote.b(true);
            animate(false);
        }
        
        public void DiscardRecord() {
            VoiceNote.b(false);
            animate(false);
        }
        
        private void animate(boolean open) {
            // Hide keyboard
            View view = conversation.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) conversation.getSystemService(conversation.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            
            
            final RelativeLayout micContainer = (RelativeLayout) conversation.findViewById(Resources.getID("wamod_conversation_voicenotemanager_mic_container"));
            final RevealLinearLayout extras = (RevealLinearLayout) conversation.findViewById(Resources.getID("wamod_conversation_voicenotemanager"));

            micContainer.setVisibility(View.VISIBLE);

            float ht_px = extras.getHeight();
            float wt_px = extras.getWidth();

            // get the center for the clipping circle
            int cx = (int)wt_px / 2;
            int cy = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, conversation.getResources().getDisplayMetrics());

            // get the final radius for the clipping circle
            int dx = Math.max(cx, (int)wt_px - cx);
            int dy = Math.max(cy, (int)ht_px - cy);
            float finalRadius = (float) Math.hypot(dx, dy);

            SupportAnimator animator = ViewAnimationUtils.createCircularReveal(micContainer, cx, cy, 0, finalRadius);
            animator.setDuration(ANIMATION_DURATION);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());

            SupportAnimator.AnimatorListener listener = new SupportAnimator.AnimatorListener() {
                @Override public void onAnimationStart() {}
                @Override public void onAnimationEnd() {micContainer.setVisibility(View.GONE);}
                @Override public void onAnimationCancel() {}
                @Override public void onAnimationRepeat() {}
            };

            if (open) {
                animator.start();
            } else {
                animator = animator.reverse();
                animator.addListener(listener);
                animator.start();
            }
        }
    }
}
