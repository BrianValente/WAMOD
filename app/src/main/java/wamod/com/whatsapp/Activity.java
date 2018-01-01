package wamod.com.whatsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.whatsapp.*;

/**
 * Created by brianvalente on 11/24/17.
 */

public class Activity {

    public static void onCreate_Before(com.whatsapp.Activity context, Bundle savedInstanceState) {
        Log.i("WAMOD", "Activity loaded: " + context.getLocalClassName());

        if (context instanceof com.whatsapp.HomeActivity) {
            new HomeActivity().onCreate_Before(context, savedInstanceState);
        }
    }

    public void call() {
        onCreate_Before(null, null);
    }

    public static void _onResume(com.whatsapp.Activity context) {
        //Toast.makeText(context, "App injected!", Toast.LENGTH_LONG).show();
    }

}
