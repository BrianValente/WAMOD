package wamod.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.whatsapp.About;
import com.whatsapp.SettingsAccount;
import com.whatsapp.SettingsChat;
import com.whatsapp.SettingsContacts;
import com.whatsapp.SettingsDataUsage;
import com.whatsapp.SettingsHelp;
import com.whatsapp.SettingsNotifications;

import wamod.activity.HomeActivity;
import wamod.fragment.settings.AboutFragment;
import wamod.fragment.settings.DebugFragment;
import wamod.fragment.settings.PrivacyFragment;
import wamod.fragment.settings.ThemesFragment;
import wamod.fragment.settings.TipsFragment;
import wamod.utils.Resources;
import wamod.utils.Theme;
import wamod.utils.Utils;

/**
 * Created by brianvalente on 12/18/17.
 */

public class SettingsRowView extends View implements View.OnClickListener {

    private HomeActivity mActivity;
    private Paint mIconPaint, mLabelPaint;
    private Bitmap mIcon;
    private String mLabel = "";
    private Drawable mIconDrawable = null;
    private int mIconMarginLeft, mIconSize;
    private int mLabelMarginLeft;
    private Rect mLabelRect;
    private float mLabelOffset;

    public SettingsRowView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public SettingsRowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public SettingsRowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public SettingsRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, HomeActivity mActivity) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        mActivity = (HomeActivity) context;

        setClickable(true);
        setFocusable(true);

        if (attributeSet != null) {
            int[] attrs = {Resources.getAttr(mActivity, "icon"), Resources.getAttr(mActivity, "label")};

            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attributeSet,
                    attrs,
                    defStyleAttr, 0);


            try {
                mLabel = a.getString(1);
                mIconDrawable = a.getDrawable(0);
            } finally {
                a.recycle();
            }
        }

        setBackground(Utils.getSelectableItemBackground(mActivity));
        setClickable(true);
        setFocusable(true);

        mIconSize = Utils.dpToPx(mActivity, 20);
        mIconMarginLeft = Utils.dpToPx(mActivity, 16);

        int color = Theme.getColor(Theme.Key.COLOR_PREFERENCE_ITEM_BUTTON_ICON);

        mIconPaint = new Paint();
        mIconPaint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));

        mIcon = Utils.drawableToBitmap(mIconDrawable);
        mIcon = Utils.resizeBitmap(mIcon, mIconSize, mIconSize);

        mLabelRect = new Rect();

        mLabelPaint = new Paint();
        mLabelPaint.setAntiAlias(true);
        mLabelPaint.setColor(Theme.getColor(Theme.Key.COLOR_PREFERENCE_ITEM_BUTTON_TITLE));
        mLabelPaint.getTextBounds(mLabel, 0, mLabel.length(), mLabelRect);
        mLabelPaint.setTextSize(Utils.spToPx(mActivity, 14));

        mLabelMarginLeft = Utils.dpToPx(mActivity, 52);

        float textHeight = mLabelPaint.descent() - mLabelPaint.ascent();
        mLabelOffset = (textHeight / 2) - mLabelPaint.descent();

        setBackgroundColor(Theme.getColor(Theme.Key.COLOR_PREFERENCE_ITEM_BUTTON_BACKGROUND));

        setOnClickListener(this);
    }

    // https://stackoverflow.com/questions/11120392/android-center-text-on-canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasHeight = canvas.getHeight();

        /*Log.info("canvas.getHeight(): " + canvas.getHeight() +
                " mLabelRect.height(): " + mLabelRect.height() +
                " mLabelRect.top: " +  mLabelRect.top +
                " mLabelPaint.ascent(): " + mLabelPaint.ascent() +
                " mLabelPaint.descent(): " + mLabelPaint.descent() +
                " mLabelRect.exactCenterY(): " + mLabelRect.exactCenterY());*/

        canvas.drawBitmap(mIcon, mIconMarginLeft, (canvasHeight - mIconSize) / 2, mIconPaint);

        canvas.drawText(mLabel, mLabelMarginLeft, (canvasHeight / 2) + mLabelOffset, mLabelPaint);
    }

    @Override
    public void onClick(View view) {
        String name = mActivity.getResources().getResourceEntryName(view.getId());

        Intent intent = null;

        switch (name) {
            // WAMOD
            case "wamod_themes":
                mActivity.mSettingsFragment.openPage(ThemesFragment.class);
                break;
            case "wamod_privacy":
                mActivity.mSettingsFragment.openPage(PrivacyFragment.class);
                break;
            case "wamod_tips":
                mActivity.mSettingsFragment.openPage(TipsFragment.class);
                break;
            case "wamod_about":
                mActivity.mSettingsFragment.openPage(AboutFragment.class);
                break;
            case "wamod_donate":
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/BrianValente")));
                break;
            case "wamod_debug":
                mActivity.mSettingsFragment.openPage(DebugFragment.class);
                break;

            // WhatsApp
            case "account_info":
                intent = new Intent(mActivity, SettingsAccount.class);
                break;
            case "chat":
                intent = new Intent(mActivity, SettingsChat.class);
                break;
            case "notifications":
                intent = new Intent(mActivity, SettingsNotifications.class);
                break;
            case "data":
                intent = new Intent(mActivity, SettingsDataUsage.class);
                break;
            case "contacts":
                intent = new Intent(mActivity, SettingsContacts.class);
                break;
            case "help":
                intent = new Intent(mActivity, SettingsHelp.class);
                break;
            case "about":
                intent = new Intent(mActivity, About.class);
                break;
        }

        if (intent != null) {
            mActivity.startActivity(intent);
        }
    }

}