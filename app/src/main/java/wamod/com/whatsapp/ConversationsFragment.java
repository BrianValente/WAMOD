package wamod.com.whatsapp;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;

import wamod.activity.HomeActivity;


public class ConversationsFragment extends ListFragment {

    public void onNotifyDataSetChanged() {
        FragmentActivity activity = getActivity();

        if (activity instanceof wamod.activity.HomeActivity) {
            ((HomeActivity) activity).mConversationsFragment.conversationsDataSetChanged();
        }
    }

}
