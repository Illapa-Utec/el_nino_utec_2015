package com.illapa.appalerta.helpers;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by emedinaa on 05/09/15.
 */
public class DeviceHelper {

    private static  final String TAG="DeviceHelper";

    public static String deviceID(Context context)
    {
        String androidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.v(TAG, "androidID " + androidID);

        return androidID;
    }
}
