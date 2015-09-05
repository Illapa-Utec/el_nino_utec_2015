package com.illapa.appalerta.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by emedinaa on 04/09/15.
 */
public class IntentHelper {

    public static void callPhone(Context context,String phoneNumber)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(callIntent);
    }
}
