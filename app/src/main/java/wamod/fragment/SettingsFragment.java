package wamod.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.whatsapp.Activity;
import com.whatsapp.MeManager;
import com.whatsapp.ProfileInfoActivity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Stack;

import wamod.activity.HomeActivity;
import wamod.utils.Log;
import wamod.utils.Resources;
import wamod.utils.Utils;
import wamod.widget.CircleImageView;
import wamod.widget.HomePageContentLayout;

public class SettingsFragment extends HomePageFragment implements View.OnClickListener {

    public SettingsFragment() {
        mFragmentType = HomeActivity.Fragment.SETTINGS;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        super.onCreateView(inflater, container);

        if (mRootView != null) {
            return mRootView;
        }

        mHomeActivity = (HomeActivity) inflater.getContext();

        mRootView = inflater.inflate(Resources.getLayout(mHomeActivity, "wamod_settings_fragment"), null);

        mContent = mRootView.findViewById(Resources.getId(mHomeActivity, "wamod_settings_content"));
        mContent.init(this);

        setProfileInfo();

        // Toolbar
        Toolbar toolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));

        Drawable menuDrawable = mHomeActivity.getDrawable(Resources.getDrawable(mHomeActivity, "ic_menu"));
        menuDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);

        toolbar.setNavigationIcon(menuDrawable);
        toolbar.setTitle(Resources.getString(mHomeActivity, "settings_general"));
        toolbar.setPadding(0, Utils.getStatusBarHeight(mHomeActivity), 0, 0);
        toolbar.setNavigationOnClickListener(new NavigationClickListener(this));

        return mRootView;
    }


    private void setProfileInfo() {
        if (Utils.isPreviewApp(mHomeActivity)) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                View profile = mRootView.findViewById(Resources.getId(mHomeActivity, "profile"));
                CircleImageView profilePictureImageView = mRootView.findViewById(Resources.getId(mHomeActivity, "profile_picture"));
                TextView pushNameTextView = mRootView.findViewById(Resources.getId(mHomeActivity, "push_name"));
                TextView statusTextView = mRootView.findViewById(Resources.getId(mHomeActivity, "status"));
                MeManager.MeInfo meInfo = Utils.getMeInfo(mHomeActivity);

                pushNameTextView.setText(meInfo.mPushName);
                statusTextView.setText("Status will be shown here.");
                profilePictureImageView.setImageBitmap(Utils.getContactBitmap(meInfo, true));

                profile.setOnClickListener(SettingsFragment.this);
            }
        }).run();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        String name = mHomeActivity.getResources().getResourceEntryName(view.getId());

        Intent intent = null;

        switch (name) {
            case "profile":
                intent = new Intent(mHomeActivity, ProfileInfoActivity.class);
                break;
        }

        if (intent != null) {
            mHomeActivity.startActivity(intent);
        }
    }

}
