package com.illapa.appalerta.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by emedinaa on 05/09/15.
 */
public class PreferencesHelper {

    private static final String ILLAPA_PREFERENCES = "com.illapa.appalerta";
    private static final String GCM_PREFERENCES = "com.illapa.appalerta.gcm";

    //Registrar GCM ID ----------------------------------------
    public static void saveGCMID(Context context, String gcmid)
    {
        SharedPreferences.Editor editor= getEditor(context);
        editor.putString(GCM_PREFERENCES, gcmid);
        editor.apply();
    }

    public static String  getGCMID(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String gcmid = sharedPreferences.getString(GCM_PREFERENCES, null);
        return gcmid;
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(ILLAPA_PREFERENCES, Context.MODE_PRIVATE);
    }
}
