package wamod.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import wamod.activity.HomeActivity;
import wamod.support.v4.widget.ViewDragHelper;
import wamod.utils.Utils;

/**
 * Created by brianvalente on 12/7/17.
 */

public class ContentLayout extends RelativeLayout {

    private HomeActivity mHomeActivity;
    private ViewDragHelper mDragHelper;
    private NavigationDrawer mNavigationDrawer;
    private int mNavigationDrawerOffset;

    private final float EDGE_SIZE = Utils.dpToPx(getContext(), 15);

    public ContentLayout(Context context) {
        super(context);
        init(context);
    }

    public ContentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ContentLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        mHomeActivity = (HomeActivity) context;
        mDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        /*int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.info("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.info("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.info("ACTION_UP");
                break;
        }*/

        boolean overNavigation = inViewInBounds(mNavigationDrawer, (int)event.getX(), (int)event.getY());
        boolean isPageOpen = mHomeActivity.isPageOpen();

        return ((mNavigationDrawer.isOpen() && !overNavigation) || (event.getX() < EDGE_SIZE && !isPageOpen));
    }

    private boolean inViewInBounds(View view, int x, int y) {
        Rect outRect = new Rect();
        int[] location = new int[2];
        view.getDrawingRect(outRect);
        view.getLocationOnScreen(location);
        outRect.offset(location[0], location[1]);
        return outRect.contains(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mNavigationDrawer == null) {
            return;
        }

        if (mDragHelper.continueSettling(true)) {
            mNavigationDrawer.postInvalidateOnAnimation();
            invalidate();
        }

        mNavigationDrawerOffset = mNavigationDrawer.getLeft();
    }

    public void setNavigationDrawer(NavigationDrawer navigationDrawer) {
        mNavigationDrawer = navigationDrawer;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mNavigationDrawer.offsetLeftAndRight(mNavigationDrawerOffset);
    }

    class ViewDragHelperCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return child == mNavigationDrawer;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            int width = mNavigationDrawer.getWidth();
            int pos = left > width ? width : left;
            return pos;
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
            return child.getWidth();
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            mNavigationDrawer.setOpen(xvel > 0);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
            mDragHelper.captureChildView(mNavigationDrawer, pointerId);
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            super.onEdgeDragStarted(edgeFlags, pointerId);
        }

    }

    public ViewDragHelper getViewDragHelper() {
        return mDragHelper;
    }
}
