package com.wamod.setup;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wamod.R;
import com.wamod.Resources;
import com.wamod.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;

/**
 * Created by brianvalente on 6/16/16.
 */
public class PageLayout extends RelativeLayout {
    final private long ANIMATION_DURATION = 500;
    private SetupActivity setupActivity;
    private String actualTheme = "";
    private RelativeLayout wamod_setupwizard_header;
    private ViewGroup wamod_setupwizard_page_controls;
    private View wamod_setupwizard_page_content;
    private ViewGroup wamod_setupwizard_pages;
    private ViewGroup wamod_setupwizard_pages_background;
    private RevealLinearLayout wamod_setupwizard_header_reveal;
    private TextView wamod_setupwizard_header_title;
    private Button wamod_setupwizard_page_next;
    private Button wamod_setupwizard_page_back;
    private boolean changingTheme = false;

    public PageLayout(Context context) {
        super(context);
    }

    public PageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PageLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode()) return;
        setupActivity = (SetupActivity) context;

        final String header_title = Utils.getAttribute_String(attrs, "header_title");

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                wamod_setupwizard_header = (RelativeLayout) LayoutInflater.from(context).inflate(Resources.getLayout("wamod_setup_page_header"), PageLayout.this);
                wamod_setupwizard_page_controls = (ViewGroup) LayoutInflater.from(context).inflate(Resources.getLayout("wamod_setup_page_footer"), PageLayout.this);
                wamod_setupwizard_page_content = findViewById(Resources.getID("wamod_setupwizard_page_content"));
                wamod_setupwizard_header_reveal = (RevealLinearLayout) findViewById(Resources.getID("wamod_setupwizard_header_reveal"));
                wamod_setupwizard_header_title = (TextView) findViewById(Resources.getID("wamod_setupwizard_header_title"));
                wamod_setupwizard_pages = (ViewGroup) setupActivity.findViewById(Resources.getID("wamod_setupwizard_pages"));
                wamod_setupwizard_pages_background = (ViewGroup) setupActivity.findViewById(Resources.getID("wamod_setupwizard_pages_background"));
                wamod_setupwizard_page_next = (Button) findViewById(Resources.getID("wamod_setupwizard_page_next"));
                wamod_setupwizard_page_back = (Button) findViewById(Resources.getID("wamod_setupwizard_page_back"));
                wamod_setupwizard_header = (RelativeLayout) wamod_setupwizard_header_reveal.getParent(); // Fixes an strange behavior

                wamod_setupwizard_page_next.setOnClickListener(new Next());
                wamod_setupwizard_page_back.setOnClickListener(new Back());

                ArrayList<PageLayout> pages = getPages();
                int pageIndex = getPageIndex(pages);
                if (pageIndex == 0) wamod_setupwizard_page_back.setEnabled(false);
                if (pages.size() == pageIndex + 1) {
                    wamod_setupwizard_page_next.setText("Finish");
                    wamod_setupwizard_page_next.setOnClickListener(new Finish());
                }

                wamod_setupwizard_header_title.setText(header_title);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void changeTheme(final JSONObject theme) {
        try {
            final int headerBackgroundColor  = Color.parseColor("#" + theme.getString("general_toolbarcolor"));
            final int headerTitleColor       = Color.parseColor("#" + theme.getString("general_toolbartextcolor"));

            wamod_setupwizard_header.setBackgroundColor(headerBackgroundColor);
            wamod_setupwizard_header_title.setTextColor(headerTitleColor);
        } catch (JSONException e) {
            Utils.manageException(e);
        }
    }

    public void changeTheme(final MotionEvent ev, final JSONObject theme) {
        try {
            if (changingTheme) return;

            String themeID = theme.getString("id");

            if (actualTheme.contentEquals(themeID)) return;
            else actualTheme = themeID;

            float x = ev.getRawX();
            float y = ev.getRawY();
            final View headerRipple = new View(setupActivity);
            final View backgroundRipple = new View(setupActivity);
            final int headerBackgroundColor  = Color.parseColor("#" + theme.getString("general_toolbarcolor"));
            final int headerTitleColor       = Color.parseColor("#" + theme.getString("general_toolbartextcolor"));
            final int contentBackgroundColor = theme.getString("nightmode_enable").contentEquals("1")? Color.parseColor("#" + theme.getString("nightmode_backgroundcolor")) : Color.WHITE;
            float headerRippleSpeed = ANIMATION_DURATION;
            float backgroundRippleSpeed = ANIMATION_DURATION;

            RevealLinearLayout.LayoutParams params = new RevealLinearLayout.LayoutParams(wamod_setupwizard_pages_background.getWidth(), wamod_setupwizard_pages_background.getHeight());

            wamod_setupwizard_header_reveal.addView(headerRipple, 0);
            headerRipple.setLayoutParams(params);
            headerRipple.setBackgroundColor(headerBackgroundColor);
            circularReveal(headerRipple, x, y, headerRippleSpeed, new SupportAnimator.AnimatorListener() {
                @Override public void onAnimationStart() {}
                @Override public void onAnimationEnd() {
                    wamod_setupwizard_header.setBackgroundColor(headerBackgroundColor);
                    wamod_setupwizard_header_title.setTextColor(headerTitleColor);
                    fadeRipple(headerRipple);
                }
                @Override public void onAnimationCancel() {}
                @Override public void onAnimationRepeat() {}
            });




            wamod_setupwizard_pages_background.addView(backgroundRipple, 0);
            backgroundRipple.setLayoutParams(params);
            backgroundRipple.setBackgroundColor(contentBackgroundColor);
            circularReveal(backgroundRipple, x, y, backgroundRippleSpeed, new SupportAnimator.AnimatorListener() {
                @Override public void onAnimationStart() {}
                @Override public void onAnimationEnd() {
                    wamod_setupwizard_pages_background.setBackgroundColor(contentBackgroundColor);
                    fadeRipple(backgroundRipple);
                }
                @Override public void onAnimationCancel() {}
                @Override public void onAnimationRepeat() {}
            });

            for (PageLayout page : getPages()) {
                if (page.getId() != getId()) page.changeTheme(theme);
            }

            changingTheme = true;
        } catch (JSONException e) {
            Utils.manageException(e);
        }

    }

    private void fadeRipple(final View v) {
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
        anim.setDuration(ANIMATION_DURATION / 2);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {
                ((ViewGroup) v.getParent()).removeView(v);
                changingTheme = false;
            }
            @Override public void onAnimationRepeat(Animation animation) {}
        });
        v.startAnimation(anim);
    }

    private void circularReveal(final View v, final float x, final float y, final float speed, final SupportAnimator.AnimatorListener animatorListener) {
        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float dx = Math.max(x, v.getWidth() - x);
                float dy = Math.max(y, v.getHeight() - y);
                float finalRadius = (float) Math.hypot(dx, dy);

                SupportAnimator animator = ViewAnimationUtils.createCircularReveal(v, (int) x, (int) y, 0, finalRadius);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.setDuration((long)speed);
                animator.addListener(animatorListener);
                animator.start();

                v.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private class Finish implements OnClickListener {
        @Override
        public void onClick(View v) {
            setupActivity.finish();
        }
    }

    private class Next implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (setupActivity.switchingPages) return;

            final ArrayList<PageLayout> pages = getPages();
            for (PageLayout page : pages) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) page.getLayoutParams();
                params.width = getWidth();
                page.setLayoutParams(params);
            }

            Animation animation = new Animation() {
                float marginLeft = 0;
                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    //super.applyTransformation(interpolatedTime, t);
                    if (interpolatedTime == 0)
                        setupActivity.switchingPages = true;
                    else if (interpolatedTime == 1)
                        setupActivity.switchingPages = false;

                    PageLayout page = pages.get(0);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) page.getLayoutParams();
                    if (interpolatedTime == 0) marginLeft = params.leftMargin;

                    params.leftMargin = (int) (marginLeft - (getWidth() * interpolatedTime));
                    page.setLayoutParams(params);
                }
            };
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(ANIMATION_DURATION);
            startAnimation(animation);
        }
    }

    private class Back implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (setupActivity.switchingPages) return;

            final ArrayList<PageLayout> pages = getPages();
            /*for (PageLayout page : pages) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getWidth(), getHeight());
                page.setLayoutParams(params);
            }*/

            Animation animation = new Animation() {
                float marginLeft = 0;

                @Override
                protected void applyTransformation(float interpolatedTime, Transformation t) {
                    if (interpolatedTime == 0)
                        setupActivity.switchingPages = true;
                    else if (interpolatedTime == 1)
                        setupActivity.switchingPages = false;

                    PageLayout page = pages.get(0);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) page.getLayoutParams();
                    if (interpolatedTime == 0) marginLeft = params.leftMargin;

                    params.leftMargin = (int) (marginLeft + (getWidth() * interpolatedTime));
                    page.setLayoutParams(params);
                }
            };
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(ANIMATION_DURATION);
            startAnimation(animation);
        }
    }

    private PageLayout getPreviousPage() {
        ArrayList<PageLayout> pages = getPages();
        return pages.get(getPageIndex(pages) - 1);
    }

    private ArrayList<PageLayout> getPages() {
        ArrayList<PageLayout> pages = new ArrayList<>();
        for (int i=0; i < wamod_setupwizard_pages.getChildCount(); i++) {
            View page = wamod_setupwizard_pages.getChildAt(i);
            if (page instanceof PageLayout) pages.add((PageLayout)page);
        }
        return pages;
    }

    private int getPageIndex(ArrayList<PageLayout> pages) {
        int i = 0;
        for (PageLayout page : pages) {
            if (page.getId() == getId()) return i;
            i++;
        }
        return 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}