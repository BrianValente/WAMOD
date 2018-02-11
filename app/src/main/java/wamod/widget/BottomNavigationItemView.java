package wamod.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;

import wamod.utils.Log;
import wamod.utils.Resources;
import wamod.utils.Theme;
import wamod.utils.Utils;

/**
 * Created by brianvalente on 11/24/17.
 */

public class BottomNavigationItemView extends View {

    private Context mContext;

    // Drawing
    private Paint mIconPaint, mLabelPaint;
    private Rect mLabelRect;
    private int mIconSize;
    private int mIconTopMargin, mLabelBottomMargin;

    // Resources
    private Bitmap mIcon;
    private String mLabel;
    private Drawable mIconDrawable;

    // Animation
    private long mAnimationStarted, mAnimationMustEnd;
    private AnimationType mAnimationType;
    private boolean mAnimating = false;
    private boolean mPressing = false;
    private int mLastMotionEvent = -1;

    // Settings
    private static final int COLOR_ACTIVE = Theme.getColor(Theme.Key.COLOR_BNV_ITEM_ACTIVE);
    private static final int COLOR_INACTIVE = Theme.getColor(Theme.Key.COLOR_BNV_ITEM_INACTIVE);
    private static final float ICON_SIZE_DP = 26;
    private static final float TEXT_SIZE_DP = 11;
    private static final float BUTTON_PRESSED_SCALE = 0.85f;
    private static final int ANIMATION_DURATION = 75;

    private enum AnimationType {
        PRESSING,
        RELEASING
    }

    public BottomNavigationItemView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BottomNavigationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        mContext = context;

        if (attributeSet != null) {
            int[] attrs = {Resources.getAttr(mContext, "icon"), Resources.getAttr(mContext, "label")};

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

        mIconSize = Utils.dpToPx(mContext, ICON_SIZE_DP);
        mIconTopMargin = Utils.dpToPx(mContext, 8f);
        mLabelBottomMargin = Utils.dpToPx(mContext, 1.5f);

        mLabelPaint = new Paint();
        mIconPaint = new Paint();
        mLabelRect = new Rect();

        mLabelPaint.setStyle(Paint.Style.FILL);
        mLabelPaint.setTextAlign(Paint.Align.CENTER);
        mLabelPaint.setTextSize(Utils.dpToPx(mContext, TEXT_SIZE_DP));
        mLabelPaint.setAntiAlias(true);
        mLabelPaint.getTextBounds(mLabel, 0, mLabel.length(), mLabelRect);

        mIconPaint.setAntiAlias(true);
        mIconPaint.setFilterBitmap(true);
        mIconPaint.setDither(true);

        mIcon = Utils.drawableToBitmap(mIconDrawable);
        mIcon = Utils.resizeBitmap(mIcon, mIconSize, mIconSize);

        setPaintColor(COLOR_INACTIVE);

        setClickable(true);
        setFocusable(true);

        //setBackground(Utils.getSelectableItemBackgroundBorderless(mContext));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        invalidate();
    }

    public void setActive(boolean active) {
        setPaintColor(active? COLOR_ACTIVE : COLOR_INACTIVE);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        animateButtonPress(event);
        return super.onTouchEvent(event);
    }

    private void animateButtonPress(MotionEvent event) {
        int action = event.getAction();

        if (action == mLastMotionEvent || (action != MotionEvent.ACTION_DOWN && action != MotionEvent.ACTION_UP)) {
            return;
        }

        mAnimating = true;
        mAnimationStarted = 0;
        mAnimationMustEnd = 0;
        mAnimationType = action == MotionEvent.ACTION_DOWN? AnimationType.PRESSING : AnimationType.RELEASING;
        mPressing = action == MotionEvent.ACTION_DOWN;

        mLastMotionEvent = action;

        invalidate();
    }

    private void setPaintColor(int color) {
        mIconPaint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        mLabelPaint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long time = 0;

        if (mAnimating) {
            time = System.currentTimeMillis();

            if (mAnimationStarted == 0) {
                mAnimationStarted = time;
                mAnimationMustEnd = time + ANIMATION_DURATION;
            } else if (time >= mAnimationMustEnd) {
                mAnimating = false;
                mLastMotionEvent = -1;
            }
        }

        if (mAnimating || mPressing) {
            float scale = BUTTON_PRESSED_SCALE;

            if (mAnimating) {
                long animationTime = time - mAnimationStarted;

                float animationTime1 = (float)animationTime / ANIMATION_DURATION;

                if (mPressing) {
                    scale = 1 - animationTime1 * (1 - BUTTON_PRESSED_SCALE);
                } else {
                    scale = 1 - (1 - animationTime1) * (1 - BUTTON_PRESSED_SCALE);
                }
            }

            canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
        }

        int xPos = canvas.getWidth() / 2;
        int yPos = canvas.getHeight() - mLabelRect.height() - mLabelBottomMargin + mLabelRect.bottom;

        canvas.drawText(mLabel, xPos, yPos, mLabelPaint);

        xPos = (canvas.getWidth() - mIconSize) / 2;
        canvas.drawBitmap(mIcon, xPos, mIconTopMargin, mIconPaint);

        if (mAnimating) {
            invalidate();
        }
    }

}
