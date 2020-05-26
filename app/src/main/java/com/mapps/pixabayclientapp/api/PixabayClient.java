package com.mapps.pixabayclientapp.api;

import android.util.Log;

import androidx.annotation.Nullable;

import com.mapps.pixabayclientapp.models.Image;
import com.mapps.pixabayclientapp.models.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PixabayClient {

    private String apiKey;

    //Pixabay Client Constructor
    public PixabayClient(String apiKey) {
        this.apiKey = apiKey;
    }

    //Retrofit library initialization
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private APIServiceInterface apiServiceInterface = retrofit.create(APIServiceInterface.class);

    //Client methods

    public void getRequest(final RequestListener requestListener) {

        Call<Request> requestCallback = apiServiceInterface.getRequest(apiKey);
        requestCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                requestListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                requestListener.onFailure(t);
            }
        });
    }

    public void getImages(final ImagesListener imagesListener) {
        Call<Request> requestCallback = apiServiceInterface.getRequest(apiKey);
        requestCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImages(int page, final ImagesListener imagesListener) {
        Call<Request> requestCallback = apiServiceInterface.getImages(page, apiKey);
        requestCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImagesPerPage(int number, final ImagesListener imagesListener) {
        Call<Request> requestCallback = apiServiceInterface.getImagesPerPage(number,apiKey);
        requestCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImage(int id, final ImageListener imageListener) {
        Call<Request> requestCallback = apiServiceInterface.getImage(id,apiKey);
        requestCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imageListener.onSuccess(response.body().getImages().get(0));
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imageListener.onFailure(t);
            }
        });
    }

    public void getImagesByOrientation(String orientation, final ImagesListener imagesListener) {
        Call<Request> imagesCallback = apiServiceInterface.getImagesByOrientation(orientation,apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImagesByCategory(String category, final ImagesListener imagesListener) {
        Call<Request> imagesCallback = apiServiceInterface.getImagesByCategory(category, apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImagesByColor(String color, final ImagesListener imagesListener){
        Call<Request> imagesCallback = apiServiceInterface.getImagesByColor(color, apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImagesByType(String type, final ImagesListener imagesListener) {
        Call<Request> imagesCallback = apiServiceInterface.getImagesByType(type, apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(@Nullable Call<Request> call, @Nullable Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(@Nullable Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImagesWithMinWidth(int minWidth, final ImagesListener imagesListener) {
        Call<Request> imagesCallback = apiServiceInterface.getImagesWithMinWidth(minWidth, apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(@Nullable Call<Request> call, @Nullable Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(@Nullable Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getImagesWithMinHeight(int minHeight, final ImagesListener imagesListener) {
        Call<Request> imagesCallback = apiServiceInterface.getImagesWithMinHeight(minHeight, apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(@Nullable Call<Request> call, @Nullable Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());
            }

            @Override
            public void onFailure(@Nullable Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    public void getEditorsChoiceImages(final ImagesListener imagesListener){
        Call<Request> imagesCallback = apiServiceInterface.getEditorsChoiceImages(apiKey);
        imagesCallback.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(@Nullable Call<Request> call, @Nullable Response<Request> response) {
                Log.d(PixabayClient.class.getSimpleName(), "onResponse: -> Code : " + response.code() );
                imagesListener.onSuccess(response.body().getImages());

            }

            @Override
            public void onFailure(@Nullable Call<Request> call, Throwable t) {
                imagesListener.onFailure(t);
            }
        });
    }

    //Listeners
    public interface RequestListener {
        void onSuccess(Request request);
        void onFailure(Throwable t);
    }

    public interface ImagesListener {
        void onSuccess(List<Image> imagesList);
        void onFailure(Throwable t);
    }

    public interface  ImageListener {
        void onSuccess(Image image);
        void onFailure(Throwable t);
    }

}
