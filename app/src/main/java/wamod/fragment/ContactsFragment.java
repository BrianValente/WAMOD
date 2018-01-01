package wamod.fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.Toolbar;

import com.whatsapp.Activity;

import wamod.activity.HomeActivity;
import wamod.utils.Resources;
import wamod.utils.Utils;

public class ContactsFragment extends HomePageFragment {

    View mRootView;
    StockContactsFragment mStockContactsFragment;

    public ContactsFragment() {
        mFragmentType = HomeActivity.Fragment.CONTACTS;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (mRootView != null)
            return mRootView;

        mHomeActivity = (HomeActivity) layoutInflater.getContext();
        mStockContactsFragment = new StockContactsFragment();
        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_calls_fragment"), null);

        getFragmentManager().beginTransaction().add(Resources.getId(mHomeActivity, "fragment"), mStockContactsFragment).commit();

        // Toolbar
        Toolbar toolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable menuDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_menu"));
        menuDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setTitle(Resources.getString(mHomeActivity, "contacts"));
        toolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);
        toolbar.setNavigationOnClickListener(new NavigationClickListener(this));

        return mRootView;
    }

    public static class StockContactsFragment extends com.whatsapp.ContactsFragment {

        boolean mOnViewCreatedCalled = false;

        @Override
        public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            if (mOnViewCreatedCalled) {
                return;
            }

            final ListView listView = getListView();

            listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    listView.addHeaderView(new View(view.getContext()));
                    listView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });

            mOnViewCreatedCalled = true;
        }

    }

}
