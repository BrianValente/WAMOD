package com.wamod.entry;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.wamod.Events;
import com.wamod.R;
import com.wamod.Resources;
import com.whatsapp.Conversation;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;

/**
 * Created by brianvalente on 10/4/15.
 */
public class Test extends Conversation {
    static LinearLayout attachPopup = null;
    static boolean wamod = true;
    private static float lastEntryHeight = 0;

    public static final int START_RECORDING = 0;
    public static final int STOP_RECORDING = 1;
    public static final int DISCARD_VOICE_NOTE = 2;

    public static final int ANIMATION_DURATION = 400;

    public static Events.VoiceNoteManager mVoiceNoteManager;
    public static Events.AttachmentsManager mAttachmentsManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.as_wamod_theme_test_conversation);

        init(this);
    }

    public static void init(final com.whatsapp.Conversation a) {
        final int HEIGHT_DP = 57;
        final int HEIGHT_PX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DP, a.getResources().getDisplayMetrics());

        mVoiceNoteManager   = new Events.VoiceNoteManager(a);
        mAttachmentsManager = new Events.AttachmentsManager(a);

        EditText originalEditText                   = (EditText)           a.findViewById(Resources.id.entry);
        final ImageButton attachBtn                 = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_btn);
        final ImageButton sendBtn                   = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_send);
        final ImageButton micBtn                    = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_mic);
        attachPopup                                 = (LinearLayout)       a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_layout);
        final RevealLinearLayout reveallinearlayout = (RevealLinearLayout) a.findViewById(Resources.id.wamod_theme_test_conversation_entry_reveallinearlayout);
        final ListView listView                     = (ListView)           a.findViewById(android.R.id.list);
        final EditText edittext                     = (EditText)           a.findViewById(Resources.id.wamod_theme_test_conversation_entry_edittext);
        final ImageButton extrasMicDelete           = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_extras_mic_delete);
        final ImageButton extrasMicSend             = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_extras_mic_send);
        final ImageButton attachments_GalleryBtn    = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_gallery);
        final ImageButton attachments_CameraBtn     = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_camera);
        final ImageButton attachments_AudioBtn      = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_audio);
        final ImageButton attachments_ContactBtn    = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_contact);
        final ImageButton attachments_LocationBtn   = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_location);
        final ImageButton attachments_EditBtn       = (ImageButton)        a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_edit);

        if (wamod) listView.setPadding(0, 0, 0, HEIGHT_PX);

        sendBtn.setOnClickListener(new SendMessage(a, edittext));
        attachBtn.setOnClickListener(new ToggleAttachmentsPopup(a));

        animateVoiceNote(a);

        edittext.setText(originalEditText.getText());
        edittext.clearFocus();
        edittext.addTextChangedListener(new EditTextTextListener(a));
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if (attachPopup.getVisibility() == View.VISIBLE) attachBtn.performClick();
            }
        });
        edittext.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (lastEntryHeight == 0) lastEntryHeight = reveallinearlayout.getHeight();
                if (wamod) {
                    float newHeight = reveallinearlayout.getHeight();
                    if (lastEntryHeight != newHeight) {
                        float difference = newHeight - lastEntryHeight;

                        // Attachments popup opened
                        if (difference == attachPopup.getHeight() || difference == -(attachPopup.getHeight())) return;
                        if (attachPopup.getVisibility() == View.VISIBLE) difference -= attachPopup.getHeight();

                        listView.scrollBy(0, (int)difference);
                        lastEntryHeight = newHeight;
                    }
                }
            }
        });

        micBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoiceNoteControl(a, START_RECORDING);
            }
        });
        extrasMicDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {VoiceNoteControl(a, DISCARD_VOICE_NOTE);
            }
        });
        extrasMicSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {VoiceNoteControl(a, STOP_RECORDING);
            }
        });

        attachments_GalleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mAttachmentsManager.attachFromGallery(); attachBtn.performClick();}
        });
        attachments_CameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mAttachmentsManager.attachFromCamera(); attachBtn.performClick();}
        });
        attachments_AudioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mAttachmentsManager.attachAudio(); attachBtn.performClick();}
        });
        attachments_ContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mAttachmentsManager.attachContact(); attachBtn.performClick();}
        });
        attachments_LocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mAttachmentsManager.attachLocation(); attachBtn.performClick();}
        });
    }

    private static void VoiceNoteControl(Conversation a, int action) {
        hideKeyboard(a);

        final RelativeLayout micContainer = (RelativeLayout) a.findViewById(Resources.id.wamod_theme_test_conversation_extras_mic_container);
        final RevealLinearLayout extras = (RevealLinearLayout) a.findViewById(Resources.id.wamod_theme_test_conversation_extras);

        micContainer.setVisibility(View.VISIBLE);

        float ht_px = extras.getHeight();
        float wt_px = extras.getWidth();

        // get the center for the clipping circle
        int cx = (int)wt_px / 2;
        int cy = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, a.getResources().getDisplayMetrics());

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

        switch (action) {
            case START_RECORDING:
                mVoiceNoteManager.StartRecording();
                animator.start();
                break;
            case STOP_RECORDING:
                mVoiceNoteManager.StopRecording();
                animator = animator.reverse();
                animator.addListener(listener);
                animator.start();
                break;
            case DISCARD_VOICE_NOTE:
                mVoiceNoteManager.DiscardRecord();
                animator = animator.reverse();
                animator.addListener(listener);
                animator.start();
                break;
        }
    }

    private static class EditTextTextListener implements TextWatcher {
        Conversation a;
        EditTextTextListener(Conversation a) {
            this.a = a;
        }
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void afterTextChanged(Editable s) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            final ImageButton sendBtn = (ImageButton) a.findViewById(Resources.id.wamod_theme_test_conversation_entry_send);
            final ImageButton micBtn = (ImageButton) a.findViewById(Resources.id.wamod_theme_test_conversation_entry_mic);

            if (s.length() == 0) {
                micBtn.setVisibility(View.VISIBLE);
                sendBtn.setVisibility(View.GONE);
            } else {
                micBtn.setVisibility(View.GONE);
                sendBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    private static void animateVoiceNote(final Conversation a) {
        final ImageView wave1 = (ImageView) a.findViewById(Resources.id.wamod_theme_test_conversation_extras_mic_wave1);
        Animation anim = new Animation() {
            float waveWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)279.67, a.getResources().getDisplayMetrics());
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

    private static class SendMessage implements View.OnClickListener {
        Conversation a;
        EditText edittext;

        SendMessage(Conversation a, EditText edittext) {
            this.a = a;
            this.edittext = edittext;
        }

        public void onClick(View v) {
            EditText originalEditText = (EditText) a.findViewById(Resources.id.entry);
            originalEditText.setText(edittext.getText());
            a.ai(a);
            edittext.setText("");
        }
    }

    private static class ToggleAttachmentsPopup implements View.OnClickListener {
        Conversation a;

        ToggleAttachmentsPopup(Conversation a) {
            this.a = a;
        }

        public void onClick(View v) {
            final ImageButton attachBtn = (ImageButton) a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_btn);

            attachPopup = (LinearLayout) a.findViewById(Resources.id.wamod_theme_test_conversation_entry_attach_layout);

            SupportAnimator animator;
            final int DURATION = ANIMATION_DURATION;

            boolean visible = (attachPopup.getVisibility() == View.VISIBLE) ? true : false;

            if (visible) hideKeyboard(a);

            attachPopup.setVisibility(View.VISIBLE);

            Point point = new Point();
            a.getWindowManager().getDefaultDisplay().getSize(point);
            int width = point.x;
            int height = attachPopup.getLayoutParams().height;

            int y = height + attachBtn.getHeight();
            int x = (int) attachBtn.getX() + (attachBtn.getWidth() / 2);

            float radius = (float) Math.hypot(width, height);

            if (visible) {
                animator = ViewAnimationUtils.createCircularReveal(attachPopup, x, y, 0, radius);
                animator = animator.reverse();
                animator.setDuration(DURATION);
                animator.addListener(new SupportAnimator.AnimatorListener() {
                    @Override
                    public void onAnimationStart() {}
                    @Override
                    public void onAnimationEnd() {
                        attachPopup.setVisibility(View.GONE);
                    }
                    @Override
                    public void onAnimationCancel() {}
                    @Override
                    public void onAnimationRepeat() {}
                });
                animator.setInterpolator(new FastOutSlowInInterpolator());
                animator.start();
            } else {
                attachPopup.setVisibility(View.VISIBLE);

                animator = ViewAnimationUtils.createCircularReveal(attachPopup, x, y, 0, radius);
                animator.setDuration(DURATION);
                animator.setInterpolator(new FastOutSlowInInterpolator());
                animator.start();
            }
        }
    }

    private static void hideKeyboard(Conversation a) {
        View view = a.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) a.getSystemService(a.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
