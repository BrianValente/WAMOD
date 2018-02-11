package wamod.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.whatsapp.HomeActivity;
import com.whatsapp.TextStatusComposerActivity;
import com.whatsapp.camera.CameraActivity;

import wamod.utils.Resources;
import wamod.utils.Theme;
import wamod.utils.Utils;

/**
 * Created by brianvalente on 11/24/17.
 */

public class BottomNavigationView extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener, View.OnClickListener, View.OnLongClickListener {

    // Properties

    private Context mContext;
    private wamod.activity.HomeActivity mHomeActivity;
    private BottomNavigationItemView mHomeItem, mCallsItem, mCameraItem, mContactsItem, mSettingsItem;

    // Constructors

    public BottomNavigationView(Context context) {
        super(context);
        init(context);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    // Methods

    private void init(Context context) {
        this.mContext = context;
        this.mHomeActivity = (wamod.activity.HomeActivity) context;
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {

        mHomeItem = findViewById(Resources.getId(mContext, "bnv_home"));
        mCallsItem = findViewById(Resources.getId(mContext, "bnv_calls"));
        mCameraItem = findViewById(Resources.getId(mContext, "bnv_camera"));
        mContactsItem = findViewById(Resources.getId(mContext, "bnv_contacts"));
        mSettingsItem = findViewById(Resources.getId(mContext, "bnv_settings"));

        mHomeItem.setOnClickListener(this);
        mCallsItem.setOnClickListener(this);
        mContactsItem.setOnClickListener(this);
        mSettingsItem.setOnClickListener(this);
        mCameraItem.setOnClickListener(this);

        mHomeItem.setOnLongClickListener(this);
        mCameraItem.setOnLongClickListener(this);

        setItemActive(mHomeItem);

        loadTheme();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public void onClick(View view) {

        BottomNavigationItemView item = (BottomNavigationItemView) view;

        if (view == mHomeItem) {
            mHomeActivity.showFragment(wamod.activity.HomeActivity.Fragment.HOME);
            setItemActive(item);
        } else if (view == mCallsItem) {
            mHomeActivity.showFragment(wamod.activity.HomeActivity.Fragment.CALLS);
            setItemActive(item);
        } else if (view == mCameraItem) {
            if (Utils.isPreviewApp(mContext))
                return;
            Intent intent = new Intent(mContext, CameraActivity.class);
            mContext.startActivity(intent);

        } else if (view == mContactsItem) {
            mHomeActivity.showFragment(wamod.activity.HomeActivity.Fragment.CONTACTS);
            setItemActive(item);
        } else if (view == mSettingsItem) {
            mHomeActivity.showFragment(wamod.activity.HomeActivity.Fragment.SETTINGS);
            setItemActive(item);
        } else {
            Toast.makeText(mContext, "Unknown state.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {

        if (Utils.isPreviewApp(mContext)) {
            return false;
        }

        if (view == mHomeItem) {
            Intent intent = new Intent(mContext, HomeActivity.class);
            intent.putExtra("wamod_show", true);
            mContext.startActivity(intent);
        } else if (mCameraItem == view) {
            Intent intent = new Intent(mContext, TextStatusComposerActivity.class);
            mContext.startActivity(intent);
        }

        return false;
    }

    public void setItemActive(wamod.activity.HomeActivity.Fragment fragmentButton) {
        switch (fragmentButton) {
            case HOME:
                setItemActive(mHomeItem);
                break;
            case CALLS:
                setItemActive(mCallsItem);
                break;
            case CONTACTS:
                setItemActive(mContactsItem);
                break;
            case SETTINGS:
                setItemActive(mSettingsItem);
                break;
        }
    }

    private void setItemActive(BottomNavigationItemView item) {
        mHomeItem.setActive(mHomeItem == item);
        mCallsItem.setActive(mCallsItem == item);
        mCameraItem.setActive(false);
        mContactsItem.setActive(mContactsItem == item);
        mSettingsItem.setActive(mSettingsItem == item);
    }

    private void loadTheme() {
        setBackgroundColor(Theme.getColor(Theme.Key.COLOR_BNV_BACKGROUND));
    }

}
