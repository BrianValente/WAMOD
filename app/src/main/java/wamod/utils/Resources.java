package wamod.utils;

import android.content.Context;

/**
 * Created by brianvalente on 11/25/17.
 */

public class Resources {
    public static int getId(Context context, String id) {
        return context.getResources().getIdentifier(id, "id", context.getPackageName());
    }

    public static int getLayout(Context context, String id) {
        return context.getResources().getIdentifier(id, "layout", context.getPackageName());
    }

    public static int getString(Context context, String id) {
        return context.getResources().getIdentifier(id, "string", context.getPackageName());
    }

    public static int getDrawable(Context context, String id) {
        return context.getResources().getIdentifier(id, "drawable", context.getPackageName());
    }

    public static int getAttr(Context context, String id) {
        return context.getResources().getIdentifier(id, "attr", context.getPackageName());
    }

    public static int getAnimator(Context context, String id) {
        return context.getResources().getIdentifier(id, "animator", context.getPackageName());
    }
}
