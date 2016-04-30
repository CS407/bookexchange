package com.cs407.bookexchange.userprefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ssunny7 on 3/29/2016.
 */
public class UserPrefs {
    private static SharedPreferences preferences = null;
    private static Context context = null;

    public static void init(Context _context) {
        context = _context;
        preferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static String readPreference(String name) {
        return preferences.getString(name, null);
    }

    public static void writePreference(String name, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static void deletePreference(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(name);
        editor.commit();
    }

    public static void doLogout() {
        deletePreference(Constants.PREF_CUR_USER_USERNAME);
        deletePreference(Constants.PREF_CUR_USER_ID);
    }
}
