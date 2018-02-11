package wamod.utils;

import java.util.HashMap;
import java.util.Random;

public class Theme {

    public enum Key {
        // General
        COLOR_PRIMARY,
        COLOR_PRIMARY_DARK,
        COLOR_ACCENT,
        COLOR_BACKGROUND,

        // Toolbar
        COLOR_TOOLBAR_TITLE,
        COLOR_TOOLBAR_MENU,
        COLOR_TOOLBAR_ITEM,

        // Bottom Navigation View
        COLOR_BNV_BACKGROUND,
        COLOR_BNV_ITEM_ACTIVE,
        COLOR_BNV_ITEM_INACTIVE,

        // Lists
        COLOR_LIST_ITEM_TITLE,
        COLOR_LIST_ITEM_SUBTITLE,

        // Preference Items
        COLOR_PREFERENCE_ITEM_BUTTON_BACKGROUND,
        COLOR_PREFERENCE_ITEM_BUTTON_ICON,
        COLOR_PREFERENCE_ITEM_BUTTON_TITLE,
        COLOR_PREFERENCE_ITEM_BUTTON_SUBTITLE
    }

    private static HashMap<Key, Integer> mLightTheme = new HashMap<>();
    private static HashMap<Key, Integer> mNightTheme = new HashMap<>();
    private static HashMap<Key, Integer> mNightBlueTheme = new HashMap<>();
    private static HashMap<Key, Integer> mWaBusinessTheme = new HashMap<>();

    private static HashMap<Key, Integer> mActualTheme;

    private static Random mRandom = new Random();

    static {
        /// Light Theme

        // General
        mLightTheme.put(Key.COLOR_PRIMARY, 0xffffffff);
        mLightTheme.put(Key.COLOR_PRIMARY_DARK, 0xffcccccc);
        mLightTheme.put(Key.COLOR_ACCENT, 0xffff0000);
        mLightTheme.put(Key.COLOR_BACKGROUND, 0xfffafafa);

        // Toolbar
        mLightTheme.put(Key.COLOR_TOOLBAR_TITLE, 0xff000000);
        mLightTheme.put(Key.COLOR_TOOLBAR_MENU, 0xff000000);
        mLightTheme.put(Key.COLOR_TOOLBAR_ITEM, 0xff000000);

        // Bottom Navigation View
        mLightTheme.put(Key.COLOR_BNV_BACKGROUND, 0xffffffff);
        mLightTheme.put(Key.COLOR_BNV_ITEM_ACTIVE, 0xff000000);
        mLightTheme.put(Key.COLOR_BNV_ITEM_INACTIVE, 0xffaaaaaa);

        // Lists
        mLightTheme.put(Key.COLOR_LIST_ITEM_TITLE, 0xff000000);
        mLightTheme.put(Key.COLOR_LIST_ITEM_SUBTITLE, 0xffaaaaaa);

        // Preference Items
        mLightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_BACKGROUND, 0xffffffff);
        mLightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_ICON, 0xff000000);
        mLightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_TITLE, 0xff000000);
        mLightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_SUBTITLE, 0xffaaaaaa);




        /// Night Theme

        // General
        mNightTheme.put(Key.COLOR_PRIMARY, 0xff1c1c1d);
        mNightTheme.put(Key.COLOR_PRIMARY_DARK, 0xff1c1c1d);
        mNightTheme.put(Key.COLOR_ACCENT, 0xffffffff);
        mNightTheme.put(Key.COLOR_BACKGROUND, 0xff000000);

        // Toolbar
        mNightTheme.put(Key.COLOR_TOOLBAR_TITLE, 0xffffffff);
        mNightTheme.put(Key.COLOR_TOOLBAR_MENU, 0xffffffff);
        mNightTheme.put(Key.COLOR_TOOLBAR_ITEM, 0xffffffff);

        // Bottom Navigation View
        mNightTheme.put(Key.COLOR_BNV_BACKGROUND, 0xff1c1c1d);
        mNightTheme.put(Key.COLOR_BNV_ITEM_ACTIVE, 0xffffffff);
        mNightTheme.put(Key.COLOR_BNV_ITEM_INACTIVE, 0xff929292);

        // Lists
        mNightTheme.put(Key.COLOR_LIST_ITEM_TITLE, 0xffffffff);
        mNightTheme.put(Key.COLOR_LIST_ITEM_SUBTITLE, 0xff8e8e8e);

        // Preference Items
        mNightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_BACKGROUND, 0xff1c1c1d);
        mNightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_ICON, 0xffffffff);
        mNightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_TITLE, 0xffffffff);
        mNightTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_SUBTITLE, 0xff8e8e8e);


        /// Night Blue Theme

        // General
        mNightBlueTheme.put(Key.COLOR_PRIMARY, 0xff24303f);
        mNightBlueTheme.put(Key.COLOR_PRIMARY_DARK, 0xff24303f);
        mNightBlueTheme.put(Key.COLOR_ACCENT, 0xff54a3f8);
        mNightBlueTheme.put(Key.COLOR_BACKGROUND, 0xff1a222c);

        // Toolbar
        mNightBlueTheme.put(Key.COLOR_TOOLBAR_TITLE, 0xffffffff);
        mNightBlueTheme.put(Key.COLOR_TOOLBAR_MENU, 0xff54a3f8);
        mNightBlueTheme.put(Key.COLOR_TOOLBAR_ITEM, 0xffffffff);

        // Bottom Navigation View
        mNightBlueTheme.put(Key.COLOR_BNV_BACKGROUND, 0xff24303f);
        mNightBlueTheme.put(Key.COLOR_BNV_ITEM_ACTIVE, 0xff55a4f8);
        mNightBlueTheme.put(Key.COLOR_BNV_ITEM_INACTIVE, 0xff82919e);

        // Lists
        mNightBlueTheme.put(Key.COLOR_LIST_ITEM_TITLE, 0xffffffff);
        mNightBlueTheme.put(Key.COLOR_LIST_ITEM_SUBTITLE, 0xff82929f);

        // Preference Items
        mNightBlueTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_BACKGROUND, 0xff24303f);
        mNightBlueTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_ICON, 0xffffffff);
        mNightBlueTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_TITLE, 0xffffffff);
        mNightBlueTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_SUBTITLE, 0xff82929f);


        /// WhatsApp Business Theme

        // General
        mWaBusinessTheme.put(Key.COLOR_PRIMARY, 0xff3a5664);
        mWaBusinessTheme.put(Key.COLOR_PRIMARY_DARK, 0xff2d4350);
        mWaBusinessTheme.put(Key.COLOR_ACCENT, 0xff66e5d4);
        mWaBusinessTheme.put(Key.COLOR_BACKGROUND, 0xffffffff);

        // Toolbar
        mWaBusinessTheme.put(Key.COLOR_TOOLBAR_TITLE, 0xffffffff);
        mWaBusinessTheme.put(Key.COLOR_TOOLBAR_MENU, 0xff66e5d4);
        mWaBusinessTheme.put(Key.COLOR_TOOLBAR_ITEM, 0xffffffff);

        // Bottom Navigation View
        mWaBusinessTheme.put(Key.COLOR_BNV_BACKGROUND, 0xff3a5664);
        mWaBusinessTheme.put(Key.COLOR_BNV_ITEM_ACTIVE, 0xff66e5d4);
        mWaBusinessTheme.put(Key.COLOR_BNV_ITEM_INACTIVE, 0xff98aeb9);

        // Lists
        mWaBusinessTheme.put(Key.COLOR_LIST_ITEM_TITLE, 0xff000000);
        mWaBusinessTheme.put(Key.COLOR_LIST_ITEM_SUBTITLE, 0xff717171);

        // Preference Items
        mWaBusinessTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_BACKGROUND, 0xfff0f0f0);
        mWaBusinessTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_ICON, 0xff000000);
        mWaBusinessTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_TITLE, 0xff000000);
        mWaBusinessTheme.put(Key.COLOR_PREFERENCE_ITEM_BUTTON_SUBTITLE, 0xffaaaaaa);




        // Random Theme
        final int min = 1;
        final int max = 4;
        final int random = mRandom.nextInt((max - min) + 1) + min;

        switch (random) {
            case 1:
                mActualTheme = mLightTheme;
                break;
            case 2:
                mActualTheme = mNightTheme;
                break;
            case 3:
                mActualTheme = mNightBlueTheme;
                break;
            case 4:
                mActualTheme = mWaBusinessTheme;
                break;
        }
    }

    public static int getColor(Key key) {
        return mActualTheme.get(key);
    }

}
