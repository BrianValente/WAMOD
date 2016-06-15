package com.wamod.setup;

import android.animation.Animator;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wamod.Resources;
import com.wamod.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;

/**
 * Created by brianvalente on 6/12/16.
 */
public class SetupActivity extends AppCompatActivity {
    final long ANIMATION_DURATION = 350;
    View wamod_setupwizard_overlay;
    View wamod_setupwizard_start_container;
    View wamod_setupwizard_pages_container;
    ViewGroup wamod_setupwizard_pages;
    ViewGroup wamod_setupwizard_pages_background;

    String actualTheme = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout("wamod_setup_main_activity"));

        final FloatingActionButton wamod_setupwizard_start = (FloatingActionButton) findViewById(Resources.getID("wamod_setupwizard_start"));
        wamod_setupwizard_overlay = findViewById(Resources.getID("wamod_setupwizard_overlay"));
        wamod_setupwizard_start_container = findViewById(Resources.getID("wamod_setupwizard_start_container"));
        wamod_setupwizard_pages_container = findViewById(Resources.getID("wamod_setupwizard_pages_container"));
        wamod_setupwizard_pages = (ViewGroup) findViewById(Resources.getID("wamod_setupwizard_pages"));
        wamod_setupwizard_pages_background = (ViewGroup) findViewById(Resources.getID("wamod_setupwizard_pages_background"));

        wamod_setupwizard_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wamod_setupwizard_overlay.setVisibility(View.VISIBLE);
                wamod_setupwizard_overlay.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // get the center for the clipping circle
                        /*int cx = (wamod_setupwizard_overlay.getLeft() + wamod_setupwizard_overlay.getRight()) / 2;
                        int cy = (wamod_setupwizard_overlay.getTop() + wamod_setupwizard_overlay.getBottom()) / 2;*/
                        float cx = wamod_setupwizard_start.getX() + wamod_setupwizard_start_container.getX() + (wamod_setupwizard_start.getWidth() / 2);
                        float cy = wamod_setupwizard_start.getY() + wamod_setupwizard_start_container.getY() + (wamod_setupwizard_start.getHeight() / 2);

                        // get the final radius for the clipping circle
                        float dx = Math.max(cx, wamod_setupwizard_overlay.getWidth() - cx);
                        float dy = Math.max(cy, wamod_setupwizard_overlay.getHeight() - cy);
                        float finalRadius = (float) Math.hypot(dx, dy);

                        // Android native animator
                        Animator animator =
                                ViewAnimationUtils.createCircularReveal(wamod_setupwizard_overlay, (int)cx, (int)cy, 0, finalRadius);
                        animator.setInterpolator(new AccelerateDecelerateInterpolator());
                        animator.setDuration(ANIMATION_DURATION);
                        animator.start();

                        wamod_setupwizard_overlay.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
        });

        wamod_setupwizard_pages.setPadding(0,0,0, Utils.getNavigationBarHeight());
        wamod_setupwizard_overlay.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Leave setup wizard");
        dialog.setMessage("Are you sure you want to leave the setup wizard?");
        dialog.setPositiveButton(getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton(getResources().getString(android.R.string.no), null);
        dialog.show();
    }

    public void changeTheme(final MotionEvent ev, final JSONObject theme) {

        try {
            String themeID = theme.getString("id");
            if (actualTheme.contentEquals(themeID)) return;
            else actualTheme = themeID;

            RelativeLayout wamod_setupwizard_header = (RelativeLayout) findViewById(Resources.getID("wamod_setupwizard_header"));
            RevealLinearLayout wamod_setupwizard_header_reveal = (RevealLinearLayout) findViewById(Resources.getID("wamod_setupwizard_header_reveal"));
            final TextView wamod_setupwizard_header_title = (TextView) wamod_setupwizard_header.findViewById(Resources.getID("wamod_setupwizard_header_title"));
            final View ripple = new View(this);
            int headerBgColor = Color.parseColor("#" + theme.getString("general_toolbarcolor"));
            final int headerTitleColor = Color.parseColor("#" + theme.getString("general_toolbartextcolor"));

            wamod_setupwizard_header_reveal.addView(ripple);
            RevealLinearLayout.LayoutParams params = new RevealLinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ripple.setLayoutParams(params);
            ripple.setBackgroundColor(headerBgColor);
            ripple.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    circularAnimation(ev.getRawX(), ev.getRawY(), ripple, wamod_setupwizard_header_title, headerTitleColor);
                    ripple.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });


            final View ripple2 = new View(this);
            wamod_setupwizard_pages_background.addView(ripple2);
            ripple2.setLayoutParams(params);
            ripple2.setBackgroundColor(theme.getString("nightmode_enable").contentEquals("1")? Color.parseColor("#" + theme.getString("nightmode_backgroundcolor")) : Color.WHITE);
            ripple2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    float dx = Math.max(ev.getRawX(), ripple2.getWidth() - ev.getRawX());
                    float dy = Math.max(ev.getRawY(), ripple2.getHeight() - ev.getRawY());
                    float finalRadius = (float) Math.hypot(dx, dy);

                    // Android native animator
                    SupportAnimator animator = ViewAnimationUtils.createCircularReveal(ripple2, (int) ev.getRawX(), (int) ev.getRawY(), 0, finalRadius);
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(ANIMATION_DURATION);
                    animator.addListener(new SupportAnimator.AnimatorListener() {
                        @Override public void onAnimationStart() {}
                        @Override public void onAnimationEnd() {
                            Log.i("WAMOD", "Hello");
                            wamod_setupwizard_pages_background.setBackground(ripple2.getBackground());

                            AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
                            anim.setDuration(ANIMATION_DURATION);
                            anim.setRepeatMode(Animation.REVERSE);
                            anim.setAnimationListener(new Animation.AnimationListener() {
                                @Override public void onAnimationStart(Animation animation) {}
                                @Override
                                public void onAnimationEnd(Animation animation) {((ViewGroup) ripple2.getParent()).removeView(ripple2);}
                                @Override public void onAnimationRepeat(Animation animation) {}
                            });
                            ripple2.startAnimation(anim);
                        }
                        @Override public void onAnimationCancel() {}
                        @Override public void onAnimationRepeat() {}
                    });
                    animator.start();

                    ripple2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

        } catch (JSONException e) {
            Utils.manageException(e);
        }

        //Toast.makeText(this, "X: " + ev.getRawX() + "Y: " + ev.getRawY(), Toast.LENGTH_LONG).show();
        /**/
    }

    public void circularAnimation(float x, float y, final View v, final TextView title, final int titleColor) {
        // get the final radius for the clipping circle
        float dx = Math.max(x, v.getWidth() - x);
        float dy = Math.max(y, v.getHeight() - y);
        float finalRadius = (float) Math.hypot(dx, dy);

        // Android native animator
        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(v, (int)x, (int)y, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(ANIMATION_DURATION);
        animator.addListener(new SupportAnimator.AnimatorListener() {
             @Override
             public void onAnimationStart() {

             }

             @Override
             public void onAnimationEnd() {
                 RelativeLayout wamod_setupwizard_header = (RelativeLayout) findViewById(Resources.getID("wamod_setupwizard_header"));
                 wamod_setupwizard_header.setBackground(v.getBackground());
                 title.setTextColor(titleColor);

                 AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
                 anim.setDuration(ANIMATION_DURATION);
                 anim.setRepeatMode(Animation.REVERSE);
                 anim.setAnimationListener(new Animation.AnimationListener() {
                     @Override
                     public void onAnimationStart(Animation animation) {

                     }

                     @Override
                     public void onAnimationEnd(Animation animation) {
                         ((ViewGroup) v.getParent()).removeView(v);
                     }

                     @Override
                     public void onAnimationRepeat(Animation animation) {

                     }
                 });
                 v.startAnimation(anim);
             }

             @Override
             public void onAnimationCancel() {

             }

             @Override
             public void onAnimationRepeat() {

             }
         });
        animator.start();
    }
}
