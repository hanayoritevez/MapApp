

package com.example.mapapp;


        import android.content.Context;
        import android.content.res.AssetManager;
        import android.util.Log;

        import  org.osmdroid.util.GeoPoint;
        import org.osmdroid.views.overlay.Marker;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.List;


/**
 *  class MarkerInterFacce
 */
public class MarkerInterFace {

    // debug
    private final static boolean D = true;
    private final static String TAG = "OSM";
    private final static String TAG_SUB = "MarkerInterFace";

    private final static String FILE_NAME = "onsen.csv";

    private final static String COMMA = ",";
    private final static String SPACE = " ";

    private AssetManager mAssetManager;

    /**
     *  constractor
     */
    public MarkerInterFace(Context context) {
        mAssetManager = context.getAssets();
    }


    /**
     *  getNodes
     */
    public List<Node> getNodes(){

        List<Node> nodes = new ArrayList<>();

        List<String> lines = readAsset(FILE_NAME);

        Log.d("marker", String.valueOf(lines.size()));
        for(String line: lines) {
            // latitude, longitude,  title,  description
            String[] cols = line.split(COMMA);

            Log.d("marker:", line);
            if (cols.length >= 4 ) {
                double lat = parseDouble(cols[2]);
                double lon = parseDouble(cols[3]);
                String title = cols[0];

                Log.d("kasu",(String)cols[0]);
                String description = cols[1];
                log_d(  lat + SPACE + lon + title + SPACE + description );
                Node node = new Node( lat, lon, title, description );
                nodes.add( node ) ;
            }
        } // for

        return nodes;
    }


    /**
     *  readAsset
     */
    private  List<String> readAsset(String fileName ) {

        List<String> lines = new ArrayList<String>();

        InputStream is = null;
        BufferedReader br = null;

        try {
            try {
                is = mAssetManager.open(fileName);
                br = new BufferedReader(new InputStreamReader(is));

                String str;
                while ((str = br.readLine()) != null) {
                    lines.add( str);
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            if (D) e.printStackTrace();
        }

        return lines;
    }


    /**
     * parseDouble
     */
    private  double parseDouble(String str) {
        double d = 0;
        try {
            d = Double.parseDouble(str);
        } catch (Exception e){
            if (D) e.printStackTrace();
        }
        return d;
    }

    /**
     * write into logcat
     */
    private  void log_d( String msg ) {
        if (D) Log.d( TAG, TAG_SUB + " " + msg );
    } // log_d

    /**
     * class Node
     */
    public class Node {
        public double lat;
        public double lon;
        public String title;
        public String description;

        // constractor
        public Node (double _lat, double _lon, String _title, String _description ) {
            lat = _lat;
            lon = _lon;
            title = _title;
            description = _description;
        } // Node

    } // class Node

}
