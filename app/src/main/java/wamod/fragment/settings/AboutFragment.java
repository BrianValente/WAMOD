package wamod.fragment.settings;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.whatsapp.Licenses;

import wamod.activity.HomeActivity;
import wamod.fragment.HomePageFragment;
import wamod.fragment.PageFragment;
import wamod.utils.Resources;
import wamod.utils.Utils;

public class AboutFragment extends PageFragment  {

    public static PageFragment create(HomePageFragment homePageFragment) {
        AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.mHomePageFragment = homePageFragment;
        aboutFragment.mLightStatusBar = false;
        return aboutFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        if (mRootView != null) {
            return mRootView;
        }

        mHomeActivity = (HomeActivity) viewGroup.getContext();
        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_settings_about"), null);

        // Toolbar
        Toolbar toolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable backDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_back"));
        backDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(backDrawable);
        toolbar.setTitle("About");
        toolbar.setNavigationOnClickListener(new NavigationClickListener());
        toolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);

        TextView versionTV = mRootView.findViewById(Resources.getId(mHomeActivity, "version"));
        versionTV.setOnClickListener(this);

        View licenses = mRootView.findViewById(Resources.getId(mHomeActivity, "licenses"));
        licenses.setOnClickListener(this);

        View madeBy = mRootView.findViewById(Resources.getId(mHomeActivity, "made_by"));
        madeBy.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onClick(View view) {
        String name = mHomeActivity.getResources().getResourceEntryName(view.getId());

        switch (name) {
            case "version":
                mHomePageFragment.openPage(DebugFragment.class);
                break;
            case "licenses":
                mHomeActivity.startActivity(new Intent(mHomeActivity, Licenses.class));
                break;
            case "made_by":
                mHomeActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/briannvalente")));
                break;
        }
    }

}
