package dev.beagracia.handsonwork_imobiliaria.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Property implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imgUrl")
    @Expose
    private String imgUrl;
    @SerializedName("propertyType")
    @Expose
    private String propertyType;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("price")
    @Expose
    private Long price;
    @SerializedName("numberOfBedrooms")
    @Expose
    private int numberOfBedrooms;
    @SerializedName("numberOfCarSpots")
    @Expose
    private int numberOfCarSpots;
    @SerializedName("numberOfBathrooms")
    @Expose
    private int numberOfBathrooms;
}
