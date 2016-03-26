package com.wamod;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.whatsapp.*;
import com.whatsapp.HomeActivity;

/**
 * Created by brianvalente on 2/24/16.
 */
public class NavigationDrawer extends RelativeLayout {
    RelativeLayout mDragView;
    ViewDragHelper mDragHelper;
    com.whatsapp.HomeActivity activity;
    LinearLayout overlay;
    private static int BORDER_SIZE = 100;
    private static int BORDER_SIZE_OPEN;
    private static final double DRAWER_SIZE_PERCENTAGE = 0.80;
    private final double AUTO_OPEN_SPEED_LIMIT = 1200;
    private boolean init = false;
    public static boolean drawerOpen = false;
    public static boolean isMoving = false;


    public NavigationDrawer(Context context) {
        super(context);
    }

    public NavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (HomeActivity) context;
        Log.i("WAMOD", "Loading drawer");
    }

    public NavigationDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (HomeActivity) context;
        Log.i("WAMOD", "Loading drawer");
    }

    public NavigationDrawer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (HomeActivity) context;
        Log.i("WAMOD", "Loading drawer");
    }

    private void init() {
        Log.i("WAMOD", "Initiating drawer");

        mDragHelper = ViewDragHelper.create(NavigationDrawer.this, 1.0f, new DragHelperCallback());
        mDragView = (RelativeLayout) findViewById(Resources.id.wamod_drawer);
        overlay = (LinearLayout) findViewById(Resources.id.wamod_drawer_overlay);
        mDragView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int newWidth = (int) (getWidth() * DRAWER_SIZE_PERCENTAGE);
                BORDER_SIZE_OPEN = getWidth() - newWidth;
                LayoutParams params = new LayoutParams(newWidth,mDragView.getHeight());
                mDragView.setLayoutParams(params);
                mDragView.setTranslationX(-newWidth);
                /*BORDER_SIZE = (int) (newWidth * 0.05);
                mDragView.setPadding(0,0,BORDER_SIZE,0);*/
                mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
                mDragView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        LinearLayout buttons = (LinearLayout) findViewById(Resources.id.wamod_drawer_buttons);
        for (int i=0; i<buttons.getChildCount(); i++) {
            if (buttons.getChildAt(i) instanceof RelativeLayout) {
                final RelativeLayout item = (RelativeLayout) buttons.getChildAt(i);
                if (!utils.prefs.getBoolean("home_drawer_dark", true)) {
                    /*ImageView icon = (ImageView) item.getChildAt(0);
                    TextView label = (TextView)  item.getChildAt(1);

                    if (icon != null && label != null) {
                        icon.setColorFilter(Color.parseColor("#22222"));
                        label.setTextColor(Color.parseColor("#22222"));
                    }*/
                    int color = Color.parseColor("#222222");

                    View label1 = item.getChildAt(0);
                    if (label1 != null && label1 instanceof TextView) ((TextView) label1).setTextColor(color);

                    View label = item.getChildAt(1);
                    if (label != null && label instanceof TextView) ((TextView) label).setTextColor(color);

                    View icon = item.getChildAt(0);
                    if (icon != null && icon instanceof ImageView) ((ImageView) icon).setColorFilter(color);
                }
                if (item != null) item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        int id = item.getId();
                        if (id == Resources.id.wamod_drawer_newgroup) {
                            intent = new Intent(activity, NewGroup.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_wamodweb) {
                            intent = new Intent(activity, WebSessionsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_setstatus) {
                            intent = new Intent(activity, SetStatus.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_changeprofilepic) {
                            intent = new Intent(activity, ProfileInfoActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_starredmessages) {
                            intent = new Intent(activity, StarredMessagesActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_wamodweb) {
                            intent = new Intent(activity, WebSessionsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_settings) {
                            intent = new Intent(activity, com.whatsapp.Settings.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_wamodsettings) {
                            intent = new Intent(activity, WAMODSettingsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_debug1) {
                            utils.switchAccount(getContext());
                        } else if (id == Resources.id.wamod_drawer_search) {
                            activity.onSearchRequested();
                        } else if (id == Resources.id.wamod_drawer_privacy) {
                            intent = new Intent(activity, WAMODSettingsActivity_Privacy.class);
                            activity.startActivity(intent);
                        }
                        openDrawer2(false);
                    }
                });
            } else if (buttons.getChildAt(i) instanceof LinearLayout) {
                LinearLayout separator = (LinearLayout) buttons.getChildAt(i);
                if (!utils.prefs.getBoolean("home_drawer_dark", true)) separator.setBackgroundColor(Color.parseColor("#55222222"));
            }
        }

        TextView userNameTV                  = (TextView)          findViewById(Resources.id.wamod_drawer_usernametv);
        TextView userNumberTV                = (TextView)          findViewById(Resources.id.wamod_drawer_usernumbertv);
        CircularImageView wamod_drawer_photo = (CircularImageView) findViewById(Resources.id.wamod_drawer_photo);

        userNameTV.setText(utils.getUserName(activity));
        userNumberTV.setText(utils.getUserPhoneNumber(activity));
        Drawable userPic = utils.getUserPicture(activity);
        if (userPic != null) wamod_drawer_photo.setImageDrawable(userPic);

        if (utils.prefs.getBoolean("home_drawer_blackheadertext", false)) {
            userNameTV.setTextColor(Color.BLACK);
            userNumberTV.setTextColor(Color.BLACK);
        }

        wamod_drawer_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer2(false);
                Intent intent = new Intent(activity, ProfileInfoActivity.class);
                activity.startActivity(intent);
            }
        });

        if (!utils.prefs.getBoolean("home_drawer_dark", true)) mDragView.setBackgroundColor(Color.parseColor("#fefefe"));

        overlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer2(false);
            }
        });

        ImageView drawerHeaderBg = (ImageView) findViewById(Resources.id.wamod_drawer_bgview);
        drawerHeaderBg.setImageDrawable(utils.getDrawerBackground(getContext()));

        final CircularImageView wamod_drawer_header_2ndprofilepic = (CircularImageView) findViewById(Resources.id.wamod_drawer_header_2ndprofilepic);
        wamod_drawer_header_2ndprofilepic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.switchAccount(activity);
            }
        });
        final Drawable secondNumberProfilePhoto = utils.get2ndNumberUserPicture(getContext());
        if (secondNumberProfilePhoto != null) wamod_drawer_header_2ndprofilepic.setImageDrawable(secondNumberProfilePhoto);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int padding = utils.getStatusBarHeight(getContext());
            wamod_drawer_header_2ndprofilepic.setPadding(0, padding, 0, 0);

            ViewGroup userInfo = (ViewGroup) wamod_drawer_photo.getParent();
            userInfo.setPadding(userInfo.getPaddingLeft(), padding, userInfo.getPaddingRight(), userInfo.getPaddingBottom());

            final RelativeLayout drawerHeader = (RelativeLayout) findViewById(Resources.id.wamod_drawer_header);
            drawerHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LayoutParams params = new LayoutParams(NavigationDrawer.this.getWidth(), drawerHeader.getHeight() + padding);
                    drawerHeader.setLayoutParams(params);

                    LinearLayout mainLayout = (LinearLayout) NavigationDrawer.this.getChildAt(0);
                    mainLayout.setPadding(0, padding, 0, 0);

                    NavigationDrawer.this.setBackgroundColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));

                    LayoutParams params1 = (RelativeLayout.LayoutParams) wamod_drawer_header_2ndprofilepic.getLayoutParams();
                    params1.setMargins(params1.leftMargin, params1.topMargin + padding, params1.rightMargin, params1.bottomMargin);
                    wamod_drawer_header_2ndprofilepic.setLayoutParams(params1);

                    final LinearLayout statusbar = (LinearLayout) findViewById(Resources.id.wamod_drawer_statusbar);
                    statusbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            statusbar.setLayoutParams(new LayoutParams(NavigationDrawer.this.getWidth(), utils.getStatusBarHeight(getContext())));
                            statusbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });

                    drawerHeader.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!init) {
            init();
            init = true;
        }
        Log.i("WAMOD", "Drawer finished inflating");
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        /*if (isDrawerTarget(event) && mDragHelper.shouldInterceptTouchEvent(event))
            return true;
        else return false;*/
        return mDragHelper.shouldInterceptTouchEvent(event);
    }

    private boolean isDrawerTarget(MotionEvent event) {
        int[] location = new int[2];
        mDragView.getLocationOnScreen(location);
        int upperLimit = location[0] + mDragView.getMeasuredWidth() + (int)(mDragView.getMeasuredWidth() * 0.15);
        int lowerLimit = location[0];
        int x = (int) event.getRawX();
        return (x > lowerLimit && x < upperLimit);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

        if (isMoving && finishedMoving()) isMoving = false;

        if (drawerOpen && drawerIsFullyClosed() && !isMoving) {
            Log.i("WAMOD", "Drawer must be opened NOW without animations");
            mDragView.offsetLeftAndRight(mDragView.getWidth());
        }
    }

    private boolean drawerIsFullyClosed() {
        int[] location = new int[2];
        mDragView.getLocationOnScreen(location);
        Log.i("WAMOD", "X: " + location[0] + " Width: " + -mDragView.getWidth());
        if (location[0] < -mDragView.getWidth() + 5) return true;
        else return false;
    }

    private boolean finishedMoving() {
        int[] location = new int[2];
        mDragView.getLocationOnScreen(location);
        if (location[0] == 0 || location[0] < (-mDragView.getWidth() - 5)) return true;
        else return false;
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Log.d("DragLayout", "clampViewPositionHorizontal " + left + "," + dx);

            final int leftBound = 0;
            final int rightBound = mDragView.getWidth();

            final int newLeft = Math.min(Math.max(left, leftBound), rightBound);

            // Get percentage
            //updateOverlayOpacity(left);

            return newLeft;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            //return super.getViewHorizontalDragRange(child);
            int range = (int) (child.getMeasuredWidth() + mDragView.getX());
            return range;
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return child.getMeasuredHeight();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (xvel > AUTO_OPEN_SPEED_LIMIT) { // speed has priority over position
                openDrawer(true);
            } else if (xvel < (-AUTO_OPEN_SPEED_LIMIT)) {
                openDrawer(false);
            } else if (mDragView.getX() > -(mDragView.getWidth() / 2)) {
                openDrawer(true);
            } else {
                openDrawer(false);
            }
            invalidate();
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragHelper.captureChildView(mDragView, pointerId);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

            // Get percentage
            //updateOverlayOpacity(left);
        }


    }

    private void openDrawer(boolean open) {
        if (open) {
            mDragHelper.settleCapturedViewAt(mDragView.getWidth(),0);
            drawerOpen = true;
            overlay.setVisibility(VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mDragView.setElevation(8);
        }
        else {
            mDragHelper.settleCapturedViewAt(0,0);
            drawerOpen = false;
            overlay.setVisibility(GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mDragView.setElevation(0);
        }
        isMoving = true;
    }

    public void openDrawer2(boolean open) {
        Log.i("WAMOD", "Opening drawer");
        if (open) {
            mDragHelper.smoothSlideViewTo(mDragView, mDragView.getWidth(), 0);
            overlay.setVisibility(VISIBLE);
            drawerOpen = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mDragView.setElevation(8);
        } else {
            mDragHelper.smoothSlideViewTo(mDragView, 0, 0);
            overlay.setVisibility(GONE);
            drawerOpen = false;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) mDragView.setElevation(0);
        }
        isMoving = true;
        invalidate();
    }

    /*private void updateOverlayOpacity(int left) {
        int percentage = (left * 100) / mDragView.getWidth();
        int alpha = ((percentage * 255) / 100) - 75;
        if (alpha < 0) alpha = 0;
        if (!drawerOpen && !isMoving) overlay.setVisibility(GONE);
        else overlay.setVisibility(VISIBLE);

        if (!drawerOpen) overlay.setClickable(false);
        else overlay.setClickable(true);

        overlay.getBackground().setAlpha(alpha);
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //updateOverlayOpacity(drawerOpen ? mDragView.getWidth() : 0);
    }
}