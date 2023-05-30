package dev.beagracia.handsonwork_imobiliaria.retrofit;

import java.util.List;

import dev.beagracia.handsonwork_imobiliaria.model.Property;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PropertyApi {

    @GET("view/{id}")
    Call<Property> getPropertyById(@Path("id") Long id);

    @GET("view")
    Call<List<Property>> getProperties();
}

