package com.illapa.appalerta;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.illapa.appalerta.model.entity.EventEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectionActivity extends AppCompatActivity {

    private static final int ZOOM =10;
    @Bind(R.id.btnInformar)
    View btnInformar;

    private GoogleMap map;
    //Lima PERU
    private double userLat=-12.0478158;
    private double userLng=-77.0622028;
    private List<EventEntity> lsEventEntities;
    private int[] arrMarkers={R.drawable.ic_marker,R.drawable.ic_marker2,
    R.drawable.ic_marker3,R.drawable.ic_marker4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        buildMap();
        populateMarkers();
        btnInformar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoInformar();
            }
        });
    }

    private void populateMarkers() {
        lsEventEntities= new ArrayList<>();
        lsEventEntities.add(new EventEntity("100",0,-12.162590, -77.014980,"LLUVIA O INUNDACION : Rio Chillon"));
        lsEventEntities.add(new EventEntity("101",1,-12.224586, -76.924299,"EPIDEMIA : Denge"));
        lsEventEntities.add(new EventEntity("102",3,-12.102660, -76.995121,"CARRETERA: Cierre de Panamericana"));
        lsEventEntities.add(new EventEntity("103",4,-14.287596, -75.559027,"ALIMENTO: Escasez de alimento"));

        for (EventEntity eventEntity:lsEventEntities) {
            drawMarker(new LatLng(eventEntity.getLat(),eventEntity.getLng()),eventEntity.getObs()
                    ,eventEntity.getCategory());
        }
    }

    private void buildMap() {

        try {
            if (map == null) {
                // above API 11
                map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

                Double lat=userLat;
                Double lon=userLng;

                map.getUiSettings().setAllGesturesEnabled(true);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setMyLocationEnabled(false);
                map.getUiSettings().setZoomControlsEnabled(false);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), ZOOM));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawMarker(LatLng latLng, String desc, int eventId){
        // Remove any existing markers on the map
        int resourceId=arrMarkers[0];
        if(eventId<arrMarkers.length)
        {
            resourceId=arrMarkers[eventId];
        }

        LatLng currentPosition = new LatLng(latLng.latitude,latLng.longitude);
        map.addMarker(new MarkerOptions()
                .position(currentPosition)
                .snippet("Lat:" + latLng.latitude + "Lng:"+ latLng.longitude)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
                .title(desc));

       // if(map != null){
            //map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, ZOOM));
       // }
    }
    private void  gotoInformar()
    {
        Intent intent= new Intent(SelectionActivity.this, MainActivity.class);
        startActivity(intent);
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
