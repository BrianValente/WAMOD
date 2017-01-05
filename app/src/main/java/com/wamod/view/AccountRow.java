package com.wamod.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wamod.AccountsManager;
import com.wamod.App;
import com.wamod.Resources;

/**
 * Created by brianvalente on 9/15/16.
 */
public class AccountRow extends RelativeLayout {
    Context context;
    AccountsManager.Account account;

    public AccountRow(Context context) {
        super(context);
        init(context);
    }

    public AccountRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AccountRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AccountRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context ctx) {
        this.context = ctx;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(Resources.getLayout("wamod_account_row"), this, true);
    }

    public void setAccount(final AccountsManager.Account account) {
        TextView  name    = (TextView)  findViewById(Resources.getID("name"));
        TextView  phone   = (TextView)  findViewById(Resources.getID("phone"));
        ImageView picture = (ImageView) findViewById(Resources.getID("picture"));

        String nameStr = account.getName();
        if (nameStr == null || nameStr.contentEquals(""))
            nameStr = context.getResources().getString(Resources.getString("wamod_accounts_noname"));

        Drawable profilePicture = account.getProfilePicture();
        if (profilePicture == null)
            profilePicture = context.getResources().getDrawable(Resources.getDrawable("ic_contact_with_background"));

        name.setText(nameStr);
        phone.setText(account.getPhoneNumber());
        picture.setImageDrawable(profilePicture);

        this.account = account;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickable(true);
                App.getAccountsManager().switchToAccount(account);
            }
        });
    }
}
