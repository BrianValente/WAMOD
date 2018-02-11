package com.whatsapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import com.whatsapp.data.StatusInfo;

import java.util.ArrayList;

import wamod.utils.Utils;

/**
 * Created by brianvalente on 11/24/17.
 */

public class StatusesFragment extends wamod.statuses.StatusesFragment {

    public ArrayList<StatusesRowAbstract> mStatusesArrayList;

    public StatusesFragment() {

        mStatusesArrayList = new ArrayList<>();

        StatusesRow statusRow = new StatusesRow();

        statusRow.mStatusInfo = new StatusInfo();
        statusRow.mStatusInfo.mJabberId = "";
        statusRow.mStatusInfo.mUnreadCount = 0;
        statusRow.mStatusInfo.mTotal = 0;

        mStatusesArrayList.add(statusRow);

        statusRow = new StatusesRow();
        statusRow.mStatusInfo = new StatusInfo();
        statusRow.mStatusInfo.mJabberId = "someone";
        statusRow.mStatusInfo.mUnreadCount = 2;
        statusRow.mStatusInfo.mTotal = 4;

        for (int i=0; i<20; i++) {
            mStatusesArrayList.add(statusRow);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public class StatusesRow extends StatusesRowAbstract {

        public StatusInfo mStatusInfo = null;

    }

    public class StatusesTitleRow extends StatusesRowAbstract {
    }


    public class StatusesRowAbstract {
    }


}
