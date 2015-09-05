package com.illapa.appalerta;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.illapa.appalerta.view.dialog.InformarEventoDialog;
import com.illapa.appalerta.view.dialog.OnCustomDialogListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnCustomDialogListener {

    @Bind(R.id.btnLluvia)
    View btnLluvia;

    @Bind(R.id.btnEpidemia)
    View btnEpidemia;

    @Bind(R.id.btnCarretera)
    View btnCarretera;

    @Bind(R.id.btnAlimento)
    View btnAlimento;

    @Bind(R.id.tviInfo)
    View tviInfo;

    private String[] arrEvents={"LLuvia o Inundación", "Epidemia","Carretera","Alimento"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        btnLluvia.setOnClickListener(optionsEvent);
        btnEpidemia.setOnClickListener(optionsEvent);
        btnCarretera.setOnClickListener(optionsEvent);
        btnAlimento.setOnClickListener(optionsEvent);
        tviInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoInfo();
            }
        });
    }

    private void gotoInfo() {
        Intent intent= new Intent(this, InfoEmergenciaActivity.class);
        startActivity(intent);
    }

    private View.OnClickListener optionsEvent= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int evento=0;
            switch (v.getId())
            {
                case R.id.btnLluvia:
                    evento=0;
                    break;
                case R.id.btnEpidemia:
                    evento=1;
                    break;
                case R.id.btnCarretera:
                    evento=2;
                    break;
                case R.id.btnAlimento:
                    evento=3;
                    break;
            }
            showInformarDialog(evento);
        }
    };

    private void showInformarDialog(int evento)
    {
        String eventName= arrEvents[evento];
        FragmentManager fragmentManager = getSupportFragmentManager();
        InformarEventoDialog dialog = new InformarEventoDialog();
        Bundle bundle= new Bundle();
        bundle.putInt("EVENT",evento);
        bundle.putString("NEVENT",eventName);
        dialog.setArguments(bundle);
        dialog.show(fragmentManager,"informar");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;*/
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        return false;
    }

    @Override
    public void onDialogPositiveClick(Dialog dialog) {

    }

    @Override
    public void onDialogNegativeClick(Dialog dialog) {

    }
}
