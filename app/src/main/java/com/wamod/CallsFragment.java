package com.wamod;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by BrianValente on 3/5/16.
 */
public class CallsFragment {
    public static void _onCreateView(final View v, final com.whatsapp.CallsFragment f) {
        ImageView clearcallsFAB = (ImageView) v.findViewById(Resources.id.wamod_fab_clearcalls);
        if (clearcallsFAB != null) {
            HomeActivity.styleFAB(clearcallsFAB);
            clearcallsFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f.clearCalls();
                }
            });
        }
    }

    public void callOnCreateView() {
        _onCreateView(null, null);
    }
}
