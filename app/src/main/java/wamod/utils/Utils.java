package wamod.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.whatsapp.Activity;
import com.whatsapp.MeManager;
import com.whatsapp.contact.StockPicture;
import com.whatsapp.contact.a.Picture;
import com.whatsapp.data.ContactInfo;

/**
 * Created by brianvalente on 12/18/17.
 */

public class Utils {

    private static int mStatusBarHeight = -1;
    private static DisplayMetrics mDisplayMetrics = null;

    public static MeManager.MeInfo getMeInfo(Activity activity) {
        return activity.mMeManager.getMeInfo();
    }

    public static Bitmap getContactBitmap(ContactInfo contactInfo, boolean returnStock) {
        Picture pictureManager = Picture.getPicture();
        Bitmap pictureBitmap = pictureManager.getBitmap(contactInfo, 200, -1.0f, true);

        if (pictureBitmap != null || !returnStock) {
            return pictureBitmap;
        } else  {
            return StockPicture.getStockPicture().getStockPicture(contactInfo);
        }
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        if (mDisplayMetrics == null) {
            mDisplayMetrics = context.getResources().getDisplayMetrics();
        }

        return mDisplayMetrics;
    }

    public static int dpToPx(Context context, float dp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getDisplayMetrics(context));
        return px;
    }

    public static int spToPx(Context context, float sp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getDisplayMetrics(context));
        return px;
    }

    public static boolean isPreviewApp(Context context) {
        return !context.getPackageName().contentEquals("com.whatsapp");
    }

    public static int getStatusBarHeight(Context context) {
        if (mStatusBarHeight > -1)
            return mStatusBarHeight;

        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        mStatusBarHeight = result;

        return result;
    }

    // https://stackoverflow.com/questions/3035692/how-to-convert-a-drawable-to-a-bitmap
    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    // https://stackoverflow.com/questions/4821488/bad-image-quality-after-resizing-scaling-bitmap/7468636#7468636
    public static Bitmap resizeBitmap(Bitmap bitmap,int newWidth,int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;

    }

    // https://stackoverflow.com/questions/20531516/how-do-i-add-selectableitembackground-to-an-imagebutton-programmatically/31292299
    public static Drawable getSelectableItemBackgroundBorderless(Context context) {
        // Create an array of the attributes we want to resolve
        // using values from a theme
        // android.R.attr.selectableItemBackground requires API LEVEL 11
        int[] attrs = new int[] { android.R.attr.selectableItemBackgroundBorderless /* index 0 */};

        // Obtain the styled attributes. 'themedContext' is a context with a
        // theme, typically the current Activity (i.e. 'this')
        TypedArray ta = context.obtainStyledAttributes(attrs);

        // Now get the value of the 'listItemBackground' attribute that was
        // set in the theme used in 'themedContext'. The parameter is the index
        // of the attribute in the 'attrs' array. The returned Drawable
        // is what you are after
        Drawable drawableFromTheme = ta.getDrawable(0 /* index */);

        // Finally free resources used by TypedArray
        ta.recycle();

        return drawableFromTheme;
    }

    // https://stackoverflow.com/questions/20531516/how-do-i-add-selectableitembackground-to-an-imagebutton-programmatically/31292299
    public static Drawable getSelectableItemBackground(Context context) {
        int[] attrs = new int[] { android.R.attr.selectableItemBackground /* index 0 */};
        TypedArray ta = context.obtainStyledAttributes(attrs);
        Drawable drawableFromTheme = ta.getDrawable(0);
        ta.recycle();
        return drawableFromTheme;
    }

}
