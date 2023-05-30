package dev.beagracia.handsonwork_imobiliaria.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import dev.beagracia.handsonwork_imobiliaria.R;
import dev.beagracia.handsonwork_imobiliaria.model.Property;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyHolder> {
    private List<Property> propertyList;

    public PropertyAdapter(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public PropertyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new PropertyHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PropertyHolder holder, int position) {
        Property property = propertyList.get(position);
        Picasso.get()
                .load(property.getImgUrl())
                .into(holder.propertyImage);
        holder.title.setText(property.getTitle());
        holder.description.setText(property.getDescription());
        holder.price.setText("R$" + property.getPrice());
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

}
