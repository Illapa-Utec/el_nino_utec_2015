package com.illapa.appalerta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.illapa.appalerta.helpers.IntentHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoEmergenciaActivity extends AppCompatActivity {

    @Bind(R.id.btnPolicia)
    View btnPolicia;

    @Bind(R.id.btnBomberos)
    View btnBomberos;

    @Bind(R.id.btnDCivil)
    View btnDCivil;

    @Bind(R.id.btnCRoja)
    View btnCRoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_emergencia);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        btnPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.callPhone(InfoEmergenciaActivity.this,"105");
            }
        });

        btnBomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.callPhone(InfoEmergenciaActivity.this,"116");
            }
        });

        btnDCivil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.callPhone(InfoEmergenciaActivity.this,"110");
            }
        });

        btnCRoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.callPhone(InfoEmergenciaActivity.this,"115");
            }
        });
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
