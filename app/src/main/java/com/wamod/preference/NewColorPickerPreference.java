package com.wamod.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wamod.Resources;
import com.wamod.utils;

/**
 * Created by BrianValente on 3/3/16.
 */
public class NewColorPickerPreference extends Preference {
    AppCompatActivity activity;
    private boolean updating = false;
    private boolean updatingFromED = false;
    private boolean alpha = false;
    private int red;
    private int green;
    private int blue;
    private int alphah = 255;

    public NewColorPickerPreference(Context context) {
        super(context);
        activity = (AppCompatActivity) context;
        init();
    }

    public NewColorPickerPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, Resources.styleable.NewColorPickerPreference);
        alpha = a.getBoolean(Resources.styleable.NewColorPickerPreference_alpha, false);
        a.recycle();
        activity = (AppCompatActivity) context;
        init();
    }

    public NewColorPickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (AppCompatActivity) context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewColorPickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        final View view = LayoutInflater.from(getContext()).inflate(Resources.layout.wamod_colorpicker_dialog, null);

        final SeekBar wamod_colorpicker_seekbar_red   = (SeekBar) view.findViewById(Resources.id.wamod_colorpicker_seekbar_red);
        final SeekBar wamod_colorpicker_seekbar_green = (SeekBar) view.findViewById(Resources.id.wamod_colorpicker_seekbar_green);
        final SeekBar wamod_colorpicker_seekbar_blue  = (SeekBar) view.findViewById(Resources.id.wamod_colorpicker_seekbar_blue);
        final SeekBar wamod_colorpicker_seekbar_alpha = (SeekBar) view.findViewById(Resources.id.wamod_colorpicker_seekbar_alpha);
        final EditText wamod_colorpicker_tv_hex       = (EditText) view.findViewById(Resources.id.wamod_colorpicker_tv_hex);
        final View wamod_colorpicker_preview          = view.findViewById(Resources.id.wamod_colorpicker_preview);

        wamod_colorpicker_seekbar_red.setOnSeekBarChangeListener(new onSeekbarChange(view));
        wamod_colorpicker_seekbar_green.setOnSeekBarChangeListener(new onSeekbarChange(view));
        wamod_colorpicker_seekbar_blue.setOnSeekBarChangeListener(new onSeekbarChange(view));
        wamod_colorpicker_seekbar_alpha.setOnSeekBarChangeListener(new onSeekbarChange( view));

        wamod_colorpicker_tv_hex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (updating) return;
                    updatingFromED = true;
                    alphah = 255;
                    Log.i("WAMOD", "Color charsequence: " + s);
                    String input = s.toString();
                    int color = Color.parseColor("#" + input);
                    if (s.length() == 6) {
                        // No alpha
                        red = Integer.parseInt(input.substring(0, 2), 16);
                        green = Integer.parseInt(input.substring(2, 4), 16);
                        blue = Integer.parseInt(input.substring(4, 6), 16);
                    } else if (s.length() == 8) {
                        // With alpha
                        alphah = Integer.parseInt(input.substring(0, 2), 16);
                        red = Integer.parseInt(input.substring(2, 4), 16);
                        green = Integer.parseInt(input.substring(4, 6), 16);
                        blue = Integer.parseInt(input.substring(6, 8), 16);
                    } else return;
                    Log.i("WAMOD", "Red: " + red + " Green: " + green + " Blue: " + blue + " Alpha: " + alpha);
                    wamod_colorpicker_seekbar_red.setProgress((red * 100) / 255);
                    wamod_colorpicker_seekbar_green.setProgress((green * 100) / 255);
                    wamod_colorpicker_seekbar_blue.setProgress((blue * 100) / 255);
                    wamod_colorpicker_seekbar_alpha.setProgress((alphah * 100) / 255);
                    wamod_colorpicker_preview.setBackgroundColor(color);
                    updatingFromED = false;
                } catch (Exception e) {
                    Log.i("WAMOD", e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (!alpha) {
            ((ViewGroup)wamod_colorpicker_seekbar_alpha.getParent()).setVisibility(View.GONE);
            wamod_colorpicker_tv_hex.setFilters(new InputFilter[] {new InputFilter.LengthFilter(6)});
        }

        dialog.setView(view);
        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hex = getHexString(view);
                utils.edit.putString(getKey(), hex);
                utils.edit.apply();
                utils.loadColorsV2(activity);
                setSummary("#" + hex);
            }
        });
        dialog.show();

        String colorHex = utils.prefs.getString(getKey(), "ffffff");
        if (!alpha && colorHex.length() == 8) colorHex = colorHex.substring(2,8);
        wamod_colorpicker_tv_hex.setText(colorHex);

        wamod_colorpicker_tv_hex.clearFocus();
        wamod_colorpicker_tv_hex.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //wamod_colorpicker_preview.setLayoutParams(new RelativeLayout.LayoutParams(((ViewGroup)wamod_colorpicker_tv_hex.getParent()).getMeasuredWidth(), wamod_colorpicker_preview.getHeight()));
                wamod_colorpicker_preview.setMinimumWidth(((ViewGroup)wamod_colorpicker_tv_hex.getParent()).getMeasuredWidth());
                wamod_colorpicker_tv_hex.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private class onSeekbarChange implements SeekBar.OnSeekBarChangeListener {
        View v;

        public onSeekbarChange(View view) {
            this.v = view;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateColorsFromSeekbars(v);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private void updateColorsFromSeekbars(View v) {
        updating = true;
        final EditText wamod_colorpicker_tv_hex       = (EditText) v.findViewById(Resources.id.wamod_colorpicker_tv_hex);
        final View wamod_colorpicker_preview          = v.findViewById(Resources.id.wamod_colorpicker_preview);

        String colorHex = getHexStringFromSeekbars(v);
        int color = Color.parseColor("#" + colorHex);

        if (!updatingFromED) {
            wamod_colorpicker_preview.setBackgroundColor(color);
            wamod_colorpicker_tv_hex.setText(colorHex);
        }
        updating = false;
    }

    private String getHexStringFromSeekbars(View v) {
        final SeekBar wamod_colorpicker_seekbar_red   = (SeekBar) v.findViewById(Resources.id.wamod_colorpicker_seekbar_red);
        final SeekBar wamod_colorpicker_seekbar_green = (SeekBar) v.findViewById(Resources.id.wamod_colorpicker_seekbar_green);
        final SeekBar wamod_colorpicker_seekbar_blue  = (SeekBar) v.findViewById(Resources.id.wamod_colorpicker_seekbar_blue);
        final SeekBar wamod_colorpicker_seekbar_alpha = (SeekBar) v.findViewById(Resources.id.wamod_colorpicker_seekbar_alpha);

        int redValue   = (wamod_colorpicker_seekbar_red.getProgress()   * 255) / 100;
        int greenValue = (wamod_colorpicker_seekbar_green.getProgress() * 255) / 100;
        int blueValue  = (wamod_colorpicker_seekbar_blue.getProgress()  * 255) / 100;
        int alphaValue = (wamod_colorpicker_seekbar_alpha.getProgress() * 255) / 100;

        String redHex   = Integer.toHexString(redValue);
        String greenHex = Integer.toHexString(greenValue);
        String blueHex  = Integer.toHexString(blueValue);
        String alphaHex = Integer.toHexString(alphaValue);

        String[] values = new String[4];
        values[0] = redHex;
        values[1] = greenHex;
        values[2] = blueHex;
        values[3] = alphaHex;

        for (int i=0; i<values.length; i++) {
            if (values[i].length() == 1) values[i] = "0" + values[i];
            else if (values[i].length() == 0) values[i] = "00";
        }

        if (!alpha) values[3] = "";

        return values[3] + values[0] + values[1] + values[2];
    }

    private String getHexString(View v) {
        try {
            final EditText wamod_colorpicker_tv_hex = (EditText) v.findViewById(Resources.id.wamod_colorpicker_tv_hex);
            String hex = wamod_colorpicker_tv_hex.getText().toString();
            int color = Color.parseColor("#" + hex);
            return hex;
        } catch (Exception e) {
            return getHexStringFromSeekbars(v);
        }
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        if (utils.nightModeShouldRun()) {
            TextView title = (TextView) view.findViewById(android.R.id.title);
            if (title != null) title.setTextColor(utils.getDarkColor(0));

            TextView summary = (TextView) view.findViewById(android.R.id.summary);
            if (summary != null) summary.setTextColor(utils.getDarkColor(1));
        }
    }
}
