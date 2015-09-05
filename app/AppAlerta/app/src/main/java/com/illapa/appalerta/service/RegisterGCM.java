package com.illapa.appalerta.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.illapa.appalerta.R;

import java.io.IOException;

/**
 * Created by emedinaa on 21/05/15.
 */
public class RegisterGCM extends AsyncTask<String,Integer,String>
{

    private static final  String TAG="RegisterGCM";
    //private String url;
    //private Usuario usuario;
    private String regid;
    private GoogleCloudMessaging gcm;
    private Context context;
    private OnCompleteGCM mlistener;

    //public RegisterGCM ( Usuario usuario, Context context, GoogleCloudMessaging gcm)
    public RegisterGCM ( OnCompleteGCM listener,Context context, GoogleCloudMessaging gcm)
    {
        //this.url = url;
        //this.usuario = usuario;
        this.gcm= gcm;
        this.context=context;
        this.mlistener = listener;
    }



    @Override
    protected String doInBackground(String... strings)
    {
        try {
            regid = gcm.register(context.getResources().getString(R.string.gcm_id));
            Log.i("gcmRegister", regid);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return regid;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);
        //LogUtils.LOGI(TAG,"onPostExecute "+s);
        mlistener.onComplete(s);
    }
}