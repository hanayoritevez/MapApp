package com.example.mapapp;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

public class MainActivity extends AppCompatActivity {

    private static final double MAP_LAT = 35.4472391;
    private static final double MAP_LON = 139.6414945;
    private static final double MAP_ZOOM = 6.0;
    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(),PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
     //   Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.mapView);
        IMapController mapController =  mMapView.getController();
        mapController.setZoom(MAP_ZOOM);
        GeoPoint centerPoint = new GeoPoint( MAP_LAT,MAP_LON );
        mapController.setCenter(centerPoint);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);

    }



    public void onResume(){
        super.onResume();
        if (mMapView!=null) {
            mMapView.onResume();
        }
    }

    public void onPause(){
        super.onPause();
        if (mMapView!=null) {
            mMapView.onPause();
        }
    }
}