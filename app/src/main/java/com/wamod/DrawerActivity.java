package com.wamod;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.pkmmte.view.CircularImageView;

/**
 * Created by brianvalente on 2/22/16.
 */
public class DrawerActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wamod_home_drawer_activity);

        ImageView fab = (ImageView) findViewById(R.id.fab);
        Drawable bg = fab.getBackground();
        bg.setColorFilter(utils.getUIColor(utils.COLOR_TOOLBAR), PorterDuff.Mode.MULTIPLY);
        fab.setBackground(bg);
        Drawable icon = fab.getDrawable();
        icon.setColorFilter(utils.getUIColor(utils.COLOR_FOREGROUND), PorterDuff.Mode.MULTIPLY);
        fab.setImageDrawable(icon);
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}
