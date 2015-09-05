package com.illapa.appalerta.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.illapa.appalerta.NotificationEventActivity;

/**
 * Created by emedinaa on 21/05/15.
 */
@SuppressWarnings("ALL")
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp = new ComponentName(context.getPackageName(), GCMIntentService.class.getName());

        startWakefulService(context, (intent.setComponent(comp)));

        //deberia ir a la actividad que corresponde
        setResultCode(NotificationEventActivity.RESULT_OK);
    }
}