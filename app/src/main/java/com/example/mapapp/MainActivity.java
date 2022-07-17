package com.example.mapapp;

import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.CustomZoomButtonsController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.TilesOverlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final double MAP_LAT = 35.4472391;
    private static final double MAP_LON = 139.6414945;
    private static final double MAP_ZOOM = 6.0;
    private MapView mMapView = null;

    // 地理院 標準地図
    private static final String TILE_SERVER_1 = "https://cyberjapandata.gsi.go.jp/xyz/std/";
    // 地理院 空中写真・衛星画像
    private static final String TILE_SERVER_2  = "https://cyberjapandata.gsi.go.jp/xyz/ort/";

    private MapInfoSet current_mapinfo = new MapInfoSet();



    private List<Integer> iImages = new ArrayList<>();
    private List<String> iTitles = new ArrayList<>();
    private List<String> iDescriptions = new ArrayList<>();
    private List<GeoPoint> iLocation = new ArrayList<>();


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

        overlayTile(2);

        Log.d("main", "steup marker start");
        setupMarker();
        Log.d("main", "steup marker done");

        mapController.setCenter(centerPoint);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
///////////////////////////////////////////////////////////

        ////// Recycler View

        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(rLayoutManager);

//        iImages.add(R.drawable.onsen);
//        iTitles.add("選択してくれ");
//        iDescriptions.add("選択してくれ");
//        iLocation.add(current_mapinfo.position);

        RecyclerView.Adapter rAdapter = new MyAdapter(iImages, iTitles, iDescriptions,iLocation);

        Log.d("main","a");//log

        recyclerView.setAdapter(rAdapter);

        Log.d("main","b");//log

        //interface部分
        ItemTouchHelper mIth  = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN ,
                        ItemTouchHelper.LEFT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {

                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        rAdapter.notifyItemMoved(fromPos, toPos);
                        return true;// true if moved, false otherwise
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        iImages.remove(fromPos);
                        iTitles.remove(fromPos);
                        iDescriptions.remove(fromPos);
                        iLocation.remove(fromPos);
                        rAdapter.notifyItemRemoved(fromPos);
                    }
                });

        mIth.attachToRecyclerView(recyclerView);

    }


    private void setupMarker(){
    MarkerInterFace util = new MarkerInterFace(this );
    List<MarkerInterFace.Node> nodes =  util.getNodes();

    // create and show markers
    for(MarkerInterFace.Node node: nodes ) {
        Marker marker = new Marker( mMapView );
        marker.setPosition( new GeoPoint(node.lat, node.lon ) );
        marker.setTitle ( node.title );
        marker.setSnippet( node.description );
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                                            @Override
                                            public boolean onMarkerClick(Marker marker, MapView mMapView) {
                                                marker.showInfoWindow();
                                                current_mapinfo.title = marker.getSnippet();
                                                current_mapinfo.description = marker.getTitle();
                                                current_mapinfo.position = marker.getPosition();
                                                return true;
                                            }
                                        });
        mMapView.getOverlays().add(marker);
    } // for

} //  setupMarker



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

    private void overlayTile(int id) {
        mMapView.setTilesScaledToDpi(true);
        mMapView.getZoomController().setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT);
        // Add tiles layer with Custom Tile Source
        final MapTileProviderBasic tileProvider = new MapTileProviderBasic(getApplicationContext());
        ITileSource tileSource =  null;
        if (id ==1) {
            tileSource = new XYTileSource( "GSI",  4, 18, 256, ".png",
                    new String[] { TILE_SERVER_1 });
        } else if (id ==2) {
            tileSource = new XYTileSource( "GSI",  4, 18, 256, ".jpg",
                    new String[] { TILE_SERVER_2 });
        }
        tileProvider.setTileSource(tileSource);
        final TilesOverlay tilesOverlay = new TilesOverlay(tileProvider, this.getBaseContext());
        tilesOverlay.setLoadingBackgroundColor(Color.TRANSPARENT);
        mMapView.getOverlays().clear();
        mMapView.getOverlays().add(tilesOverlay);
        mMapView.invalidate();
    } //  overlayTile
    /**
     * clearOverlay
     */
    private void clearOverlay() {
        mMapView.getOverlays().clear();
        mMapView.invalidate();
    } // clearOverlay
}






