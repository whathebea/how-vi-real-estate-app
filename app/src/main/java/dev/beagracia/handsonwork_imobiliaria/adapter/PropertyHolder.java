package dev.beagracia.handsonwork_imobiliaria.adapter;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.beagracia.handsonwork_imobiliaria.R;
import lombok.NonNull;

public class PropertyHolder extends RecyclerView.ViewHolder {
    ImageView propertyImage;
    TextView title, description, price;

    public PropertyHolder(@NonNull View recyclerViewItem) {
        super(recyclerViewItem);
        propertyImage = recyclerViewItem.findViewById(R.id.imageView2);
        title = recyclerViewItem.findViewById(R.id.textView);
        description = recyclerViewItem.findViewById(R.id.textView2);
        price = recyclerViewItem.findViewById(R.id.textView3);

    }
}
