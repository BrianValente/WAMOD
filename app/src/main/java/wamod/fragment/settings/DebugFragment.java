package wamod.fragment.settings;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.ArrayList;

import wamod.activity.HomeActivity;
import wamod.fragment.HomePageFragment;
import wamod.fragment.PageFragment;
import wamod.utils.Resources;
import wamod.utils.Utils;

public class DebugFragment extends PageFragment {

    public static PageFragment create(HomePageFragment homePageFragment) {
        DebugFragment debugFragment = new DebugFragment();
        debugFragment.mHomePageFragment = homePageFragment;
        return debugFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super.onCreateView(layoutInflater, viewGroup);

        if (mRootView != null) {
            return mRootView;
        }

        mHomeActivity = (HomeActivity) viewGroup.getContext();
        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_settings_debug"), null);

        // Toolbar
        Toolbar toolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable backDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_back"));
        backDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setNavigationIcon(backDrawable);
        toolbar.setTitle("Debug");
        toolbar.setNavigationOnClickListener(new NavigationClickListener());
        toolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);

        ArrayList<Button> buttons = new ArrayList<>();

        buttons.add((Button) mRootView.findViewById(Resources.getId(mHomeActivity, "theming")));
        buttons.add((Button) mRootView.findViewById(Resources.getId(mHomeActivity, "privacy")));
        buttons.add((Button) mRootView.findViewById(Resources.getId(mHomeActivity, "about")));
        buttons.add((Button) mRootView.findViewById(Resources.getId(mHomeActivity, "debug")));

        for (Button button : buttons) {
            button.setOnClickListener(this);
        }

        return mRootView;
    }

    @Override
    public void onClick(View view) {
        String name = mHomeActivity.getResources().getResourceEntryName(view.getId());

        switch (name) {
            case "theming":
                mHomePageFragment.openPage(ThemingFragment.class);
                break;
            case "privacy":
                mHomePageFragment.openPage(PrivacyFragment.class);
                break;
            case "about":
                mHomePageFragment.openPage(AboutFragment.class);
                break;
            case "debug":
                mHomePageFragment.openPage(DebugFragment.class);
                break;
        }
    }

}
