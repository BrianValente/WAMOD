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
    final long ANIMATION_DURATION = 425;
    View wamod_setupwizard_overlay;
    View wamod_setupwizard_start_container;
    ViewGroup wamod_setupwizard_pages;
    boolean switchingPages = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout("wamod_setup_main_activity"));

        final FloatingActionButton wamod_setupwizard_start = (FloatingActionButton) findViewById(Resources.getID("wamod_setupwizard_start"));
        wamod_setupwizard_overlay = findViewById(Resources.getID("wamod_setupwizard_overlay"));
        wamod_setupwizard_start_container = findViewById(Resources.getID("wamod_setupwizard_start_container"));
        wamod_setupwizard_pages = (ViewGroup) findViewById(Resources.getID("wamod_setupwizard_pages"));

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
        PageLayout colorPalettePage = (PageLayout) findViewById(Resources.getID("wamod_setupwizard_pages_colorpalette"));
        colorPalettePage.changeTheme(ev, theme);
    }


}