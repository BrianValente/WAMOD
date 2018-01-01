package wamod.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.whatsapp.Activity;

import wamod.activity.HomeActivity;
import wamod.utils.Resources;
import wamod.utils.Utils;

public class CallsFragment extends HomePageFragment {

    View mRootView;
    com.whatsapp.CallsFragment mStockCallsFragment;

    public CallsFragment() {
        mFragmentType = HomeActivity.Fragment.CALLS;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (mRootView != null)
            return mRootView;

        mHomeActivity = (HomeActivity) layoutInflater.getContext();
        mStockCallsFragment = new com.whatsapp.CallsFragment();
        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_calls_fragment"), null);

        getFragmentManager().beginTransaction().add(Resources.getId(mHomeActivity, "fragment"), mStockCallsFragment).commit();

        // Toolbar
        Toolbar toolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable menuDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_menu"));
        menuDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setTitle(Resources.getString(mHomeActivity, "calls"));
        toolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);
        toolbar.setNavigationOnClickListener(new NavigationClickListener(this));

        return mRootView;
    }
}
