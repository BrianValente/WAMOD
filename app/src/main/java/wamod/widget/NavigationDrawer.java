package wamod.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whatsapp.Activity;
import com.whatsapp.MeManager;
import com.whatsapp.ProfileInfoActivity;
import com.whatsapp.contact.NumberParser;
import com.whatsapp.contact.StockPicture;
import com.whatsapp.contact.a.Picture;

import wamod.support.v4.widget.ViewDragHelper;
import wamod.utils.Resources;
import wamod.utils.Utils;

/**
 * Created by brianvalente on 12/7/17.
 */

public class NavigationDrawer extends RelativeLayout {

    private Context mContext;
    private boolean mOpened = false;
    private ContentLayout mParent;
    private ViewDragHelper mDragHelper;

    public NavigationDrawer(Context context) {
        super(context);
        init(context);
    }

    public NavigationDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavigationDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public NavigationDrawer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
    }

    public void toggle() {
        mOpened = !mOpened;
        setOpen(mOpened);
    }

    public void setOpen(boolean open) {
        mOpened = open;

        int left = open? getWidth() : 0;
        mParent.getViewDragHelper().smoothSlideViewTo(this, left, 0);
        mParent.invalidate();
    }

    public boolean onBackPressed() {
        if (mOpened) {
            setOpen(false);
            return true;
        }
        return false;
    }

    public void setOpenUnanimated(boolean open) {
        mOpened = open;

        int left = open? 0 : getWidth() * -1;
        offsetLeftAndRight(left);
    }

    public boolean isOpen() {
        return mOpened;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        ViewParent parent = getParent();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setTranslationX(getWidth() * -1);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        if (parent instanceof ContentLayout) {
            mParent = (ContentLayout) parent;
            mParent.setNavigationDrawer(this);
            mDragHelper = mParent.getViewDragHelper();
        }

        // Open Profile Info when pic is clicked
        View profile = findViewById(Resources.getId(mContext, "profile_picture"));

        if (profile != null) {

            profile.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ProfileInfoActivity.class);
                    mContext.startActivity(intent);
                    setOpen(false);
                }
            });

        }

        TextView nameTV = findViewById(Resources.getId(mContext, "name"));
        TextView numberTV = findViewById(Resources.getId(mContext, "number"));
        ImageView pictureIM = findViewById(Resources.getId(mContext, "profile_picture"));


        if (isInEditMode() || Utils.isPreviewApp(mContext)) {
            return;
        }


        MeManager meManager = ((Activity) mContext).mMeManager;
        MeManager.MeInfo user = meManager.getMeInfo();

        nameTV.setText(user.mPushName);
        numberTV.setText(NumberParser.parseNumber(user));

        //Bitmap picture = StockPicture.getStockPicture().getStockPicture(user);
        Picture picture = Picture.getPicture();
        Bitmap pictureBitmap = picture.getBitmap(user, 200, -1.0f, false);

        if (pictureBitmap == null) {
            pictureBitmap = StockPicture.getStockPicture().getStockPicture(user);
        }

        pictureIM.setImageBitmap(pictureBitmap);


        final View header = findViewById(Resources.getId(mContext, "header"));
        header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                header.setPadding(0, 50, 0, 0);
            }
        });
    }
}
