package com.wamod.settings;

import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.wamod.ColorsManager;
import com.wamod.R;
import com.wamod.Resources;
import com.wamod.Utils;
import com.wamod.dialog.ColorPickerDialog;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by brianvalente on 12/16/16.
 */
public class MyColors extends Activity implements AdapterView.OnItemClickListener {
    ArrayList<Map.Entry<String, ?>> mColors;
    ListView mListView;
    Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Utils.loadColorsBeforeSuper(this);
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout("wamod_activity_list"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View footerView = LayoutInflater.from(this).inflate(Resources.getLayout("wamod_mycolors_add"), null);
        mColors = ColorsManager.getCustomColors();
        mListView = (ListView) findViewById(android.R.id.list);
        mAdapter = new Adapter(this, mListView, mColors);

        mListView.setAdapter(mAdapter);
        mListView.addFooterView(footerView);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
        Map.Entry<String, ?> color = mColors.get(position);
        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, color.getKey(), true);
        colorPickerDialog.setOnColorChangeListener(new ColorPickerDialog.OnColorChangeListener() {
            @Override
            public void onColorChange() {
                mAdapter.updateView(position);
            }
        });
        Utils.tintAndShowDialog(colorPickerDialog);
    }

    class Adapter extends ArrayAdapter {
        ArrayList<Map.Entry<String, ?>> mColors;
        ListView mListView;

        public Adapter(Context context, ListView listView, ArrayList<Map.Entry<String, ?>> colors) {
            super(context, 0);
            mListView = listView;
            mColors = colors;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(Resources.getLayout("wamod_mycolors_row"), null);
            }

            Map.Entry<String, ?> color = mColors.get(position);

            String colorName = color.getKey();
            String colorValue = (String) color.getValue();

            TextView title = (TextView) v.findViewById(Resources.getID("title"));
            title.setText(colorName);

            View colorView = v.findViewById(Resources.getID("color"));
            colorView.setBackgroundColor(Color.parseColor(colorValue));

            return v;
        }

        public void updateView(int position) {
            View v = mListView.getChildAt(position -
                    mListView.getFirstVisiblePosition());

            if(v == null)
                return;

            Map.Entry<String, ?> color = mColors.get(position);

            String colorName = color.getKey();
            String colorValue = (String) color.getValue();

            TextView title = (TextView) v.findViewById(Resources.getID("title"));
            title.setText(colorName);

            View colorView = v.findViewById(Resources.getID("color"));
            colorView.setBackgroundColor(Color.parseColor(colorValue));
        }

        @Override
        public int getCount() {
            return mColors.size();
        }

        @Override
        public Object getItem(int position) {
            return mColors.get(position);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.loadColorsV2(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}
