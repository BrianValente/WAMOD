package com.whatsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import wamod.fragment.OldAppCompatBridgeListFragment;

/**
 * Created by brianvalente on 11/24/17.
 */

public class ConversationsFragment extends wamod.com.whatsapp.ConversationsFragment {

    public ArrayList<Row> mArrayList = new ArrayList<>();

    public ConversationsFragment() {
        mArrayList.add(new Row() {
            @Override
            public String getJabberId() {
                return "5491100000000@s.whatsapp.net";
            }
        });

        for(int i=0; i<20; i++) {
            mArrayList.add(new Row() {
                @Override
                public String getJabberId() {
                    return "someone";
                }
            });
        }
    }

    String[] numbers_text = new String[] { "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven",
            "twelve", "thirteen", "fourteen", "fifteen" };

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                numbers_text);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public interface Row {
        String getJabberId();
    }
}
