package wamod.statuses;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;

import wamod.activity.HomeActivity;

/**
 * Created by brianvalente on 11/26/17.
 */

public class StatusesFragment extends ListFragment {

    public void onNotifyDataSetChanged() {
        FragmentActivity activity = getActivity();

        if (activity instanceof HomeActivity) {
            ((HomeActivity) activity).mConversationsFragment.statusesDataSetChanged();
        }
    }

}
