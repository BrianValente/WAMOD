package wamod.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.whatsapp.Activity;

import wamod.fragment.CallsFragment;
import wamod.fragment.ContactsFragment;
import wamod.fragment.ConversationsFragment;
import wamod.fragment.HomePageFragment;
import wamod.fragment.SettingsFragment;
import wamod.utils.Resources;
import wamod.utils.Theme;
import wamod.widget.BottomNavigationView;
import wamod.widget.NavigationDrawer;

/**
 * Created by brianvalente on 11/24/17.
 */

public class HomeActivity extends Activity {

    public ConversationsFragment mConversationsFragment;
    public CallsFragment         mCallsFragment;
    public ContactsFragment      mContactsFragment;
    public SettingsFragment      mSettingsFragment;

    public HomePageFragment mCurrentFragment;

    public RelativeLayout mHomeContent;

    public NavigationDrawer mNavigationDrawer;
    public FragmentManager mFragmentManager;

    private int mDefaultSystemUIVisibility;

    private BottomNavigationView mBottomNavigationView;

    public enum Fragment {
        HOME,
        CALLS,
        CONTACTS,
        SETTINGS
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            finish();
            startActivity(getIntent());
            // TODO: Implement savedInstanceState.
            return;
        }

        setContentView(getResources().getIdentifier("wamod_home_activity", "layout", getPackageName()));

        // Transparent status bar
        Window window = getWindow();
        mDefaultSystemUIVisibility = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(mDefaultSystemUIVisibility);

        mConversationsFragment = new ConversationsFragment();
        mCallsFragment         = new CallsFragment();
        mContactsFragment      = new ContactsFragment();
        mSettingsFragment      = new SettingsFragment();

        mFragmentManager  = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(Resources.getId(this, "home_fragment"), mConversationsFragment);
        fragmentTransaction.add(Resources.getId(this, "home_fragment"), mCallsFragment);
        fragmentTransaction.add(Resources.getId(this, "home_fragment"), mContactsFragment);
        fragmentTransaction.add(Resources.getId(this, "home_fragment"), mSettingsFragment);

        //fragmentTransaction.show(mConversationsFragment);

        mCurrentFragment = mConversationsFragment;

        mNavigationDrawer = findViewById(Resources.getId(this, "drawer"));
        mHomeContent      = findViewById(Resources.getId(this, "home_content"));
        mBottomNavigationView = findViewById(Resources.getId(this, "bottom_navigation_view"));

        mHomeContent.setBackgroundColor(Theme.getColor(Theme.Key.COLOR_BACKGROUND));

        fragmentTransaction.replace(Resources.getId(this, "home_fragment"), mConversationsFragment);
        fragmentTransaction.commit();
    }

    public void showFragment(Fragment fragment) {
        android.support.v4.app.Fragment fragment1 = getHomePageFragment(fragment);

        if (mCurrentFragment == fragment1 && mCurrentFragment.isPageOpen()) {
            mCurrentFragment.getOpenPage().close(true);
            return;
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.setCustomAnimations(Resources.getAnimator(this, "wamod_home_fragment_enter_anim"), 0, 0, 0);
        transaction.replace(Resources.getId(this, "home_fragment"), fragment1);
        transaction.commit();

        mBottomNavigationView.setItemActive(fragment);

        mCurrentFragment = (HomePageFragment) fragment1;
        mCurrentFragment.onPageResume();
    }

    private HomePageFragment getHomePageFragment(Fragment fragment) {
        switch (fragment) {
            case HOME:
                return mConversationsFragment;
            case CALLS:
                return mCallsFragment;
            case CONTACTS:
                return mContactsFragment;
            case SETTINGS:
                return mSettingsFragment;
            default:
                return null;
        }
    }

    public void showPageInFragment(Fragment fragment, Class pageFragent) {
        showFragment(fragment);
        mFragmentManager.executePendingTransactions();
        mCurrentFragment.openPage(pageFragent);
    }

    public boolean isPageOpen() {
        return mCurrentFragment.isPageOpen();
    }

    @Override
    public void onBackPressed() {
        /*if (mNavigationDrawer.isOpen()) {
            mNavigationDrawer.setOpen(false);
        } else if (mCurrentFragment.isPageOpen()) {
            mCurrentFragment.getOpenPage().close(true);
        } else if (mCurrentFragment != mConversationsFragment) {
            showFragment(Fragment.HOME);
        } else {
            finish();
        }*/

        if (!(mNavigationDrawer.onBackPressed() || mCurrentFragment.onBackPressed() || processOnBackPressed())) {
            finish();
        }
    }

    private boolean processOnBackPressed() {
        if (mCurrentFragment != mConversationsFragment) {
            showFragment(Fragment.HOME);
            return true;
        }
        return false;
    }

    public void setLightStatusBar(boolean light) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(light? mDefaultSystemUIVisibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : mDefaultSystemUIVisibility);
        }
    }
}
