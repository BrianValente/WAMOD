package com.wamod.WAclass;

import android.view.View;
import android.widget.ImageView;

import com.wamod.Resources;

/**
 * Created by BrianValente on 3/5/16.
 */
public class ContactsFragment {

    /* Called on
     *    com.whatsapp.ContactsFragment.onCreateView(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
     * Before
     *    return-object v0
     * Smali
     *    invoke-static {v0, p0}, Lcom/wamod/WAclass/ContactsFragment;->_onCreateView(Landroid/view/View;Lcom/whatsapp/ContactsFragment;)V
     */
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
