package wamod.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whatsapp.ArchivedConversationsActivity;
import com.whatsapp.GroupMembersSelector;
import com.whatsapp.StarredMessagesActivity;
import com.whatsapp.WebSessionsActivity;

import wamod.activity.HomeActivity;
import wamod.fragment.settings.AboutFragment;
import wamod.utils.Log;
import wamod.utils.Resources;

/**
 * Created by brianvalente on 12/8/17.
 */

public class NavigationDrawerItem extends RelativeLayout implements View.OnClickListener {

    private HomeActivity mHomeActivity;
    private ImageView mIcon;
    private TextView mLabel;
    private Drawable mIconDrawable;
    private String mLabelString;

    public NavigationDrawerItem(Context context) {
        super(context);
        init(context, null, 0);
    }

    public NavigationDrawerItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public NavigationDrawerItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs, defStyleAttr);
    }

    public NavigationDrawerItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        mHomeActivity = (HomeActivity) context;

        if (attributeSet != null) {
            int[] attrs = {Resources.getAttr(mHomeActivity, "icon"), Resources.getAttr(mHomeActivity, "label")};

            Log.info("icon: " + Resources.getAttr(mHomeActivity, "icon") + " label: " + Resources.getAttr(mHomeActivity, "label"));

            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attributeSet,
                    attrs,
                    defStyleAttr, 0);


            try {
                mLabelString = a.getString(1);
                mIconDrawable = a.getDrawable(0);
            } finally {
                a.recycle();
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = LayoutInflater.from(mHomeActivity).inflate(Resources.getLayout(mHomeActivity, "wamod_home_drawer_item"), null);
        addView(view);

        setClickable(true);
        setFocusable(true);

        mIcon = findViewById(Resources.getId(mHomeActivity, "icon"));
        mLabel = findViewById(Resources.getId(mHomeActivity, "label"));

        mIcon.setImageDrawable(mIconDrawable);
        mLabel.setText(mLabelString);

        setGravity(Gravity.CENTER_VERTICAL);

        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = mHomeActivity.getResources().getResourceEntryName(view.getId());

        Intent intent = null;

        switch (name) {
            case "new_group":
                intent = new Intent(mHomeActivity, GroupMembersSelector.class);
                break;
            case "archived_chats":
                intent = new Intent(mHomeActivity, ArchivedConversationsActivity.class);
                break;
            case "starred_messages":
                intent = new Intent(mHomeActivity, StarredMessagesActivity.class);
                break;
            case "whatsapp_web":
                intent = new Intent(mHomeActivity, WebSessionsActivity.class);
                break;
            case "about":
                //intent = new Intent(mContext, About.class);
                mHomeActivity.showPageInFragment(HomeActivity.Fragment.SETTINGS, AboutFragment.class);
                break;
            case "wamod_settings":
                mHomeActivity.showFragment(HomeActivity.Fragment.SETTINGS);
                break;
            case "donate":
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/BrianValente"));
        }

        if (intent != null) {
            mHomeActivity.startActivity(intent);
        }

        closeDrawer();

    }

    private void closeDrawer() {
        View view = ((android.app.Activity) mHomeActivity).findViewById(Resources.getId(mHomeActivity, "drawer"));

        if (view != null && view instanceof NavigationDrawer) {
            ((NavigationDrawer) view).setOpen(false);
        }
    }
}
