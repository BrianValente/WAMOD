package com.wamod;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by BrianValente on 3/3/16.
 */
public class WAMODSettingsActivity_Home extends WAMODSettings {
    static Context ctx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        utils.loadColorsBeforeSuper(this);
        super.onCreate(savedInstanceState);
        ctx = this;
        getFragmentManager().beginTransaction().replace(android.R.id.content, new WAMODSettingsFragment()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        utils.loadColorsV2(this);
    }

    public static class WAMODSettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("wamod");
            addPreferencesFromResource(Resources.xml.wamodsettings_home);

            Preference drawerBg = findPreference("home_drawer_bg");
            drawerBg.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Intent in = new Intent(Intent.ACTION_PICK);
                    in.setType("image/*");
                    startActivityForResult(in, 1);
                    return false;
                }
            });
        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            //super.onActivityResult(requestCode, resultCode, data);
            try {
                if (null != data) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = ctx.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap photo = BitmapFactory.decodeFile(picturePath);

                    FileOutputStream out = null;
                    try {
                        String path = utils.getApplicationPath(ctx) + "/files/wamod_drawer_bg.png";
                        out = new FileOutputStream(path);
                        photo.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                        // PNG is a lossless format, the compression factor (100) is ignored
                        Toast.makeText(ctx, ctx.getResources().getString(id.wamod_done), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                dialog.setMessage("There was an error, try another image.");
                dialog.show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}