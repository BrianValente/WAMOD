package com.wamod.activity.home;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.Resources;
import com.wamod.Utils;

/**
 * Created by brianvalente on 10/3/16.
 */
public class HomeActivity extends AppCompatActivity {
    private Toolbar               mToolbar;
    private View                  mStatusBarColorView;
    private ActionBar             mActionBar;
    private NavigationView        mNavigationView;
    private DrawerLayout          mDrawerLayout;
    private RelativeLayout        mContent;
    private boolean               mIsLollipop;
    private CallsFragment         mCallsFragment;
    private ConversationsFragment mConversationsFragment;
    private ContactsFragment      mContactsFragment;

    public static final int FRAGMENT_CALLS         = 0;
    public static final int FRAGMENT_CONVERSATIONS = 1;
    public static final int FRAGMENT_CONTACTS      = 2;

    public static final int ANIMATION_DURATION = (int) (Utils.getSystemShortAnimationDuration() * 0.75);

    private static int FRAGMENT_ACTIVE = 1;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //setTheme(Resources.getStyle("WAMOD.Theme.Home"));
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout("wamod_activity_home"));


        // Initialize fields
        mContent = (RelativeLayout) findViewById(Resources.getID("content"));
        mToolbar = (Toolbar) findViewById(Resources.getID("toolbar"));
        mStatusBarColorView = findViewById(Resources.getID("status_bar_color"));
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mNavigationView = (NavigationView) findViewById(Resources.id.wamod_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(Resources.id.wamod_drawer_parent);
        mIsLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

        mCallsFragment         = (CallsFragment)         getFragmentManager().findFragmentById(Resources.getID("fragment_calls"));
        mConversationsFragment = (ConversationsFragment) getFragmentManager().findFragmentById(Resources.getID("fragment_conversations"));
        mContactsFragment      = (ContactsFragment)      getFragmentManager().findFragmentById(Resources.getID("fragment_contacts"));

        // Stop Android for drawing a translucent background on bars
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        // Load toolbar and status bar colors
        mStatusBarColorView.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_STATUSBAR));
        mStatusBarColorView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, Utils.getStatusBarHeight(this)));
        mToolbar.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR));

        // Set toolbar up button (Navigation bar toggle)
        Drawable upIndicator = getResources().getDrawable(Resources.drawable.wamod_ic_menu);
        upIndicator.setColorFilter(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_ICONS), PorterDuff.Mode.MULTIPLY);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(upIndicator);

        // Set toolbar title
        mActionBar.setTitle("WhatsApp");

        // Set navigation bar color
        if (mIsLollipop)
            getWindow().setNavigationBarColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_NAVBAR));

        // Set activity background color
        mContent.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_BACKGROUND));

        // Set fragments initial visibility
        mCallsFragment.getView().setAlpha(FRAGMENT_ACTIVE == FRAGMENT_CALLS? 1 : 0);
        mConversationsFragment.getView().setAlpha(FRAGMENT_ACTIVE == FRAGMENT_CONVERSATIONS? 1 : 0);
        mContactsFragment.getView().setAlpha(FRAGMENT_ACTIVE == FRAGMENT_CONTACTS? 1 : 0);

        switch (FRAGMENT_ACTIVE) {
            case FRAGMENT_CALLS:
                mCallsFragment.getView().bringToFront();
                break;
            case FRAGMENT_CONVERSATIONS:
                mConversationsFragment.getView().bringToFront();
                break;
            case FRAGMENT_CONTACTS:
                mContactsFragment.getView().bringToFront();
                break;
                
        }

        // Avoid showing a space for the status bar when it's not present
        updateBarsVisibility();
    }

    public void setActiveFragment(int fragment) {
        if (FRAGMENT_ACTIVE == fragment) return;
        final View oldFragment = getFragment(FRAGMENT_ACTIVE).getView();
        final View newFragment = getFragment(fragment).getView();

        newFragment.bringToFront();

        final int dpToPixel_100 = Utils.convertDpToPixel(1, HomeActivity.this);

        Animation oldAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);

                float interpolatedTimeInverted = (interpolatedTime * -1) + 1;

                int top = (int)((interpolatedTime * 40) * dpToPixel_100);
                oldFragment.setTop(top);
                oldFragment.setAlpha(interpolatedTimeInverted);
            }
        };

        Animation newAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);

                float interpolatedTimeInverted = (interpolatedTime * -1) + 1;

                int top = (int)((interpolatedTimeInverted * 40) * dpToPixel_100);
                newFragment.setTop(top);
                newFragment.setAlpha(interpolatedTime);
            }
        };

        oldAnim.setDuration(ANIMATION_DURATION);
        newAnim.setDuration(ANIMATION_DURATION);

        oldAnim.setInterpolator(new DecelerateInterpolator());
        newAnim.setInterpolator(new DecelerateInterpolator());

        oldFragment.startAnimation(oldAnim);
        newFragment.startAnimation(newAnim);

        FRAGMENT_ACTIVE = fragment;
    }

    private android.app.Fragment getFragment(int fragment) {
        switch (fragment) {
            case FRAGMENT_CALLS:
                return mCallsFragment;
            case FRAGMENT_CONVERSATIONS:
                return mConversationsFragment;
            case FRAGMENT_CONTACTS:
                return mContactsFragment;
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showNavigationDrawer();
                return true;
            /*case 0:
                a.onSearchRequested();
                return true;*/
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBarsVisibility();
    }

    private void updateBarsVisibility() {
        mStatusBarColorView.setVisibility(View.VISIBLE);
        if (Utils.isFullscreen(mStatusBarColorView))
            mStatusBarColorView.setVisibility(View.VISIBLE);
        else
            mStatusBarColorView.setVisibility(View.GONE);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        updateBarsVisibility();
    }

    private void showNavigationDrawer() {
        mDrawerLayout.openDrawer(mNavigationView);
    }
}
