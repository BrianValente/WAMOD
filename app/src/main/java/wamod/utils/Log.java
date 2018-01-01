package wamod.utils;

/**
 * Created by brianvalente on 11/26/17.
 */

public class Log {
    public static void info(String message) {
        android.util.Log.i("WAMOD", message);
    }

    public static void flag() {
        android.util.Log.i("WAMOD", "Flag");
    }
}
