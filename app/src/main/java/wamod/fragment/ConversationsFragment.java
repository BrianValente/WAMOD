package wamod.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.Toolbar;

import com.whatsapp.StatusesFragment;
import com.whatsapp.StatusesFragment.StatusesRowAbstract;
import com.whatsapp.camera.CameraActivity;
import com.whatsapp.data.StatusInfo;
import com.whatsapp.statusplayback.StatusPlaybackActivity;

import java.util.ArrayList;

import wamod.activity.HomeActivity;
import wamod.statuses.StatusesAdapter;
import wamod.utils.Log;
import wamod.utils.Resources;
import wamod.utils.Utils;

public class ConversationsFragment extends HomePageFragment {

    View mRootView = null;
    Toolbar mToolbar;
    public StockConversationsFragment mStockConversationsFragment;

    public ConversationsFragment() {
        mFragmentType = HomeActivity.Fragment.HOME;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        if (mRootView != null)
            return mRootView;

        mHomeActivity = (HomeActivity) layoutInflater.getContext();

        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_conversations_fragment"), null);

        mStockConversationsFragment = new StockConversationsFragment();

        mHomeActivity.getSupportFragmentManager().beginTransaction().replace(Resources.getId(mHomeActivity, "fragment"), mStockConversationsFragment).commit();

        // Toolbar

        mToolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable menuDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_menu"));
        menuDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        mToolbar.setNavigationIcon(menuDrawable);
        mToolbar.setTitle(Resources.getString(mHomeActivity, "website_type_home"));
        mToolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);
        mToolbar.setNavigationOnClickListener(new NavigationClickListener(this));

        return mRootView;
    }

    public static class StockConversationsFragment extends com.whatsapp.ConversationsFragment {

        HomeActivity mHomeActivity;
        com.whatsapp.StatusesFragment mStockStatusesFragment;
        StatusesAdapter mStatusesAdapter;
        RecyclerView mStatusesRecyclerView;

        @Override
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            mHomeActivity = (HomeActivity) layoutInflater.getContext();
            return super.onCreateView(layoutInflater, viewGroup);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            final ListView listView = getListView();
            mStockStatusesFragment = new com.whatsapp.StatusesFragment();

            mStatusesAdapter = new StatusesAdapter(mHomeActivity, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StatusesRowAbstract status = getStatusesDataSet().get(mStatusesRecyclerView.getChildAdapterPosition(view));

                    if (status instanceof StatusesFragment.StatusesRow) {
                        StatusInfo statusInfo = ((StatusesFragment.StatusesRow) status).mStatusInfo;

                        Intent intent;

                        if (statusInfo.mJabberId.contentEquals("") && statusInfo.mTotal == 0) {
                            intent = new Intent(mHomeActivity, CameraActivity.class);
                        } else {
                            intent = new Intent(mHomeActivity, StatusPlaybackActivity.class);
                            intent.putExtra("jid", statusInfo.mJabberId);
                        }

                        mHomeActivity.startActivity(intent);
                    }
                }
            });

            listView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    View headerView = mHomeActivity.getLayoutInflater().inflate(Resources.getLayout(mHomeActivity, "wamod_conversations_fragment_header"), null);

                    mStatusesRecyclerView = headerView.findViewById(Resources.getId(mHomeActivity, "statuses_recyclerview"));
                    mStatusesRecyclerView.setLayoutManager(new LinearLayoutManager(mHomeActivity, LinearLayoutManager.HORIZONTAL, false));
                    mStatusesRecyclerView.setAdapter(mStatusesAdapter);
                    mStatusesRecyclerView.setHorizontalScrollBarEnabled(false);

                    listView.addHeaderView(headerView);

                    mHomeActivity.getSupportFragmentManager().beginTransaction().replace(Resources.getId(mHomeActivity, "statuses_fragment"), mStockStatusesFragment).commit();
                    listView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }

        public void statusesDataSetChanged() {
            Log.info("statusesDataSetChanged");
            mStatusesAdapter.notifyDataSetChanged();
        }

        public ArrayList<StatusesRowAbstract> getStatusesDataSet() {
            ArrayList<StatusesRowAbstract> array = mStockStatusesFragment.mStatusesArrayList;
            Log.info("getStatusesDataSet called. count: " + array.size());
            return array;
        }
    }


}
