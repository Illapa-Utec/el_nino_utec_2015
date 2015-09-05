package com.illapa.appalerta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationEventActivity extends AppCompatActivity {

    @Bind(R.id.tviMessage) TextView tviMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_event);
        ButterKnife.bind(this);
        if(getIntent().getExtras()!=null)
        {
            String txt= getIntent().getExtras().getString("TITLE")+"\n"+
                    getIntent().getExtras().getString("MSG");
            tviMessage.setText(txt);
        }
        /*getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitNotification();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       return false;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exitNotification();
    }
    private void exitNotification()
    {
        Intent intent= new Intent(NotificationEventActivity.this, SelectionActivity.class);
        startActivity(intent);
        finish();
    }
}
