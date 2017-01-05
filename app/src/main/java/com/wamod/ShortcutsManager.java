package com.wamod;

import android.annotation.SuppressLint;
import android.content.*;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.IBinder;
import android.service.chooser.ChooserTarget;
import com.wamod.activity.home.HomeActivity;
import com.whatsapp.ContactChooserTargetService;
import com.whatsapp.Conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by brianvalente on 10/21/16.
 */
@SuppressLint("NewApi")
public class ShortcutsManager {
    Context context;
    ShortcutManager mSystemShortcutManager;
    List<ShortcutInfo> shortcuts = new ArrayList<>();

    private ShortcutsManager(Context context) {
        this.context = context;
        mSystemShortcutManager = context.getSystemService(ShortcutManager.class);
    }

    public static ShortcutsManager getShortcutsManager(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return null;
        else return new ShortcutsManager(context);
    }

    public void loadShortcuts() {
        ContactChooserTargetService contactChooserTargetService = new ContactChooserTargetService();
        List<ChooserTarget> targets = contactChooserTargetService.onGetChooserTargets(null, null);
        for (int i = 0; i<4; i++)
            addByTarget(targets.get(i));
    }

    private void addDummyShortcut() {
        mSystemShortcutManager.removeAllDynamicShortcuts();

        Bitmap profilePhoto = Utils.drawableToBitmap(Utils.context.getDrawable(Resources.getDrawable("ic_shortcut_contact")));
        shortcuts.add(0, new ShortcutInfo.Builder(context, "none")
                .setShortLabel("Disabled for this build")
                .setIcon(Icon.createWithBitmap(profilePhoto))
                .setIntent(new Intent(context, HomeActivity.class).setAction(Intent.ACTION_ASSIST))
                .build());
        mSystemShortcutManager.addDynamicShortcuts(shortcuts);
    }

    private void addByTarget(ChooserTarget target) {
        mSystemShortcutManager.removeAllDynamicShortcuts();

        Icon icon = target.getIcon();
        if (icon == null)
            icon = Icon.createWithBitmap(Utils.drawableToBitmap(Utils.context.getDrawable(Resources.getDrawable("ic_shortcut_contact"))));

        shortcuts.add(0, new ShortcutInfo.Builder(context, target.getIntentExtras().getString("jid"))
                .setShortLabel(target.getTitle())
                .setIcon(icon)
                .setIntent(new Intent(context, Conversation.class).putExtra("jid", target.getIntentExtras().getString("jid")).setAction(Intent.ACTION_ASSIST))
                .build());
        mSystemShortcutManager.addDynamicShortcuts(shortcuts);
    }

    private void addByJabberId(String jabberId) {
        mSystemShortcutManager.removeAllDynamicShortcuts();
        Contact contact = Contact.getContactFromJabberId(jabberId);

        Bitmap profilePhoto = App.getContactsManager().getContactPhoto(contact.jabberId);
        if (profilePhoto == null)
            profilePhoto = Utils.drawableToBitmap(Utils.context.getDrawable(Resources.getDrawable("ic_shortcut_contact")));
        else
            profilePhoto = Utils.getRoundedCornerBitmap(profilePhoto);

        shortcuts.add(0, new ShortcutInfo.Builder(context, jabberId)
                .setShortLabel(contact.fullName == null? contact.jabberId : contact.fullName)
                .setIcon(Icon.createWithBitmap(profilePhoto))
                .setIntent(new Intent(context, Conversation.class).putExtra("jid", contact.jabberId).setAction(Intent.ACTION_ASSIST))
                .build());
        mSystemShortcutManager.addDynamicShortcuts(shortcuts);
    }
}
