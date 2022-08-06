package com.example.mapapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.osmdroid.util.GeoPoint;

import java.util.List;
//import androidx.annotation.NonNull;

//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//
//    private List<String> dataArray = new ArrayList<>();
//
//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        // each data item is just a string in this case
//        TextView mTextView;
//
//        ViewHolder(View v) {
//            super(v);
//            mTextView = (TextView)v.findViewById(R.id.text_view);
//        }
//    }
//
//    // Provide a suitable constructor (depends on the kind of dataset)
//    MyAdapter(List<String> dataset) {
//        dataArray = dataset;
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    @NonNull
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // create a new view
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_list_view, parent, false);
//
//        // set the view's size, margins, paddings and layout parameters
//
//        return new ViewHolder(view);
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.mTextView.setText(dataArray.get(position));
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return dataArray.size();
//    }
//}
public class ShioriAdapter extends RecyclerView.Adapter<ShioriAdapter.ViewHolder> {

    private List<Integer> iImages;
    private List<String> iTitles;
    private List<String> iDescriptions;
    private List<GeoPoint> iLocation;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView imageView;
        TextView textView;
        TextView locationView;

        ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image_view);
            textView = v.findViewById(R.id.text_view);
            locationView = v.findViewById(R.id.location_view);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    ShioriAdapter(List<Integer> itemImages, List<String> itemTitles, List<String> itemDescription, List<GeoPoint> itemLocation) {
        this.iImages = itemImages;
        this.iTitles = itemTitles;
        this.iDescriptions = itemDescription;
        this.iLocation = itemLocation;
    }
    // Create new views (invoked by the layout manager)
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shiori_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageView.setImageResource(iImages.get(position));
        holder.textView.setText(iTitles.get(position));
        private GeoPoint  ll = iLocation.get(position);
        holder.locationView.setText("北緯:"+String.valueOf(ll.getLongitude())+" 東経:"+String.valueOf(ll.getLatitude()));

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return iTitles.size();
    }
    

}