package com.illapa.appalerta.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.illapa.appalerta.R;
import com.illapa.appalerta.helpers.DeviceHelper;
import com.illapa.appalerta.model.entity.ReportResponse;
import com.illapa.appalerta.request.ApiClient;
import com.illapa.appalerta.utils.MathUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by emedinaa on 17/08/15.
 */
public class InformarEventoDialog extends DialogFragment {
    private static final String TAG = "IEVENTODIALOG";
    /** The system calls this to get the DialogFragment's layout, regardless
     of whether it's being displayed as a dialog or an embedded fragment. */
    OnCustomDialogListener mListener;
    private String observacion;
    private int eventId;
    private View rlayLoading;

    private double[] latitudes ={-5.379933,-6.996381,-9.193367,-10.665085,-13.458168,-12.0478158,-3.749683,-4.603400,-5.224580,-6.546887,
            -6.686837,-7.262075};
    private double[] longitudes ={ -80.101877,-79.135080,-78.322092, -77.531076, -75.992990,-77.0622028, -80.472767, -81.017540, -81.028526,
            -79.932640,-79.484692, -78.432751};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        return inflater.inflate(R.layout.layout_dialog_evento, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (OnCustomDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    /** The system calls this only when creating the layout in a dialog. */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // The only reason you might override this method when using onCreateView() is
        // to modify any dialog characteristics. For example, the dialog includes a
        // title by default, but your custom layout might not need it. So here you can
        // remove the dialog title, but you must call the superclass to get the Dialog.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        String eventName="";
        if(getArguments()!=null)
        {
            eventName= getArguments().getString("NEVENT");
            eventId= getArguments().getInt("EVENT");
        }
        rlayLoading= getView().findViewById(R.id.rlayLoading);
                ((TextView) (getView().findViewById(R.id.tviTitle))).setText("Informar sobre \n" + eventName);
        final EditText eteObservaciones= (EditText)(getView().findViewById(R.id.eteObservaciones));

        //events
        getView().findViewById(R.id.btnInformar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*dismiss();
                if (mListener != null)
                    mListener.onDialogPositiveClick(InformarEventoDialog.this.getDialog());*/
                observacion= eteObservaciones.getText().toString().trim();
                sendEvent();
            }
        });

        getView().findViewById(R.id.iviClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (mListener != null)
                    mListener.onDialogNegativeClick(InformarEventoDialog.this.getDialog());
            }
        });
    }

    private void showLoading(boolean state)
    {
        int visibility= (state)?(View.VISIBLE):(View.GONE);
        rlayLoading.setVisibility(visibility);
    }

    private void sendEvent()
    {
        int size= latitudes.length-1;
        int rnd= MathUtils.randomByRange(0,size);
        double lat=latitudes[rnd];
        double lng=longitudes[rnd];
        String deviceID= DeviceHelper.deviceID(getActivity());
        //eventId

        ApiClient.getILlapaApiClient().registerEvent(lat, lng, deviceID, eventId, observacion, new Callback<ReportResponse>()
        {
            @Override
            public void success(ReportResponse eventResponse, Response response) {
                Log.v(TAG, "success " + eventResponse.toString() + " | response " + response.toString());
                //UserEntity userEntity= Translate.toUser(success);
                //PreferencesHelper.saveSesionUser(loginView.getContext(),userEntity);
                //PreferencesHelper.saveUserType(loginView.getContext(),userEntity.getType());

                //loginView.loginSuccess(userEntity);
                showLoading(false);
                if(eventResponse!=null)
                {

                    if(eventResponse.isSuccess())
                    {
                        dismiss();
                    }else
                    {

                    }
                }else
                {

                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.v(TAG, "error " + error);
                //loginView.loginError(error);
                showLoading(false);
            }
        });
    }
}
