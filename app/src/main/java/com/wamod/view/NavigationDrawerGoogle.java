package com.wamod.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.*;
import com.pkmmte.view.CircularImageView;
import com.wamod.*;
import com.wamod.App;
import com.wamod.settings.Privacy;
import com.wamod.settings.Settings;
import com.wamod.setup.SetupActivity;
import com.whatsapp.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by brianvalente on 2/24/16.
 */
public class NavigationDrawerGoogle extends RelativeLayout {
    AppCompatActivity activity;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    final int DRAWER_SECTION_SWITCH_ANIMATION_DURATION = 100;

    public NavigationDrawerGoogle(Context context) {
        super(context);
        if (!isInEditMode()) {
            activity = (AppCompatActivity) context;
            init2();
        }
    }

    public NavigationDrawerGoogle(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            activity = (AppCompatActivity) context;
            init2();
        }

    }

    public NavigationDrawerGoogle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            activity = (AppCompatActivity) context;
            init2();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationDrawerGoogle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            activity = (AppCompatActivity) context;
            init2();
        }
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

        // Hide status bar background
        try {
            final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) activity.findViewById(Resources.id.wamod_drawer_overlay);
            Field field = CoordinatorLayout.class.getDeclaredField("mDrawStatusBarBackground");
            field.setAccessible(true);
            field.setBoolean(coordinatorLayout, false);
        } catch (NoSuchFieldException e) {
            Utils.manageException(e);
        } catch (IllegalAccessException e) {
            Utils.manageException(e);
        }


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
                            intent = new Intent(activity, GroupMembersSelector.class);
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
                        } else if (id == Resources.id.wamod_drawer_search) {
                            activity.onSearchRequested();
                        } else if (id == Resources.id.wamod_drawer_privacy) {
                            intent = new Intent(activity, Privacy.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.getID("wamod_drawer_archivedchats")) {
                            intent = new Intent(activity, com.whatsapp.ArchivedConversationsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == Resources.getID("wamod_drawer_debug")) {
                            intent = new Intent(activity, SetupActivity.class);
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


        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int padding = Utils.getStatusBarHeight(getContext());

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
        }*/

        final LinearLayout accountsList   = (LinearLayout) activity.findViewById(Resources.getID("drawer_accounts"));
        final ExpandableIndicator expand_indicator = (ExpandableIndicator) activity.findViewById(Resources.getID("expand_indicator"));


        DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (expand_indicator.isExpanded())
                    toggleAccountsSection();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        };

        drawerLayout.addDrawerListener(drawerListener);

        expand_indicator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleAccountsSection();
            }
        });

        for (AccountsManager.Account account : com.wamod.App.getAccountsManager().getAccounts()) {
            Log.i("NavigationDrawer", "Loading account id " + account.getId());
            AccountRow accountRow = new AccountRow(activity);
            accountRow.setAccount(account);
            accountsList.addView(accountRow, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        View add_account = findViewById(Resources.getID("add_account"));
        add_account.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getAccountsManager().showAddAccountPrompt(activity);
            }
        });
    }

    private void toggleAccountsSection() {
        final View accountsListView = activity.findViewById(Resources.getID("drawer_accounts_view"));
        final View itemsView        = activity.findViewById(Resources.getID("wamod_drawer_buttons"));
        final ExpandableIndicator expand_indicator = (ExpandableIndicator) activity.findViewById(Resources.getID("expand_indicator"));

        boolean expanded = !expand_indicator.isExpanded();
        expand_indicator.setExpanded(expanded);
        if (expanded) {
            showDrawerSectionWithAnimation(accountsListView);
            hideDrawerSectionWithAnimation(itemsView);
        } else {
            hideDrawerSectionWithAnimation(accountsListView);
            showDrawerSectionWithAnimation(itemsView);
        }
    }

    private void showDrawerSectionWithAnimation(final View v) {
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                if (interpolatedTime == 0) v.setVisibility(VISIBLE);
                v.setAlpha(interpolatedTime);
                float interpolatedTimeInverted = -interpolatedTime + 1;
                float marginTop = interpolatedTimeInverted * 30;
                //v.setPadding(0, marginTop, 0, 0);
                v.setY(marginTop);
            }
        };
        animation.setDuration(DRAWER_SECTION_SWITCH_ANIMATION_DURATION);
        v.startAnimation(animation);
    }

    private void hideDrawerSectionWithAnimation(final View v) {
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                super.applyTransformation(interpolatedTime, t);
                if (interpolatedTime == 1) v.setVisibility(GONE);
                interpolatedTime = -interpolatedTime + 1;
                v.setAlpha(interpolatedTime);
            }
        };
        animation.setDuration(DRAWER_SECTION_SWITCH_ANIMATION_DURATION);
        v.startAnimation(animation);
    }
}