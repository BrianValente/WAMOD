package com.wamod.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.wamod.Resources;
import com.wamod.Utils;
import com.wamod.settings.Settings;
import com.wamod.settings.Privacy;
import com.whatsapp.HomeActivity;
import com.whatsapp.NewGroup;
import com.whatsapp.ProfileInfoActivity;
import com.whatsapp.SetStatus;
import com.whatsapp.StarredMessagesActivity;
import com.whatsapp.WebSessionsActivity;

/**
 * Created by brianvalente on 2/24/16.
 */
public class NavigationDrawerGoogle extends RelativeLayout {
    HomeActivity activity;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    public NavigationDrawerGoogle(Context context) {
        super(context);
        activity = (HomeActivity) context;
        init2();
    }

    public NavigationDrawerGoogle(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (HomeActivity) context;
        init2();
    }

    public NavigationDrawerGoogle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (HomeActivity) context;
        init2();
    }

    public NavigationDrawerGoogle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (HomeActivity) context;
        init2();
    }

    private void init2() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                init();
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void init() {
        final NavigationView navigationView = (NavigationView) activity.findViewById(Resources.id.wamod_drawer);
        final DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(Resources.id.wamod_drawer_parent);
        final ViewStub wamod_drawer_header = (ViewStub) activity.findViewById(Resources.id.wamod_drawer_header);

        // Load header style
        final int headerStyleID = Integer.parseInt(Utils.prefs.getString("home_drawer_header_style", "0"));
        int headerLayoutID;
        switch (headerStyleID) {
            case 0:
            default:
                headerLayoutID = Resources.layout.wamod_home_drawer_header_wamod;
                break;
            case 1:
                headerLayoutID = Resources.layout.wamod_home_drawer_header_wamodcentered;
                break;
        }
        wamod_drawer_header.setLayoutResource(headerLayoutID);
        wamod_drawer_header.inflate();

        try {
            if (Utils.prefs.getBoolean("home_drawer_dark", true)) {
                int bgColor = Color.parseColor("#" + Utils.prefs.getString("drawer_dark_background", "404040"));
                setBackgroundColor(bgColor);
            } else {
                int bgColor = Color.parseColor("#" + Utils.prefs.getString("drawer_light_background", "fefefe"));
                setBackgroundColor(bgColor);
            }
        } catch (Exception e) {
            Utils.manageException(e);
        }

        LinearLayout buttons = (LinearLayout) activity.findViewById(Resources.getID("wamod_drawer_buttons"));
        for (int i=0; i<buttons.getChildCount(); i++) {
            if (buttons.getChildAt(i) instanceof RelativeLayout) {
                final RelativeLayout item = (RelativeLayout) buttons.getChildAt(i);
                if (!Utils.prefs.getBoolean("home_drawer_dark", true)) {
                    /*ImageView icon = (ImageView) item.getChildAt(0);
                    TextView label = (TextView)  item.getChildAt(1);

                    if (icon != null && label != null) {
                        icon.setColorFilter(Color.parseColor("#22222"));
                        label.setTextColor(Color.parseColor("#22222"));
                    }*/

                    int color = Color.parseColor("#222222");

                    View label1 = item.getChildAt(0);
                    if (label1 != null && label1 instanceof TextView) ((TextView) label1).setTextColor(color);

                    View label = item.getChildAt(1);
                    if (label != null && label instanceof TextView) ((TextView) label).setTextColor(color);

                    View icon = item.getChildAt(0);
                    if (icon != null && icon instanceof ImageView) ((ImageView) icon).setColorFilter(color);
                }
                if (item != null) item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        int id = item.getId();
                        if (id == Resources.id.wamod_drawer_newgroup) {
                            intent = new Intent(activity, NewGroup.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_wamodweb) {
                            intent = new Intent(activity, WebSessionsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_setstatus) {
                            intent = new Intent(activity, SetStatus.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_changeprofilepic) {
                            intent = new Intent(activity, ProfileInfoActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_starredmessages) {
                            intent = new Intent(activity, StarredMessagesActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_wamodweb) {
                            intent = new Intent(activity, WebSessionsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_settings) {
                            intent = new Intent(activity, com.whatsapp.Settings.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_wamodsettings) {
                            intent = new Intent(activity, Settings.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.id.wamod_drawer_debug1) {
                            Utils.switchAccount(getContext());
                        } else if (id == Resources.id.wamod_drawer_search) {
                            activity.onSearchRequested();
                        } else if (id == Resources.id.wamod_drawer_privacy) {
                            intent = new Intent(activity, Privacy.class);
                            activity.startActivity(intent);
                        }
                        drawerLayout.closeDrawer(navigationView);
                }
                });
            } else if (buttons.getChildAt(i) instanceof LinearLayout) {
                LinearLayout separator = (LinearLayout) buttons.getChildAt(i);
                if (!Utils.prefs.getBoolean("home_drawer_dark", true)) separator.setBackgroundColor(Color.parseColor("#55222222"));
            }
        }

        TextView userNameTV                  = (TextView)          findViewById(Resources.id.wamod_drawer_usernametv);
        TextView userNumberTV                = (TextView)          findViewById(Resources.id.wamod_drawer_usernumbertv);
        CircularImageView wamod_drawer_photo = (CircularImageView) findViewById(Resources.id.wamod_drawer_photo);

        userNameTV.setText(Utils.getUserName(activity));
        userNumberTV.setText(Utils.getUserPhoneNumber(activity));
        Drawable userPic = Utils.getUserPicture(activity);
        if (userPic != null) wamod_drawer_photo.setImageDrawable(userPic);

        if (Utils.prefs.getBoolean("home_drawer_blackheadertext", false)) {
            userNameTV.setTextColor(Color.BLACK);
            userNumberTV.setTextColor(Color.BLACK);
        }

        wamod_drawer_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navigationView);
                Intent intent = new Intent(activity, ProfileInfoActivity.class);
                activity.startActivity(intent);
            }
        });

        ImageView drawerHeaderBg = (ImageView) findViewById(Resources.id.wamod_drawer_bgview);
        drawerHeaderBg.setImageDrawable(Utils.getDrawerBackground(getContext()));

        final CircularImageView wamod_drawer_header_2ndprofilepic = (CircularImageView) findViewById(Resources.id.wamod_drawer_header_2ndprofilepic);
        wamod_drawer_header_2ndprofilepic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.switchAccount(activity);
            }
        });
        if (!Utils.prefs.getBoolean("home_drawer_showsecondaccount", true)) wamod_drawer_header_2ndprofilepic.setVisibility(GONE);
        final Drawable secondNumberProfilePhoto = Utils.get2ndNumberUserPicture(getContext());
        if (secondNumberProfilePhoto != null) wamod_drawer_header_2ndprofilepic.setImageDrawable(secondNumberProfilePhoto);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int padding = Utils.getStatusBarHeight(getContext());
            if (headerStyleID == 0) wamod_drawer_header_2ndprofilepic.setPadding(0, padding, 0, 0);

            ViewGroup userInfo = (ViewGroup) wamod_drawer_photo.getParent();
            switch (headerStyleID) {
                case 0:
                default:
                    ((RelativeLayout.LayoutParams) userInfo.getLayoutParams()).topMargin = padding;
                    break;
                case 1:
                    ((LinearLayout.LayoutParams) userInfo.getLayoutParams()).topMargin = padding;
                    break;
            }

            //serInfo.setPadding(userInfo.getPaddingLeft(), padding, userInfo.getPaddingRight(), userInfo.getPaddingBottom());

            final RelativeLayout drawerHeader = (RelativeLayout) findViewById(Resources.id.wamod_drawer_header);
            drawerHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LayoutParams params = new LayoutParams(NavigationDrawerGoogle.this.getWidth(), drawerHeader.getHeight() + padding);
                    drawerHeader.setLayoutParams(params);

                    LayoutParams params1 = (LayoutParams) wamod_drawer_header_2ndprofilepic.getLayoutParams();
                    params1.setMargins(params1.leftMargin, params1.topMargin + padding, params1.rightMargin, params1.bottomMargin);
                    if (headerStyleID == 0) wamod_drawer_header_2ndprofilepic.setLayoutParams(params1);

                    final LinearLayout statusbar = (LinearLayout) findViewById(Resources.id.wamod_drawer_statusbar);
                    statusbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            statusbar.setLayoutParams(new LayoutParams(NavigationDrawerGoogle.this.getWidth(), Utils.getStatusBarHeight(getContext())));
                            statusbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });

                    drawerHeader.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
}