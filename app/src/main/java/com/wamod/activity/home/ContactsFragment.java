package com.wamod.activity.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.wamod.Resources;

/**
 * Created by brianvalente on 12/5/16.
 */
public class ContactsFragment extends android.app.Fragment {
    /*private LinearLayout mLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayout = (LinearLayout) inflater.inflate(Resources.getLayout("wamod_activity_home_contacts"), container, false);
        return mLayout;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(Resources.getLayout("wamod_activity_home_contacts"), container, false);
    }
}
