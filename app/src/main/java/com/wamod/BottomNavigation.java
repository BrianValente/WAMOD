package com.wamod;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.whatsapp.HomeActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by BrianValente on 3/24/16.
 */
public class BottomNavigation extends LinearLayout {
    RelativeLayout callsBtn;
    RelativeLayout chatsBtn;
    RelativeLayout contactsBtn;
    HomeActivity.TabsPager pager;
    Context ctx;
    float originalLabelTextSize = 0;
    float originalIconTopMargin = 0;
    int inactiveColor;
    int activeColor;
    int backgroundColor;
    int activeItem;
    boolean firstAnim = true;
    float labelMultiplier = utils.Nexus6PResToActualDevice(this.getContext(), 9, 9)[0];
    float iconMultiplier = utils.Nexus6PResToActualDevice(this.getContext(), 5, 5)[0];

    public BottomNavigation(Context context) {
        super(context);
        this.ctx = context;
        init();
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                View tabs = ((HomeActivity) ctx).findViewById(Resources.id.tabs);
                View pager_holder = ((HomeActivity) ctx).findViewById(Resources.id.pager_holder);
                ViewGroup content = (ViewGroup) ((HomeActivity) ctx).findViewById(Resources.id.content);
                pager = (HomeActivity.TabsPager) ((HomeActivity) ctx).findViewById(Resources.id.pager);

                callsBtn    = (RelativeLayout) findViewById(Resources.id.wamod_bottomnav_calls);
                chatsBtn    = (RelativeLayout) findViewById(Resources.id.wamod_bottomnav_chats);
                contactsBtn = (RelativeLayout) findViewById(Resources.id.wamod_bottomnav_contacts);

                loadColors();
                setInactive();

                tabs.setVisibility(View.GONE);
                pager_holder.setPadding(0,0,0,0);
                /*RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(content.getLayoutParams());
                params.setMargins(0,0,0,utils.Nexus6PResToActualDevice(getContext(),224,224)[0]);
                content.setLayoutParams(params);*/
                content.setPadding(0,0,0,getHeight());

                pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        switch (position) {
                            case 0:
                                setActive(callsBtn, 0);
                                break;
                            case 1:
                                setActive(chatsBtn, 1);
                                break;
                            case 2:
                                setActive(contactsBtn, 2);
                                break;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                setActive(chatsBtn, 1);

                /*View label = callsBtn.getChildAt(1);
                if (label != null && label instanceof TextView) originalLabelTextSize = ((TextView) label).getTextSize();*/
                View icon = callsBtn.getChildAt(0);
                if (icon != null && icon instanceof ImageView) originalIconTopMargin = ((ImageView) icon).getPaddingTop();

                callsBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setActive(callsBtn, 0);
                        pager.setCurrentItem(0);
                    }
                });

                chatsBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setActive(chatsBtn, 1);
                        pager.setCurrentItem(1);
                    }
                });

                contactsBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setActive(contactsBtn, 2);
                        pager.setCurrentItem(2);
                    }
                });

                Log.i("WAMOD", "Text size: " + originalLabelTextSize + "Icon margin: " + originalIconTopMargin);

                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void setActive(RelativeLayout button, int n) {
        if (activeItem == n) return;
        activeItem = n;

        setInactive();

        final View label = button.getChildAt(1);
        final View icon  = button.getChildAt(0);

        if (label != null && label instanceof TextView) {
            TextView lb = ((TextView) label);
            lb.setTextColor(activeColor);
        }
        if (icon != null && icon instanceof ImageView) {
            ImageView icn = ((ImageView) icon);
            icn.setColorFilter(activeColor);
        }

        Animation anim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 0 && originalLabelTextSize == 0)
                    originalLabelTextSize = ((TextView) label).getTextSize();
                if (interpolatedTime == 0)
                    ((TextView) label).setTextSize(TypedValue.COMPLEX_UNIT_PX, originalLabelTextSize);

                float newTextSize = (labelMultiplier * interpolatedTime) + originalLabelTextSize;
                ((TextView) label).setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);


                if (interpolatedTime == 0 && originalIconTopMargin == 0)
                    originalIconTopMargin = icon.getPaddingTop();
                int newPadding = (int) (originalIconTopMargin - (iconMultiplier * interpolatedTime));
                icon.setPadding(0, newPadding, 0, 0);
            }
        };
        anim.setDuration(100);
        if (firstAnim) {
            anim.setDuration(0);
            firstAnim = false;
        }
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        button.startAnimation(anim);
    }

    private void setInactive() {
        ArrayList<RelativeLayout> buttons = new ArrayList<>();
        buttons.add(callsBtn);
        buttons.add(chatsBtn);
        buttons.add(contactsBtn);

        for (int i = 0; i<buttons.size(); i++) {
            if (!(i == activeItem)) {
                RelativeLayout btn = buttons.get(i);

                btn.setAnimation(null);

                final View label = btn.getChildAt(1);
                final View icon = btn.getChildAt(0);

                if (label != null && label instanceof TextView) {
                    TextView lb = ((TextView) label);
                    lb.setTextColor(inactiveColor);
                }
                if (icon != null && icon instanceof ImageView) {
                    ImageView icn = ((ImageView) icon);
                    icn.setColorFilter(inactiveColor);
                }

                if (((TextView) label).getTextSize() != originalLabelTextSize) {
                    Animation anim = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            float interpolatorTime = (-interpolatedTime) + 1;
                            float newTextSize = (labelMultiplier * interpolatorTime) + originalLabelTextSize;
                            ((TextView) label).setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);



                            int newPadding = (int) (originalIconTopMargin - (iconMultiplier * interpolatorTime));
                            icon.setPadding(0,newPadding,0,0);
                        }
                    };
                    anim.setDuration(100);
                    anim.setInterpolator(new AccelerateDecelerateInterpolator());
                    btn.startAnimation(anim);
                }
            }
        }
    }

    private void loadColors() {
        if (utils.prefs.getBoolean("home_bottomnavigationbar_autocolor", true)) {
            if (utils.prefs.getBoolean("general_darkmode", true)) {
                backgroundColor = utils.getDarkColor(2);
                inactiveColor = utils.getDarkColor(1);
            } else {
                backgroundColor = Color.WHITE;
                inactiveColor = Color.parseColor("#555555");
            }
            activeColor = utils.getUIColor(utils.COLOR_TOOLBAR);
        } else {
            backgroundColor = Color.parseColor("#" + utils.prefs.getString("home_bottomnavigationbar_colors_bg", "FFFFFF"));
            activeColor = Color.parseColor("#" + utils.prefs.getString("home_bottomnavigationbar_colors_activeitem", "000000"));
            inactiveColor = Color.parseColor("#" + utils.prefs.getString("home_bottomnavigationbar_colors_inactiveitem", "555555"));
        }
        setBackgroundColor(backgroundColor);
    }
}
