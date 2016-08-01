package com.quandoo.restaurants.model.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Responsible for storing primitive data in shared preference
 * <p/>
 * Created by hendabdel-latif on 7/30/2016.
 */
public class SharedPreferencesManager {

    private static final String SHARED_PREFERENCE_NAME = "Pref";
    private static SharedPreferencesManager mStorageManger;
    private SharedPreferences mSharedPreferences;

    /**
     * Default constructor
     *
     * @param context
     */
    private SharedPreferencesManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME, 0);
    }

    /**
     * Get single instance from StorageManger
     *
     * @return
     */
    public static SharedPreferencesManager getInstance(Context context) {
        if (mStorageManger == null)
            mStorageManger = new SharedPreferencesManager(context);
        return mStorageManger;
    }

    /**
     * Get the Boolean value from {@link SharedPreferences}
     *
     * @param key
     * @return empty if we don't find the value
     */
    public boolean getBooleanValue(String key, boolean defaultValue) {

        boolean value = defaultValue;

        if (key != null) {
            value = mSharedPreferences.getBoolean(key, defaultValue);
        }

        return value;
    }

    /**
     * Add Boolean value in {@link SharedPreferences}
     *
     * @param key
     * @param value
     * @return
     */
    public boolean addBoolean(String key, boolean value) {

        boolean added = false;

        if (key != null) {
            SharedPreferences.Editor editor = mSharedPreferences.edit()
                    .putBoolean(key, value);
            added = editor.commit();
        }

        return added;
    }

    /**
     * Clear the content of storage
     */
    public boolean clear() {

        SharedPreferences.Editor editor = mSharedPreferences.edit().clear();
        return editor.commit();
    }


}
