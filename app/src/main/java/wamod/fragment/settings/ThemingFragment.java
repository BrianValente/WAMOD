package wamod.fragment.settings;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import wamod.activity.HomeActivity;
import wamod.fragment.HomePageFragment;
import wamod.fragment.PageFragment;
import wamod.utils.Resources;
import wamod.utils.Utils;

public class ThemingFragment extends PageFragment {

    public static PageFragment create(HomePageFragment homePageFragment) {
        ThemingFragment privacyFragment = new ThemingFragment();
        privacyFragment.mHomePageFragment = homePageFragment;
        return privacyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        if (mRootView != null) {
            return mRootView;
        }

        mHomeActivity = (HomeActivity) viewGroup.getContext();
        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_settings_privacy"), null);

        // Toolbar
        Toolbar toolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable backDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_back"));
        backDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setNavigationIcon(backDrawable);
        toolbar.setTitle("Theming");
        toolbar.setNavigationOnClickListener(new NavigationClickListener());
        toolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);

        return mRootView;
    }

}
