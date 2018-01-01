package wamod.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import wamod.activity.HomeActivity;
import wamod.widget.HomePageContentLayout;

public class HomePageFragment extends OldAppCompatBridgeFragment {

    public HomeActivity mHomeActivity;
    public HomePageContentLayout mContent;
    public Stack<PageFragment> mPages = new Stack<>();
    public View mRootView;
    public static final String PAGE_TAG = "page_tag";
    public HomeActivity.Fragment mFragmentType;
    public boolean mLightStatusBar = true;


    private boolean mOnResumeCalled = false;

    public HomePageFragment() {

    }

    @Override
    public final void onResume() {
        super.onResume();

        if (!mOnResumeCalled) {
            onPageResume();
            mOnResumeCalled = true;
        }
    }

    public void onPageResume() {
        if (mHomeActivity != null) {
            mHomeActivity.setLightStatusBar(mLightStatusBar);
        }

        if (!mPages.empty()) {
            mPages.lastElement().onPageResume();
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        mHomeActivity = (HomeActivity) layoutInflater.getContext();

        if (mRootView != null) {
            // Recreate pages

            new Thread(new Runnable() {
                public void run() {
                    for (Object pageFragment : mPages.toArray()) {
                        getChildFragmentManager().beginTransaction().add(((PageFragment) pageFragment).mFrameLayoutId, (PageFragment) pageFragment).commit();
                    }
                }
            }).start();
        }

        return super.onCreateView(layoutInflater, viewGroup);
    }

    public void openPage(final Class pageFragmentClass) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                final PageFragment pageFragment;

                try {
                    pageFragment = (PageFragment) pageFragmentClass.getMethod("create", HomePageFragment.class).invoke(null, HomePageFragment.this);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                mContent.addPageAnimated(pageFragment, getChildFragmentManager());
                mPages.push(pageFragment);
            }
        }, 0);
    }

    public void closeOpenedPage() {
        // Remove PageFragment from the pages list
        PageFragment pageFragment = mPages.pop();

        // Tell HomePageContentLayout to animate the FrameLayout
        mContent.removePageAnimated(pageFragment);

        // Call onPageResume to the actual page
        if (mPages.empty()) {
            onPageResume();
        } else {
            mPages.lastElement().onPageResume();
        }
    }

    public boolean isPageOpen() {
        return !mPages.empty();
    }

    public PageFragment getOpenPage() {
        return mPages.isEmpty() ? null : mPages.lastElement();
    }

    public class NavigationClickListener implements View.OnClickListener {

        HomePageFragment mHomePageFragment;

        public NavigationClickListener(HomePageFragment homePageFragment) {
            this.mHomePageFragment = homePageFragment;
        }

        @Override
        public void onClick(View view) {
            mHomePageFragment.mHomeActivity.mNavigationDrawer.setOpen(true);
        }
    }

    public boolean onBackPressed() {
        if (isPageOpen()) {
            closeOpenedPage();
            return true;
        }
        return false;
    }

}
