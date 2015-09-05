package com.illapa.appalerta;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.illapa.appalerta.helpers.DeviceHelper;
import com.illapa.appalerta.helpers.PreferencesHelper;
import com.illapa.appalerta.model.entity.BaseResponse;
import com.illapa.appalerta.request.ApiClient;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private static long SLEEP_TIME = 4000;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    String SENDER_ID = "625105971646";
    private GoogleCloudMessaging gcm;
    private String regid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Start the next activity
                String deviceID= DeviceHelper.deviceID(SplashActivity.this);

                Intent intent= new Intent(SplashActivity.this, SelectionActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SLEEP_TIME);*/
        initGCM();

    }
    private void initGCM() {

        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
            regid = PreferencesHelper.getGCMID(this);
            if(regid!=null)
            {
                registerGCMServer();
            }else
            {
                registerInBackground();
            }
        } else {
            Log.v(TAG, "No valid Google Play Services APK found.");
            finish();
        }

    }

    private void registerGCMServer() {
        String deviceID= DeviceHelper.deviceID(SplashActivity.this);
        Log.v(TAG, "regID "+regid+ "  deviceID "+deviceID);
        ApiClient.getILlapaApiClient().registerPhone(regid, deviceID, new Callback<BaseResponse>() {
            @Override
            public void success(BaseResponse baseResponse, Response response) {
                Log.v(TAG, "register phone "+baseResponse);
                gotoHome();
            }

            @Override
            public void failure(RetrofitError error) {

                Log.v(TAG, "error phone "+error);
                finish();
            }
        });
    }

    private void gotoHome() {

        Intent intent= new Intent(SplashActivity.this, SelectionActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(SplashActivity.this);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;


                    /*// You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    sendRegistrationIdToBackend();

                    // For this demo: we don't need to send it because the device will send
                    // upstream messages to a server that echo back the message using the
                    // 'from' address in the message.

                    // Persist the regID - no need to register again.
                    storeRegistrationId(context, regid);*/
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    msg=null;
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                //mDisplay.append(msg + "\n");
                Log.v(TAG, " onPostExecute " + msg);
                if(msg!=null)
                {
                    registerGCMServer();
                    PreferencesHelper.saveGCMID(SplashActivity.this,regid);
                }else
                {
                    Log.v(TAG, "error GCM");
                    finish();
                }

                // You should send the registration ID to your server over HTTP, so it
                // can use GCM/HTTP or CCS to send messages to your app.


                // For this demo: we don't need to send it because the device will send
                // upstream messages to a server that echo back the message using the
                // 'from' address in the message.

                // Persist the regID - no need to register again.
                //storeRegistrationId(context, regid);

            }
        }.execute(null, null, null);
    }

    private boolean checkPlayServices()
    {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.v(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
