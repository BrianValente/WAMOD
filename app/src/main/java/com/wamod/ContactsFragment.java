package com.wamod;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by BrianValente on 3/5/16.
 */
public class ContactsFragment {
    public static void _onCreateView(final View v, final com.whatsapp.ContactsFragment f) {
        ImageView reloadContactsFAB = (ImageView) v.findViewById(Resources.id.wamod_fab_reloadcontacts);
        if (reloadContactsFAB != null) {
            HomeActivity.styleFAB(reloadContactsFAB);
            reloadContactsFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    f.reloadContacts();
                }
            });
        }
    }
}
