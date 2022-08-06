package com.example.mapapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class SubAdapter extends RecyclerView.Adapter<SubAdapter.ViewHolder> {

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

        ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image_view);
            textView = v.findViewById(R.id.text_view);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    SubAdapter(List<Integer> itemImages, List<String> itemTitles, List<String> itemDescription, List<GeoPoint> itemLocation) {
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
                .inflate(R.layout.sub_list_view, parent, false);
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

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return iTitles.size();
    }

    public void add(MapInfoSet data){

        int I = iTitles.size();

        for (int j=0;j<I;j++) {
            Log.w("subadaptor", "title is " + iTitles.get(j));
        }

        Log.w("subadaptor", "input data: " + data.title);

        this.iDescriptions.add(data.description);
        this.iLocation.add(data.position);
        this.iTitles.add(data.title);
        this.iImages.add(R.drawable.onsen);

        I = iTitles.size();

        for (int j=0;j<I;j++) {
            Log.w("subadaptor", "title is " + iTitles.get(j));
        }
    }


}