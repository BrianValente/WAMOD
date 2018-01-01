package wamod.com.whatsapp;

import android.os.Bundle;

/**
 * Created by brianvalente on 11/27/17.
 */

public abstract class ActivityInjector {

    public abstract void onCreate_Before(com.whatsapp.Activity context, Bundle savedInstanceState);
    public abstract void onCreate_After(com.whatsapp.Activity context, Bundle savedInstanceState);

    public abstract void onResumeBefore(com.whatsapp.Activity context);
    public abstract void onResume_After(com.whatsapp.Activity context);

}
