package jandroid.android.jeffsaa.jandroid_toolkit.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class PreferManager {

    public static void set(Context context, String key, String value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(key, value);
        editor.apply();
    }

    public static void set(Context context, String key, int value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putInt(key, value);
        editor.apply();
    }

    public static void set(Context context, String key, float value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void set(Context context, String key, long value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putLong(key, value);
        editor.apply();
    }

    public static void set(Context context, String key, boolean value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void set(Context context, String key, Set<String> value){
        SharedPreferences.Editor editor = getEditor(context);
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static String get(Context context, String key, String d){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getString(key, d);
    }

    public static void del(Context context, String key){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings.edit().remove(key).apply();
    }

    private static SharedPreferences.Editor getEditor(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.edit();
    }

}