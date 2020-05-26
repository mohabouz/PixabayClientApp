package com.mapps.pixabayclientapp.api;



import com.mapps.pixabayclientapp.models.Image;
import com.mapps.pixabayclientapp.models.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServiceInterface {

    //TODO Change all methods signatures

    @GET("api/")
    Call<Request> getRequest(@Query("key") String key);

    @GET("api/")
    Call<Request> getImages(@Query("page") int page, @Query("key") String key);

    @GET("api/")
    Call<Request> getImage(@Query("id") int id, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesPerPage(@Query("per_page") int number, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesByOrientation(@Query("orientation") String orientation, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesByCategory(@Query("category") String category, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesByColor(@Query("color") String color, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesByType(@Query("image_type") String color, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesWithMinWidth(@Query("min_width") int color, @Query("key") String key);

    @GET("api/")
    Call<Request> getImagesWithMinHeight(@Query("min_height") int color, @Query("key") String key);

    @GET("api/?editors_choice=true")
    Call<Request> getEditorsChoiceImages(@Query("key") String key);

}
