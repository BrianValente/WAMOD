package wamod.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wamod.R;
import com.whatsapp.Activity;

import wamod.utils.Resources;
import wamod.widget.NavigationDrawer;

/**
 * Created by brianvalente on 12/7/17.
 */

public class DrawerActivity extends Activity implements View.OnClickListener {

    private NavigationDrawer mNavigationDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout(this, "wamod_drawer_activity"));

        mNavigationDrawer = findViewById(Resources.getId(this, "drawer"));
        Button toggle = findViewById(Resources.getId(this, "toggle"));
        toggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mNavigationDrawer.toggle();
    }

}
