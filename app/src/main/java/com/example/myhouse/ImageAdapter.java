/**
 * The image adapter which set text on the TextView
 */

package com.example.myhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    Context context;
    List<ImageModel> imageModelList;

    public ImageAdapter(Context context, List<ImageModel> imageModelList) {
        this.context = context;
        this.imageModelList = imageModelList;
    }

    //The view holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_for_recyclerview,parent,false);
        return new ViewHolder(v);
    }

    // Function that set the values on the view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel imageModel = imageModelList.get(position);
        holder.tvCity.setText("City: " + imageModel.getCity());
        holder.tvDesc.setText("Description: " + imageModel.getDescription());
        holder.tvPrice.setText("Price: " + imageModel.getPrice());

        String imageUri = null;
        imageUri = imageModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvCity, tvDesc, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_recyclerview_id);
            tvCity = itemView.findViewById(R.id.city_recyclerview_id);
            tvDesc = itemView.findViewById(R.id.desc_recyclerview_id);
            tvPrice = itemView.findViewById(R.id.price_recyclerview_id);
        }
    }
}
