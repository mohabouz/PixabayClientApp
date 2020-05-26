package com.mapps.pixabayclientapp.ui.main;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mapps.pixabayclientapp.api.PixabayClient;
import com.mapps.pixabayclientapp.models.Image;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private static String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<String> message;
    private MutableLiveData<List<Image>> images;
    private MutableLiveData<Integer> currentPage;

    private PixabayClient api = new PixabayClient("7857814-6a87eb02016ef6b7e27287125");

    public MainViewModel() {
        message = new MutableLiveData<>();
        images = new MutableLiveData<>();
        currentPage = new MutableLiveData<>();
        currentPage.setValue(2);
        api.getImagesPerPage(40, new PixabayClient.ImagesListener() {
            @Override
            public void onSuccess(List<Image> imagesList) {
                setImages(imagesList);
                Log.d(TAG, "TotalItems: " + imagesList.size());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void setMessage(String text) {
        if(message != null)
            message.setValue(text);
    }

    public void setImages(List<Image> images) {
        this.images.setValue(images);
    }

    public MutableLiveData<List<Image>> getImages() {
        return images;
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public MutableLiveData<Integer> getCurrentPage() {
        return currentPage;
    }

    public void incrementPage() {
        if (currentPage.getValue() != null) {
            currentPage.setValue(currentPage.getValue() + 1);
        }
    }

    public void addData(int page) {
        api.getImages(page, new PixabayClient.ImagesListener(){

            @Override
            public void onSuccess(List<Image> imagesList) {
                List<Image> tempList = getImages().getValue();
                if (tempList != null) {
                    tempList.addAll(imagesList);
                }
                images.setValue(tempList);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
