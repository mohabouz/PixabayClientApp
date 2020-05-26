package com.mapps.pixabayclientapp.ui.details;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mapps.pixabayclientapp.R;
import com.mapps.pixabayclientapp.api.PixabayClient;
import com.mapps.pixabayclientapp.models.Image;
import com.mapps.pixabayclientapp.ui.main.MainActivity;

public class DetailsActivity extends AppCompatActivity {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final int UI_ANIMATION_DELAY = 300;

    private final Handler mHideHandler = new Handler();

    private View mContentView;
    private View mControlsView;

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private boolean mVisible;

    private String wallpaperName;
    private long wallpaperId;

    ImageView wallpaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        wallpaperName = getIntent().getStringExtra(MainActivity.WALLPAPER_NAME);
        wallpaperId = getIntent().getLongExtra(MainActivity.WALLPAPER_ID,0);

        mVisible = true;
        mContentView = findViewById(R.id.fullscreen_content);

        wallpaper = findViewById(R.id.wallpaper);

        PixabayClient api = new PixabayClient("7857814-6a87eb02016ef6b7e27287125");
        api.getImage((int) wallpaperId,new PixabayClient.ImageListener(){

            @Override
            public void onSuccess(Image image) {
                Glide.with(getBaseContext())
                        .load(image.getLargeImageURL())
                        .thumbnail(Glide.with(getBaseContext()).load(image.getPreviewURL()))
                        .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(getBaseContext(), "Failed to load wallpaper", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target,
                                                   DataSource dataSource,
                                                   boolean isFirstResource) {

                        return false;
                    }
                }).into(wallpaper);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
;

    }



}
