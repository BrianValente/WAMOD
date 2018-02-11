package wamod.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wamod.utils.Resources;

/**
 * Created by brianvalente on 12/17/17.
 */

public class OldAppCompatBridgeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(inflater, container);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return null;
    }
}
