package wamod.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import wamod.fragment.HomePageFragment;
import wamod.fragment.PageFragment;
import wamod.support.v4.widget.ViewDragHelper;
import wamod.utils.Utils;

import static wamod.fragment.HomePageFragment.PAGE_TAG;

public class HomePageContentLayout extends RelativeLayout {

    private HomePageFragment mHomePageFragment;
    public ViewDragHelper mDragHelper;
    private PageFragment mPageToClose;

    private final float EDGE_SIZE = Utils.dpToPx(getContext(), 20);

    public HomePageContentLayout(Context context) {
        super(context);
    }

    public HomePageContentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomePageContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(HomePageFragment fragment) {
        mHomePageFragment = fragment;

        mDragHelper = ViewDragHelper.create(this, 1.0f, new HomePageContentLayout.ViewDragHelperCallback());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getX() < EDGE_SIZE || mDragHelper.getCapturedView() != null) {
            mDragHelper.processTouchEvent(event);
            return true;
        }

        return false;
    }

    private boolean inViewInBounds(View view, float x, float y) {
        Rect outRect = new Rect();
        int[] location = new int[2];
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains((int) x, (int) y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    public void addPageAnimated(PageFragment pageFragment, FragmentManager fragmentManager) {
        if (mPageToClose != null) {
            destroyClosingPage();
        }

        final int id = View.generateViewId();

        FrameLayout frameLayout = new FrameLayout(mHomePageFragment.mHomeActivity);
        frameLayout.setBackgroundColor(Color.WHITE);
        frameLayout.setId(id);
        frameLayout.setClickable(true);
        frameLayout.setTag(PAGE_TAG);
        frameLayout.setAlpha(1);
        frameLayout.setElevation(30);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        addView(frameLayout);

        frameLayout.offsetLeftAndRight(getWidth());
        mDragHelper.smoothSlideViewTo(frameLayout, 0, 0);
        pageFragment.mFrameLayoutId = id;
        fragmentManager.beginTransaction().add(id, pageFragment).commit();
    }

    public void removePageAnimated(final PageFragment pageFragment) {
        /*mPageToClose = pageFragment;
        Animation anim = new TranslateAnimation(0,getWidth(),0,0);
        anim.setDuration(200);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(0);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //pageFragment.destroy();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        pageFragment.getFrameLayout().startAnimation(anim);*/

        if (mPageToClose != null) {
            destroyClosingPage();
        }

        mPageToClose = pageFragment;
        FrameLayout frameLayout = mPageToClose.getFrameLayout();
        if (frameLayout == null) {
            destroyClosingPage();
        } else {
            mDragHelper.smoothSlideViewTo(frameLayout, getWidth(), 0);
        }

        invalidate();
    }

    class ViewDragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            if (mPageToClose != null) {
                destroyClosingPage();
            }
            return child.getTag() == PAGE_TAG;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            int width = child.getWidth();
            int pos = left > 0 ? left < width? left : width : 0;
            return pos;
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            return child.getWidth();
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            boolean pageMustClose = xvel > 0;

            if (pageMustClose) {
                mHomePageFragment.closeOpenedPage();
            } else {
                mDragHelper.smoothSlideViewTo(releasedChild, 0, 0);
            }

            invalidate();
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            mDragHelper.captureChildView(mHomePageFragment.getOpenPage().getFrameLayout(), pointerId);
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
            mDragHelper.captureChildView(mHomePageFragment.getOpenPage().getFrameLayout(), pointerId);
        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);

            if (mPageToClose != null && state == ViewDragHelper.STATE_IDLE) {
                destroyClosingPage();
            }

        }
    }

    private void destroyClosingPage() {
        mPageToClose.destroy();
        mPageToClose = null;
    }

}
