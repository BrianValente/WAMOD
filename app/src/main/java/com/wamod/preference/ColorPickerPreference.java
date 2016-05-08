package com.wamod.preference;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.Preference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.wamod.id;
import com.wamod.utils;

import yuku.ambilwarna.AmbilWarnaDialog;

/**
 * Created by BrianValente on 3/3/16.
 */
public class ColorPickerPreference extends Preference {
    AppCompatActivity activity;

    public ColorPickerPreference(Context context) {
        super(context);
        activity = (AppCompatActivity) context;
        init();
    }

    public ColorPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (AppCompatActivity) context;
        init();
    }

    public ColorPickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (AppCompatActivity) context;
        init();
    }

    public ColorPickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (AppCompatActivity) context;
        init();
    }

    private void init() {
        setSummary("#" + utils.prefs.getString(getKey(), "0"));
    }

    @Override
    protected void onClick() {
        super.onClick();
        final Context ctx = getContext();
        final boolean lollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        final Window window = activity.getWindow();
        final ActionBar actionBar = activity.getSupportActionBar();
        final ViewGroup viewGroupActionBar = (ViewGroup) activity.findViewById(id.action_bar);
        Integer color = Color.parseColor("#" + utils.prefs.getString(getKey(), "ffffff"));
        if (!(utils.prefs.getBoolean("debug_disablecolorpicker", false))) {
            final AmbilWarnaDialog dialog = new AmbilWarnaDialog(ctx, color, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    String hex = Integer.toHexString(color).substring(2).toLowerCase();
                    utils.edit.putString(getKey(), hex);
                    setSummary("#" + hex);
                    utils.edit.apply();

                    utils.loadColorsV2(activity);

                    /*switch (getKey()) {
                        case "general_statusbarcolor":
                            if (lollipop) window.setStatusBarColor(Color.parseColor("#" + utils.prefs.getString("general_statusbarcolor", "ffffff")));
                            break;
                        case "general_toolbarcolor":
                            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#" + utils.prefs.getString("general_toolbarcolor", "ffffff"))));
                            break;
                        case "general_navbarcolor":
                            if (lollipop) window.setNavigationBarColor(Color.parseColor("#" + utils.prefs.getString("general_navbarcolor", "ffffff")));
                            break;
                        case "general_toolbarforeground":
                            utils.tintToolbarItems(viewGroupActionBar, activity.getResources());
                            break;
                    }*/
                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                }
            });
            dialog.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle(ctx.getResources().getString(id.insertahexcolorwithout));

            final EditText input = new EditText(ctx);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            input.setText(utils.prefs.getString(getKey(), "ffffff"));
            builder.setView(input);

            builder.setPositiveButton(ctx.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String hex = input.getText().toString().toLowerCase();
                    try {
                        int color = Color.parseColor("#" + hex);
                        utils.edit.putString(getKey(), hex);
                        setSummary("#" + hex);
                        utils.edit.apply();

                        utils.loadColorsV2(activity);
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(ctx, "Error lol", Toast.LENGTH_LONG).show();
                    }
                }
            });
            builder.setNegativeButton(ctx.getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
}
