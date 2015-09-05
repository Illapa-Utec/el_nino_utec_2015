package com.illapa.appalerta.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.illapa.appalerta.NotificationEventActivity;
import com.illapa.appalerta.R;

/**
 * Created by emedinaa on 21/05/15.
 */
public class GCMIntentService extends IntentService {

    private static  String TAG= "GCMIntentService";
    private static final int NOTIF_ALERTA_ID = 1;

    public GCMIntentService()
    {
        super("GCMIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);
        Bundle extras = intent.getExtras();
        Log.v(TAG, "extras " + extras);


        if (!extras.isEmpty())
        {
            //[{msj=El mensaje, from=625105971646, title=Hola Edu, android.support.content.wakelockid=1, collapse_key=do_not_collapse}]
            /*if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType))
            {
            }*/
            String typeMessage= extras.getString("_type");
            String msj = extras.getString("msj");
            String title = extras.getString("title");
            Log.v(TAG, "extras " + extras.toString());
            if(msj!=null && title!=null)
            {
                showNotificationApp(msj,title);
            }else
            {
                showNotificationApp("mensaje por defecto","Illapa");
            }
        }


        GCMBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void showNotificationApp(String msg, String title)
    {
        NotificationManager notificationManager =(NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Intent intent = new Intent(getApplicationContext(), NotificationEventActivity.class);
        Bundle bundle =new Bundle();
        bundle.putString("MSG",msg);
        bundle.putString("TITLE",title);
        //bundle.putSerializable("ENTITY",reqDoctorEntity);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pintent = PendingIntent.getActivity(getApplicationContext(),iUniqueId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification mNotification = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle("Alerta Illapa")
                .setContentText(msg)
                .setContentIntent(pintent)
                .setSmallIcon(R.drawable.ic_illapa)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.FLAG_NO_CLEAR)
                .build();

        notificationManager.notify(0, mNotification);

    }


}
